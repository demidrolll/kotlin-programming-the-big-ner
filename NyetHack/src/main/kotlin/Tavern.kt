import java.io.File

private const val TAVERN_MASTER = "Taernyl"
private const val TAVERN_NAME = "$TAVERN_MASTER's Folly"

private val firstNames = setOf("Alex", "Mordoc", "Sophie", "Tariq")
private val lastNames = setOf("Ironfoot", "Fernsworth", "Baggins", "Downstrider")

private val menuData = File("data/tavern-menu-data.txt")
  .readText()
  .split("\n")

data class MenuItem(
  val type: String,
  val name: String,
  val price: String
)

private val menuItems = List(menuData.size) {
  val (type, name, price) = menuData[it].split(",")
  MenuItem(type, name, price.trim())
}

fun visitTavern() {
  narrate("$heroName enters $TAVERN_NAME")
  narrate("There are several items for sale:")
  val maxName = menuItems.stream().map { it.name.length }.max(Int::compareTo).orElseThrow() + 3
  val maxPrice = menuItems.stream().map { it.price.length }.max(Int::compareTo).orElseThrow()
  var currentType = ""
  menuItems.sortedBy { it.type }.forEach {
    if (currentType != it.type) {
      currentType = it.type
      val typeName = "~[$currentType]~"
      println(typeName.padStart(((maxName + maxPrice) / 2) + (typeName.length / 2)))
    }
    println(it.name.padEnd(maxName, '.') + it.price.padStart(maxPrice, '.'))
  }

  val patrons = mutableSetOf<String>()
  while (patrons.size < 10) {
    patrons += "${firstNames.random()} ${lastNames.random()}"
  }

  narrate("$heroName sees several patrons in the tavern:")
  narrate(patrons.joinToString())
  repeat(3) {
    placeOrder(patrons.random(), menuItems.random().name)
  }
}

private fun placeOrder(patronName: String, menuItemName: String) {
  narrate("$patronName speaks with $TAVERN_MASTER to place an order")
  narrate("$TAVERN_MASTER hands $patronName a $menuItemName")
}