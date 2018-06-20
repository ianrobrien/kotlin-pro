package util

internal data class LinkedListNode<T>(var value: T, var next: LinkedListNode<T>? = null) {
    constructor(value: T) : this(value, null)
}