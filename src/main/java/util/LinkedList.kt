package util

class LinkedList<E>(override var size: Int = 0) : MutableList<E> {
    private var head: LinkedListNode<E>? = null

    override fun contains(element: E): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEmpty(): Boolean {
        return this.size == 0
    }

    override fun iterator(): MutableIterator<E> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun lastIndexOf(element: E): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
            newNode.next = splicePoint
            splicePoint.next = newNode
        }
        size++
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addAll(elements: Collection<E>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listIterator(index: Int): MutableListIterator<E> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

}