package com.asi.projectmanag.models

import java.io.Serializable

data class User (
    val id: String = "",
    var name : String = "",
    val email : String = "",
    val image : String = "",
    val mobile : Long = 0,
    val fcmToken : String =""
) : Serializable{
    override fun toString(): String {
        return "User(id='$id', name='$name', email='$email', image='$image', mobile=$mobile, fcmToken='$fcmToken')"
}


}