package util

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import java.util.concurrent.ThreadLocalRandom
import kotlin.test.assertTrue

@RunWith(JUnitPlatform::class)
class BinarySearchTreeTest : Spek({
    describe("a binary search tree integers") {
        lateinit var bst: BinarySearchTree<Int>
        val TEST_SIZE = 100
        beforeEachTest {
            bst = BinarySearchTree()
            bst.addAll(generateSequence { ThreadLocalRandom.current().nextInt() }.take(TEST_SIZE))
        }
        on("construction") {
            val localBst = BinarySearchTree<Int>()
            it("should have zero elements") {
                assertTrue(localBst.size == 0)
            }
        }
        on("add") {
            it("should increase in size") {
                assertTrue(bst.size == TEST_SIZE)
                bst.add(0)
                assertTrue(bst.size == TEST_SIZE + 1)
            }
        }
    }
})