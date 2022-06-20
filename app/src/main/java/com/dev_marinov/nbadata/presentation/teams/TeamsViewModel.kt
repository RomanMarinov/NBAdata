package com.dev_marinov.nbadata.presentation.teams

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev_marinov.nbadata.data.ObjectListTeams
import com.dev_marinov.nbadata.model.teams.RequestDataTeams
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class TeamsViewModel : ViewModel() {

    private var _teams: MutableLiveData<HashMap<Int, ObjectListTeams>> = MutableLiveData()
    val teams: LiveData<HashMap<Int, ObjectListTeams>> = _teams

    init {
        getTeams()
    }

    private fun getTeams(){
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://free-nba.p.rapidapi.com/teams?page=0")
            .get()
            .addHeader("X-RapidAPI-Host", "free-nba.p.rapidapi.com")
            .addHeader("X-RapidAPI-Key", "3f235a9f12msh754db7d5868e472p168e1djsn3c41eccf8098")
            .build()

        try {
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("333", "-onFailure=")
                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {
                    //Log.e("333", "-onResponse=" + response.body?.string())
                    try {
                        val hashMap: HashMap<Int, ObjectListTeams> = HashMap()

                        val jsonObject = JSONObject(response.body?.string())
                        val k = jsonObject.getJSONArray("data").length()

                        for (n in 0 until k) {// until значит что n in [1, 10), 10 будет исключён

                            val fullName = jsonObject.getJSONArray("data").getJSONObject(n).getString("full_name")
                            val city = jsonObject.getJSONArray("data").getJSONObject(n).getString("city")
                            val conference = jsonObject.getJSONArray("data").getJSONObject(n).getString("conference")
                            val division = jsonObject.getJSONArray("data").getJSONObject(n).getString("division")

                            hashMap[n] = ObjectListTeams(
                                fullName = fullName,
                                city = city,
                                conference = conference,
                                division = division)
                        }
                            _teams.postValue(hashMap)
                    }
                    catch (e: JSONException) {
                        Log.e("333", "-try catch низ=" + e)
                    }

                }
            })
        }
        catch (e: JSONException) {
            Log.e("333", "-try catch низ=" + e)
        }
    }
}