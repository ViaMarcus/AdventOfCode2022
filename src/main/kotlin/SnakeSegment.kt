import kotlin.math.abs

class SnakeSegment(
	val before: SnakeSegment? = null,
	var x: Int = 0,
	var y: Int = 0,
) {
	var after: SnakeSegment? = null

	fun addAfter(): SnakeSegment {
		this.after = SnakeSegment(this)
		return this.after!!
	}

	fun move(parentMoveX: Int, parentMoveY: Int) {
		if (before == null) {
			this.x += parentMoveX
			this.y += parentMoveY
			after?.move(parentMoveX, parentMoveY)
		} else {
			val diffX = before.x - this.x
			val diffY = before.y - this.y
			if ((abs(diffX) == 2 && abs(diffY) == 0)) {
				this.x += parentMoveX
				after?.move(parentMoveX, 0)
			} else if ((abs(diffX) == 2 && abs(diffY) == 0)) {
				this.y += parentMoveY
				after?.move(0, parentMoveY)
			} else if (abs(diffX) > 1 || abs(diffY) > 1) {
				val moveX = diffX.coerceIn(-1..1)
				val moveY = diffY.coerceIn(-1..1)
				this.x += moveX
				this.y += moveY
				after?.move(moveX, moveY)
			}
		}
	}

	override fun toString(): String {
		return "SnakeSegment: X: $x, Y: $y"
	}
}
