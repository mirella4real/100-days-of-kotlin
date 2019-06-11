data class Recipe(var name: String)

fun main(args: Array<String>){
    var mShoppingList: MutableList<String> = mutableListOf("Tea", "Eggs", "Milk")
    println("Print original list: $mShoppingList \n")

    val xtraShopping = listOf<String>("Cookies", "Sugar", "Eggs")
    mShoppingList.addAll(xtraShopping)
    println("mShoppingList items added: $mShoppingList \n")

    if(mShoppingList.contains("Tea")){
        mShoppingList.set(mShoppingList.indexOf("Tea"), "Coffee")
    }

    mShoppingList.sort()
    println("mShoppingList sorted: $mShoppingList\n")

    mShoppingList.reverse()
    println("mShoppingList reversed: $mShoppingList\n")

    val mShoppingSet: MutableSet<String> = mShoppingList.toMutableSet()
    println("mShoppingSet: $mShoppingSet")
    println("Note that duplicates were eliminated when converting to a set\n")

    val moreShopping = setOf("Chives", "Spinach", "Milk")
    mShoppingSet.addAll(moreShopping)
    println("moreShopping set items added: $mShoppingSet \n")

    mShoppingList = mShoppingSet.toMutableList()
    println("mShoppingList new version: $mShoppingList\n")

    println("Now on to Maps\n")

    val r1 = Recipe("Chicken Soup")
    val r2 = Recipe("Quinoa Salad")
    val r3 = Recipe("Thai Curry")
    val r4 = Recipe("Jambalaya")
    val r5 = Recipe("Sausage Rolls")

    val mRecipeMap = mutableMapOf("Recipe1" to r1, "Recipe2" to r2, "Recipe3" to r3)
    println("mRecipeMap original: $mRecipeMap\n")
    val recipesToAdd = mutableMapOf("Recipe4" to r4, "Recipe5" to r5)
    mRecipeMap.putAll(recipesToAdd)
    println("Updated mRecipeMap: $mRecipeMap\n")

    if(mRecipeMap.containsKey("Recipe1")){
        println(mRecipeMap.getValue("Recipe1"))
    }




}