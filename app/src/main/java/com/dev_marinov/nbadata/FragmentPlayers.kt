package com.dev_marinov.nbadata

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

open class FragmentPlayers : Fragment() {

    lateinit var recyclerView: RecyclerView
    var adapterList: AdapterListPlayers? = null
    var myViewGroup: ViewGroup? = null
    var myLayoutInflater: LayoutInflater? = null
    var gridLayoutManager: GridLayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        myViewGroup = container
        myLayoutInflater = inflater
        // отображать желаемую разметку и возвращать view в initInterface .
        // onCreateView() возвращает объект View, который является корневым элементом разметки фрагмента.
        return initInterface()
    }

    // https://stackoverflow.com/questions/54266160/changing-a-recyclerviews-layout-upon-orientation-change-only-works-on-the-first
    fun initInterface(): View? { // удалить android:configChanges из манифеста для применения данной стратегии
        val view: View
        // если уже есть надутый макет, удалить его.
        if (myViewGroup != null) {
            myViewGroup!!.removeAllViewsInLayout() // отличается от removeAllView
        }
        // получить экран ориентации
        val orientation = requireActivity().resources.configuration.orientation
        // раздуть соответствующий макет в зависимости от ориентации экрана
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            view = layoutInflater.inflate(R.layout.fragment_players, myViewGroup, false)

            myRecyclerLayoutManagerAdapter(view, 1, (activity as MainActivity?)?.lastVisibleItem)
        } else {
            view = layoutInflater.inflate(R.layout.fragment_players, myViewGroup, false)

            myRecyclerLayoutManagerAdapter(view, 2, (activity as MainActivity?)?.lastVisibleItem)
        }

        if ((activity as MainActivity?)?.hashMap?.size == 0) {
            Log.e("333", "arrayList.size()=" + (activity as MainActivity?)?.hashMap?.size)

            getData();/// + 20;

        } else {
            Log.e("333", "FragmentHome arrayList.size()  НЕ ПУСТОЙ=")
        }

        return view // в onCreateView() возвращаем объект View, который является корневым элементом разметки фрагмента.
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        Log.e("333", "-зашел FragmentHome onConfigurationChanged-")
        // ДО СОЗДАНИЯ НОВОГО МАКЕТА ПИШЕМ ПЕРЕМЕННЫЕ В КОТОРЫЕ СОХРАНЯЕМ ЛЮБЫЕ ДАННЫЕ ИЗ ТЕКУЩИХ VIEW
        // создать новый макет------------------------------
        val view: View = initInterface()!!
        // ПОСЛЕ СОЗДАНИЯ НОВОГО МАКЕТА ПЕРЕДАЕМ СОХРАНЕННЫЕ ДАННЫЕ В СТАРЫЕ(ТЕ КОТОРЫЕ ТЕКУЩИЕ) VIEW
        // отображать новую раскладку на экране
        myViewGroup?.addView(view)
        super.onConfigurationChanged(newConfig)
    }

     fun myRecyclerLayoutManagerAdapter(view: View, column: Int, lastVisibleItem: Int?) {

         recyclerView = view.findViewById(R.id.recyclerView)

         gridLayoutManager = GridLayoutManager(activity, column)
         recyclerView.layoutManager = gridLayoutManager

         adapterList = AdapterListPlayers(this.requireActivity(), (activity as MainActivity).hashMap, recyclerView)

         recyclerView.adapter = adapterList



    }

    fun getData(){


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


                            (activity as MainActivity).hashMap.set(n, ObjectListPlayers(firstName, lastName, position,
                                team, city, conference, division))

                        }

                        (activity as MainActivity).runOnUiThread {
                            adapterList?.notifyDataSetChanged()
                        }

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