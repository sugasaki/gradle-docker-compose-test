import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class FuelAccessTest {

    @Test
    fun `firstExampleExpectation test`() {
        val url = "http://localhost:8080/firstExampleExpectation"
        val actual = FuelAccess.get(url)
        assertThat(actual.first).isEqualTo("some response1")
        assertThat(actual.second).isEqualTo(200)
    }

    @Test
    fun `secondExampleExpectation test`() {
        val url = "http://localhost:8080/secondExampleExpectation"
        val actual = FuelAccess.get(url)
        assertThat(actual.first).isEqualTo("some response2")
        assertThat(actual.second).isEqualTo(200)
    }
}
