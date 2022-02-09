package com.dikacandra.sebaran_kuliner_kota_pekanbaru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dikacandra.sebaran_kuliner_kota_pekanbaru.db.KulinerRoomDatabase
import com.dikacandra.sebaran_kuliner_kota_pekanbaru.db.MenuDao
import com.dikacandra.sebaran_kuliner_kota_pekanbaru.model.Restoran
import com.dikacandra.sebaran_kuliner_kota_pekanbaru.db.RestoranDao
import com.dikacandra.sebaran_kuliner_kota_pekanbaru.model.Menu
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_edit.button_delete
import kotlinx.android.synthetic.main.activity_edit.button_save
import kotlinx.android.synthetic.main.activity_edit.edit_text_namaRestoran
import kotlinx.android.synthetic.main.activity_edit_menu.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailRestoranActivity : AppCompatActivity() {

    val EDIT_MENU_EXTRA = "edit_menu_extra"
    private lateinit var menu: Menu
    private var isUpdate = false
    private lateinit var database: KulinerRoomDatabase
    private lateinit var dao: MenuDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_restoran)


    }
}