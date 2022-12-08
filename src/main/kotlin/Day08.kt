object Day08 : AoCDay {
	fun createArray() = object {}::class.java.getResource("input_08_1")!!
		.readText()
		.lines()
		.dropLast(1)
		.map { line -> line.map { it.digitToInt() } }

	fun getDirections(array: List<List<Int>>, line: List<Int>, indexY: Int, indexX: Int): List<List<Int>> {
		val nsLine = array.map { it[indexX] }
		val north = nsLine.take(indexY)
		val south = nsLine.drop(indexY + 1)
		val west = line.take(indexX)
		val east = line.drop(indexX + 1)

		return listOf(north.reversed(), south, west.reversed(), east);
	}


	override fun solvePart1() {
		val array = createArray()
		var visibleTrees = 0;

		array.forEachIndexed { indexY, line ->
			line.forEachIndexed x@ { indexX, tree ->
				val directions = getDirections(array, line, indexY, indexX)

				if (directions.any { it.isEmpty() || it.max() < tree }) {
					visibleTrees++;
				}
			}
		}

		println(visibleTrees)
	}

	override fun solvePart2() {
		val array = createArray()

		var maxScenicScore = 0;

		array.forEachIndexed { indexY, line ->
			line.forEachIndexed x@ { indexX, tree ->
				val directions = getDirections(array, line, indexY, indexX)
				val viewable = directions.map { direction ->
					direction.takeUntil { viewTree ->
						viewTree < tree
					}
				}
				val scenicScore = viewable.map { it.size }.reduce { acc, i -> acc * i }
				maxScenicScore = maxScenicScore.coerceAtLeast(scenicScore)
			}
		}
		println(maxScenicScore)
	}
}
