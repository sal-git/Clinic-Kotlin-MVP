package com.stephenlightcap.clinic.view

import com.stephenlightcap.clinic.model.model_schema.User

/**
 * Created by Germex on 5/21/2017.
 */
interface I_MainView {
    fun updateUserInfo(user: User)

    fun onResume()
}