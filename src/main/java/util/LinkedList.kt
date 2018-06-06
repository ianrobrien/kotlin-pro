package util

class LinkedList<E>(override var size: Int = 0) : MutableList<E> {
    private var head: LinkedListNode<E>? = null

    override fun contains(element: E): Boolean {
        return indexOf(element) >= 0
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        return elements.all { contains(it) }
    }

    override fun get(index: Int): E {
        rangeCheck(index)
        return getNode(index).value
    }

    private fun getNode(index: Int): LinkedListNode<E> {
        rangeCheck(index)
        var node = head
        for (i in 0 until index) {
            node = node?.next
        }
        return node ?: throw IllegalStateException()
    }

    private fun rangeCheck(index: Int) {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index: $index, Size: ${this.size}")
        }
    }

    override fun indexOf(element: E): Int {
        if (head != null) {
            var index = 0
            var currentNode = head
            while (currentNode != null) {
                if (currentNode.value == element) {
                    return index
                }
                currentNode = currentNode.next
                index++
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
        if (head != null) {
            var index = 0
            var currentNode = head
            while (currentNode != null) {
                if (currentNode.value == element) {
                    lastIndex = index
                }
                currentNode = currentNode.next
                index++
            }
        }
        return lastIndex
    }

    override fun add(element: E): Boolean {
        val previousSize = size
        if (head == null) {
            head = LinkedListNode(element)
        } else {
            getTailNode()?.next = LinkedListNode(element)
        }
        return ++size == previousSize + 1
    }

    private fun getTailNode(): LinkedListNode<E>? {
        return if (head == null) {
            null
        } else {
            var tail = head
            while (tail?.next != null) {
                tail = tail.next
            }
            tail
        }
    }

    override fun add(index: Int, element: E) {
        rangeCheckForAdd(index)
        val newNode = LinkedListNode(element)
        if (index == 0) {
            // the next node is either the current head or null
            newNode.next = head
            head = newNode
        } else {
            val splicePoint = getNode(index - 1)
            newNode.next = splicePoint.next
            splicePoint.next = newNode
        }
        size++
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        val expectedSize = size + elements.size
        rangeCheckForAdd(index)
        var insertIndex = index
        for (element in elements) {
            add(insertIndex, element)
            insertIndex++
        }
        return expectedSize == size
    }

    override fun addAll(elements: Collection<E>): Boolean {
        val expectedSize = size + elements.size
        for (element in elements) {
            this.add(element)
        }
        return expectedSize == size
    }

    private fun rangeCheckForAdd(index: Int) {
        if (index < 0 || index > size) {
            throw IndexOutOfBoundsException("Index: $index, Size: ${this.size}")
        }
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listIterator(): MutableListIterator<E> {
        return ListItr()
    }

    override fun listIterator(index: Int): MutableListIterator<E> {
        return ListItr(index)
    }

    override fun remove(element: E): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeAt(index: Int): E {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(index: Int, element: E): E {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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