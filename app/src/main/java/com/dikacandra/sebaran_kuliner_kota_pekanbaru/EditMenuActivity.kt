package com.dikacandra.sebaran_kuliner_kota_pekanbaru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dikacandra.sebaran_kuliner_kota_pekanbaru.db.KulinerRoomDatabase
import com.dikacandra.sebaran_kuliner_kota_pekanbaru.db.MenuDao
import com.dikacandra.sebaran_kuliner_kota_pekanbaru.model.Menu
import kotlinx.android.synthetic.main.activity_edit.button_delete
import kotlinx.android.synthetic.main.activity_edit.button_save
import kotlinx.android.synthetic.main.activity_edit.edit_text_namaRestoran
import kotlinx.android.synthetic.main.activity_edit_menu.*

class EditMenuActivity : AppCompatActivity() {

    val EDIT_MENU_EXTRA = "edit_menu_extra"
    private lateinit var menu: Menu
    private var isUpdate = false
    private lateinit var database: KulinerRoomDatabase
    private lateinit var dao: MenuDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_menu)

        database = KulinerRoomDatabase.getDatabase(applicationContext)
        dao = database.getMenuDao()

        if (intent.getParcelableExtra<Menu>(EDIT_MENU_EXTRA) != null){
            button_delete.visibility = View.VISIBLE
            isUpdate = true
            menu = intent.getParcelableExtra(EDIT_MENU_EXTRA)!!
            edit_text_namaRestoran.setText(menu.namaRestoran)
            edit_text_namaMenu.setText(menu.namaMenu)
            edit_text_hargaMenu.setText(menu.hargaMenu)
            edit_text_jenisMenu.setText(menu.jenisMenu)

            edit_text_namaRestoran.setSelection(menu.namaMenu.length)

        }

        button_save.setOnClickListener {
            val namaRestoran = edit_text_namaRestoran.text.toString()
            val namaMenu = edit_text_namaMenu.text.toString()
            val hargaMenu = edit_text_hargaMenu.text.toString()
            val jenisMenu = edit_text_jenisMenu.text.toString()

            if (namaRestoran.isEmpty() && namaMenu.isEmpty() && hargaMenu.isEmpty() && jenisMenu.isEmpty()){
                Toast.makeText(applicationContext, "Menu cannot be empty", Toast.LENGTH_SHORT).show()
            }
            else{
                if (isUpdate){
                    saveMenu(Menu(id = menu.id, namaRestoran = namaRestoran, namaMenu = namaMenu, hargaMenu = hargaMenu, jenisMenu = jenisMenu))
                }
                else{
                    saveMenu(Menu(namaRestoran = namaRestoran, namaMenu = namaMenu, hargaMenu = hargaMenu, jenisMenu = jenisMenu))
                }
            }

            finish()
        }

        button_delete.setOnClickListener {
            deleteMenu(menu)
            finish()
        }

    }

    private fun saveMenu(menu: Menu){

        if (dao.getById(menu.id).isEmpty()){

            dao.insert(menu)
        }
        else{

            dao.update(menu)
        }

        Toast.makeText(applicationContext, "Menu saved", Toast.LENGTH_SHORT).show()

    }

    private fun deleteMenu(menu: Menu){
        dao.delete(menu)
        Toast.makeText(applicationContext, "Menu removed", Toast.LENGTH_SHORT).show()
    }
}