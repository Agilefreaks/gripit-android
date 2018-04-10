package com.agilefreaks.gripit.data

import com.agilefreaks.gripit.data.entity.RouteEntity
import com.google.gson.reflect.TypeToken
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SerializerTest {
    private val JSON: String =
            "[ {" +
                    "    \"id\": 1," +
                    "    \"name\": \"Test Name\"," +
                    "    \"image_location\": \"file:///picture.jpg\"," +
                    "    \"grade\": \"A\"," +
                    "    \"type\": [\"Easy\"]," +
                    "    \"add_date\": \"June 2020\"," +
                    "    \"route_setter\": \"Climber\"," +
                    "    \"notes\": [\"note\"]" +
                    "}," +
                    "{ " +
                    "    \"id\": 2," +
                    "    \"name\": \"Test Name2\"," +
                    "    \"image_location\": \"file:///picture.jpg2\"," +
                    "    \"grade\": \"A2\"," +
                    "    \"type\": [\"Easy\"]," +
                    "    \"add_date\": \"June 2022\"," +
                    "    \"route_setter\": \"Climber\"," +
                    "    \"notes\": [\"note\"]" +
                    "}" +
                    "]"

    lateinit var serializer: Serializer

    @Before
    fun setUp() {
        serializer = Serializer()
    }

    @Test
    fun testSerialize() {
        val routes1: List<RouteEntity> = serializer.deserialize(JSON, object : TypeToken<List<RouteEntity>>(){})
        val json = serializer.serialize(routes1, object: TypeToken<List<RouteEntity>>(){})
        val routes2 = serializer.deserialize(json, object : TypeToken<List<RouteEntity>>(){})

        assertThat(routes1, `is`(routes2))
        assertThat(routes1[0].id, `is`(not(routes2[1].id)))
        assertThat(routes1[0].routeSetter, `is`(equalTo(routes2[1].routeSetter)))
        assertThat(routes1[0].type, `is`(equalTo(routes2[1].type)))
    }

    @Test
    fun testDeserialize() {
        val routes: List<RouteEntity> = serializer.deserialize(JSON, object : TypeToken<List<RouteEntity>>(){})

        assertThat(routes[0].id, `is`(1))
        assertThat(routes[0].name, `is`("Test Name"))
        assertThat(routes[0].type[0], `is`("Easy"))
        assertThat(routes[1].notes, `is`(listOf("note")))
    }
}