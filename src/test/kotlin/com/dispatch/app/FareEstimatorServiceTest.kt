package com.dispatch.app

import kotlin.test.Test
import kotlin.test.assertEquals

class FareEstimatorServiceTest {
    private val service = FareEstimatorService()

    @Test
    fun estimateFareGo() {
        val result = service.estimateFare(
            pickupLat = 12.9716,
            pickupLng = 77.5946,
            dropLat = 13.0827,
            dropLng = 80.2707,
            category = RideCategory.GO
        )
        val expectedDuration = result.distanceInKm * 2
        val expectedTotal = 50.0 + 10.0 * result.distanceInKm + 2.0 * expectedDuration
        assertEquals(50.0, result.baseFare)
        assertEquals(expectedDuration, result.durationInMin, 0.0001)
        assertEquals(expectedTotal, result.totalFare, 0.01)
        assertEquals(1.0, result.surgeMultiplier)
    }

    @Test
    fun estimateFareSuv() {
        val result = service.estimateFare(
            pickupLat = 12.9716,
            pickupLng = 77.5946,
            dropLat = 13.0827,
            dropLng = 80.2707,
            category = RideCategory.SUV
        )
        val expectedDuration = result.distanceInKm * 2
        val expectedTotal = 100.0 + 20.0 * result.distanceInKm + 4.0 * expectedDuration
        assertEquals(100.0, result.baseFare)
        assertEquals(expectedDuration, result.durationInMin, 0.0001)
        assertEquals(expectedTotal, result.totalFare, 0.01)
        assertEquals(1.0, result.surgeMultiplier)
    }
}
