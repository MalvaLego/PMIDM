package com.example.appprojectom

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class MainActivity : AppCompatActivity() {

    lateinit var cvTitulo: CardView
    lateinit var cvHoras: CardView
    lateinit var tvHoras: TextView
    lateinit var rsHoras: RangeSlider
    lateinit var cvNumeroPersonas: CardView
    lateinit var tvNumeroPersonas: TextView
    lateinit var tvGastosPersonas: TextView
    lateinit var btnNumeroPersonasMas: FloatingActionButton
    lateinit var btnNumeroPersonasMenos: FloatingActionButton
    lateinit var cvHardware: CardView
    lateinit var tvUnidadesHardware: TextView
    lateinit var tvGastosHardware: TextView
    lateinit var btnHardwareMas: FloatingActionButton
    lateinit var btnHardwareMenos: FloatingActionButton
    lateinit var cvDificultad: CardView
    lateinit var tvDificultad: TextView
    lateinit var btnBasico: AppCompatButton
    lateinit var btnAvanzado: AppCompatButton
    lateinit var btnIntermedio: AppCompatButton
    lateinit var btnExperto: AppCompatButton
    lateinit var cvGastoTotal: CardView
    lateinit var tvGastosTotal: TextView
    lateinit var btnReset: Button

    var horasfloat:Float= 700F
    var gastoTotal=0
    var numeroPersonas=0
    var gastoPersonas=0
    var numeroUnidadesHardware=0
    var gastoHardware=0
    var gastoHoras=0
    var gastoDifucultad=0


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

    private fun initComponents() {
        cvTitulo = findViewById(R.id.cvTitulo);
        cvHoras = findViewById(R.id.cvHoras);
        tvHoras = findViewById(R.id.tvHoras);
        rsHoras = findViewById(R.id.rsHoras);
        cvNumeroPersonas = findViewById(R.id.cvNumeroPersonas);
        tvNumeroPersonas = findViewById(R.id.tvNumeroPersonas);
        tvGastosPersonas = findViewById(R.id.tvGastosPersonas);
        btnNumeroPersonasMas = findViewById(R.id.btnNumeroPersonasMas);
        btnNumeroPersonasMenos = findViewById(R.id.btnNumeroPersonasMenos);
        cvHardware = findViewById(R.id.cvHardware);
        tvUnidadesHardware = findViewById(R.id.tvUnidadesHardware);
        tvGastosHardware = findViewById(R.id.tvGastosHardware);
        btnHardwareMas = findViewById(R.id.btnHardwareMas);
        btnHardwareMenos = findViewById(R.id.btnHardwareMenos);
        cvDificultad = findViewById(R.id.cvDificultad);
        tvDificultad = findViewById(R.id.tvDificultad);
        btnBasico = findViewById(R.id.btnBasico);
        btnAvanzado = findViewById(R.id.btnAvanzado);
        btnIntermedio = findViewById(R.id.btnIntermedio);
        btnExperto = findViewById(R.id.btnExperto);
        cvGastoTotal = findViewById(R.id.cvGastoTotal);
        tvGastosTotal = findViewById(R.id.tvGastosTotal);
        btnReset = findViewById(R.id.btnReset);
    }

    private fun initListeners() {
        rsHoras.addOnChangeListener { _, value, _ ->
            if (gastoHoras!=0){
                gastoTotal=gastoTotal-gastoHoras
                gastoHoras=0
            }
            horasfloat=value
            tvHoras.text=horasfloat.toInt().toString()
            gastoHoras=horasfloat.toInt()
            gastoTotal=gastoTotal+gastoHoras
            sumaTotal()
        }
        btnNumeroPersonasMas.setOnClickListener(){
            numeroPersonas=numeroPersonas+1
            tvNumeroPersonas.text="Personas: "+numeroPersonas.toString()
            gastoTotal=gastoTotal+120
            gastoPersonas=gastoPersonas+120
            tvGastosPersonas.text=gastoPersonas.toString()+" euros"
            sumaTotal()
        }
        btnNumeroPersonasMenos.setOnClickListener(){
            if (numeroPersonas>0){
                numeroPersonas=numeroPersonas-1
                tvNumeroPersonas.text="Personas: "+numeroPersonas.toString()
                gastoTotal=gastoTotal-120
                gastoPersonas=gastoPersonas-120
                tvGastosPersonas.text=gastoPersonas.toString()+" euros"
                sumaTotal()
            }
        }
        btnHardwareMas.setOnClickListener(){
            numeroUnidadesHardware=numeroUnidadesHardware+1
            tvUnidadesHardware.text="Hardware: "+numeroUnidadesHardware.toString()+" ud."
            gastoTotal=gastoTotal+80
            gastoHardware=gastoHardware+80
            tvGastosHardware.text=gastoHardware.toString()+" euros"
            sumaTotal()
        }
        btnHardwareMenos.setOnClickListener(){
            if (numeroUnidadesHardware>0){
                numeroUnidadesHardware=numeroUnidadesHardware-1
                tvUnidadesHardware.text="Hardware: "+numeroUnidadesHardware.toString()+" ud."
                gastoTotal=gastoTotal-80
                gastoHardware=gastoHardware-80
                tvGastosHardware.text=gastoHardware.toString()+" euros"
                sumaTotal()
            }
        }

        btnBasico.setOnClickListener(){
            if (gastoDifucultad!=0){
                gastoTotal=gastoTotal-gastoDifucultad
                gastoDifucultad=0
            }
            tvDificultad.text="Básico"
            gastoDifucultad=(gastoTotal/100)*2
            gastoTotal=gastoTotal+gastoDifucultad
            sumaTotal()
        }
        btnIntermedio.setOnClickListener(){
            if (gastoDifucultad!=0){
                gastoTotal=gastoTotal-gastoDifucultad
                gastoDifucultad=0
            }
            tvDificultad.text="Intermedio"
            gastoDifucultad=(gastoTotal/100)*5
            gastoTotal=gastoTotal+gastoDifucultad
            sumaTotal()
        }
        btnAvanzado.setOnClickListener(){
            if (gastoDifucultad!=0){
                gastoTotal=gastoTotal-gastoDifucultad
                gastoDifucultad=0
            }
            tvDificultad.text="Avanzado"
            gastoDifucultad=(gastoTotal/100)*7
            gastoTotal=gastoTotal+gastoDifucultad
            sumaTotal()
        }
        btnExperto.setOnClickListener(){
            if (gastoDifucultad!=0){
                gastoTotal=gastoTotal-gastoDifucultad
                gastoDifucultad=0
            }
            tvDificultad.text="Experto"
            gastoDifucultad=(gastoTotal/100)*10
            gastoTotal=gastoTotal+gastoDifucultad
            sumaTotal()
        }
        btnReset.setOnClickListener(){
            inicio()
        }

    }

    private fun inicio() {
        gastoTotal=0
        tvGastosTotal.text="Gastos total: 0"
        horasfloat=0F
        tvHoras.text="0"
        numeroPersonas=0
        tvNumeroPersonas.text="Personas: 0"
        gastoPersonas=0
        tvGastosPersonas.text="0 euros"
        numeroUnidadesHardware=0
        tvUnidadesHardware.text="Hardware: 0 ud."
        gastoHardware=0
        tvGastosHardware.text="0 euros"
        tvDificultad.text="___"
    }


    private fun sumaTotal() {
        tvGastosTotal.text="Gastos total: "+gastoTotal
    }

}