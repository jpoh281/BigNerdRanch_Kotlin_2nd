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
            val flight = fetchFlight("HJP")
            println(flight)
        }
        println("Finished")

    }
}

suspend fun fetchFlight(passengerName: String): FlightStatus {
    val client = HttpClient(CIO)

    println("Started fetching flight info")
    val flightResponse = client.get<String>(FLIGHT_ENDPOINT).also {
        println("Finished fetching flight info")
    }

    println("Started fetching loyalty info")
    val loyaltyResponse = client.get<String>(LOYALTY_ENDPOINT).also {
        println("Finished fetching loyalty info")
    }

    println("Combining flight data")
    return FlightStatus.parse(
        passengerName = passengerName, flightResponse = flightResponse,
        loyaltyResponse = loyaltyResponse
    )
} 