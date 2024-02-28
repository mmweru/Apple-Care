package com.example.applecare.presentation.sign_in

data class SignInResult(
    val data: com.example.applecare.presentation.sign_in.UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?
)
