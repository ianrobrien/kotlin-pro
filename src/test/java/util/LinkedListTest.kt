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
        }
    }
})

