package com.example.appfinanzas

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {

    lateinit var tvAlquiler: TextView
    lateinit var tvCoche: TextView
    lateinit var tvGastosVivienda: TextView
    lateinit var btnGastosViviendaMas: FloatingActionButton
    lateinit var btnGastosViviendaMenos: FloatingActionButton
    lateinit var tvGastosComida: TextView
    lateinit var btnGastosComidaMas: FloatingActionButton
    lateinit var btnGastosComidaMenos: FloatingActionButton
    lateinit var btn100Euros: AppCompatButton
    lateinit var btn200Euros: AppCompatButton
    lateinit var btn300Euros: AppCompatButton
    lateinit var btn400Euros: AppCompatButton
    lateinit var tvGastosTotal: TextView
    lateinit var rsAlquiler: RangeSlider
    lateinit var rsCoche: RangeSlider

    var alquilerfloat:Float= 200.0F
    var gastoAlquiler=0
    var cochefloat:Float= 200.0F
    var gastoCoche=0
    var gastoVivienda=0
    var gastoComida=0
    var gastoTotal=0
    var gastoSalir=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initComponents();
        initListeners();
        inicio();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initComponents(){
        tvAlquiler = findViewById(R.id.tvAlquiler);
        tvCoche = findViewById(R.id.tvCoche);
        tvGastosVivienda = findViewById(R.id.tvGastosVivienda);
        btnGastosViviendaMas = findViewById(R.id.btnGastosViviendaMas);
        btnGastosViviendaMenos = findViewById(R.id.btnGastosViviendaMenos);
        tvAlquiler = findViewById(R.id.tvAlquiler);
        tvGastosComida = findViewById(R.id.tvGastosComida);
        btnGastosComidaMas = findViewById(R.id.btnGastosComidaMas);
        btnGastosComidaMenos = findViewById(R.id.btnGastosComidaMenos);
        btn100Euros = findViewById(R.id.btn100Euros);
        btn200Euros = findViewById(R.id.btn200Euros);
        btn300Euros = findViewById(R.id.btn300Euros);
        btn400Euros = findViewById(R.id.btn400Euros);
        tvGastosTotal = findViewById(R.id.tvGastosTotal);
        rsAlquiler= findViewById(R.id.rsAlquiler)
        rsCoche= findViewById(R.id.rsCoche)

    }

    private fun initListeners(){
        rsAlquiler.addOnChangeListener { _, value, _ ->
            if (gastoAlquiler!=0){
                gastoTotal=gastoTotal-gastoAlquiler
                gastoAlquiler=0
            }
            alquilerfloat=value
            tvAlquiler.text=alquilerfloat.toInt().toString()
            gastoAlquiler=alquilerfloat.toInt()
            gastoTotal=gastoTotal+gastoAlquiler
            sumaTotal()
        }

        rsCoche.addOnChangeListener { _, value, _ ->
            if (gastoCoche!=0){
                gastoTotal=gastoTotal-gastoCoche
                gastoCoche=0
            }
            cochefloat=value
            tvCoche.text=cochefloat.toInt().toString()
            gastoCoche=cochefloat.toInt()
            gastoTotal=gastoTotal+gastoCoche
            sumaTotal()
        }

        btnGastosViviendaMas.setOnClickListener(){
            gastoTotal=gastoTotal+5
            gastoVivienda=gastoVivienda+5
            tvGastosVivienda.text=gastoVivienda.toString()+" euros"
            sumaTotal()
        }
        btnGastosViviendaMenos.setOnClickListener(){
            if (gastoVivienda>0){
                gastoTotal=gastoTotal-5
                gastoVivienda=gastoVivienda-5
                tvGastosVivienda.text=gastoVivienda.toString()+" euros"
                sumaTotal()
            }
        }

        btnGastosComidaMas.setOnClickListener(){
            gastoTotal=gastoTotal+5
            gastoComida=gastoComida+5
            tvGastosComida.text=gastoComida.toString()+" euros"
            sumaTotal()
        }
        btnGastosComidaMenos.setOnClickListener(){
            if (gastoComida>0){
                gastoTotal=gastoTotal-5
                gastoComida=gastoComida-5
                tvGastosComida.text=gastoComida.toString()+" euros"
                sumaTotal()
            }
        }

        btn100Euros.setOnClickListener(){

                gastoTotal=gastoTotal-gastoSalir
                gastoSalir=100
                gastoTotal=gastoTotal+gastoSalir
                sumaTotal()

                habilitarBotones()
            btn100Euros.isEnabled=false

        }
        btn200Euros.setOnClickListener(){
            gastoTotal=gastoTotal-gastoSalir
            gastoSalir=200
            gastoTotal=gastoTotal+gastoSalir
            sumaTotal()

            habilitarBotones()
            btn200Euros.isEnabled=false
        }
        btn300Euros.setOnClickListener(){
            gastoTotal=gastoTotal-gastoSalir
            gastoSalir=300
            gastoTotal=gastoTotal+gastoSalir
            sumaTotal()

            habilitarBotones()
            btn300Euros.isEnabled=false
        }
        btn400Euros.setOnClickListener(){
            gastoTotal=gastoTotal-gastoSalir
            gastoSalir=400
            gastoTotal=gastoTotal+gastoSalir
            sumaTotal()

            habilitarBotones()
            btn400Euros.isEnabled=false
        }


    }




    private fun inicio(){

    }




    private fun habilitarBotones(){
        btn100Euros.isEnabled=true
        btn200Euros.isEnabled=true
        btn300Euros.isEnabled=true
        btn400Euros.isEnabled=true
    }

    private fun sumaTotal() {
        tvGastosTotal.text="Gastos total: "+gastoTotal
    }


}