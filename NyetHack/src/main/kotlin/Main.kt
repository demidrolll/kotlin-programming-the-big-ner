fun main() {
  narrate("A hero enters the town of Kronstadt. What is their name?")
  val heroName = readLine() ?: ""

  changeNarratorMood()
  narrate("$heroName heads to the town square")
}