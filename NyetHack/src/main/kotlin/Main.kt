var heroName: String = ""

fun main() {
  heroName = promptHeroName()
  // changeNarratorMood()
  narrate("$heroName, ${createTitle(heroName)}, heads to the town square")
  visitTavern()
}

private fun promptHeroName(): String {
  narrate("A hero enters the town of Kronstadt. What is their name?") {
    "\u001b[33;1m$it\u001b[0m"
  }

  /*val heroName = readLine()
  require(heroName != null && heroName.isNotEmpty()) {
    "The hero must have a name"
  }*/
  println("Madrigal")
  return "Madrigal"
}

private fun createTitle(name: String): String {
  return when {
    name.all { it.isDigit() } -> "The Identifiable"
    name.none { it.isLetter() } -> "The Witness Protection Member"
    name.count { it.lowercase() in "aeiou" } > 4 -> "The Master of Vowels"
    else -> "The Renowned Hero"
  }
}