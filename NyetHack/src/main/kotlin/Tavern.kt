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

private val menuItemPrices = List(menuData.size) { index ->
  val (_, name, price) = menuData[index].split(",")
  name to price.toDouble()
}.toMap()

fun visitTavern() {
  narrate("$heroName enters $TAVERN_NAME")
  narrate("There are several items for sale:")
  narrate(menuItems.joinToString())

  val patrons = mutableSetOf<String>()
  val patronGold = mutableMapOf(
    TAVERN_MASTER to 86.00,
    heroName to 4.50,
  )
  while (patrons.size < 5) {
    val patronName = "${firstNames.random()} ${lastNames.random()}"
    patrons += patronName
    patronGold += patronName to 6.0
  }

  narrate("$heroName sees several patrons in the tavern:")
  narrate(patrons.joinToString())
  println(patronGold)
  repeat(3) {
    placeOrder(patrons.random(), menuItems.random(), patronGold)
  }
  println(patronGold)
}

private fun placeOrder(
  patronName: String,
  menuItemName: String,
  patronGold: MutableMap<String, Double>,
) {
  val itemPrice = menuItemPrices.getValue(menuItemName)
  narrate("$patronName speaks with $TAVERN_MASTER to place an order")
  if (itemPrice <= patronGold.getOrDefault(patronName, 0.0)) {
    narrate("$TAVERN_MASTER hands $patronName a $menuItemName")
    narrate("$patronName pays $TAVERN_MASTER $itemPrice gold")
    patronGold[patronName] = patronGold.getValue(patronName) - itemPrice
    patronGold[TAVERN_MASTER] = patronGold.getValue(TAVERN_MASTER) + itemPrice
  } else {
    narrate("$TAVERN_MASTER says, \"You need more coin for a $menuItemName\"")
  }
}