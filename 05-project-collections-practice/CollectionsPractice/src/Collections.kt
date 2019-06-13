fun main(args: Array<String>){

    var listA = mutableListOf<String>("a", "a", "b")
    var listB = mutableListOf<String>("a", "c")

    println("Using addAll() on lists")
    println("This is listA: $listA")
    println("This is listB: $listB")

    listA.addAll(listB)
    println("This is listA after applying addAll(): $listA \n")


    var listC = mutableListOf<String>("a", "a", "b")
    var listD = mutableListOf<String>("a", "c")

    println("Using union() on lists")
    println("This is listC: $listC")
    println("This is listD: $listD")

    val listE = listD.union(listC)

    println("This is listE after listD.union(listC): $listE")
    println("This is listC: $listC")
    println("This is listD: $listD")

}

