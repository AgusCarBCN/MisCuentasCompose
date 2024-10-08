package com.blogspot.agusticar.miscuentasv2.main.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("category_table")
data class Category(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") private val id: Int = 0,
    @ColumnInfo("name") private val name: String
) {

    val categoryId: Int
        get() = id
    val categoryName: String
        get() = name

}