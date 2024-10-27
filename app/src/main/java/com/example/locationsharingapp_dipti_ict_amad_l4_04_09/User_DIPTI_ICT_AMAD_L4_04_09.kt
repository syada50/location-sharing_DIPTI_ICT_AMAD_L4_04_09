package com.example.locationsharingapp_dipti_ict_amad_l4_04_09

import com.google.firebase.firestore.PropertyName

data class User_DIPTI_ICT_AMAD_L4_04_09(
    val userId: String,
    @get:PropertyName("displayName")
    @set:PropertyName("displayName")
    var displayName: String = "",

    @get:PropertyName("email")
    @set:PropertyName("email")
    var email: String = "",

    @get:PropertyName("location")
    @set:PropertyName("location")
    var location: String = ""
) {
    // No-argument constructor
    constructor() : this("", "", "")
}
