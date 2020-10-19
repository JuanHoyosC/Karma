package com.example.karma.viewModel

import androidx.lifecycle.ViewModel
import com.example.karma.data.Domicilio
import com.example.karma.data.Fotocopia
import com.example.karma.data.KM5
import com.example.karma.repository.AuthRepository
import com.example.karma.repository.UsuarioRepository

class UsuarioViewModel: ViewModel() {

    public val usuarioRepository = UsuarioRepository;


    fun crearUsuario(email: String) {
        usuarioRepository.crearUsuario(email);
    }

    fun obtenerUsuario() {
        usuarioRepository.obtenerUsuario();
    }

    fun agregarFotocopia(fotocopia: Fotocopia) {
        usuarioRepository.agregarFotocopia(fotocopia)
    }

    fun agregarDomicilio(domicilio: Domicilio) {
        usuarioRepository.agregarDomicilio(domicilio)
    }


    fun agregarKM5(km5: KM5) {
        usuarioRepository.agregarKM5(km5)
    }


    fun obtenerFavoresDomicilio () {
        usuarioRepository.obtenerFavoresDomicilio()
    }


    fun obtenerFavoresFotocopia() {
        usuarioRepository.obtenerFavoresFotocopia()
    }

    fun obtenerFavoresKM5() {
        usuarioRepository.obtenerFavoresKM5()
    }

    fun terminarFavor() {
        usuarioRepository.terminarFavor()
    }

    fun tomarUnFavor(uid: String) {
        usuarioRepository.tomarFavor(uid)
    }

    fun completarUnFavor(uid: String) {
        usuarioRepository.completarFavor(uid)
    }

    fun obtenerDetalles(uid: String) {
        usuarioRepository.obtenerDetalle(uid);
    }

}