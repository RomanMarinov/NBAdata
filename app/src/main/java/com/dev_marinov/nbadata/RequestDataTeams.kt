package com.dev_marinov.nbadata

import android.util.Log
import androidx.fragment.app.FragmentActivity
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class RequestDataTeams {



    fun getData(requireActivity: FragmentActivity) {

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
                        val s = response.body?.string()

                        val jsonObject: JSONObject = JSONObject(s)
                        val k = jsonObject.getJSONArray("data").length()

                        for (n in 0 until k) {// until значит что n in [1, 10), 10 будет исключён

                            val fullName = jsonObject.getJSONArray("data").getJSONObject(n).getString("full_name")

                            val city = jsonObject.getJSONArray("data").getJSONObject(n).getString("city")

                            val conference = jsonObject.getJSONArray("data").getJSONObject(n).getString("conference")

                            val division = jsonObject.getJSONArray("data").getJSONObject(n).getString("division")

                            (requireActivity as MainActivity).hashMapTeams.set(n, ObjectListTeams(fullName, city, conference, division))

                        }

                       MainActivity.myInterFaceTeams.methodMyInterFaceTeams()

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