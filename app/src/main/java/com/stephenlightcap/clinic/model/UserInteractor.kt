package com.stephenlightcap.clinic.model

import android.util.Log
import com.stephenlightcap.clinic.model.model_schema.User
import io.realm.Realm
import android.os.Handler
import com.stephenlightcap.clinic.model.model_schema.Appointments
import com.stephenlightcap.clinic.model.model_schema.Doctor

/**
 * Created by Germex on 5/21/2017.
 */
class UserInteractor : I_UserInteractor {

    private val realm = Realm.getDefaultInstance()

    override fun saveUser(user: User, listener: I_UserInteractor.OnFinishedListenerUser) {

        /**
         * This thread is to simulate network time / saving.
         * Listener acts as a callback to update any views.
         */
        Handler().postDelayed({
            realm.executeTransaction { realm -> realm.copyToRealmOrUpdate(user) }
            listener.onFinishedUser(user)
        }, 2000)

    }

    override fun updateUserName() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUserWeight() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUserBMI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUserHeight() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUser(): User {
        //int id, String name, double weight, double height

        val query = realm.where(User::class.java)
        val realmResults = query.findAll()

        var defaultUser = User()

        if (realmResults.isEmpty()) {
            //String name, String study, String image, String description

            realm.executeTransaction { realm ->

                defaultUser = User(0, "Stephen", 0.0, 0.0, 0)
                val doc = realm.copyToRealm(Doctor("test", "test", "test", "test"))
                val appointment = realm.copyToRealm(Appointments(1433701251000L, doc, "test"))

                //defaultUser.appointments.add(appointment)

                realm.copyToRealmOrUpdate(defaultUser)


            }



            return defaultUser
        } else {
            return realmResults.first()
        }

    }

    override fun addAppointment(appointment: Appointments, listener: I_UserInteractor.OnFinishedListenerUser) {
        val user: User = getUser()

        /**
         * This thread is to simulate network time / saving.
         * Listener acts as a callback to update any views.
         */
        Handler().postDelayed({
            realm.executeTransaction { realm ->
                user.appointments.add(appointment)
                realm.copyToRealmOrUpdate(user)
            }
            listener.onFinishedUser(user)
        }, 2000)
    }


}