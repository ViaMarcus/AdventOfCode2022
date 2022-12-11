import javax.script.ScriptEngineManager

class Monkey(
	init: String,
	val part1: Boolean
) {
	val inventory = mutableListOf<Long>()
	var tossedTimes = 0L;

	var inspectOperator: Char = '*'
	var inspectAmount: String
	var divisibleBy: Int = 1;
	var targetTrue: Int = -1
	var targetFalse: Int = -1

	fun test(item: Long) = item % divisibleBy == 0L

	fun inspect(item: Long): Long {
		return if (inspectAmount == "old") {
			item * item
		} else if (inspectOperator == '*') {
			item * inspectAmount.toInt()
		} else item + inspectAmount.toInt()
	}

	fun toss() {
		inventory.forEach {
			val newValue = if (part1) inspect(it) / 3 else inspect(it) % divideWith
			tossedTimes++
			val target = if (test(newValue)) targetTrue else targetFalse
			val receiver = monkeys[target]
			receiver.receive(newValue)
		}
		inventory.clear()
	}

	fun receive(item: Long) {
		inventory.add(item)
	}

	init {
		val lines = init.lines().map { it.trim() }.drop(1)
		val startingItems = lines[0].removePrefix("Starting items: ")
			.split(", ")
			.map { it.toLong() }
		inventory.addAll(startingItems)

		val inspect = lines[1].removePrefix("Operation: new = old ").split(" ")
		inspectOperator = inspect[0][0]
		inspectAmount = inspect[1]

		divisibleBy = lines[2].removePrefix("Test: divisible by ").toInt()
		targetTrue = lines[3].takeLast(1).toInt()
		targetFalse = lines[4].takeLast(1).toInt()

		monkeys.add(this)
	}

	override fun toString(): String {
		return "inventory: $inventory; tossedTimes: $tossedTimes; true: $targetTrue; false: $targetFalse; test: $divisibleBy; inspect: $inspectOperator $inspectAmount"
	}

	companion object {
		val monkeys = mutableListOf<Monkey>()
		var divideWith = 3
	}
}
