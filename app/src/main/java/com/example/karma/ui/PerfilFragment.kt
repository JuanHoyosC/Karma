package com.example.karma.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.karma.R
import com.example.karma.viewModel.UsuarioViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_perfil.*
import kotlinx.android.synthetic.main.fragment_perfil.view.*


class PerfilFragment : Fragment() {
    val usuarioViewModel: UsuarioViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

            usuarioViewModel.obtenerUsuario()
            usuarioViewModel.usuarioRepository.karma.observe(viewLifecycleOwner, Observer { karma ->
                view.estadoPerfil.text = "Cantidad de karma: $karma ";
            })

        usuarioViewModel.usuarioRepository.movimiento.observe(viewLifecycleOwner, Observer { movimientos ->
            view.movimiento1.text = movimientos[movimientos.size - 1]
            view.movimiento2.text = movimientos[movimientos.size - 2]
            view.movimiento3.text = movimientos[movimientos.size - 3]
        })

        return view
    }


}