//Declaraciones if/else junto con null
fun main() {
    var favoriteActor: String? = "Sandra Oh"

    if (favoriteActor != null) {
      println("The number of characters in your favorite actor's name is ${favoriteActor.length}.")
    }
}

//verificacion de null con if/else
fun main() {
    var favoriteActor: String? = "Sandra Oh"

    val lengthOfName = if (favoriteActor != null) {
      favoriteActor.length
    } else {
      0
    }

    println("The number of characters in your favorite actor's name is $lengthOfName.")
}

//operador ELvis?

fun main() {
    var favoriteActor: String? = "Sandra Oh"

    val lengthOfName = favoriteActor?.length ?: 0

    println("The number of characters in your favorite actor's name is $lengthOfName.")
}