package util

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@RunWith(JUnitPlatform::class)
class LinkedListNodeTest : Spek({
    describe("a LinkedListNode") {
        val testValue = "testValue"
        val linkedListNode = LinkedListNode(testValue)
        on("construction") {
            it("should store the initialized value as the value") {
                assertEquals(testValue, linkedListNode.value)
            }
            it("should point to a null next node") {
                assertNull(linkedListNode.next)
            }
        }
        on("setting the next node") {
            it("should support non-null values") {
                val nextNodeValue = "nextNodeValue"
                linkedListNode.next = LinkedListNode(nextNodeValue)
                assertEquals(nextNodeValue, linkedListNode.next?.value)
            }
            it("should support null values") {
                assertNotNull(linkedListNode.next)
                linkedListNode.next = null
                assertNull(linkedListNode.next)
            }
        }
    }
})