package com.blogspot.agusticar.miscuentasv2

import android.app.Application
import androidx.room.Room
import com.blogspot.agusticar.miscuentasv2.main.data.database.AppDataBase
import dagger.hilt.android.HiltAndroidApp

/**
 * esta clase ClimbingApplication se utiliza como la clase de aplicación principal para una aplicación de Android y
 * está configurada para trabajar con Hilt para la inyección de dependencias.
 */
@HiltAndroidApp
class MisCuentasApp:Application() {

    /*companion object{
        lateinit var database: AppDataBase

    }*/

  /*  override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDataBase::class.java, "app_database").build()  // Initialize database here.
    }*/
}