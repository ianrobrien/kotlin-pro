package util

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

@RunWith(JUnitPlatform::class)
class LinkedListTest : Spek({

    fun getTestElement(): String {
        return UUID.randomUUID().toString()
    }

    describe("A LinkedList of any size") {
        val emptyLinkedList = LinkedList<String>()
        it("should throw an exception when adding an element at an invalid index") {
            assertFailsWith(IndexOutOfBoundsException::class, { emptyLinkedList.add(-1, "") })
            assertFailsWith(IndexOutOfBoundsException::class, { emptyLinkedList.add(100, "") })
        }
    }
    describe("An empty LinkedList") {
        lateinit var ll: LinkedList<String>
        beforeEachTest {
            ll = LinkedList()
        }
        on("default construction") {
            it("should be empty") {
                assertTrue(ll.isEmpty())
                assertEquals(0, ll.size)
            }
        }
        context("adding a single element") {
            it("should correctly add the element to the head") {
                val testElement = getTestElement()
                ll.add(testElement)
                assertEquals(testElement, ll[0])
                assertEquals(1, ll.size)
            }
            it("should correctly add the element to the head by index") {
                val testElement = getTestElement()
                ll.add(0, testElement)
                assertTrue(ll[0] == testElement)
                assertTrue(ll.size == 1)
            }
        }
    }
    describe("A LinkedList with one element") {
        lateinit var ll: LinkedList<String>
        val defaultElement = getTestElement()
        context("adding an element") {
            beforeEachTest {
                ll = LinkedList()
                ll.add(defaultElement)
            }
            it("should correctly add the element to the tail") {
                val tailElement = getTestElement()
                ll.add(tailElement)
                assertEquals(defaultElement, ll[0])
                assertEquals(tailElement, ll[1])
                assertEquals(2, ll.size)
            }
            it("should correctly add the element to the head by index") {
                val headElement = getTestElement()
                ll.add(0, headElement)
                assertEquals(headElement, ll[0])
                assertEquals(defaultElement, ll[1])
                assertEquals(ll.size, 2)
            }
            it("should correctly add the element to the tail by index") {
                val tailElement = getTestElement()
                ll.add(1, tailElement)
                assertEquals(defaultElement, ll[0])
                assertEquals(tailElement, ll[1])
                assertEquals(2, ll.size)
            }
        }
    }
    describe("An LinkedList with multiple elements") {
        lateinit var ll: LinkedList<String>
        val defaultElementFirst = getTestElement()
        val defaultElementSecond = getTestElement()
        context("adding an element") {
            beforeEachTest {
                ll = LinkedList()
                ll.add(defaultElementFirst)
                ll.add(defaultElementSecond)
            }
            it("should correctly add the element to the tail") {
                val tailElement = getTestElement()
                ll.add(tailElement)
                assertEquals(defaultElementFirst, ll[0])
                assertEquals(defaultElementSecond, ll[1])
                assertEquals(tailElement, ll[2])
                assertEquals(3, ll.size)
            }
            it("should correctly add the element to the head by index") {
                val headElement = getTestElement()
                ll.add(0, headElement)
                assertEquals(headElement, ll[0])
                assertEquals(defaultElementFirst, ll[1])
                assertEquals(defaultElementSecond, ll[2])
                assertEquals(ll.size, 3)
            }
            it("should correctly add the element to the tail by index") {
                val tailElement = getTestElement()
                ll.add(2, tailElement)
                assertEquals(defaultElementFirst, ll[0])
                assertEquals(defaultElementSecond, ll[1])
                assertEquals(tailElement, ll[2])
                assertEquals(3, ll.size)
            }
            it("should check contains on all elements") {
                val tailElement = getTestElement()
                ll.add(tailElement)
                assertTrue(ll.contains(defaultElementFirst))
                assertTrue(ll.contains(defaultElementSecond))
                assertTrue(ll.contains(tailElement))

                val arrayList = java.util.ArrayList<String>()
                arrayList.add(defaultElementFirst)
                arrayList.add(defaultElementSecond)
                arrayList.add(tailElement)

                assertTrue(ll.containsAll(arrayList))
            }
        }
    }
    describe("a linked list of duplicate items") {
        val ll = LinkedList<String>()
        val defaultElementFirst = getTestElement()
        ll.add(defaultElementFirst)
        ll.add(defaultElementFirst)
        ll.add(defaultElementFirst)

        it("should return the tail when returning lastIndexOf") {
            assertEquals(ll.lastIndexOf(defaultElementFirst), ll.size - 1)
        }
    }
    describe("a linked list of integers") {
        context("should support adding a range") {
            it("should add a collection to the tail") {
                val front = LinkedList<Int>()
                for (i in 0 until 5) {
                    front.add(i)
                }
                val back = LinkedList<Int>()
                for (i in 5 until 10) {
                    back.add(i)
                }
                front.addAll(back)
                for (i in 0..9) {
                    assertEquals(i, front[i])
                }
            }
            it("should add a collection to the middle") {
                val ll = LinkedList<Int>()
                ll.add(0)
                ll.add(1)
                ll.add(2)
                ll.add(8)
                ll.add(9)

                val middle = LinkedList<Int>()
                middle.add(3)
                middle.add(4)
                middle.add(5)
                middle.add(6)
                middle.add(7)

                ll.addAll(3, middle)
                for (i in 0..9) {
                    assertEquals(i, ll[i])
                }
            }
        }
    }
})

