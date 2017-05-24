package com.stephenlightcap.clinic.base

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by Germex on 5/22/2017.
 */
class Clinic : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)
    }


}