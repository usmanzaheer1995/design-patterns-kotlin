package observer

class ForecastDisplay(private val weatherData: WeatherData) : Observer, DisplayElement {
    private var currentPressure = 29.92f
    private var lastPressure = 0f

    init {
        weatherData.registerObserver(this)
    }
    override fun display() {
        print("Forecast: ")
        when {
            currentPressure > lastPressure -> println("Improving weather on the way!")
            currentPressure == lastPressure -> println("More of the same")
            currentPressure < lastPressure -> println("Watch out for cooler, rainy weather")
        }
    }

    override fun update() {
        lastPressure = currentPressure
        currentPressure = weatherData.getPressure()

        display()
    }
}
