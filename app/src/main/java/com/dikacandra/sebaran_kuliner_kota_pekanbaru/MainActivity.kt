package com.dikacandra.sebaran_kuliner_kota_pekanbaru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.dikacandra.sebaran_kuliner_kota_pekanbaru.db.KulinerRoomDatabase
import com.dikacandra.sebaran_kuliner_kota_pekanbaru.model.Restoran
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getRestoranData()

        button_tambah_menu.setOnClickListener {
            startActivity(Intent(this, EditActivity::class.java))
        }

    }

    private fun getRestoranData(){
        val database = KulinerRoomDatabase.getDatabase(applicationContext)
        val dao = database.getRestoranDao()
        val listItems = arrayListOf<Restoran>()
        listItems.addAll(dao.getAll())
        setupRecyclerView(listItems)
        if (listItems.isNotEmpty()){
            text_view_restoran_empty.visibility = View.GONE
        }
        else{
            text_view_restoran_empty.visibility = View.VISIBLE
        }
    }

    private fun setupRecyclerView(listItems: ArrayList<Restoran>){
        recycler_view_main.apply {
            adapter = KulinerAdapter(listItems, object : KulinerAdapter.RestoranListener{
                override fun OnItemClicked(restoran: Restoran) {
                    val intent = Intent(this@MainActivity, EditActivity::class.java)
                    intent.putExtra(EditActivity().EDIT_RESTORAN_EXTRA, restoran)
                    startActivity(intent)
                }
            })

            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        getRestoranData()
    }
}