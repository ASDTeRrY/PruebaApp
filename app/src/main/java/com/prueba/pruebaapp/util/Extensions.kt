package com.prueba.pruebaapp.util

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun Context.showToast(msg: String?) = Toast.makeText(this,msg?:"No se pudo procesar la solicitud",
    Toast.LENGTH_LONG).show()

fun RecyclerView.setHorizontalLayout(context: Context) {
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
}