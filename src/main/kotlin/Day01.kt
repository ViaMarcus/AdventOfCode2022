object Day01: AoCDay {
	override fun solvePart1() {
		val file = "input_1_1"
		val max = object {}::class.java.getResource(file)!!
			.readText()
			.split("\n\n")
			.map { it.lines().map{ s -> ("0" + s).toInt() }.sum() }
			.max()
		println(max)
	}

	override fun solvePart2() {
		val file = "input_1_1"
		val last3Sum = object {}::class.java.getResource(file)!!
			.readText()
			.split("\n\n")
			.map { it.lines().map{ s -> ("0" + s).toInt() }.sum() }
			.sorted()
			.takeLast(3)
			.sum()
		println(last3Sum)
	}
}
