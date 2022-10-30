import java.io.File
import java.lang.Math.random
import kotlin.random.Random

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
  repeat(3) {
    placeOrder(patrons.random(), patronGold)
  }
  displayPatronBalances(patronGold)
}

private fun placeOrder(
  patronName: String,
  patronGold: MutableMap<String, Double>,
) {
  narrate("$patronName speaks with $TAVERN_MASTER to place an order")
  val ordersCount = Random.nextInt(3) + 1
  val orderedItems = mutableListOf<String>()
  var totalSum = 0.0
  repeat(ordersCount) {
    val item = menuItems.random()
    orderedItems += item
    totalSum += menuItemPrices.getValue(item)
  }
  narrate("$patronName ordered $orderedItems, total sum: $totalSum")
  if (totalSum <= patronGold.getOrDefault(patronName, 0.0)) {
    narrate("$patronName pays $TAVERN_MASTER $totalSum gold")
    patronGold[patronName] = patronGold.getValue(patronName) - totalSum
    patronGold[TAVERN_MASTER] = patronGold.getValue(TAVERN_MASTER) + totalSum
  } else {
    narrate("$TAVERN_MASTER says, \"You need more coin\"")
  }
}

private fun displayPatronBalances(patronGold: Map<String, Double>) {
  narrate("$heroName intuitively knows how much money each patron has")
  patronGold.forEach { (patron, balance) ->
    narrate("$patron has ${"%.2f".format(balance)} gold")
  }
}