object Day06 : AoCDay {
	override fun solvePart1() {
		val lines = object {}::class.java.getResource("input_06_1")!!
			.readText()
			.lines()
    var sum = 0;
    lines.forEach { line ->
        for (i in 0..line.length - 4) {
            val subSequence = line.subSequence(i, i + 4)
            val subSet = subSequence.toSet()
            if (subSet.size == 4){
                sum += i + 4
                return@forEach
            }
        }
    }

		println(sum)
	}

	override fun solvePart2() {
      val lines = object {}::class.java.getResource("input_06_1")!!
          .readText()
          .lines()
      var sum = 0;
      lines.forEach { line ->
          for (i in 0..line.length - 14) {
              val subSequence = line.subSequence(i, i + 14)
              val subSet = subSequence.toSet()
              if (subSet.size == 14){
                  sum += i + 14
                  return@forEach
              }
          }
      }
      println(sum)
	}
}
