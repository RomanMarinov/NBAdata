package com.dev_marinov.nbadata.model

import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.dev_marinov.nbadata.data.ObjectListPlayers
import com.dev_marinov.nbadata.presentation.FragmentPlayers
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

object RequestDataPlayers {

    var hashMapPlayers: HashMap<Int, ObjectListPlayers> = HashMap()

    fun getData() {

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://free-nba.p.rapidapi.com/players?page=0&per_page=25")
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

                            val firstName = jsonObject.getJSONArray("data").getJSONObject(n).getString("first_name")

                            val lastName = jsonObject.getJSONArray("data").getJSONObject(n).getString("last_name")

                            val position = jsonObject.getJSONArray("data").getJSONObject(n).getString("position")

                            val team = jsonObject.getJSONArray("data").getJSONObject(n).getJSONObject("team")
                                .getString("full_name")

                            val city = jsonObject.getJSONArray("data").getJSONObject(n).getJSONObject("team")
                                .getString("city")

                            val conference = jsonObject.getJSONArray("data").getJSONObject(n).getJSONObject("team")
                                .getString("conference")

                            val division = jsonObject.getJSONArray("data").getJSONObject(n).getJSONObject("team")
                                .getString("division")


                            hashMapPlayers.set(n, ObjectListPlayers(firstName, lastName, position,
                                team, city, conference, division)
                            )

                        }

                        FragmentPlayers.myInterFacePlayers.methodMyInterFacePlayers()

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

    fun getPlayers() = hashMapPlayers
}