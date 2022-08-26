data class FlightStatus(
    val flightNumber: String,
    val passengerName: String,
    val passengerLoyaltyTier: String,
    val originAirport: String,
    val destinationAirport: String,
    val status: String,
    val departureTimeInMinute: Int
) {

    companion object {
        fun parse(flightResponse: String, loyaltyResponse: String, passengerName: String): FlightStatus {
            val (flightNumber, originAirport, destinationAirport, status, departureTimeInMinute) = flightResponse.split(
                ","
            )

            val (loyaltyTierName, milesFlown, milesToNextTier) = loyaltyResponse.split(",")

            return FlightStatus(
                flightNumber = flightNumber,
                passengerName = passengerName,
                passengerLoyaltyTier = loyaltyTierName,
                originAirport = originAirport,
                destinationAirport = destinationAirport,
                status = status,
                departureTimeInMinute = departureTimeInMinute.toInt()
            )
        }
    }

}