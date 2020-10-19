package com.example.karma.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.karma.R
import com.example.karma.data.Fotocopia
import com.example.karma.repository.UsuarioRepository
import kotlinx.android.synthetic.main.list_item_fotocopias.view.*

class FotocopiasAdapter  (val fotocopia: ArrayList<Fotocopia>): RecyclerView.Adapter<FotocopiasAdapter.ViewHolder>() {
    public val usuarioRepository = UsuarioRepository;
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_fotocopias, parent, false)

        view.findViewById<CardView>(R.id.cardFotocopia).setOnClickListener {
            val uid = it.uidFotocopiaCard.text.toString()
            usuarioRepository.obtenerDetalle(uid)
            view.findNavController().navigate(R.id.action_listaFavoresFragment_to_detalleFragment)
        }
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fotocopia.size
    }



    override fun onBindViewHolder(holder: FotocopiasAdapter.ViewHolder, position: Int) {
        holder.bind(fotocopia[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(fotocopia: Fotocopia) {
           if(fotocopia.realizado == false) {
               itemView.tituloFotocopiaCard.text = fotocopia.titulo;
               itemView.codigoFotocopiaCard.text = fotocopia.codigo;
               itemView.lugarFotocopiaCard.text = fotocopia.lugar;
               itemView.uidFotocopiaCard.text = fotocopia.uid;
           }
        }
    }

}