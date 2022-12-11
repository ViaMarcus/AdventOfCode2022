import kotlin.math.abs

object Day10 : AoCDay {
	override fun solvePart1() {
		val lines = object {}::class.java.getResource("input_10_1")!!
			.readText()
			.lines()
			.dropLast(1)

		var cycle = 0
		var xValue = 1
		var signalStrength = 0

		fun addIfShould() {
			if ((cycle % 40) == 20) {
				println("cycle: " + cycle + ", adding " + cycle * xValue)
				signalStrength += cycle * xValue
			}
		}

		lines.forEach { line ->
			if (line == "noop") {
				cycle++
				addIfShould()
			} else {
				cycle++
				val toAdd = Integer.parseInt(line.removePrefix("addx "))
				addIfShould()
				cycle++
				addIfShould()
				xValue += toAdd
			}
		}

		println(signalStrength)
	}

	override fun solvePart2() {
		val lines = object {}::class.java.getResource("input_10_1")!!
			.readText()
			.lines()
			.dropLast(1)

		var cycle = 0
		var xValue = 1
		val pixels = mutableListOf<Char>()

		fun drawPixelIfShould() {
			val currPixelX = pixels.size % 40
			if (abs(currPixelX - xValue) < 2) {
				pixels.add('#')
			} else {
				pixels.add('.')
			}
		}

		lines.forEach { line ->
			if (line == "noop") {
				cycle++
				drawPixelIfShould()
			} else {
				cycle++
				val toAdd = Integer.parseInt(line.removePrefix("addx "))
				drawPixelIfShould()
				cycle++
				drawPixelIfShould()
				xValue += toAdd
			}
		}

		pixels.joinToString(separator = "").divide(40).forEach { println(it) }
	}
}
