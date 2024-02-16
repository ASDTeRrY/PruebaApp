package com.prueba.pruebaapp.util

import android.content.Context
import android.widget.Toast
fun Context.toast(msg: String?) = Toast.makeText(this,msg?:"No se pudo procesar la solicitud",
    Toast.LENGTH_LONG).show()