object Day04 : AoCDay {
	override fun solvePart1() {
		val numberOfContained = object {}::class.java.getResource("input_04_1")!!
			.readText()
			.lines()
        .dropLast(1)
        .filter {
            val split = it.split(",", "-")
            val range1 = IntRange(split[0].toInt(), split[1].toInt())
            val range2 = IntRange(split[2].toInt(), split[3].toInt())
            val intersect = range1.intersect(range2)
            (intersect.containsAll(range1.toSet()) || intersect.containsAll(range2.toSet()))
        }
        .size


		println(numberOfContained)
	}

	override fun solvePart2() {
      val numberOfOverlaps = object {}::class.java.getResource("input_04_1")!!
          .readText()
          .lines()
          .dropLast(1)
          .filter {
              val split = it.split(",", "-")
              val range1 = IntRange(split[0].toInt(), split[1].toInt())
              val range2 = IntRange(split[2].toInt(), split[3].toInt())
              val intersect = range1.intersect(range2)
              intersect.isNotEmpty()
          }
          .size

		println(numberOfOverlaps)
	}
}
