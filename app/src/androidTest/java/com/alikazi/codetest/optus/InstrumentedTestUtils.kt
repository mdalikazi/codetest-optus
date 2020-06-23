package com.alikazi.codetest.optus

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers

fun <VH : RecyclerView.ViewHolder> clickOnFirstItemInRecyclerView(recyclerViewId: Int) {
    Espresso.onView(ViewMatchers.withId(recyclerViewId))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        .perform(
            RecyclerViewActions
                .actionOnItemAtPosition<VH>
                    (0, ViewActions.click()))
}

fun isRecyclerViewNotEmpty(recyclerView: RecyclerView): Boolean {
    return recyclerView.adapter != null && recyclerView.adapter?.itemCount != 0
}