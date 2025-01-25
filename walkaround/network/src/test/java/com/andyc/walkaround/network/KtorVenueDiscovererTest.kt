@file:OptIn(ExperimentalCoroutinesApi::class)

package com.andyc.walkaround.network

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isLessThanOrEqualTo
import assertk.assertions.isNotNull
import com.andyc.core.data.networking.HttpClientFactory
import com.andyc.core.domain.location.Location
import com.andyc.core.domain.util.Result
import com.andyc.core.test_util.MainCoroutineExtension
import com.andyc.core.test_util.TestMockEngine
import com.andyc.walkaround.location.MockLocationObserver
import io.ktor.client.engine.mock.MockEngineConfig
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class KtorVenueDiscovererTest {

    companion object {
        @JvmField
        @RegisterExtension
        val mainCoroutineExtension = MainCoroutineExtension()
    }

    private lateinit var testDispatcher: TestDispatcher
    private lateinit var mockLocationObserver: MockLocationObserver
    private lateinit var mockEngine: TestMockEngine
    private lateinit var ktorVenueDiscoverer: KtorVenueDiscoverer

    @BeforeEach
    fun setUp() {
        testDispatcher = mainCoroutineExtension.testDispatcher
        mockLocationObserver = MockLocationObserver()

        val mockEngineConfig = MockEngineConfig().apply {
            requestHandlers.add { request ->
                val relativeUrl = request.url.encodedPath
                if (relativeUrl == "/v1/pages/restaurants") {
                    respond(
                        content = Json.encodeToString(woltRestaurantResponseStub),
                        headers = headers {
                            set("Content-Type", "application/json")
                        }
                    )
                } else {
                    respond(
                        content = byteArrayOf(),
                        status = HttpStatusCode.InternalServerError
                    )
                }
            }
        }
        mockEngine = TestMockEngine(
            dispatcher = testDispatcher,
            mockEngineConfig = mockEngineConfig
        )

        val httpClient = HttpClientFactory().getClient(mockEngine)
        ktorVenueDiscoverer = KtorVenueDiscoverer(
            locationObserver = mockLocationObserver,
            client = httpClient
        )
    }

    @Test
    fun testObserverLocation() = runTest {
        ktorVenueDiscoverer.observeCurrentLocation().test {
            val emission1 = awaitItem()
            testDispatcher.scheduler.advanceTimeBy(10_000L)
            val emission2 = awaitItem()

            assertThat(emission1).isEqualTo(Location(60.169418, 24.931618))
            assertThat(emission2).isEqualTo(Location(60.169818, 24.932906))
        }
    }

    @Test
    fun testGetNearbyVenues() = runTest {
        ktorVenueDiscoverer.observeCurrentLocation().test {
            awaitItem()

            val venues = ktorVenueDiscoverer.getNearbyVenues()
            assertThat(venues).isInstanceOf(Result.Success::class)

            if (venues is Result.Success) {
                val response = mockEngine.mockEngine.responseHistory[0]
                assertThat(response).isNotNull()

                assertThat(venues.data.size).isLessThanOrEqualTo(15)
            }
        }
    }
}

val woltRestaurantResponseStub: WoltRestaurantResponse
    get() {
        val items = mutableListOf<Item>()
        repeat(20) {
            items.add(
                Item(
                    image = Image(url = "mockImageUrl$it"),
                    venue = VenueDto(
                        address = "mockAddress$it",
                        id = "mockId$it",
                        location = listOf(1.0, 1.0),
                        name = "mockName$it",
                        rating = Rating(score = 9.0),
                        shortDescription = "shortDescription$it"
                    )
                )
            )
        }

        return WoltRestaurantResponse(
            sections = listOf(
                Section(),
                Section(
                    items = items
                )
            )
        )
    }