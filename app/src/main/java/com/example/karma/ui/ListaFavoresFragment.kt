package com.example.karma.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.karma.R
import com.example.karma.viewModel.UsuarioViewModel
import kotlinx.android.synthetic.main.fragment_lista_favores.*
import kotlinx.android.synthetic.main.fragment_lista_favores.view.*


class ListaFavoresFragment : Fragment() {


    val usuarioViewModel : UsuarioViewModel by activityViewModels()
    private val km5Adapter = KM5Adapter(ArrayList())
    private val fotocopiasAdapter = FotocopiasAdapter(ArrayList())
    private val domiciliosAdapter = DomicilioAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_lista_favores, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // requireView gets the root view for the fragment's layout
        // (the one returned by onCreateView).
        usuarioViewModel.usuarioRepository.obtenerFavoresKM5()
        usuarioViewModel.usuarioRepository.obtenerFavoresFotocopia()
        usuarioViewModel.usuarioRepository.obtenerFavoresDomicilio()

        requireView().courses_recycler.adapter = domiciliosAdapter
        requireView().courses_recycler.layoutManager = LinearLayoutManager(requireContext())

        // get the live data and start observing
        usuarioViewModel.usuarioRepository.allDomicilioLiveData.observe(getViewLifecycleOwner(), Observer {
            domiciliosAdapter.domicilio.clear()
            domiciliosAdapter.domicilio.addAll(it)
            domiciliosAdapter.notifyDataSetChanged()
        })

        filtroDomicilios.setOnClickListener {
            requireView().courses_recycler.adapter = domiciliosAdapter
            requireView().courses_recycler.layoutManager = LinearLayoutManager(requireContext())

            // get the live data and start observing
            usuarioViewModel.usuarioRepository.allDomicilioLiveData.observe(getViewLifecycleOwner(), Observer {
                domiciliosAdapter.domicilio.clear()
                domiciliosAdapter.domicilio.addAll(it)
                domiciliosAdapter.notifyDataSetChanged()
            })
        }


        filtroFotocopias.setOnClickListener {
            requireView().courses_recycler.adapter = fotocopiasAdapter
            requireView().courses_recycler.layoutManager = LinearLayoutManager(requireContext())


            // get the live data and start observing
            usuarioViewModel.usuarioRepository.allFotocopiaLiveData.observe(getViewLifecycleOwner(), Observer {
                for (lista in it){
                    Log.d("lista foto", lista.toString())
                }
                fotocopiasAdapter.fotocopia.clear()
                fotocopiasAdapter.fotocopia.addAll(it)
                fotocopiasAdapter.notifyDataSetChanged()
            })
        }

        filtroKM5.setOnClickListener {
            requireView().courses_recycler.adapter = km5Adapter
            requireView().courses_recycler.layoutManager = LinearLayoutManager(requireContext())
            // get the live data and start observing

            usuarioViewModel.usuarioRepository.allKM5LiveData.observe(getViewLifecycleOwner(), Observer {
                    for (lista in it){
                        Log.d("lista", lista.toString())
                    }
            })
            usuarioViewModel.usuarioRepository.allKM5LiveData.observe(getViewLifecycleOwner(), Observer {

                km5Adapter.km5.clear()
                km5Adapter.km5.addAll(it)
                km5Adapter.notifyDataSetChanged()
            })
        }

    }


}