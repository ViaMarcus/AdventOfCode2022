object Day12 : AoCDay {
	override fun solvePart1() {
		val lines = object {}::class.java.getResource("input_12_1")!!
			.readText()
			.lines()
			.dropLast(1)
			.map { it.toCharArray().map { c -> c.code } }

		val matrix: MutableList<List<XYNode>> = mutableListOf()
		var lineAbove: List<XYNode>? = null
		var nodeLeft: XYNode? = null
		var start: XYNode? = null
		var end: XYNode? = null

		lines.forEach { intArr ->
			val currentRow = mutableListOf<XYNode>()
			intArr.forEachIndexed { index, int ->
				val newNode = XYNode(left = nodeLeft, lineAbove?.get(index), value = int)
				currentRow.add(newNode)
				nodeLeft = newNode
				if (int == 69) end = newNode
				if (int == 83) start = newNode
			}
			matrix.add(currentRow)
			lineAbove = currentRow
		}

		start!!.value = 96
		start!!.cost = 0
		end!!.value = 122

		var costMap: Map<Int, List<XYNode>>;
		var ended = false

		var currentCost = 0

		while (!ended) {
			costMap = matrix.flatten().groupBy { it.cost ?: Int.MAX_VALUE }
			costMap[currentCost]?.forEach {
				it.populateNeighbors()
				if (it == end) ended = true
			}
			currentCost++;
		}


		println(end!!.cost)
	}

	override fun solvePart2() {
		val lines = object {}::class.java.getResource("input_12_1")!!
			.readText()
			.lines()
			.dropLast(1)
			.map { it.toCharArray().map { c -> c.code } }

		val matrix: MutableList<List<XYNode>> = mutableListOf()
		var lineAbove: List<XYNode>? = null
		var nodeLeft: XYNode? = null
		val starts: MutableList<XYNode> = mutableListOf()
		var end: XYNode? = null

		lines.forEach { intArr ->
			val currentRow = mutableListOf<XYNode>()
			intArr.forEachIndexed { index, int ->
				val newNode = XYNode(left = nodeLeft, lineAbove?.get(index), value = int)
				currentRow.add(newNode)
				nodeLeft = newNode
				if (int == 69) end = newNode
				if (int == 97) starts.add(newNode)
				if (int == 83) starts.add(newNode.also { it.value = 97 })
			}
			matrix.add(currentRow)
			lineAbove = currentRow
		}

		end!!.value = 122
		end!!.cost = 0

		var costMap: Map<Int, List<XYNode>>;
		var ended = false

		var currentCost = 0

		while (!ended) {
			costMap = matrix.flatten().groupBy { it.cost ?: Int.MAX_VALUE }
			costMap[currentCost]?.forEach {
				it.populateNeighbors()
				if (starts.contains(it)) {
					ended = true
					println(it.cost)
				}

			}
			currentCost++;
		}

		println(end!!.cost)
	}
}
