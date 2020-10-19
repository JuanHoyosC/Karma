package com.example.karma.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.karma.R
import com.example.karma.data.Domicilio
import com.example.karma.data.Favor
import com.example.karma.data.Fotocopia
import com.example.karma.data.KM5
import com.example.karma.viewModel.UsuarioViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_pedir_favor.*
import kotlinx.android.synthetic.main.fragment_pedir_favor.view.*


class PedirFavorFragment : Fragment() {
    val usuarioViewModel: UsuarioViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_pedir_favor, container, false)
        val user = FirebaseAuth.getInstance().currentUser

        usuarioViewModel.obtenerFavoresDomicilio()
        usuarioViewModel.obtenerFavoresKM5()
        usuarioViewModel.obtenerFavoresFotocopia()

        user?.let {
            val database = Firebase.database
            val uid = user.uid
            val email = user.email

            val navController = findNavController()
            view.btnSolicitarFotocopias.setOnClickListener {
                val lugar = view.lugarFavor.text.toString();
                val codigo = view.codigoFavor.text.toString();
                if (lugar.isNotEmpty() && codigo.isNotEmpty()) {
                    val favor = Fotocopia(email.toString(), uid.toString(),lugar, codigo);
                    usuarioViewModel.agregarFotocopia(favor)
                    solicitudErronea.text = "";
                    navController.navigate(R.id.action_pedirFavorFragment_to_verificarFavorFragment)
                } else {
                    solicitudErronea.text = "Solicitud erronea";
                }
            }

            view.btnSolicitarKM5.setOnClickListener {
                val lugar = view.lugarFavor.text.toString();
                val objecto = view.objetoFavor.text.toString();
                val cantidad = view.cantidadFavor.text.toString();
                if (lugar.isNotEmpty() && objecto.isNotEmpty() && cantidad.isNotEmpty()) {
                    val favor = KM5(email.toString(), uid.toString(), lugar, objecto, cantidad)
                    usuarioViewModel.agregarKM5(favor)
                    solicitudErronea.text = "";
                    navController.navigate(R.id.action_pedirFavorFragment_to_verificarFavorFragment)
                } else {
                    solicitudErronea.text = "Solicitud erronea";
                }
            }

            view.btnSolicitarDomicilio.setOnClickListener {
                val lugar = view.lugarFavor.text.toString();
                val ref = view.referenciaFavor.text.toString();
                if (lugar.isNotEmpty() && ref.isNotEmpty()) {
                    solicitudErronea.text = "";
                    val favor = Domicilio(email.toString(), uid.toString(), lugar, ref)
                    usuarioViewModel.agregarDomicilio(favor)
                    navController.navigate(R.id.action_pedirFavorFragment_to_verificarFavorFragment)
                } else {
                    solicitudErronea.text = "Solicitud erronea";
                }
            }

        }
        return view
    }

}