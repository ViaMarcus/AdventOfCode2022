class TreeNode<T>(
	val parent: TreeNode<T>?,
	val value: T,
	val label: String
) {
	val children: MutableList<TreeNode<T>> = mutableListOf();

	fun addChild(childValue: T, childLabel: String): TreeNode<T> {
		val child = TreeNode(this, childValue, childLabel)
		this.children.add(child)
		return child
	}

	fun getAllChildren(): List<TreeNode<T>> = (children.flatMap { it.getAllChildren() }).plus(this)

	fun getAllValues() = getAllChildren().map { it.value }
}
