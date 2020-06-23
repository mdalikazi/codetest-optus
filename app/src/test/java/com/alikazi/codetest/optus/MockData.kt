package com.alikazi.codetest.optus

import com.alikazi.codetest.optus.models.Photo
import com.alikazi.codetest.optus.models.User

fun getMockUsers(): List<User> {

    val user1 = User(
        id = 1,
        name = "John Doe",
        username = "johndoe",
        email = "johndoe@gmail.com",
        phone = "1234567890",
        website = "www.johndoe.com",
        address = getMockAddress1(),
        company = getMockCompany1()
    )
    val user2 = User(
        id = 2,
        name = "Joe Doan",
        username = "joedoan",
        email = "joedoan@outlook.com",
        phone = "0987654321",
        website = "www.joedoan.com",
        address = getMockAddress2(),
        company = getMockCompany2()
    )

    return listOf(user1, user2)

}

fun getMockCompany1(): User.Company =
    User.Company(
        name = "ABC Company",
        catchPharse = "This is a great company",
        bs = ""
    )

fun getMockCompany2() = getMockCompany1().copy(name = "XYZ Company")

fun getMockAddress1(): User.Address =
    User.Address(
        street = "1 York Street",
        suite = "",
        city = "Sydney",
        zipcode = "67208",
        location = User.Geo("-151.9", "31.5")
    )

fun getMockAddress2() = getMockAddress1().copy(street = "12 Auburn Road")

fun getMockPhotos(): List<Photo> {
    val photo1 = Photo(
        id = 1,
        albumId = 1,
        title = "This is first photo",
        url = "https://via.placeholder.com/600/92c952",
        thumbnailUrl = "https://via.placeholder.com/150/92c952"
    )

    val photo2 = Photo(
        id = 2,
        albumId = 1,
        title = "This is second photo",
        url = "https://via.placeholder.com/600/771796",
        thumbnailUrl = "https://via.placeholder.com/150/771796"
    )

    return listOf(photo1, photo2)

}