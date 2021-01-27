import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

object FuelAccess {

    fun get(url: String): Pair<String, Int> {
        val (request, response, result) = url
            .httpGet()
            .responseString()

        return when (result) {
            is Result.Success -> result.get() to response.statusCode
            is Result.Failure -> result.getException().toString() to response.statusCode
        }
    }
}
