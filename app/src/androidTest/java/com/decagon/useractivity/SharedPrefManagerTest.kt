package com.decagon.useractivity

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class SharedPrefManagerTest {

    var sharedPrefs = HashMap<String, String>()
    private var sharedObj = SharedPrefManager(sharedPrefs)
    private val state = "Niger"
    private val city = "Minna"

    @Before
    fun ini () {
        sharedPrefs["state"] = "Lagos"
        sharedPrefs["city"] = "Ikeja"
    }

    @Test
    fun clearData_clear_returnCleared() {

        var result = sharedPrefs.clear()

        var obj = SharedPrefManager(sharedPrefs)

        assertThat(obj.clearData(), `is`(result))


    }

    @Test
    fun clearByKey_userPresent_returnUser() {

        val key = "2"
        val data = sharedPrefs.containsKey(key)

        val sharedPMObj = SharedPrefManager(sharedPrefs).clearByKey<Boolean>(key)

        assertThat(data, `is`(sharedPMObj))

    }

    @Test
    fun clearByKey_userMissing_returnFalse() {

        val key = "3"
        val data = sharedPrefs.containsKey(key)

        val sharedPMObj = SharedPrefManager(sharedPrefs).clearByKey<Boolean>(key)

        assertThat(data, `is`(sharedPMObj))

    }



    @Test
    fun saveData_data_returnArrayList() {

        val userJson = sharedObj.gson.toJson(city)
        var result = arrayListOf(userJson)
        sharedPrefs[state] = userJson

        assertThat(result, `is`(sharedObj.saveData(city,state)))
    }

    @Test
    fun keepData_user_storeUser() {
        val userJsn = sharedObj.gson.toJson(city)
        sharedPrefs[state] = userJsn

    }

    @Test
    fun test_checkUser(){
        val user = ""
        val getUser = sharedObj.gson.fromJson(user, User::class.java)

        assertThat(getUser, `is`(sharedObj.checkUser(state)))
    }

}