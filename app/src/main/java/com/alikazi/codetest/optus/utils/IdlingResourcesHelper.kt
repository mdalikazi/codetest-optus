package com.alikazi.codetest.optus.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResourcesHelper {

    private val countingIdlingResource = CountingIdlingResource("GLOBAL")

    fun getIdlingResource(): IdlingResource = countingIdlingResource

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }

}