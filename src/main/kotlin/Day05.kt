object Day05 : AoCDay {
	override fun solvePart1() {
		val input = object {}::class.java.getResource("input_05_1")!!
			.readText()
      .split("\n\n")
    val layers = input[0].lines().map { line -> line.divide(4).map { it[1] } }
    val numberOfStacks = layers.last().size + 1 / 4 // Last line is only 3 chars, and will cause rounding down, so we +1
    val stacks = List<MutableList<Char>>(numberOfStacks) { mutableListOf() }

    layers.dropLast(1).forEach { layer ->
        layer.forEachIndexed { index, c ->
            if(c.isLetter()) stacks[index].add(0, c)
        }
    }
		val operations = input[1]
			.lines()
			.dropLast(1)
			.map { it.removePrefix("move ").split(" to ", " from ") }
			.onEach { println(it) }
			.map { Triple(it[0].toInt(), it[1].toInt(), it[2].toInt()) }
		operations.forEach { op ->
			repeat(op.first) {
				stacks[op.third - 1].add(stacks[op.second - 1].removeLast())
			}
		}

		println(stacks.map { it.last() }.joinToString(separator = ""))
	}

	override fun solvePart2() {
		val input = object {}::class.java.getResource("input_05_1")!!
			.readText()
			.split("\n\n")
		val layers = input[0].lines().map { line -> line.divide(4).map { it[1] } }
		val numberOfStacks = layers.last().size + 1 / 4 // Last line is only 3 chars, and will cause rounding down, so we +1
		val stacks = List<MutableList<Char>>(numberOfStacks) { mutableListOf() }

		layers.dropLast(1).forEach { layer ->
			layer.forEachIndexed { index, c ->
				if(c.isLetter()) stacks[index].add(0, c)
			}
		}
		val operations = input[1]
			.lines()
			.dropLast(1)
			.map { it.removePrefix("move ").split(" to ", " from ") }
			.onEach { println(it) }
			.map { Triple(it[0].toInt(), it[1].toInt(), it[2].toInt()) }
		operations.forEach { op ->
			val fromStack = stacks[op.second - 1]
			stacks[op.third - 1].addAll(fromStack.takeLast(op.first))
			val stackAfter = fromStack.dropLast(op.first)
			fromStack.clear()
			fromStack.addAll(stackAfter)
		}

		println(stacks.map { it.last() }.joinToString(separator = ""))
	}
}
