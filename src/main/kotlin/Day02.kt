object Day02 : AoCDay {
	override fun solvePart1() {
		val lines = object {}::class.java.getResource("input_02_1")!!
			.readText()
			.lines()
			.dropLast(1)
		val inputToThrow = mapOf(
			"A" to 0, // Rock
			"B" to 1, // Paper
			"C" to 2, // Scissor
			"X" to 0,
			"Y" to 1,
			"Z" to 2,
		)
		val points = listOf(
			listOf(3, 6, 0),
			listOf(0, 3, 6),
			listOf(6, 0, 3)
		)

		println("Input is size: " + lines.size)
		var score = 0

		lines.forEach {
			val split = it.split(" ")
			val opThrow = inputToThrow[split[0]]!!
			val meThrow = inputToThrow[split[1]]!!
			score += meThrow + 1

			val resultScore = points[opThrow][meThrow]
			score += resultScore
		}

		println(score)
	}

	override fun solvePart2() {
		val lines = object {}::class.java.getResource("input_02_1")!!
			.readText()
			.lines()
			.dropLast(1)
		val inputToThrow = listOf("A", "B", "C")
		var score = 0
		val pointsForGame = listOf("X", "Y", "Z")
		val winning = listOf(2, 3, 1)
		val losing = listOf(3, 1, 2)
		lines.forEach {
			val split = it.split(" ")
			val opThrow = inputToThrow.indexOf(split[0])
			val win = split[1]
			val winPoints = pointsForGame.indexOf(win) * 3
			score += winPoints

			val throwPoints = when (win) {
				"X" -> losing[opThrow]
				"Y" -> opThrow + 1
				"Z" -> winning[opThrow]
				else -> 0
			}
			score += throwPoints
		}

		println(score)
	}
}
