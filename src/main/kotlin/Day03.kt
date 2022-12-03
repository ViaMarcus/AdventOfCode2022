object Day03 : AoCDay {
	val alphabet = "abcdefghijklmnopqrstuvwxyz"
	val priority = " " + alphabet + alphabet.uppercase()

	override fun solvePart1() {
		val lines = object {}::class.java.getResource("input_03_1")!!
			.readText()
			.lines()
			.dropLast(1)
		var sum = 0
		lines.forEach {
			val comp1 = it.substring(0 until it.length / 2).toList()
			val comp2 = it.substring(it.length / 2).toCharArray().toList()
			val common = comp1.intersect(comp2).first()
			sum += priority.indexOf(common)
		}

		println(sum)
	}

	override fun solvePart2() {
		val lines = object {}::class.java.getResource("input_03_1")!!
			.readText()
			.lines()
			.dropLast(1)
			.iterator()
		val groups = mutableListOf<List<String>>()

		while (lines.hasNext()) {
			groups.add(listOf(lines.next(), lines.next(), lines.next()))
		}

		var sum = 0;
		groups.forEach {
			val common = it[0].toList().intersect(it[1].toSet()).intersect(it[2].toSet()).first()
			sum += priority.indexOf(common)
		}

		println(sum)
	}
}
