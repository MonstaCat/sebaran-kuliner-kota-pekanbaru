package com.dikacandra.sebaran_kuliner_kota_pekanbaru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dikacandra.sebaran_kuliner_kota_pekanbaru.db.KulinerRoomDatabase
import com.dikacandra.sebaran_kuliner_kota_pekanbaru.model.Restoran
import com.dikacandra.sebaran_kuliner_kota_pekanbaru.db.RestoranDao
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    val EDIT_RESTORAN_EXTRA = "edit_restoran_extra"
    private lateinit var restoran: Restoran
    private var isUpdate = false
    private lateinit var database: KulinerRoomDatabase
    private lateinit var dao: RestoranDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        database = KulinerRoomDatabase.getDatabase(applicationContext)
        dao = database.getRestoranDao()

        if (intent.getParcelableExtra<Restoran>(EDIT_RESTORAN_EXTRA) != null){
            button_delete.visibility = View.VISIBLE
            isUpdate = true
            restoran = intent.getParcelableExtra(EDIT_RESTORAN_EXTRA)!!
            edit_text_namaRestoran.setText(restoran.namaRestoran)
            edit_text_lokasi.setText(restoran.lokasi)

            edit_text_namaRestoran.setSelection(restoran.namaRestoran.length)

        }

        button_save.setOnClickListener {
            val namaRestoran = edit_text_namaRestoran.text.toString()
            val lokasi = edit_text_lokasi.text.toString()

            if (namaRestoran.isEmpty() && lokasi.isEmpty()){
                Toast.makeText(applicationContext, "Restoran cannot be empty", Toast.LENGTH_SHORT).show()
            }
            else{
                if (isUpdate){
                    saveRestoran(Restoran(id = restoran.id, namaRestoran = namaRestoran, lokasi = lokasi))
                }
                else{
                    saveRestoran(Restoran(namaRestoran = namaRestoran, lokasi = lokasi))
                }
            }

            finish()
        }

        button_delete.setOnClickListener {
            deleteRestoran(restoran)
            finish()
        }

    }

    private fun saveRestoran(restoran: Restoran){

        if (dao.getById(restoran.id).isEmpty()){

            dao.insert(restoran)
        }
        else{

            dao.update(restoran)
        }

        Toast.makeText(applicationContext, "Restoran saved", Toast.LENGTH_SHORT).show()

    }

    private fun deleteRestoran(restoran: Restoran){
        dao.delete(restoran)
        Toast.makeText(applicationContext, "Restoran removed", Toast.LENGTH_SHORT).show()
    }
}