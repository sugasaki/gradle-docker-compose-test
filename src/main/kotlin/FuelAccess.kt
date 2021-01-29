import com.github.kittinunf.fuel.core.Parameters
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

object FuelAccess {

    /**
     * API
     * @param path String
     * @param parameters List<Pair<String, Any?>>? - Parameters the optional parameters
     * @return Pair<String, Int>
     */
    fun get(endPoint: String, parameters: Parameters? = null): Pair<String, Int> {
        val (request, response, result) = endPoint
            .httpGet(parameters)
            .responseString()

        return when (result) {
            is Result.Success -> result.get() to response.statusCode
            is Result.Failure -> result.getException().toString() to response.statusCode
        }
    }
}
