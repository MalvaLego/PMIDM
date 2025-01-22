package com.example.botones12

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.lang.System.exit
import java.util.LinkedList
import java.util.Queue
import java.util.Random

class MainActivity : AppCompatActivity() {

    lateinit var btnJugar: Button
    lateinit var tvTiempo: TextView
    lateinit var tvJugadas: TextView

    lateinit var btn01: Button
    lateinit var btn02: Button
    lateinit var btn03: Button
    lateinit var btn04: Button
    lateinit var btn05: Button
    lateinit var btn06: Button
    lateinit var btn07: Button
    lateinit var btn08: Button
    lateinit var btn09: Button
    lateinit var btn10: Button
    lateinit var btn11: Button
    lateinit var btn12: Button
    lateinit var btnReset: Button

    lateinit var etNombre: EditText
    lateinit var tvMejorPuntuacion: TextView
    lateinit var tvUltimos: TextView
    //lateinit var tvMejores5: TextView

    var contadorTiempo=10
    var numeroRandomBotones=0
    var numeroPulsadas=0
    var numeroPulsadasComparacion=0
    val colaJugadas: Queue<String> = LinkedList()
    val listaMejoresPuntuaciones= LinkedList<String>()
    val stack = ArrayDeque<String>(5)
    var registro1Puntuaciones=0
    var registro2Puntuaciones=0
    var registro3Puntuaciones=0
    var registro4Puntuaciones=0
    var registro5Puntuaciones=0
    var rachaEnable=false
    var randomRacha=0


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
        tvTiempo=findViewById(R.id.tvTiempo);
        tvJugadas=findViewById(R.id.tvJugadas);
        btnJugar=findViewById(R.id.btnJugar)
        btn01=findViewById(R.id.btn01)
        btn02=findViewById(R.id.btn02)
        btn03=findViewById(R.id.btn03)
        btn04=findViewById(R.id.btn04)
        btn05=findViewById(R.id.btn05)
        btn06=findViewById(R.id.btn06)
        btn07=findViewById(R.id.btn07)
        btn08=findViewById(R.id.btn08)
        btn09=findViewById(R.id.btn09)
        btn10=findViewById(R.id.btn10)
        btn11=findViewById(R.id.btn11)
        btn12=findViewById(R.id.btn12)
        btnReset=findViewById(R.id.btnReset)
        etNombre=findViewById(R.id.etNombre)
        tvMejorPuntuacion=findViewById(R.id.tvMejorPuntuacion);
        tvUltimos=findViewById(R.id.tvUltimos5);
        //tvMejores5=findViewById(R.id.tvMejores5)
        colaJugadas.size==5
        stack.size==5
        listaMejoresPuntuaciones.size==5
        numeroPulsadasComparacion=0
        stack.add(0, registro1Puntuaciones.toString())
        stack.add(1, registro2Puntuaciones.toString())
        stack.add(2, registro3Puntuaciones.toString())
        stack.add(3, registro4Puntuaciones.toString())
        stack.add(4, registro5Puntuaciones.toString())
        //tvMejores5.text=stack.toString()

    }

    private fun initListeners(){
        btnJugar.setOnClickListener(){
            temporizadores()
            jugar()
        }

        btnReset.setOnClickListener(){
            reset();
        }

        btn01.setOnClickListener(){
            jugar()
            sumarBotones()
        }
        btn02.setOnClickListener(){
            jugar()
            sumarBotones()
        }
        btn03.setOnClickListener(){
            jugar()
            sumarBotones()
        }
        btn04.setOnClickListener(){
            jugar()
            sumarBotones()
        }
        btn05.setOnClickListener(){
            jugar()
            sumarBotones()
        }
        btn06.setOnClickListener(){
            jugar()
            sumarBotones()
        }
        btn07.setOnClickListener(){
            jugar()
            sumarBotones()
        }
        btn08.setOnClickListener(){
            jugar()
            sumarBotones()
        }
        btn09.setOnClickListener(){
            jugar()
            sumarBotones()
        }
        btn10.setOnClickListener(){
            jugar()
            sumarBotones()
        }
        btn11.setOnClickListener(){
            jugar()
            sumarBotones()
        }
        btn12.setOnClickListener(){
            jugar()
            sumarBotones()
        }
    }

    private fun inicio(){
        contadorTiempo=10
        numeroPulsadas=0
        tvTiempo.text="Tiempo restante: 10"
        tvJugadas.text="Jugadas totales: 0 botones"
        btnJugar.isEnabled= true
        btnReset.isEnabled=false
        botonesDesactivados();
        randomRacha = (1..10).random()
        Log.i("natalia",randomRacha.toString())
        if (randomRacha>6){
            rachaEnable=true
            tvJugadas.setBackgroundColor(Color.GREEN)
        }else{
            rachaEnable=false
            tvJugadas.setBackgroundColor(Color.RED)
        }
    }

    private fun jugar(){

        if ((contadorTiempo<=4) && (rachaEnable==true)){
            if (contadorTiempo==4){
                temporizadorRacha();
            }
        }else{
            numeroRandomBotones = (1..12).random()
            botonesDesactivados()

            if (numeroRandomBotones==1){
                btn01.isEnabled= true
            }else if (numeroRandomBotones==2){
                btn02.isEnabled= true
            }else if (numeroRandomBotones==3){
                btn03.isEnabled= true
            }else if (numeroRandomBotones==4){
                btn04.isEnabled= true
            }else if (numeroRandomBotones==5){
                btn05.isEnabled= true
            }else if (numeroRandomBotones==6){
                btn06.isEnabled= true
            }else if (numeroRandomBotones==7){
                btn07.isEnabled= true
            }else if (numeroRandomBotones==8){
                btn08.isEnabled= true
            }else if (numeroRandomBotones==9){
                btn09.isEnabled= true
            }else if (numeroRandomBotones==10){
                btn10.isEnabled= true
            }else if (numeroRandomBotones==11){
                btn11.isEnabled= true
            }else if (numeroRandomBotones==12){
                btn12.isEnabled= true
            }
        }

        btnJugar.isEnabled= false
        btnReset.isEnabled=false
    }

    private fun temporizadores() {
        var postDelayed01 = android.os.Handler().postDelayed({
            cuentaAtrasTexto()
        }, 1000)
        var postDelayed02 = android.os.Handler().postDelayed({
            cuentaAtrasTexto()
        }, 2000)
        var postDelayed03 = android.os.Handler().postDelayed({
            cuentaAtrasTexto()
        }, 3000)
        var postDelayed04 = android.os.Handler().postDelayed({
            cuentaAtrasTexto()
        }, 4000)
        var postDelayed05 = android.os.Handler().postDelayed({
            cuentaAtrasTexto()
        }, 5000)
        var postDelayed06 = android.os.Handler().postDelayed({
            cuentaAtrasTexto()
        }, 6000)
        var postDelayed07 = android.os.Handler().postDelayed({
            cuentaAtrasTexto()
        }, 7000)
        var postDelayed08 = android.os.Handler().postDelayed({
            cuentaAtrasTexto()
        }, 8000)
        var postDelayed09 = android.os.Handler().postDelayed({
            cuentaAtrasTexto()
        }, 9000)
        var postDelayed10 = android.os.Handler().postDelayed({
            cuentaAtrasTexto()
            terminado()
        }, 10000)

    }

    private fun terminado() {
        btnReset.isEnabled= true
        botonesDesactivados()
        tvTiempo.setBackgroundColor(Color.RED)

        if (etNombre.text.isEmpty()){
            colaJugadas.add("Desconocido: "+numeroPulsadas)

        }else{
            if (colaJugadas.elementAtOrNull(4) != null){
                colaJugadas.poll()
                colaJugadas.add(etNombre.text.toString()+": "+numeroPulsadas)
            }else {
                colaJugadas.add(etNombre.text.toString()+": "+numeroPulsadas)
            }
        }

        if (numeroPulsadas>=numeroPulsadasComparacion){
            tvMejorPuntuacion.text="Récord de puntos\nBest: "+colaJugadas.last()
            numeroPulsadasComparacion=numeroPulsadas
        }
        imprimirJugadas()
    }

    private fun cuentaAtrasTexto() {
        contadorTiempo=contadorTiempo-1
        tvTiempo.text="Tiempo restante: "+contadorTiempo.toString()
    }


    private fun reset() {
        inicio();
    }

    private fun sumarBotones() {
        numeroPulsadas=numeroPulsadas+1
        tvJugadas.text="Jugadas totales: "+numeroPulsadas+" botones"
    }

    private fun botonesDesactivados() {
        btn01.isEnabled= false
        btn02.isEnabled= false
        btn03.isEnabled= false
        btn04.isEnabled= false
        btn05.isEnabled= false
        btn06.isEnabled= false
        btn07.isEnabled= false
        btn08.isEnabled= false
        btn09.isEnabled= false
        btn10.isEnabled= false
        btn11.isEnabled= false
        btn12.isEnabled= false
    }

    private fun imprimirJugadas(){
        var listaParareversar= LinkedList(colaJugadas)
        listaParareversar.reverse()

        val result = (0 until 5).joinToString(" \n") {
            listaParareversar.elementAtOrNull(it)?.toString() ?: " "
        }
        tvUltimos.text="Últimas jugadas:\n"+result.toString()
    }

    private fun temporizadorRacha() {
        var postDelayed01 = android.os.Handler().postDelayed({
            cambiarColorParpadeoAzul()
        }, 500)
        var postDelayed02 = android.os.Handler().postDelayed({
            cambiarColorParpadeoVerde()
        }, 1000)
        var postDelayed03 = android.os.Handler().postDelayed({
            cambiarColorParpadeoAzul()
        }, 1500)
        var postDelayed04 = android.os.Handler().postDelayed({
            cambiarColorParpadeoVerde()
        }, 2000)
        var postDelayed05 = android.os.Handler().postDelayed({
            cambiarColorParpadeoAzul()
        }, 2500)

    }

    private fun cambiarColorParpadeoAzul(){
        tvTiempo.setBackgroundColor(Color.BLUE)
    }

    private fun cambiarColorParpadeoVerde(){
        tvTiempo.setBackgroundColor(Color.GREEN)
    }



}
