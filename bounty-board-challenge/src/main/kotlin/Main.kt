const val HERO_NAME = "Madrigal"
const val PUB_NAME = "The Unicorn’s Horn"

fun main(args: Array<String>) {
  println("Hello World!")

  println(HERO_NAME)
  var playerLevel = 5
  println(playerLevel)

  playerLevel++
  println(playerLevel)

  var hasSteed = "unknown"
  var publicanName = "Publican"
  var gold = 50

  var drinkMenu = setOf("mead", "wine", "LaCroix")
  println(drinkMenu)

  var mirrorName = HERO_NAME.reversed()
  println(mirrorName)
}