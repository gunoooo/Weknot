package com.example.weknot_android.network.request

data class SignUpRequest(var id: String,
                         var pw: String,
                         var name: String,
                         var birth: String,
                         var gender: String,
                         var phoneNumber: String,
                         var intro: String ) {
    constructor() : this("", "", "", "", "", "" ,"")
}