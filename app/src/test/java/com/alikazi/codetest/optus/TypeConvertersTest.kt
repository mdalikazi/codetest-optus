package com.alikazi.codetest.optus

import com.alikazi.codetest.optus.database.MyTypeConverters
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import org.junit.Test

class TypeConvertersTest {

    private val addressJson = Gson().toJson(getMockAddress1())
    private val companyJson = Gson().toJson(getMockCompany1())

    @Test
    fun stringToAddressTest() {
        assertEquals(getMockAddress1(), MyTypeConverters().stringToAddress(addressJson))
    }

    @Test
    fun addressToStringTest() {
        assertEquals(addressJson.toString(), MyTypeConverters().addressToString(getMockAddress1()))
    }

    @Test
    fun stringToCompanyTest() {
        assertEquals(getMockCompany1(), MyTypeConverters().stringToCompany(companyJson.toString()))
    }

    @Test
    fun companyToStringTest() {
        assertEquals(companyJson.toString(), MyTypeConverters().companyToString(getMockCompany1()))
    }

}