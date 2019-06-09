
interface Roamable {
    fun roam()
}
abstract class Animal : Roamable{
    abstract val image: String
    abstract val food: String
    abstract val habitat: String
    abstract val animal: String
    var hunger = 10

    abstract fun makeNoise()

    abstract fun eat()

    override fun roam(){
        println("The Animal is roaming")
    }

    fun sleep() {
        println("The Animal is sleeping")
    }
}

class Hippo : Animal() {
    override val image = "hippo.jpg"
    override val food = "grass"
    override val habitat = "water"
    override val animal = "hippo"


    override fun makeNoise(){
        println("Grunt! Grunt!")
    }

    override fun eat() {
        println("The Hippo is eating $food")
    }
}

abstract class Canine : Animal() {
    override fun roam() {
        println("The Canine is roaming")
    }
}

abstract class Feline : Animal() {
    override fun roam() {
        println("The Feline is prowling")
    }
}

class Wolf : Canine() {
    override val image = "wolf.jpg"
    override val food = "meat"
    override val habitat = "forests"
    override val animal = "wolf"

    override fun makeNoise() {
        println("Hooooooowl!")
    }

    override fun eat() {
        println("The Wolf is eating $food")
    }
}

class Lion : Feline() {
    override val image = "lion.jpg"
    override val food = "meat"
    override val habitat = "jungle"
    override val animal = "lion"

    override fun makeNoise() {
        println("Roar!")
    }

    override fun eat() {
        println("The Lion is eating $food")
    }
}

class Vehicle : Roamable {
    override fun roam() {
        println("The Vehicle is roaming")
    }
}

class Vet {
    fun giveShot(animal : Animal) {
        print("The vet has administered a shot to the ${animal.animal}: ")
        animal.makeNoise()
    }
}

fun main(args: Array<String>) {
    val animals = arrayOf(Hippo(), Wolf(), Lion())
    for (item in animals){
        item.roam()
        item.eat()
    }

    val vet = Vet()
    val wolf = Wolf()
    val hippo = Hippo()
    val lion = Lion()
    vet.giveShot(wolf)
    vet.giveShot(hippo)
    vet.giveShot(lion)

    val roamables = arrayOf(Hippo(), Wolf(), Vehicle())
    for(item in roamables){
        item.roam()
        if(item is Animal){
            item.eat()
        }
    }
}