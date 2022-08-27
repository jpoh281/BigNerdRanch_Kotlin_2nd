import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        println("Getting the latest flight info...")
        val flights = fetchFlights()
        val flightDescriptions = flights.joinToString {
            "${it.passengerName} (${it.flightNumber})"
        }
        println("Found flights for $flightDescriptions")
        val flightsAtGate = MutableStateFlow(flights.size)

        launch {
            flightsAtGate.takeWhile { it > 0 }.onCompletion {
                println("Finished tracking all flights")
            }.collect { flightCount ->
                println("There are $flightCount flights being tracked")
            }
        }
        launch {
            flights.forEach {
                watchFlight(it)
                flightsAtGate.value = flightsAtGate.value - 1
            }
        }
    }
}

suspend fun watchFlight(initialFlight: FlightStatus) {
    val passengerName = initialFlight.passengerName
    val currentFlight: Flow<FlightStatus> = flow {
        var flight = initialFlight

        while (flight.departureTimeInMinute >= 0 && !flight.isFlightCanceled) {
            emit(flight)
            delay(1000)
            flight = flight.copy(departureTimeInMinute = flight.departureTimeInMinute - 1)
        }
    }

    currentFlight.map { flight ->
        when (flight.boardingState) {
            BoardingState.FlightCanceled -> "Your flight was canceled"
            BoardingState.BoardingNotStarted -> "Boarding will start soon"
            BoardingState.WaitingToBoard -> "Other passengers ar boarding"
            BoardingState.Boarding -> "You can now board the plane"
            BoardingState.BoardingEnded -> "The boarding doors have closed"
        } +  " (Flight departs in ${flight.departureTimeInMinute} minutes)"
    }.onCompletion {
        println("Finished tracking $passengerName's flight")
    }.collect { status ->
        println("$passengerName $status")
    }
}

suspend fun fetchFlights(passengerNames: List<String> = listOf("Madrigal", "Polarcubis")) =
    passengerNames.map { fetchFlight(it) }