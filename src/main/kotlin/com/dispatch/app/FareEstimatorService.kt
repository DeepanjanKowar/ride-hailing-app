package com.dispatch.app

import kotlin.math.*

enum class RideCategory {
    GO,
    SUV
}

data class FareEstimate(
    val distanceInKm: Double,
    val durationInMin: Double,
    val baseFare: Double,
    val distanceFare: Double,
    val timeFare: Double,
    val surgeMultiplier: Double,
    val totalFare: Double
)

class FareEstimatorService {
    fun estimateFare(
        pickupLat: Double,
        pickupLng: Double,
        dropLat: Double,
        dropLng: Double,
        category: RideCategory
    ): FareEstimate {
        val distance = haversine(pickupLat, pickupLng, dropLat, dropLng)
        val duration = distance * 2
        val rates = when (category) {
            RideCategory.GO -> Triple(50.0, 10.0, 2.0)
            RideCategory.SUV -> Triple(100.0, 20.0, 4.0)
        }
        val (base, perKm, perMin) = rates
        val distanceFare = perKm * distance
        val timeFare = perMin * duration
        val surgeMultiplier = 1.0
        val total = (base + distanceFare + timeFare) * surgeMultiplier

        return FareEstimate(
            distanceInKm = distance,
            durationInMin = duration,
            baseFare = base,
            distanceFare = distanceFare,
            timeFare = timeFare,
            surgeMultiplier = surgeMultiplier,
            totalFare = total
        )
    }

    private fun haversine(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371.0
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).pow(2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return R * c
    }
}
