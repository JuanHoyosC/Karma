package com.example.karma.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.karma.data.UsuarioSesion
import com.example.karma.recursos.PreferenceProvider
import com.example.karma.repository.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class AuthViewModel: ViewModel() {

    private val authRepository = AuthRepository;


    fun ingresar(usuario: UsuarioSesion): Task<AuthResult> {
       return authRepository.ingresar(usuario)
    }


    fun registrarse(usuario: UsuarioSesion): Task<AuthResult> {
        return authRepository.registrarse(usuario)
    }



}