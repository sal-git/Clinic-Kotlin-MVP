package com.stephenlightcap.clinic.presenter

import com.stephenlightcap.clinic.model.model_schema.Appointments
import com.stephenlightcap.clinic.model.model_schema.User

/**
 * Created by Germex on 5/21/2017.
 */
interface I_MainPresenter {
    fun onResume()

    fun saveUser(user: User)

    fun onDestroy()

    fun addAppointment(appointment: Appointments)
}