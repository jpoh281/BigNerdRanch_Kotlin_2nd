import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        println("Getting the latest flight info...")
        val flights = fetchFlights()
        val flightDescriptions = flights.joinToString {
            "${it.passengerName} (${it.flightNumber})"
        }
        println("Found flighs for $flightDescriptions")

        flights.forEach {
            watchFlight(it)
        }
    }
}

fun watchFlight(initialFlight: FlightStatus) {
    val currentFlight: Flow<FlightStatus> = flow {
        var flight = initialFlight
        repeat(5) {
            emit(flight)
            delay(1000)
            flight = flight.copy(departureTimeInMinute = flight.departureTimeInMinute - 1)
        }
    }
}

suspend fun fetchFlights(passengerNames: List<String> = listOf("Madrigal", "Polarcubis")) =
    passengerNames.map { fetchFlight(it) }