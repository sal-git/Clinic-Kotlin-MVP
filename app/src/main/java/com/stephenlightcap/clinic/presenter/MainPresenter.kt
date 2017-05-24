package com.stephenlightcap.clinic.presenter

import com.stephenlightcap.clinic.model.*
import com.stephenlightcap.clinic.model.model_schema.Appointments
import com.stephenlightcap.clinic.model.model_schema.User
import com.stephenlightcap.clinic.view.*

/**
 * Created by Germex on 5/21/2017.
 */
class MainPresenter(view: I_MainView, interactor: I_UserInteractor) : I_MainPresenter, I_UserInteractor.OnFinishedListenerUser {

    var view: I_MainView? = null
    var interactor: I_UserInteractor? = null

    /**
     * init is for the main constructor that is presented in the class header.
     */
    init {
        this.view = view
        this.interactor = interactor
    }

    override fun onResume() {
        //Safe call
        view?.updateUserInfo(interactor!!.getUser())
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getUser() : User {
        return interactor!!.getUser()
    }

    override fun saveUser(user: User) {
        interactor!!.saveUser(user, this)
    }

    override fun addAppointment(appointment: Appointments) {
        interactor!!.addAppointment(appointment, this)
    }

    /**
     * Updates views with the user - user contains
     * all changes and appointments.
     */
    override fun onFinishedUser(user: User) {
        if (view != null) {
            view!!.updateUserInfo(user)
        }
    }

}