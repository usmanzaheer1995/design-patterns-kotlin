package observer

class WeatherData() : Subject {
    private var observers: MutableList<Observer> = mutableListOf()
    private var temperature = 0f
    private var humidity = 0f
    private var pressure = 0f

    override fun registerObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        observers.forEach { it.update() }
    }

    fun setMeasurements(temperature: Float, humidity: Float, pressure: Float) {
        this.temperature = temperature
        this.humidity = humidity
        this.pressure = pressure
        measurementsChanged()
    }

    fun getTemperature(): Float {
        return temperature
    }

    fun getHumidity(): Float {
        return humidity
    }

    fun getPressure(): Float {
        return pressure
    }

    private fun measurementsChanged() {
        notifyObservers()
    }
}
