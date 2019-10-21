package com.example.weknot_android.model.user

class FbUser {
    var id: String? = null
    var name: String? = null
    var photo: String? = null
    var uid: String? = null

    constructor()

    constructor(user: User, uid: String) {
        this.id = user.id
        this.name = user.name
        this.photo = user.picture
        this.uid = uid
    }
}