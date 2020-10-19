package com.example.karma.repository

import android.app.AlertDialog
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.karma.data.UsuarioSesion
import com.example.karma.recursos.PreferenceProvider
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

object AuthRepository {


    fun registrarse (usuario: UsuarioSesion): Task<AuthResult> {
       return FirebaseAuth.getInstance().createUserWithEmailAndPassword(usuario.correo, usuario.pass)
    }

    fun ingresar (usuario: UsuarioSesion): Task<AuthResult> {
        return FirebaseAuth.getInstance().signInWithEmailAndPassword(usuario.correo, usuario.pass)
    }





}