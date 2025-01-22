package com.example.appbuscapaco

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.System.exit

class MainActivity : AppCompatActivity() {
    lateinit var btnIniciar: Button
    lateinit var tvProgreso: TextView
    lateinit var pbBusca: ProgressBar
    lateinit var tvPalabra: TextView
    lateinit var tvResultado: TextView

    //pbBusca.progress = factorInt

    var palabraParaAdivinar="paco"
    var palabraEnUso=""
    var numeroProgreso=0
    var numeroIteraciones=0
    var numeroSegundos=0
    var inicialTime = System.currentTimeMillis()
    var finalTime = System.currentTimeMillis()
    var abc = arrayOf(
        "a", "b", "c", "d", "e", "f",
        "g", "h", "i", "j", "k", "l",
        "m", "n", "o", "p", "q", "r",
        "s", "t", "u", "v", "x", "y", "z"
    )
    var palabrasFalsas = arrayOf(
        "ERROR", "245", "HOLA", "ADIOS", "JJJ", "GGG"
    )
    var totalCombinations = 100
    var currentIteration = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initComponents()
        initListeners()
        inicio()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initComponents() {
        btnIniciar = findViewById(R.id.btnIniciar)
        tvProgreso = findViewById(R.id.tvProgreso)
        pbBusca = findViewById(R.id.pbBusca)
        tvPalabra = findViewById(R.id.tvPalabra)
        tvResultado = findViewById(R.id.tvResultado)

    }

    private fun initListeners() {
        btnIniciar.setOnClickListener(){

            inicio()
            btnIniciar.isEnabled=false
            inicialTime = System.currentTimeMillis()
            iniciarCalculos()
        }
    }

    private fun inicio() {
        numeroProgreso=0
        palabraEnUso="p"
        numeroIteraciones=0
        currentIteration = 0
        pbBusca.progress=0
    }

    private fun iniciarCalculos() {
        val job2 = lifecycleScope.launch {

            for (i in 1..10000){
                if (!palabraEnUso.equals(palabraParaAdivinar)) {

                    var randomLetra1 = abc.random()
                    var randomLetra2 = abc.random()
                    var randomLetra3 = abc.random()
                    var randomLetra4 = abc.random()

                    palabraEnUso="pa"+randomLetra3+randomLetra4
                    numeroIteraciones=numeroIteraciones+1

                    tvPalabra.text="palabra: pa"+randomLetra3+randomLetra4
                    tvResultado.text="buscando, iteración: "+numeroIteraciones


                    if (pbBusca.progress>=90){

                            palabraEnUso="pa"+randomLetra3+randomLetra4
                            numeroIteraciones=numeroIteraciones+1

                            tvPalabra.text="palabra: pa"+randomLetra3+randomLetra4
                            tvResultado.text="buscando, iteración: "+numeroIteraciones

                            delay(1)

                        pbBusca.progress=90
                        tvProgreso.text ="Progreso: ${pbBusca.progress}%"


                    }else{
                        updateProgress()
                    }
                }else{

                    if (pbBusca.progress<90){
                        var progresoAlEncontrarlo=pbBusca.progress
                        progresoAlEncontrarlo=progresoAlEncontrarlo/10

                        while (pbBusca.progress<100){
                            pbBusca.progress=pbBusca.progress+progresoAlEncontrarlo
                            tvProgreso.text ="Progreso: ${pbBusca.progress}%"

                            var randomLetra3 = abc.random()
                            var randomLetra4 = abc.random()

                            numeroIteraciones=numeroIteraciones+1

                            tvPalabra.text="palabra: pa"+randomLetra3+randomLetra4
                            tvResultado.text="buscando, iteración: "+numeroIteraciones
                            delay(300)
                        }

                    }

                    while (pbBusca.progress!=100){
                        pbBusca.progress=pbBusca.progress+1
                        tvProgreso.text ="Progreso: ${pbBusca.progress}%"
                        delay(500)

                        var muestroFake = palabrasFalsas.random()
                        numeroIteraciones=numeroIteraciones+1

                        tvPalabra.text="palabra: "+muestroFake
                        tvResultado.text="buscando, iteración: "+numeroIteraciones
                    }


                    terminado()
                    break
                }
                delay(5)

                //numeroSegundos=numeroSegundos+1
            }

        }
    }

    fun updateProgress() {
        currentIteration+=20
        var progress = (currentIteration.toDouble() / totalCombinations ).toInt()

        pbBusca.progress = progress
        tvProgreso.text ="Progreso: $progress%"
    }

    /*
    fun trucoFinal() {
        currentIteration+=30
        var progress = (currentIteration.toDouble() / totalCombinations ).toInt()

        pbBusca.progress = progress +pogresoAlEncontrarlo
        tvProgreso.text ="Progreso: $progress%"
    }


     */
    private fun terminado() {
        btnIniciar.isEnabled=true
        tvResultado.setBackgroundColor(Color.GREEN)
        finalTime = System.currentTimeMillis()
        tvPalabra.text="palabra: "+palabraEnUso
        tvProgreso.text="Progreso "+pbBusca.progress+"%"
        tvResultado.text="paco encontrado. Iteración: "+numeroIteraciones.toString()+"\n" +
                "tiempo total: "+(finalTime.toInt()-inicialTime.toInt())/ 1000.0+" segundos"
    }
}




