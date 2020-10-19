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
import kotlinx.android.synthetic.main.fragment_detalle.view.*


class DetalleFragment : Fragment() {
    val usuarioViewModel: UsuarioViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_detalle, container, false)


        usuarioViewModel.usuarioRepository.estadoDetalle.observe(viewLifecycleOwner, Observer { estado ->
            view.estadoDetalle.text = "Estado: " + estado;
        })

        view.btnTomarFavor.setOnClickListener {
            usuarioViewModel.usuarioRepository.uidDetalle.observe(viewLifecycleOwner, Observer { uid ->
                usuarioViewModel.tomarUnFavor(uid)
            })
        }


        view.btnCompletarFavor.setOnClickListener {
            usuarioViewModel.usuarioRepository.uidDetalle.observe(viewLifecycleOwner, Observer { uid ->
                usuarioViewModel.completarUnFavor(uid)
            })
        }

        return view
    }


}