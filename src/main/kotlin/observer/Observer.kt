package observer

interface Observer {
    val weatherData: WeatherData

    fun update()
}
