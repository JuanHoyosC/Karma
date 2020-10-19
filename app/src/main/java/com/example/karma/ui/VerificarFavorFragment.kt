package com.example.karma.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.karma.R
import com.example.karma.viewModel.UsuarioViewModel
import kotlinx.android.synthetic.main.fragment_verificar_favor.*
import kotlinx.android.synthetic.main.fragment_verificar_favor.view.*


class VerificarFavorFragment : Fragment() {
    val usuarioViewModel: UsuarioViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_verificar_favor, container, false)
        usuarioViewModel.obtenerUsuario()
        usuarioViewModel.usuarioRepository.estadoFavor.observe(viewLifecycleOwner, Observer { estado ->
            view.estadoVerificarFavor.text = "Estado del favor: " + estado;
        })


        usuarioViewModel.usuarioRepository.tituloFavor.observe(viewLifecycleOwner, Observer { titulo ->
            view.tituloFavor.text = titulo;
        })

        usuarioViewModel.usuarioRepository.realizadoFavor.observe(viewLifecycleOwner, Observer { realizado ->
            if(!realizado){
                view.favorRealizado.text = "No finalizada";
            }else {
                view.favorRealizado.text = "Favor realizado";
            }
        })


        view.btnCompletarFavor.setOnClickListener {
            usuarioViewModel.usuarioRepository.realizadoFavor.observe(viewLifecycleOwner, Observer { realizado ->
                if(realizado){
                    usuarioViewModel.terminarFavor()
                }
            })
        }


        view.btnRegresar.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_verificarFavorFragment_to_homeFragment)
        }
        return view
    }





}