package com.D121211074.makeup

import android.app.Application
import com.D121211074.makeup.data.AppCointainer
import com.D121211074.makeup.data.DefaultAppContainer

class MyApplication: Application() {
    lateinit var container: AppCointainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}