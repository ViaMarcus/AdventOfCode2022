import java.time.LocalDateTime

fun main(args: Array<String>) {
	val todaysMonthDay = ("0" + LocalDateTime.now().minusDays(1).dayOfMonth).takeLast(2)
	val todaysClass = Class.forName("Day$todaysMonthDay").kotlin.objectInstance as AoCDay;
	println("Part1")
//	todaysClass.solvePart1();
	println("Part2")
	todaysClass.solvePart2()
}
