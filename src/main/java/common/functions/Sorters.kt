package common.functions

fun <T> MutableList<T>.bubbleSort(comparator: Comparator<in T>) {
    for (i in 1 until size) {
        var swapped = false
        for (j in 1..this.size - i) {
            if (comparator.compare(this[j - 1], this[j]) > 0) {
                swapped = true
                val temp = this[j]
                this[j] = this[j - 1]
                this[j - 1] = temp
            }
        }
        if (!swapped) {
            return
        }
    }
}
