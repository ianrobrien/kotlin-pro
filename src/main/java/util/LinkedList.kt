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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
            (0..index).forEach { next = next?.next ?: throw IllegalStateException() }
            next?.next = adding
        }
        size++
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addAll(elements: Collection<E>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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