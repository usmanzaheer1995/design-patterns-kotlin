package observer

class StatisticsDisplay(private val weatherData: WeatherData) : Observer, DisplayElement {
    private var maxTemp = 0f
    private var minTemp = 200f
    private var tempSum = 0f
    private var numReadings = 0

    init {
        weatherData.registerObserver(this)
    }

    override fun update() {
        val temperature = weatherData.getTemperature()
        tempSum += temperature
        numReadings++

        if (temperature > maxTemp) {
            maxTemp = temperature
        }

        if (temperature < minTemp) {
            minTemp = temperature
        }

        display()
    }

    override fun display() {
        println("Avg/Max/Min temperature = ${tempSum / numReadings}/$maxTemp/$minTemp")
    }
}
