package util

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import kotlin.test.assertTrue

@RunWith(JUnitPlatform::class)
class LinkedListTest : Spek({
    describe("a linked list of integers") {
        val linkedList = LinkedList<Int>()
        on("construction") {
            it("should have zero elements") {
                assertTrue(linkedList.size == 0)
            }
        }
        on("add") {
            val newElement = 175
            linkedList.add(newElement)
            it("should increase in size") {
                assertTrue(linkedList.size == 1)
                assertTrue(linkedList[0] == newElement)
                assertTrue(linkedList.indexOf(newElement) == 0)
            }
            it("should contain the new element") {
                assertTrue(linkedList.contains(newElement))
            }
        }
    }
})