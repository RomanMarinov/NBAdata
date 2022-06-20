package com.dev_marinov.nbadata.presentation.games

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev_marinov.nbadata.data.ObjectListGames
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class GamesViewModel : ViewModel() {

    private val _games: MutableLiveData<HashMap<Int, ObjectListGames>> = MutableLiveData()
    val games: LiveData<HashMap<Int, ObjectListGames>> = _games

    init {
        getGames()
    }

    private fun getGames(){
        Log.e("333", "-getGames=")
    //    viewModelScope.launch(Dispatchers.IO) {

            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://free-nba.p.rapidapi.com/games?page=0&per_page=25")
                .get()
                .addHeader("X-RapidAPI-Host", "free-nba.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "3f235a9f12msh754db7d5868e472p168e1djsn3c41eccf8098")
                .build()

          //  try {
  //              suspend {
                    client.newCall(request).enqueue(object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            Log.e("333", "-onFailure=")
                        }

                        @Throws(IOException::class)
                        override fun onResponse(call: Call, response: Response) {
                            Log.e("333", "-onResponse=" + response.body)

                            val hashMap: HashMap<Int, ObjectListGames> = HashMap()

                            try {

                                val jsonObject = JSONObject(response.body?.string())
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


                                    hashMap[n] = ObjectListGames(
                                        date = date,
                                        teamHome = teamHome,
                                        cityHome =cityHome,
                                        conferenceHome = conferenceHome,
                                        divisionHome = divisionHome,
                                        scoreHome = scoreHome,
                                        periodHome = periodHome,
                                        seasonHome = seasonHome,
                                        statusHome = statusHome,
                                        teamVisitor = teamVisitor,
                                        conferenceVisitor = conferenceVisitor,
                                        cityVisitor = cityVisitor,
                                        divisionVisitor = divisionVisitor,
                                        scoreVisitor = scoreVisitor)

                                }

                                _games.postValue(hashMap)

                            }
                            catch (e: JSONException) {
                                Log.e("333", "-try catch низ=" + e)
                            }
                        }
                    })

   //             }
//            }
//            catch (e: JSONException) {
//                Log.e("333", "-try catch низ=" + e)
//            }

   //     }


    }

}

