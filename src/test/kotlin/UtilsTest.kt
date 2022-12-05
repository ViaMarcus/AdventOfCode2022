import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UtilsTest {
	@Test
	fun divideTest() {
		val parts = "QWERTYUIOPÅ".divide(3)
		assertEquals(parts[0], "QWE")
		assertEquals(parts[1], "RTY")
		assertEquals(parts[2], "UIO")
		assertEquals(parts[3], "PÅ")
	}
}
