package dev.zacsweers.jsonserialization.models.kotlinx_serialization

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import kotlinx.serialization.json.okio.decodeFromBufferedSource
import kotlinx.serialization.json.okio.encodeToBufferedSink
import okio.BufferedSink
import okio.BufferedSource
import java.io.InputStream
import java.io.OutputStream

@Serializable
class Response {

    var users: List<User>? = null

    var status: String? = null

    @SerialName("is_real_json")
    @SerializedName("is_real_json") // Annotation needed for GSON
    @Json(name = "is_real_json")
    var isRealJson: Boolean = false

    fun stringify(serializer: KSerializer<Response>): String {
        return kotlinx.serialization.json.Json.encodeToString(serializer, this)
    }

    fun encode(serializer: KSerializer<Response>, outputStream: OutputStream) {
        kotlinx.serialization.json.Json.encodeToStream(serializer, this, outputStream)
    }

    fun encode(serializer: KSerializer<Response>, sink: BufferedSink) {
        kotlinx.serialization.json.Json.encodeToBufferedSink(serializer, this, sink)
    }

    companion object {
        @JvmStatic
        fun parse(serializer: KSerializer<Response>, str: String): Response {
            return kotlinx.serialization.json.Json.decodeFromString(serializer, str)
        }

        @JvmStatic
        fun parse(serializer: KSerializer<Response>, inputStream: InputStream): Response {
            return kotlinx.serialization.json.Json.decodeFromStream(serializer, inputStream)
        }

        @JvmStatic
        fun parse(serializer: KSerializer<Response>, source: BufferedSource): Response {
            return kotlinx.serialization.json.Json.decodeFromBufferedSource(serializer, source)
        }

        @JvmStatic
        fun underlyingSerializer(): KSerializer<Response> {
            return serializer()
        }
    }
}
