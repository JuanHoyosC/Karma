package com.example.karma.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.karma.R
import com.example.karma.data.Usuario
import com.example.karma.viewModel.UsuarioViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {


    val usuarioViewModel: UsuarioViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        usuarioViewModel.obtenerUsuario();
        usuarioViewModel.usuarioRepository.obtenerFavoresKM5()
        usuarioViewModel.usuarioRepository.obtenerFavoresFotocopia()
        usuarioViewModel.usuarioRepository.obtenerFavoresDomicilio()

        val navController = findNavController()

       view.btnPerfil.setOnClickListener {
           navController.navigate(R.id.action_homeFragment_to_perfilFragment)
       }

        view.btnPedirFavor.setOnClickListener {
            usuarioViewModel.usuarioRepository.karma.observe(viewLifecycleOwner, Observer { karma ->
                usuarioViewModel.usuarioRepository.pidioFavor.observe(viewLifecycleOwner, Observer { favor ->
                    if(karma.toInt() < 2 || favor ) {
                        navController.navigate(R.id.action_homeFragment_to_verificarFavorFragment)
                    }else {
                        navController.navigate(R.id.action_homeFragment_to_pedirFavorFragment)
                    }
                })

            })
        }

        view.btn_salir.setOnClickListener {
            usuarioViewModel.usuarioRepository.movimiento.value?.clear()
            navController.navigate(R.id.action_homeFragment_to_loginFragment)
        }

        view.btnFavores.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_listaFavoresFragment)
        }

        return view
    }

}