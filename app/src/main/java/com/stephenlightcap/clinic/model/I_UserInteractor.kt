package com.stephenlightcap.clinic.model

import com.stephenlightcap.clinic.model.model_schema.Appointments
import com.stephenlightcap.clinic.model.model_schema.User

/**
 * Created by Germex on 5/21/2017.
 */
interface I_UserInteractor {

    interface OnFinishedListenerUser {
        fun onFinishedUser(user: User)
    }

    fun saveUser(user: User, listener: OnFinishedListenerUser)

    fun addAppointment(appointment: Appointments, listener: OnFinishedListenerUser)

    fun updateUserName()

    fun updateUserWeight()

    fun updateUserBMI()

    fun updateUserHeight()

    fun getUser(): User
}