package com.stephenlightcap.clinic.view

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.view.View
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import com.stephenlightcap.clinic.R

import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.dialog_edit_user.view.edittext_dialog_name

import butterknife.BindView
import butterknife.ButterKnife
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.domain.Event
import com.stephenlightcap.clinic.model.UserInteractor
import com.stephenlightcap.clinic.model.model_schema.Appointments
import com.stephenlightcap.clinic.model.model_schema.Doctor
import com.stephenlightcap.clinic.model.model_schema.User
import com.stephenlightcap.clinic.presenter.MainPresenter
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.dialog_edit_user.*
import kotlinx.android.synthetic.main.dialog_add_appointment.*
import java.util.*

class MainActivity : AppCompatActivity(), I_MainView, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {


    private var presenter: MainPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

//        val fab = findViewById(R.id.fab) as FloatingActionButton
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Update user", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        presenter = MainPresenter(this, UserInteractor())

        //Set listeners
        menu_item.setOnClickListener(this)
        menu_item_2.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()

        updateUserInfo(presenter!!.getUser())

    }

    override fun updateUserInfo(user: User) {
        textview_home_name.text = user.name
        textview_home_bmi.text = user.bmi.toString()
        textview_home_height.text = user.height.toString()
        textview_home_weight.text = user.weight.toString()

        if (user.appointments != null) {
            user.appointments
                    .map { Event(Color.WHITE, it.date, it.reasonForVisit) }
                    .forEach { compactcalendar_view.addEvent(it) }
            compactcalendar_view.invalidate()
        }
        
    }

    /**
     * Handles on clicks for each view in activity.
     */
    override fun onClick(p0: View?) {

        when (p0!!.id) {
            R.id.menu_item -> openEditUserDialog()
            R.id.menu_item_2 -> openAddAppointmentDialog()
        }

    }

    /**
     * Opens a dialog to save appointments
     */
    private fun openAddAppointmentDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_add_appointment)

        //TODO create real doctors
        //Fake for now
        val doc = Doctor("Dr. Steve", "Heart", "image/path", "Working here for X years")
        var date: Long = 0


        dialog.compactcalendar_view_add.setListener(object : CompactCalendarView.CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {
                val events = dialog.compactcalendar_view_add.getEvents(dateClicked)
                Log.d("Calendar:", "Day was clicked: " + dateClicked + " with events " + events)
                date = dateClicked.time
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date) {
                //Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth)
            }
        })


        dialog.button_dialog_appointment_submit.setOnClickListener {
            //Date date, Doctor doctor, String reasonForVisit
            val appointment = Appointments(date, doc, dialog.edittext_dialog_appointment_reason.text.toString())
            presenter!!.addAppointment(appointment)
            dialog.dismiss()
        }

        dialog.button_dialog_appointment_cancel.setOnClickListener { dialog.dismiss() }


        dialog.show()
    }

    /**
     * I'm not sure if Dialogs should be open in presenter or view as it is a view, but
     * contains business logic - to an extent, it still calls presenter to do DB work.
     */
    private fun openEditUserDialog() {
        var dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_edit_user)

        var user = presenter!!.getUser()

        Log.d("Dialog::", "User name - " + user.name)

        dialog.edittext_dialog_name.setText(user.name.toString())
        dialog.editext_dialog_weight.setText(user.weight.toString())
        dialog.edittext_dialog_height.setText(user.height.toString())
        dialog.edittext_dialog_bmi.setText(user.bmi.toString())

        dialog.button_dialog_cancel.setOnClickListener { dialog.dismiss() }

        dialog.button_dialog_editUser.setOnClickListener {
            user = User(0, dialog.edittext_dialog_name.text.toString(), dialog.editext_dialog_weight.text.toString().toDouble(),
                    dialog.edittext_dialog_height.text.toString().toDouble(), dialog.edittext_dialog_bmi.text.toString().toInt())
            presenter!!.saveUser(user)
            dialog.dismiss()
        }

        dialog.show()

    }


    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * Menu buttons.
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_history) {
            // Handle the camera action
        } else if (id == R.id.nav_medication) {

        } else if (id == R.id.nav_doctors) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_profile) {

        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
