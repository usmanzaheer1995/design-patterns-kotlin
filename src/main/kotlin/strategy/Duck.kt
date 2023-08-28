package strategy

/*
 *	1. Principle: Identify the aspects of your application that vary and separate them from what stays the same.
 * 	2. Principle: Program to an interface, not an implementation.
 *	3. Principle: Favour composition over inheritance.
 *
 *	4. Strategy Pattern: This pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable.
 *						This lets the algorithm vary independently of clients that use it.
*/

interface FlyBehaviour {
    fun fly()
}

class FlyWithWings : FlyBehaviour {
    override fun fly() {
        println("I'm flying!")
    }
}

class FlyNoWay : FlyBehaviour {
    override fun fly() {
        println("I can't fly!")
    }
}

class FlyRocketPowered : FlyBehaviour {
    override fun fly() {
        println("I'm flying with a rocket!")
    }
}

interface QuackBehaviour {
    fun quack()
}

class Quack : QuackBehaviour {
    override fun quack() {
        println("Quack")
    }
}

class MuteQuack : QuackBehaviour {
    override fun quack() {
        println("<< Silence >>")
    }
}

class Squeak : QuackBehaviour {
    override fun quack() {
        println("Squeak")
    }
}

abstract class Duck {
    abstract var flyBehaviour: FlyBehaviour
    abstract var quackBehaviour: QuackBehaviour

    abstract fun display()

    fun performFly() {
        flyBehaviour.fly()
    }

    fun performQuack() {
        quackBehaviour.quack()
    }

    fun swim() {
        println("All ducks float, even decoys!")
    }
}

class MallardDuck(override var flyBehaviour: FlyBehaviour, override var quackBehaviour: QuackBehaviour) : Duck() {
    override fun display() {
        println("I'm a real Mallard duck")
    }
}

class ModelDuck(override var flyBehaviour: FlyBehaviour, override var quackBehaviour: QuackBehaviour) : Duck() {
    override fun display() {
        println("I'm a model duck")
    }
}

object DuckStrategy {
    fun init() {
        val mallardDuck = MallardDuck(FlyWithWings(), Quack())
        mallardDuck.display()
        mallardDuck.performFly()
        mallardDuck.performQuack()

        val modelDuck = ModelDuck(FlyNoWay(), MuteQuack())
        modelDuck.display()
        modelDuck.performFly()

        modelDuck.flyBehaviour = FlyRocketPowered()

        modelDuck.performFly()
    }
}
