object Day11 : AoCDay {
	override fun solvePart1() {
		val monkeyText = object {}::class.java.getResource("input_11_1")!!
			.readText()
			.split("\n\n")

		monkeyText.forEach { Monkey(it) }

		val rounds = 20

		repeat(rounds) { it ->
			println("Round $it")
			Monkey.monkeys.forEach { println(it) }
			Monkey.monkeys.forEach {
				it.toss()
			}
		}

		println(Monkey.monkeys.map { it.tossedTimes }.sorted().takeLast(2) )
	}

	override fun solvePart2() {
		val lines = object {}::class.java.getResource("input_11_1")!!
			.readText()
			.lines()

		println()
	}
}
