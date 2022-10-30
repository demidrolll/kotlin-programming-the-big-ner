import java.io.File

private const val TAVERN_MASTER = "Taernyl"
private const val TAVERN_NAME = "$TAVERN_MASTER's Folly"

private val firstNames = setOf("Alex", "Mordoc", "Sophie", "Tariq")
private val lastNames = setOf("Ironfoot", "Fernsworth", "Baggins", "Downstrider")

private val menuData = File("data/tavern-menu-data.txt")
  .readText()
  .split("\n")

private val menuItems = List(menuData.size) {
  val (_, name, _) = menuData[it].split(",")
  name
}

fun visitTavern() {
  narrate("$heroName enters $TAVERN_NAME")
  narrate("There are several items for sale:")
  narrate(menuItems.joinToString())

  val patrons = mutableSetOf<String>()
  val patronGold = mutableMapOf(
    TAVERN_MASTER to 86.00,
    heroName to 4.50,
  )
  while (patrons.size < 10) {
    val patronName = "${firstNames.random()} ${lastNames.random()}"
    patrons += patronName
    patronGold += patronName to 6.0
  }

  narrate("$heroName sees several patrons in the tavern:")
  narrate(patrons.joinToString())
  repeat(3) {
    placeOrder(patrons.random(), menuItems.random())
  }
}

private fun placeOrder(patronName: String, menuItemName: String) {
  narrate("$patronName speaks with $TAVERN_MASTER to place an order")
  narrate("$TAVERN_MASTER hands $patronName a $menuItemName")
}