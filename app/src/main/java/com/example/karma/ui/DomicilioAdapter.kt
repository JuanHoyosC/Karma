package com.example.karma.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.karma.R
import com.example.karma.data.Domicilio
import com.example.karma.data.Fotocopia
import com.example.karma.repository.UsuarioRepository
import kotlinx.android.synthetic.main.list_item_domicilios.view.*
import kotlinx.android.synthetic.main.list_item_fotocopias.view.*

class DomicilioAdapter (val domicilio: ArrayList<Domicilio>): RecyclerView.Adapter<DomicilioAdapter.ViewHolder>() {
    public val usuarioRepository = UsuarioRepository;
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_domicilios, parent, false)

        view.findViewById<CardView>(R.id.cardDomicilios).setOnClickListener {
            val uid = it.uidDomicilioCard.text.toString()
            usuarioRepository.obtenerDetalle(uid)
            view.findNavController().navigate(R.id.action_listaFavoresFragment_to_detalleFragment)
        }
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return domicilio.size
    }



    override fun onBindViewHolder(holder: DomicilioAdapter.ViewHolder, position: Int) {
        holder.bind(domicilio[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(domicilio: Domicilio) {
            if(domicilio.realizado == false) {
                itemView.lugarDomicilioCard.text = domicilio.lugar
                itemView.refDomicilioCard.text = domicilio.ref
                itemView.tituloDomicilioCard.text = domicilio.titulo
                itemView.uidDomicilioCard.text = domicilio.uid
            }
        }
    }

}