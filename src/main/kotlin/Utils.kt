fun String.divide(chars: Int): List<String> {
	assert(chars > 0)
	val outList = mutableListOf<String>()
	var index = 0;
	while (index in this.indices) {
		outList.add(this.drop(index).take(chars))
		index += chars
	}
	return outList
}

inline fun <T> Iterable<T>.takeUntil(predicate: (T) -> Boolean): List<T> {
	val list = ArrayList<T>()
	for (item in this) {
		list.add(item)
		if (!predicate(item))
			break
	}
	return list
}
