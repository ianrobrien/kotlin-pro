package util

class LinkedList<E>(override var size: Int = 0) : MutableList<E> {
    var head: LinkedListNode<E>? = null

    override fun contains(element: E): Boolean {
        return indexOf(element) >= 0
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        return elements.all { contains(it) }
    }

    override fun get(index: Int): E {
        var current = head
        (0 until index).forEach { current = current?.next ?: throw IllegalStateException() }
        return current?.value ?: throw IllegalStateException()
    }

    override fun indexOf(element: E): Int {
        var index = -1
        this.forEach {
            index++
            if (it == element) {
                return index
            }
        }
        return -1
    }

    override fun isEmpty(): Boolean {
        return this.size == 0
    }

    override fun iterator(): MutableIterator<E> {
        return Itr()
    }

    override fun lastIndexOf(element: E): Int {
        var lastIndex = -1

        var next = this.head
        var index = 0
        while (next != null) {
            if (next.value == element) {
                lastIndex = index
            }
            index++
            next = next.next
        }

        return lastIndex
    }

    override fun add(element: E): Boolean {
        var previousSize = size
        add(size, element)
        return size == ++previousSize
    }

    override fun add(index: Int, element: E) {
        val adding = LinkedListNode(element)
        if (head == null) head = LinkedListNode(element) else {
            var next = head
            (0 until index - 1).forEach { next = next?.next ?: throw IllegalStateException() }
            next?.next = adding
        }
        size++
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        val previousSize = this.size
        var insertPoint = this.head

        (0 until index - 1).forEach { insertPoint = insertPoint?.next }
        val next = insertPoint?.next
        for (element in elements) {
            insertPoint?.next = LinkedListNode(element)
            insertPoint = insertPoint?.next
            size++
        }
        insertPoint?.next = next

        return this.size == previousSize + elements.size
    }

    override fun addAll(elements: Collection<E>): Boolean {
        return addAll(size, elements)
    }

    override fun clear() {
        this.head = null
        this.size = 0
    }

    override fun listIterator(): MutableListIterator<E> {
        return ListItr()
    }

    override fun listIterator(index: Int): MutableListIterator<E> {
        return ListItr(index)
    }

    override fun remove(element: E): Boolean {
        val previousSize = this.size
        if (this.head?.value == element) {
            this.head = this.head?.next
        } else {
            var current = this.head
            while (current?.next != null) {
                if (current.next?.value == element) {
                    current.next = current.next?.next
                    size--
                } else {
                    current = current.next
                }
            }
        }
        return this.size == previousSize + 1
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        val previousSize = this.size
        this
                .filter { elements.contains(it) }
                .forEach { this.remove(it) }
        return this.size != previousSize
    }

    override fun removeAt(index: Int): E {
        if (index > this.size) throw IndexOutOfBoundsException()
        var node = this.head
        (0 until index - 1).forEach { node = node?.next ?: throw IllegalStateException() }
        val previousValue = node?.value
        node?.next = node?.next?.next
        size--
        return previousValue ?: throw IllegalStateException()
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        val previousSize = this.size
        this
                .filterNot { elements.contains(it) }
                .forEach { this.remove(it) }
        return this.size != previousSize
    }

    override fun set(index: Int, element: E): E {
        if (index > this.size) throw IndexOutOfBoundsException()
        var node = this.head
        (0 until index - 1).forEach { node = node?.next ?: throw IllegalStateException() }
        val previousValue = node?.value
        node?.value = element
        return previousValue ?: throw IllegalStateException()
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<E> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    open inner class Itr : MutableIterator<E> {
        internal var cursor: Int = 0    // index of next element to return
        internal var lastRet = -1       // index of last element returned; -1 if no such
        internal var next = head

        override fun hasNext(): Boolean {
            return cursor != size
        }

        override fun next(): E {
            val current = next ?: throw NoSuchElementException()
            next = current.next
            cursor++
            return current.value
        }

        override fun remove() {
            if (lastRet < 0) throw IllegalStateException()
            removeAt(lastRet)
            lastRet = -1
        }
    }

    inner class ListItr() : MutableListIterator<E>, Itr() {
        constructor(index: Int) : this() {
            cursor = index
        }

        override fun hasPrevious(): Boolean {
            return cursor != 0
        }

        override fun nextIndex(): Int {
            return cursor
        }

        override fun previous(): E {
            val i = cursor - 1
            if (i < 0) throw NoSuchElementException()
            cursor = i
            var node = head
            (0..i).forEach { node = node?.next ?: throw IllegalStateException() }
            return node?.value ?: throw IllegalStateException()
        }

        override fun previousIndex(): Int {
            return cursor - 1
        }

        override fun add(element: E) {
            add(cursor++, element)
            lastRet = -1
        }

        override fun set(element: E) {
            set(lastRet, element)
        }
    }
}