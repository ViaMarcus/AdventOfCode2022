object Day11 : AoCDay {
	override fun solvePart1() {
		val monkeyText = object {}::class.java.getResource("input_11_1")!!
			.readText()
			.split("\n\n")

		monkeyText.forEach { Monkey(it, true) }

		val rounds = 20

		repeat(rounds) { it ->
			println("Round $it")
			Monkey.monkeys.forEach { println(it) }
			Monkey.monkeys.forEach {
				it.toss()
			}
		}

		val top2 = Monkey.monkeys.map { it.tossedTimes }.sorted().takeLast(2)
		println("" + top2[0] + " * " + top2[1] + " = " + top2[0] * top2[1])

		Monkey.monkeys.clear()
	}

	override fun solvePart2() {
		val monkeyText = object {}::class.java.getResource("input_11_1")!!
			.readText()
			.split("\n\n")

		monkeyText.forEach { Monkey(it, false) }

		val divider = Monkey.monkeys.map { it.divisibleBy }.reduce { acc, i -> acc * i }
		Monkey.divideWith = divider

		val rounds = 10000

		repeat(rounds) { it ->
			Monkey.monkeys.forEach {
				it.toss()
			}
		}

		println(Monkey.monkeys)

		val top2 = Monkey.monkeys.map { it.tossedTimes }.sorted().takeLast(2)
		println("" + top2[0] + " * " + top2[1] + " = " + top2[0] * top2[1])
	}
}
