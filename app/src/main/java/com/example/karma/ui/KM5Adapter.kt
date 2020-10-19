package com.example.karma.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.karma.R
import com.example.karma.data.Fotocopia
import com.example.karma.data.KM5
import com.example.karma.repository.UsuarioRepository
import com.example.karma.viewModel.UsuarioViewModel
import kotlinx.android.synthetic.main.list_item_fotocopias.view.*
import kotlinx.android.synthetic.main.list_item_km5.view.*

class KM5Adapter (val km5: ArrayList<KM5>): RecyclerView.Adapter<KM5Adapter.ViewHolder>() {
    public val usuarioRepository = UsuarioRepository;
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_km5, parent, false)
        view.findViewById<CardView>(R.id.cardKM5).setOnClickListener {
            val uid = it.uidKM5Card.text.toString()
            usuarioRepository.obtenerDetalle(uid);
            view.findNavController().navigate(R.id.action_listaFavoresFragment_to_detalleFragment)
        }

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return km5.size
    }



    override fun onBindViewHolder(holder: KM5Adapter.ViewHolder, position: Int) {
        holder.bind(km5[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(km5: KM5) {
            if(km5.realizado == false) {
                itemView.tituloKM5Card.text = km5.titulo
                itemView.queKM5Card.text = km5.que
                itemView.lugarKM5Card.text = km5.lugar
                itemView.cantidadKm5Card.text = km5.cantidad
                itemView.uidKM5Card.text = km5.uid
            }
        }
    }

}