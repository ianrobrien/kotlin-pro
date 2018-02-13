package util

data class LinkedListNode<T>(val value: T, var next: LinkedListNode<T>? = null) {
    constructor(value: T) : this(value, null)
}