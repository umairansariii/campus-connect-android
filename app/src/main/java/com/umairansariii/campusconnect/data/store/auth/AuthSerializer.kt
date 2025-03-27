package com.umairansariii.campusconnect.data.store.auth

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object AuthSerializer: Serializer<AuthState> {
    override val defaultValue: AuthState
        get() = AuthState()

    override suspend fun readFrom(input: InputStream): AuthState {
        return try {
            Json.decodeFromString(
                deserializer = AuthState.serializer(),
                string = input.readBytes().decodeToString(),
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: AuthState, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = AuthState.serializer(),
                value = t,
            ).encodeToByteArray()
        )
    }
}