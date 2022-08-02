import kotlin.random.Random
import kotlin.random.nextInt

var narrationModifier: (String) -> String = { it }

inline fun narrate(
    message: String,
    modifier: (String) -> String = { narrationModifier(it) }
) {

    println(modifier((message)))
}

fun changeNarratorMood() {
    val mood: String
    val modifier: (String) -> String

    when (Random.nextInt(1..8)) {
        1 -> {
            mood = "loud"
            modifier = { message ->
                val numExclamationPoints = 3
                message.uppercase() + "!".repeat(numExclamationPoints)
            }
        }

        2 -> {
            mood = "tired"
            modifier = { message ->
                message.lowercase().replace(" ", "...")

            }
        }

        3 -> {
            mood = "unsure"
            modifier = { message ->
                "$message?"
            }
        }

        4 -> {
            var narrationGiven = 0
            mood = "like sending an itemized bill"
            modifier = { message ->
                narrationGiven++
                "$message.\n(I have narrated $narrationGiven things)"
            }
        }
        5 -> {
            mood = "lazy"
            modifier = { message ->
                message.take(message.length / 2)
            }
        }
        6 -> {
            mood = "leet"
            modifier = { message ->
                message.replace(Regex("[LlEeTt]")) {
                    when (it.value) {
                        "L", "l" -> {
                            "1"
                        }
                        "E", "e" -> {
                            "3"
                        }
                        "T", "t" -> {
                            "7"
                        }
                        else -> it.value
                    }
                }
            }
        }
        7 -> {
            mood = "poetic"
            modifier = { message ->
                message.replace(Regex(" ")) {
                    if (Random.nextBoolean()) {
                        it.value
                    } else {
                        "\n"
                    }
                }
            }
        }
        else -> {
            mood = "professional"
            modifier = { message ->
                "$message."
            }
        }
    }

    narrationModifier = modifier
    narrate("The narrator begins to feel $mood")
}