import kotlin.math.min

class XYNode(
	var value: Int,
) {
	var cost: Int? = null
	var left: XYNode? = null
	var right: XYNode? = null
	var up: XYNode? = null
	var down: XYNode? = null

	constructor(left: XYNode?, up: XYNode?, value: Int): this(
		value
	) {
		this.left = left
		this.up = up
		left?.right = this
		up?.down = this
	}

	fun populateNeighbors() {
		listOf(left, right, up, down).forEach {
			if (it == null) return@forEach

			if (it.value <= this.value + 1 && it.cost == null) {
				it.cost = this.cost!! + 1
			}
		}
	}
}
