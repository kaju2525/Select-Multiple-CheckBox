package com.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_types_goods.*
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {


    lateinit var myAdapter: MyAdapter
    lateinit var list: ArrayList<Model>
    var data: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_types_goods)


        btn_ok.setOnClickListener({
            selectItem()
        })

        list_types_goods.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        list = ArrayList()

        list.add(Model(23,"Banana",false))
        list.add(Model(24,"Apple",false))
        list.add(Model(13,"Mango",false))
        list.add(Model(53,"Graps",false))
        list.add(Model(93,"Lichi",false))
        list.add(Model(80,"Oringe",false))

        myAdapter = MyAdapter(this@MainActivity, list)
        list_types_goods.adapter = myAdapter
        setupSearchView()
    }

  private fun selectItem(){

      val arrsList=ArrayList<JSONObject>()
      for(i in 0 until list.size){
          val v:Model=list[i]
          if(v.isSelected){
              val json=JSONObject()
              json.put("ID",v.ids)
              json.put("Name",v.name)
              arrsList.add(json)
          }
      }
      Toast.makeText(applicationContext," "+arrsList,Toast.LENGTH_SHORT).show()
      Log.d("TAGS",""+arrsList)

    }

    private fun setupSearchView() {
        search_view.setIconifiedByDefault(false);
        search_view.setOnQueryTextListener(this);
        search_view.isSubmitButtonEnabled = true;
        search_view.queryHint = "Search Here";
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false;
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        myAdapter.filter(newText!!);
        return true;
    }
}
