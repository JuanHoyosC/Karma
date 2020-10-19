package com.example.karma.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.karma.R
import com.example.karma.data.Usuario
import com.example.karma.data.UsuarioSesion
import com.example.karma.viewModel.AuthViewModel
import com.example.karma.viewModel.UsuarioViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : Fragment() {

    val authViewModel: AuthViewModel by activityViewModels()
    val usuarioViewModel: UsuarioViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_login, container, false)

        view.btnRegistrarse.setOnClickListener {

            if(view.correo.text.isNotEmpty() && view.pass.text.isNotEmpty()){
                val correo = view.correo.text.toString();
                val pass = view.pass.text.toString();
                val usuario = UsuarioSesion(correo,  pass)
                authViewModel.registrarse(usuario).addOnCompleteListener {
                    if(it.isSuccessful){
                        view.success.text = "Se ha registrado correctamente";
                        view.error.text = "";
                        val correo = view.correo.text.toString();
                        usuarioViewModel.crearUsuario(correo)
                    }else{
                        view.error.text = "Ha ocurrido un error al registrarse";
                    }
                }
            }
        }

        val navController = findNavController()
        view.btnIngresar.setOnClickListener {
            if(view.correo.text.isNotEmpty() && view.pass.text.isNotEmpty()){
                val correo = view.correo.text.toString();
                val pass = view.pass.text.toString();
                val usuario = UsuarioSesion(correo,  pass)
                authViewModel.ingresar(usuario).addOnCompleteListener {
                    if(it.isSuccessful){
                        view.success.text = "Ha ingresado correctamente";
                        view.error.text = "";
                        navController.navigate(R.id.action_loginFragment_to_homeFragment)

                    }else{
                        view.success.text = "";
                        view.error.text = "Ha ocurrido un error al ingresar";
                    }
                }
            }
        }
        return view
    }




}