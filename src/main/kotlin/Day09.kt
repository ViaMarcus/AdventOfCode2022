import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import kotlin.math.abs

object Day09 : AoCDay {
	fun getVisitedCoords(ops: List<Pair<Char, Int>>, size: Int): Set<Pair<Int, Int>> {
		val visitedCoords = mutableSetOf<Pair<Int, Int>>()

		val segments = List(size) { Triple(AtomicInteger(0), AtomicInteger(0), AtomicReference('N')) }

		ops.forEach { (dir, amount) ->
			repeat(amount) {
				val firstSegment = segments[0]
				firstSegment.third.set(dir)
				when (dir) {
					'U' -> firstSegment.second.getAndIncrement()
					'D' -> firstSegment.second.getAndDecrement()
					'R' -> firstSegment.first.getAndIncrement()
					'L' -> firstSegment.first.getAndDecrement()
				}
				segments.reduce { prev, new ->
					val x = prev.first
					val y = prev.second
					val tailX = new.first
					val tailY = new.second
					val prevNodeMovement = prev.third

					if (abs(tailX.get() - x.get()) > 1) {
						if (prevNodeMovement.get() == 'R') {
							tailX.set(x.get() - 1)
							tailY.set(y.get())
						} else {
							tailX.set(x.get() + 1)
							tailY.set(y.get())
						}
						new.third.set(prevNodeMovement.get())
					} else if (abs(tailY.get() - y.get()) > 1) {
						if (prevNodeMovement.get() == 'U') {
							tailX.set(x.get())
							tailY.set(y.get() - 1)
						} else {
							tailX.set(x.get())
							tailY.set(y.get() + 1)
						}
						new.third.set(prevNodeMovement.get())
					} else {
						new.third.set('N')
					}

					return@reduce new
				}
				visitedCoords.add(Pair(segments.last().first.get(), segments.last().second.get()))
			}
		}
		return visitedCoords
	}

	override fun solvePart1() {
		val ops = object {}::class.java.getResource("input_09_1")!!
			.readText()
			.lines()
			.dropLast(1)
			.map { Pair(it[0], Integer.parseInt(it.substring(2))) }

		val visitedCoords = getVisitedCoords(ops, 2)

		println(visitedCoords.size)
	}

	override fun solvePart2() {
		val ops = object {}::class.java.getResource("input_09_1")!!
			.readText()
			.lines()
			.dropLast(1)
			.map { Pair(it[0], Integer.parseInt(it.substring(2))) }

		val head = SnakeSegment()
		val segments = mutableListOf(head)
		var prevSegment = head
		repeat(9) {
			prevSegment = prevSegment.addAfter()
			segments.add(prevSegment)
		}
		val tail = prevSegment
		val locationList = mutableSetOf<Pair<Int, Int>>()

		ops.forEach { (dir, amount) ->
			repeat(amount) {
				when (dir) {
					'U' -> head.move(0, 1)
					'D' -> head.move(0, -1)
					'R' -> head.move(1, 0)
					'L' -> head.move(-1, 0)
				}

				locationList.add(Pair(tail.x, tail.y))
			}
		}

		println(locationList.size)
	}
}
