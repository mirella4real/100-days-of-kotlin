abstract class Pet(var name: String)

// if the superclass (Pet) contains parameters,
// you must pass values as arguments when you call the superclass

class Cat(name: String) : Pet(name)
class Dog(name: String) : Pet(name)
class Fish(name: String) : Pet(name)

class Vet<T: Pet>{
    fun treat(t: T){
        println("Treat Pet ${t.name}")
    }
}

//Use "T" to denote generic type.
//Convention:
//Generic type can be "T"
//collection class or interface, use "E" (for element) or "K" and "V" (for key and value) if it is a map

//"T" is a generic type that can be Pet or one of its subclasses
class Contest<T: Pet> (var vet: Vet<in T>){

    //The scores property has to keep track of each contestant and their score, so use a map
    val scores: MutableMap<T, Int> = mutableMapOf()

    //Put the contestant and their score in the mutablemap as long as the score is greater than or equal to 0
    fun addScore(t: T, score: Int = 0){
        if (score >=0) scores.put(t, score)
    }

    fun getWinners() : MutableSet<T> {
        val highScore = scores.values.max()
        val winners: MutableSet<T> = mutableSetOf()

        for ((t, score) in scores){
            if (score == highScore) winners.add(t)
        }
        return winners
    }
}

interface Retailer<out T> {
    fun sell() : T
}

class CatRetailer : Retailer<Cat> {
    override fun sell(): Cat {
        println("Sell cat \n")
        return Cat("")
    }
}

class DogRetailer : Retailer<Dog> {
    override fun sell(): Dog {
        println("Sell dog\n")
        return Dog("")
    }
}

class FishRetailer : Retailer<Fish>
{
    override fun sell(): Fish {
        println("Sell fish\n")
        return Fish("")
    }
}
fun main(args: Array<String>){
    val catFuzz = Cat("Fuzz Lightyear")
    val catKatsu = Cat("Katsu")
    val fishFinny = Cat("Finny McGraw")

    val catVet = Vet<Cat>()
    val fishVet = Vet<Fish>()
    val petVet = Vet<Pet>()

    catVet.treat(catFuzz)
    petVet.treat(catKatsu)
    petVet.treat(fishFinny)

    val catContest = Contest<Cat>(catVet)
    catContest.addScore(catFuzz, 50)
    catContest.addScore(catKatsu, 45)
    val topCat = catContest.getWinners().first()
    println("Cat contest winner is ${topCat.name}\n")

    val petContest = Contest<Pet>(petVet)
    petContest.addScore(catFuzz, 50)
    petContest.addScore(fishFinny, 56)
    val topPet = petContest.getWinners().first()
    println("Pet contest winner is ${topPet.name}\n")

    val fishContest = Contest<Fish>(petVet)

    val dogRetailer: Retailer<Dog> = DogRetailer()
    val catRetailer: Retailer<Cat> = CatRetailer()
    val petRetailer: Retailer<Pet> = CatRetailer()
    petRetailer.sell()
}