package com.example.karma.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.karma.data.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

object UsuarioRepository {

    var karma = MutableLiveData<Int>()
    var movimiento =  MutableLiveData<ArrayList<String>>()
    var tituloFavor = MutableLiveData<String>()
    var estadoFavor = MutableLiveData<String>()
    var realizadoFavor = MutableLiveData<Boolean>()
    var pidioFavor = MutableLiveData<Boolean>()
    var cantFavor = MutableLiveData<Int>()

    var favoresDomicilio =  MutableLiveData<ArrayList<Domicilio>>()
    var favoresKM5 =  MutableLiveData<ArrayList<KM5>>()
    var favoresFotocopia =  MutableLiveData<ArrayList<Fotocopia>>()

    //Detalles
    var estadoDetalle = MutableLiveData<String>()
    var realizadoDetalle = MutableLiveData<Boolean>()
    var uidDetalle = MutableLiveData<String>()
    var tomoFavorquien = MutableLiveData<String>()

    var allFotocopia = mutableListOf<Fotocopia>()
    var allFotocopiaLiveData = MutableLiveData<List<Fotocopia>>()
    var allDomicilio = mutableListOf<Domicilio>()
    var allDomicilioLiveData = MutableLiveData<List<Domicilio>>()
    var allKM5 = mutableListOf<KM5>()
    var allKM5LiveData = MutableLiveData<List<KM5>>()



    fun crearUsuario (email: String) {
            val database = Firebase.database
            val user = FirebaseAuth.getInstance().currentUser
            user?.let {
                val uid = user.uid
                val myRef = database.getReference("$uid")
                var movimientos: ArrayList<String> = ArrayList();
                movimientos.add("No hay movimientos")
                movimientos.add("No hay movimientos")
                movimientos.add("No hay movimientos")

                val usuario = Usuario(email.toString(), 2, false,  movimientos);
                myRef.setValue(usuario)
            }
    }

    fun obtenerUsuario() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val database = Firebase.database
            val uid = user.uid
            val myRef = database.getReference("$uid")
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    karma.value = dataSnapshot.child("karma").getValue().toString().toInt()
                    movimiento.value = dataSnapshot.child("movimientos").getValue<ArrayList<String>>()
                    tituloFavor.value = dataSnapshot.child("favores").child("titulo").getValue().toString()
                    estadoFavor.value = dataSnapshot.child("favores").child("estado").getValue().toString()
                    realizadoFavor.value = dataSnapshot.child("favores").child("realizado").getValue().toString().toBoolean()
                    pidioFavor.value = dataSnapshot.child("pidioFavor").getValue().toString().toBoolean()
                    cantFavor.value = dataSnapshot.child("cantFavores").getValue().toString().toInt()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("", "Failed to read value.", error.toException())
                }
            })
        }
    }


    fun agregarKM5(favor: KM5) {
        val database = Firebase.database

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {

            val uid = user.uid
            val myRef = database.getReference("$uid")
            myRef.child("favores").setValue(favor)
            myRef.child("karma").setValue(karma.value.toString().toInt() - 2)
            movimiento.value?.add("Se pidio un favor");
            myRef.child("movimientos").setValue(movimiento.value)
            myRef.child("pidioFavor").setValue(true)

            val myKM5 = database.getReference("favoresKM5")
            favoresKM5.value?.add(favor)
            myKM5.setValue(favoresKM5.value)
        }
    }

    fun agregarFotocopia(favor: Fotocopia) {
        val database = Firebase.database

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {

            val uid = user.uid
            val email = user.email
            val myRef = database.getReference("$uid")
            myRef.child("favores").setValue(favor)
            myRef.child("karma").setValue(karma.value.toString().toInt() - 2)
            movimiento.value?.add("Se pidio un favor");
            myRef.child("movimientos").setValue(movimiento.value)
            myRef.child("pidioFavor").setValue(true)

            val myFotocopia = database.getReference("favoresFotocopia")
            favoresFotocopia.value?.add(favor)
            myFotocopia.setValue(favoresFotocopia.value)
        }
    }

    fun agregarDomicilio(favor: Domicilio) {
        val database = Firebase.database

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {

            val uid = user.uid
            val myRef = database.getReference("$uid")
            myRef.child("favores").setValue(favor)
            myRef.child("karma").setValue(karma.value.toString().toInt() - 2)
            movimiento.value?.add("Se pidio un favor");
            myRef.child("movimientos").setValue(movimiento.value)
            myRef.child("pidioFavor").setValue(true)

            val myDomicilio = database.getReference("favoresDomicilio")
            favoresDomicilio.value?.add(favor)
            myDomicilio.setValue(favoresDomicilio.value)

        }
    }



    fun obtenerFavoresDomicilio() {
        val database = Firebase.database
        val myRef = database.getReference("favoresDomicilio")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                favoresDomicilio.value = dataSnapshot.getValue<ArrayList<Domicilio>>()
                allDomicilio.clear()
                dataSnapshot.getValue<List<Domicilio>>()?.let { allDomicilio.addAll(it) }
                allDomicilioLiveData.postValue(allDomicilio)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException())
            }
        })
    }


    fun obtenerFavoresKM5() {
        val database = Firebase.database
        val myRef = database.getReference("favoresKM5")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                favoresKM5.value = dataSnapshot.getValue<ArrayList<KM5>>()
                allKM5.clear()
                dataSnapshot.getValue<List<KM5>>()?.let { allKM5.addAll(it) }
                allKM5LiveData.postValue(allKM5)

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException())
            }
        })
    }

    fun obtenerFavoresFotocopia() {
        val database = Firebase.database
        val myRef = database.getReference("favoresFotocopia")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                favoresFotocopia.value = dataSnapshot.getValue<ArrayList<Fotocopia>>()
                allFotocopia.clear()
                dataSnapshot.getValue<List<Fotocopia>>()?.let { allFotocopia.addAll(it) }
                allFotocopiaLiveData.postValue(allFotocopia)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException())
            }
        })
    }

    fun tomarFavor(uid: String) {
        val database = Firebase.database
        val myRef = database.getReference("$uid")

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val uid2 = user.uid
            val myRef2 = database.getReference("$uid2")
           if(uid !== uid2 && cantFavor.value.toString().toInt() !== 1 && estadoDetalle.value.toString() !== "Asignado") {
               movimiento.value?.add("Se tomo un favor");
               myRef2.child("movimientos").setValue(movimiento.value)
               myRef2.child("cantFavores").setValue(1)
               myRef.child("favores").child("estado").setValue("Asignado");
               myRef.child("tomoFavorQuien").setValue(uid2)
           }
        }
    }

    fun completarFavor(uid: String) {
        val database = Firebase.database

        val myRef = database.getReference("$uid")
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val uid2 = user.uid
            val myRef2 = database.getReference("$uid2")
            if(uid2 !== uid && tomoFavorquien.value == uid2) {
                movimiento.value?.add("Se completo un favor");
                myRef.child("favores").child("realizado").setValue(true);
                myRef2.child("movimientos").setValue(movimiento.value)

                myRef2.child("cantFavores").setValue(0)
                Log.d("karma", karma.value.toString())
                myRef2.child("karma").setValue((karma.value?.toInt() ?: 2) + 2)
            }
        }
    }

    fun terminarFavor() {
        val database = Firebase.database

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val uid2 = user.uid
            val myRef2 = database.getReference("$uid2")
            if(estadoFavor.value.toString() !== "Completado"){
                movimiento.value?.add("Se completo un favor");
                myRef2.child("movimientos").setValue(movimiento.value)
                myRef2.child("favores").child("estado").setValue("Completado");
                myRef2.child("pidioFavor").setValue(false)


                if(tituloFavor.value == "Fotocopia") {
                    for (fotocopia in favoresFotocopia.value!!){
                        if(fotocopia.titulo == tituloFavor.value.toString() && fotocopia.uid == uid2){
                            favoresFotocopia.value!!.remove(fotocopia)
                        }
                    }

                    val myFotocopia = database.getReference("favoresFotocopia")
                    myFotocopia.setValue(favoresFotocopia.value)
                }

                if(tituloFavor.value == "KM5") {
                    var pos = 0;
                    for (km5 in favoresKM5.value!!){
                        if(km5.titulo == tituloFavor.value.toString() && km5.uid == uid2){
                            favoresKM5.value!!.remove(km5);

                        }
                    }

                    val myKM5 = database.getReference("favoresKM5")

                    myKM5.setValue(favoresKM5.value)
                }

                if(tituloFavor.value == "Domicilio") {

                    for (domicilio in  favoresDomicilio.value!!){
                        if(domicilio.titulo == tituloFavor.value.toString() && domicilio.uid == uid2){
                            favoresDomicilio.value!!.remove(domicilio)
                        }
                    }

                    val myDomicilio = database.getReference("favoresDomicilio")
                    myDomicilio.setValue(favoresDomicilio.value)
                }

            }
        }
    }

    fun obtenerDetalle (uid: String) {
        val database = Firebase.database
        val myRef = database.getReference("$uid")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                estadoDetalle.value = dataSnapshot.child("favores").child("estado").getValue().toString()
                realizadoDetalle.value = dataSnapshot.child("favores").child("realizado").getValue().toString().toBoolean()
                uidDetalle.value = dataSnapshot.child("favores").child("uid").getValue().toString()
                tomoFavorquien.value = dataSnapshot.child("tomoFavorQuien").getValue().toString()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException())
            }
        })

    }
}


