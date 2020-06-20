package com.alikazi.codetest.optus.database

import androidx.room.TypeConverter
import com.alikazi.codetest.optus.models.User
import com.google.gson.Gson

class MyTypeConverters {

    @TypeConverter
    fun stringToAddress(jsonString: String): User.Address {
        return Gson().fromJson(jsonString, User.Address::class.java)
    }

    @TypeConverter
    fun addressToString(address: User.Address): String {
        return Gson().toJson(address)
    }

    @TypeConverter
    fun stringToCompany(jsonString: String): User.Company {
        return Gson().fromJson(jsonString, User.Company::class.java)
    }

    @TypeConverter
    fun companyToString(company: User.Company): String {
        return Gson().toJson(company)
    }

    @TypeConverter
    fun geoToString(jsonString: String): User.Geo {
        return Gson().fromJson(jsonString, User.Geo::class.java)
    }

    @TypeConverter
    fun stringToGeo(geo: User.Geo): String {
        return Gson().toJson(geo)
    }

}