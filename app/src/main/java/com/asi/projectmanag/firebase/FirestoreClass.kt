package com.asi.projectmanag.firebase

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.asi.projectmanag.activities.BaseActivity
import com.asi.projectmanag.activities.SignUpActivity
import com.asi.projectmanag.models.User
import com.asi.projectmanag.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase

class FirestoreClass{

    private val mfirestore = FirebaseFirestore.getInstance()

    fun registerUser(activity : SignUpActivity, userInfo: User){

        getCurrentUserId()?.let {
            mfirestore.collection(Constants.USERS)
                .document(it)
                .set(userInfo, SetOptions.merge())
                .addOnSuccessListener {
                    Log.d("FirestoreClass", "Logging userInfo before userRegisteredSuccess")
                    Log.d("userinfo2", "$userInfo")

                    Toast.makeText(
                        activity, "you have succesfully registered ", Toast.LENGTH_LONG
                    ).show()

                    FirebaseAuth.getInstance().signOut()


                    activity.userRegisteredSuccess()
                }
                .addOnFailureListener { e->
                    Log.e(activity.javaClass.simpleName,"Error", e)
                    Log.e("FirestoreClass", "Failed to register user: $userInfo")

                }
        }
    }

    private fun getCurrentUserId(): String? {
        return FirebaseAuth.getInstance().currentUser?.uid
    }

}