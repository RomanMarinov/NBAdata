package com.dev_marinov.nbadata.model

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import com.dev_marinov.nbadata.data.ObjectListGames
import com.dev_marinov.nbadata.presentation.FragmentGames
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

object RequestDataGames {

    var hashMapGames: HashMap<Int, ObjectListGames> = HashMap()

    fun getData() {

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://free-nba.p.rapidapi.com/games?page=0&per_page=25")
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

                            val date = jsonObject.getJSONArray("data").getJSONObject(n).getString("date")

                            val teamHome = jsonObject.getJSONArray("data").getJSONObject(n).getJSONObject("home_team")
                                .getString("full_name")

                            val cityHome = jsonObject.getJSONArray("data").getJSONObject(n).getJSONObject("home_team")
                                .getString("city")

                            val conferenceHome = jsonObject.getJSONArray("data").getJSONObject(n).getJSONObject("home_team")
                                .getString("conference")

                            val divisionHome = jsonObject.getJSONArray("data").getJSONObject(n).getJSONObject("home_team")
                                .getString("division")

                            val scoreHome = jsonObject.getJSONArray("data").getJSONObject(n).getString("home_team_score")
                            val periodHome = jsonObject.getJSONArray("data").getJSONObject(n).getString("period")
                            val seasonHome = jsonObject.getJSONArray("data").getJSONObject(n).getString("season")
                            val statusHome = jsonObject.getJSONArray("data").getJSONObject(n).getString("status")

                            val teamVisitor = jsonObject.getJSONArray("data").getJSONObject(n).getJSONObject("visitor_team")
                                .getString("full_name")

                            val conferenceVisitor = jsonObject.getJSONArray("data").getJSONObject(n).getJSONObject("visitor_team")
                                .getString("conference")

                            val cityVisitor = jsonObject.getJSONArray("data").getJSONObject(n).getJSONObject("visitor_team")
                                .getString("city")

                            val divisionVisitor = jsonObject.getJSONArray("data").getJSONObject(n).getJSONObject("visitor_team")
                                .getString("division")

                            val scoreVisitor = jsonObject.getJSONArray("data").getJSONObject(n).getString("visitor_team_score")


                            hashMapGames[n] = ObjectListGames(date, teamHome, cityHome, conferenceHome, divisionHome,
                                scoreHome, periodHome, seasonHome, statusHome, teamVisitor, conferenceVisitor, cityVisitor,
                                divisionVisitor, scoreVisitor)

                        }

                        FragmentGames.myInterFaceGames.methodMyInterFaceGames()

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

    fun getGames() = hashMapGames
}