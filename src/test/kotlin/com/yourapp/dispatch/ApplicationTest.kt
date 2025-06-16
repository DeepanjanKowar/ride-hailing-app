package com.yourapp.dispatch

import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

class ApplicationTest {
    @Test
    fun testHealth() = testApplication {
        val response = client.get("/health")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("{\"message\":\"Dispatch Service is running\"}", response.bodyAsText())
    }
}
