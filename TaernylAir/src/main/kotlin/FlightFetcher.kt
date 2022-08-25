import kotlinx.coroutines.*
import java.net.URL

private const val BASE_URL = "http://kotlin-book.bignerdranch.com/2e"
private const val FLIGHT_ENDPOINT = "$BASE_URL/flight"

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

suspend fun fetchFlight(): String = withContext(Dispatchers.IO){
    URL(FLIGHT_ENDPOINT).readText()
} 