package com.agilefreaks.gripit.data

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Serializer @Inject constructor() {

    val gsonBuilder: GsonBuilder = GsonBuilder()

    init {
        gsonBuilder.setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    }

    fun <T> serialize(toSerialize: Any, typeToken: TypeToken<T>): String {
        val type = typeToken.type
        return gsonBuilder.create().toJson(toSerialize, type)
    }

    fun <T> deserialize(string: String, typeToken: TypeToken<T>): T {
        val type = typeToken.type
        return gsonBuilder.create().fromJson(string, type)
    }
}
