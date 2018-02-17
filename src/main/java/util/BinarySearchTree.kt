package util

class BinarySearchTree<E>(override var size: Int = 0) : MutableCollection<E> where E : Comparable<E> {
    private var root: BinarySearchTreeNode<E>? = null

    override fun contains(element: E): Boolean {
        return containsRec(this.root, element)
    }

    private fun containsRec(node: BinarySearchTreeNode<E>?, element: E): Boolean {
        if (node == null) {
            return false
        }
        if (element < node.value) {
            return if (node.left?.value == element) {
                true
            } else {
                containsRec(node.left, element)
            }
        }
        return if (node.right?.value == element) {
            true
        } else {
            containsRec(node.right, element)
        }
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        return elements.all { this.contains(it) }
    }

    override fun isEmpty(): Boolean {
        return this.size == 0
    }

    override fun add(element: E): Boolean {
        val previousSize = this.size
        if (this.root == null) {
            this.root = BinarySearchTreeNode(element)
            this.size++
        } else {
            addRec(this.root, element)
        }
        return this.size == previousSize + 1
    }

    private fun addRec(node: BinarySearchTreeNode<E>?, element: E) {
        if (node == null) throw IllegalStateException()
        if (element < node.value) {
            if (node.left == null) {
                node.left = BinarySearchTreeNode(element)
            } else {
                addRec(node.left, element)
                return
            }
        } else {
            if (node.right == null) {
                node.right = BinarySearchTreeNode(element)
            } else {
                addRec(node.right, element)
                return
            }
        }
        this.size++
    }

    override fun addAll(elements: Collection<E>): Boolean {
        var changed = false
        elements.forEach { changed = this.add(it) || changed }
        return changed
    }

    override fun clear() {
        this.root = null
        this.size = 0
    }

    override fun iterator(): MutableIterator<E> {
        return Itr()
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
            if (!elements.contains(it)) {
                changed = this.remove(it) || changed
            }
        }
        return changed
    }

    open inner class Itr : MutableIterator<E> {
        override fun hasNext(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun next(): E {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun remove() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}