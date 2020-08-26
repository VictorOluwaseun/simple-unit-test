package com.decagon.useractivity

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class SharedPrefManagerTest {
//    @Test
//    fun test_clearData(){
////        val sharedPrefs: HashMap<String, String>
//        val sharedPrefs : HashMap<String, String> = HashMap<String, String>()
//
//        sharedPrefs.put("state", "Niger")
//        sharedPrefs.put("city", "Minna")
//
//
////        val sharedPrefManagerObj = SharedPrefManager(hashMapOf())
//
//        assertEquals(SharedPrefManager(sharedPrefs).clearData(), `is`(sharedPrefs.clear()))
//    }



    var sharedPrefs = HashMap<String, String>()
    private var sharedObj = SharedPrefManager(sharedPrefs)
    private val key = "3"
    private val user = "Joi"

    @Before
    fun ini () {
        sharedPrefs["1"] = "User 1"
        sharedPrefs["2"] = "User 2"
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

        val userJson = sharedObj.gson.toJson(user)
        var result = arrayListOf(userJson)
        sharedPrefs[key] = userJson

        assertThat(result, `is`(sharedObj.saveData(user,key)))
    }

    @Test
    fun keepData_user_storeUser() {
        val userJsn = sharedObj.gson.toJson(user)
        sharedPrefs[key] = userJsn

    }

    @Test
    fun test_checkUser(){
        val user = ""
        val getUser = sharedObj.gson.fromJson(user, User::class.java)

        assertThat(getUser, `is`(sharedObj.checkUser(key)))
    }

}