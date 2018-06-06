package common.functions

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import java.util.concurrent.ThreadLocalRandom
import kotlin.test.assertEquals

@RunWith(JUnitPlatform::class)
class SortersTest : Spek({
    val TEST_SIZE = 20000

    fun getOrderedList(size: Int): List<Int> {
        var i = 0
        return generateSequence { i++ }.take(size).toList()
    }

    fun getUnorderedList(size: Int): List<Int> {
        val unorderedList = getOrderedList(size).toMutableList()
        for (i in unorderedList) {
            val randomIndex = Math.abs(ThreadLocalRandom.current().nextInt()) % size
            val temp = unorderedList[randomIndex]
            unorderedList[randomIndex] = unorderedList[i]
            unorderedList[i] = temp
        }
        return unorderedList.toList()
    }

    describe("A collection of sorting methods") {
        val unorderedList = getUnorderedList(TEST_SIZE).toMutableList()
        context("1000000 numbers in random sequence") {
            it("should sort a list of integers by bubble sort method") {
                unorderedList.bubbleSort((Comparator { i, j -> i.compareTo(j) }))
                for (i in 0 until TEST_SIZE) {
                    assertEquals(i, unorderedList[i])
                }
            }
        }
    }
})
