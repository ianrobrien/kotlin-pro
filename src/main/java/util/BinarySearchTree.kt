package util

class BinarySearchTree<E>(override var size: Int) : MutableCollection<E> {
    override fun contains(element: E): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        return elements.all { this.contains(it) }
    }

    override fun isEmpty(): Boolean {
        return this.size == 0
    }

    override fun add(element: E): Boolean {
        val previousSize = this.size
        this.size++
        return this.size == previousSize + 1
    }

    override fun addAll(elements: Collection<E>): Boolean {
        var changed = false
        elements.forEach { changed = this.add(it) || changed }
        return changed
    }

    override fun clear() {
        this.size = 0
    }

    override fun iterator(): MutableIterator<E> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(element: E): Boolean {
        val previousSize = this.size
        this.size--
        return this.size != previousSize
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        var changed = false
        elements.forEach { changed = this.remove(it) || changed }
        return changed
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        var changed = false
        this.forEach {
            if (elements.contains(it)) {
                changed = this.remove(it) || changed
            }
        }
        return changed
    }
}