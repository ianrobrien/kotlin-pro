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
class GitLabTest : Spek({
    describe("A file to test GitLab CI integration") {
        on("something going wrong") {
            it("should fail CI") {
                assertTrue(false)
            }
        }
    }
})