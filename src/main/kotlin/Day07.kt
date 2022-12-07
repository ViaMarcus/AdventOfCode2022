object Day07 : AoCDay {
	fun createFileStructure(): TreeNode<MutableList<Int>> {
		val lines = object {}::class.java.getResource("input_07_1")!!
			.readText()
			.lines()
			.dropLast(1)
			.drop(1) // drop cd /
		val rootNode: TreeNode<MutableList<Int>> = TreeNode(parent = null, value = mutableListOf(), "/")
		var currentNode: TreeNode<MutableList<Int>> = rootNode
		lines.forEach { line ->
			if (line.length == 7 && line.substring(0..6) == "$ cd ..") {
				currentNode = currentNode.parent!!;
			} else if (line.substring(0..3) == "$ cd") {
				val folder = line.substring(5..5)
				val child = currentNode.addChild(mutableListOf(), folder)
				currentNode = child;
			} else if (line.substring(0..3) == "$ ls") {
			} else if(line.substring(0..2) == "dir") {
			} else { // file listing
				val size = line.split(" ")[0].toInt()
				currentNode.value.add(size)
			}
		}

		return rootNode
	}

	override fun solvePart1() {
		val rootNode = createFileStructure()
		val fileSizeSum = rootNode.getAllChildren()
			.map { it.getAllValues().flatten().sum() }
			.filter { it <= 100000 }
			.sum()

		println(fileSizeSum)
	}

	override fun solvePart2() {
		val rootNode = createFileStructure()
		val currentUsed = rootNode.getAllValues().flatten().sum()
		val totalSpace = 70_000_000;
		val totalFree = totalSpace - currentUsed;
		val needed = 30_000_000 - totalFree
		val smallestFreeable = rootNode
			.getAllChildren()
			.map { it.getAllValues().flatten().sum() }
			.sorted()
			.first { it > needed }

		println(smallestFreeable)
	}
}
