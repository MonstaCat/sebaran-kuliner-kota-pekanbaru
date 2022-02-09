package com.dikacandra.sebaran_kuliner_kota_pekanbaru.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

//Entity annotation to specify the table's name
@Entity(tableName = "restoran")
//Parcelable annotation to make parcelable object
@Parcelize
data class Restoran(
    //PrimaryKey annotation to declare primary key
    //ColumnInfo annotation to specify the column's name
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "namaRestoran") var namaRestoran: String = "",
    @ColumnInfo(name = "lokasi") var lokasi: String = ""
) : Parcelable {
}