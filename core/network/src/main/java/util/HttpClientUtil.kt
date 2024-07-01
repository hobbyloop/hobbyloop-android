// util/HttpClientUtil.kt
package util

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

object HttpClientUtil {

    suspend inline fun <reified T, reified R> makeRequest(
        client: HttpClient,
        method: HttpMethod,
        endPoint: String,
        accessToken: String? = null,
        requestBody: R? = null
    ): T {
        try {
            val response: HttpResponse = client.request(endPoint) {
                this.method = method
                headers {
                    accessToken?.let {
                        append(HttpHeaders.Authorization, "Bearer $it")
                    }
                    append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                }
                if (requestBody != null) {
                    val requestBodyJson = Json.encodeToString(requestBody)
                    setBody(requestBodyJson)
                }
            }

            if (response.status.value in 400..499) {
                Log.e("HTTP Logger", "${response.status.value} Client Error: ${response.bodyAsText()}")
            } else if (response.status.value >= 500) {
                Log.e("HTTP Logger", "${response.status.value} Server Error: ${response.bodyAsText()}")
            }

            return response.body()
        } catch (e: Exception) {
            Log.e("HTTP Logger", "Request failed: ${e.message}")
            throw e
        }
    }

    suspend inline fun <reified T, reified R> postRequest(
        client: HttpClient,
        endPoint: String,
        requestBody: R,
        accessToken: String? = null
    ): T = makeRequest(client, HttpMethod.Post, endPoint, accessToken, requestBody)

        /*
        suspend inline fun <reified T> getRequest(
            client: HttpClient,
            endPoint: String,
            accessToken: String? = null
        ): T = makeRequest(client, HttpMethod.Get, endPoint, accessToken, null)

        suspend inline fun <reified T> deleteRequest(
            client: HttpClient,
            endPoint: String,
            accessToken: String? = null
        ): T = makeRequest(client, HttpMethod.Delete, endPoint, accessToken, null)
    */
    suspend inline fun <reified T, reified R> putRequest(
        client: HttpClient,
        endPoint: String,
        requestBody: R,
        accessToken: String? = null
    ): T = makeRequest(client, HttpMethod.Put, endPoint, accessToken, requestBody)
}
