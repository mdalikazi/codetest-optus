package com.alikazi.codetest.optus

import com.alikazi.codetest.optus.models.Photo
import com.alikazi.codetest.optus.models.User

fun getMockUsers(): List<User> {

    val address1 = User.Address(
        street = "1 York Street",
        suite = "",
        city = "Sydney",
        zipcode = "67208",
        location = User.Geo("-151.9", "31.5")
    )
    val company1 = User.Company(
        name = "ABC Company",
        catchPharse = "This is a great company",
        bs = ""
    )
    val user1 = User(
        id = 1,
        name = "John Doe",
        username = "johndoe",
        email = "johndoe@gmail.com",
        phone = "1234567890",
        website = "www.johndoe.com",
        address = address1,
        company = company1
    )

    val address2 = address1.copy(street = "12 Auburn Road")
    val company2 = company1.copy(name = "XYZ Company")
    val user2 = User(
        id = 2,
        name = "Joe Doan",
        username = "joedoan",
        email = "joedoan@outlook.com",
        phone = "0987654321",
        website = "www.joedoan.com",
        address = address2,
        company = company2
    )

    return listOf(user1, user2)

}

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