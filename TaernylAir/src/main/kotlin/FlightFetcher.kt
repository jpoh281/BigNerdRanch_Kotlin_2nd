import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import java.net.URL

private const val BASE_URL = "http://kotlin-book.bignerdranch.com/2e"
private const val FLIGHT_ENDPOINT = "$BASE_URL/flight"
private const val LOYALTY_ENDPOINT = "$BASE_URL/loyalty"

fun main() {
    runBlocking {
        println("Start")
        launch {
            val flight = fetchFlight()
            println(flight)
        }
        println("Finished")

    }
}

suspend fun fetchFlight(): String {
    val client = HttpClient(CIO)

    val flightResponse = client.get<String>(FLIGHT_ENDPOINT)
    val loyaltyResponse = client.get<String>(LOYALTY_ENDPOINT)

    return "$flightResponse\n$loyaltyResponse"
} 