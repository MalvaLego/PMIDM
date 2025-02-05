package com.example.appcifrado

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var btnParImpar: Button
    lateinit var btnCesar: Button
    lateinit var btnNoCesar: Button
    lateinit var btnBilateral: Button
    lateinit var btnNoBilateral: Button
    lateinit var btnVenegere: Button
    lateinit var btnNoVenegere: Button
    lateinit var etFrase: EditText
    lateinit var tvResultado: TextView
    lateinit var btnCriptogramaAFrase: Button
    lateinit var btnTextoAFrase: Button



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
        btnParImpar=findViewById(R.id.btnParImpar);
        btnCesar=findViewById(R.id.btnCesar);
        btnNoCesar=findViewById(R.id.btnNoCesar);
        btnBilateral=findViewById(R.id.btnBilateral);
        btnNoBilateral=findViewById(R.id.btnNoBilateral);
        btnVenegere=findViewById(R.id.btnVenegere);
        btnNoVenegere=findViewById(R.id.btnNoVenegere);
        etFrase=findViewById(R.id.etFrase);
        tvResultado=findViewById(R.id.tvResultado);
        btnCriptogramaAFrase=findViewById(R.id.btnCriptogramaAFrase);
        btnTextoAFrase=findViewById(R.id.btnTextoAFrase);
    }

    private fun initListeners(){
        btnParImpar.setOnClickListener(){
            criptParImpar(etFrase.text.toString());
        }
        btnCesar.setOnClickListener(){
            criptCesar(etFrase.text.toString());
        }
        btnNoCesar.setOnClickListener(){
            desencriptCesar(tvResultado.text.toString());
        }
        btnBilateral.setOnClickListener(){
            criptBilateral(etFrase.text.toString());
        }
        btnNoBilateral.setOnClickListener(){
            desencriptBilateral(tvResultado.text.toString());
        }
        btnVenegere.setOnClickListener(){
            criptVenegere(etFrase.text.toString());
        }
        btnNoVenegere.setOnClickListener(){
            desencriptVenegere(tvResultado.text.toString());
        }
        btnCriptogramaAFrase.setOnClickListener(){
            criptogramaAFrase();
        }
        btnTextoAFrase.setOnClickListener(){
            textoAFrase();
        }
    }

    private fun inicio(){
        tvResultado.text="hola"
    }

    fun criptParImpar(frase : String){
        //var letra1=""
        //var letra2=""
        //var orden_natural = IntArray(1000)
        var criptograma =""

        for (i in frase.indices step 2){
            if (i+1>=frase.length){
                criptograma=criptograma+frase[i].toString()
            }else{
                criptograma=criptograma+frase[i+1].toString()
                criptograma=criptograma+frase[i].toString()
            }
        }
        tvResultado.text=criptograma
    }

    //Criptación César
    fun criptCesar(frase : String) {
        var frase_len: Int = 0
        var orden_natural = IntArray(1000)
        var orden_cripto = IntArray(1000)

        val abcCesar: String = "abcdefghijklmnñopqrstuvwxyz .123"
        val abcCesar_len: Int = abcCesar.length

        var criptograma:String = ""
        frase_len = frase.length

        for (i in 0..frase_len - 1) {
            for (j in 0..abcCesar_len - 1) {
                if (frase[i] == abcCesar[j]) {
                    orden_natural[i]=j
                    //print("\n")
                    orden_cripto[i]=j+3
                }
            }
        }

        criptograma = ""
        for (i in 0..frase_len - 1) {
            criptograma = criptograma + abcCesar[orden_cripto[i]]
        }
        tvResultado.text=criptograma
    }
    fun desencriptCesar(frase : String) {
        var frase_len: Int = 0
        frase_len = frase.length

        var orden_natural = IntArray(1000)
        var orden_cripto = IntArray(1000)

        val abcCesar: String = "abcdefghijklmnñopqrstuvwxyz .123"
        val abcCesar_len: Int = abcCesar.length

        var criptograma:String = ""

        for (i in 0..frase_len - 1) {
            for (j in 0..abcCesar_len - 1) {
                if (frase[i] == abcCesar[j]) {
                    orden_natural[i]=j
                    orden_cripto[i]=j-3
                }
            }
        }

        criptograma = ""
        for (i in 0..frase_len - 1) {
            criptograma = criptograma + abcCesar[orden_cripto[i]]
        }
        tvResultado.text=criptograma
    }

    // Criptación Bilateral
    fun criptBilateral(frase : String) {
        var frase_len: Int = 0
        var orden_natural = IntArray(1000)
        var orden_cripto = IntArray(1000)
        var orden_criptoMenos = IntArray(1000)

        val abcBilateral: String = "abcdefghijklmnñopqrstuvwxyz .123"
        val abcBilateral_len: Int = abcBilateral.length

        var criptograma:String = ""
        frase_len = frase.length

        for (i in 0..frase_len - 1) {
            for (j in 0..abcBilateral_len - 1) {
                if (frase[i] == abcBilateral[j]) {
                    orden_natural[i]=j
                    orden_cripto[i]=(j + 3) % abcBilateral_len
                    orden_criptoMenos[i]=(j - 3 + abcBilateral_len) % abcBilateral_len
                }
            }
        }

        criptograma = ""
        for (i in frase.indices step 2){
            if (i+1>=frase.length){
                criptograma = criptograma + abcBilateral[orden_cripto[i]]
            }else{
                criptograma = criptograma + abcBilateral[orden_cripto[i]]
                criptograma = criptograma + abcBilateral[orden_criptoMenos[i+1]]
            }
        }
        tvResultado.text=criptograma
    }
    fun desencriptBilateral(frase : String) {
        var frase_len: Int = 0
        var orden_natural = IntArray(1000)
        var orden_cripto = IntArray(1000)
        var orden_criptoMenos = IntArray(1000)

        val abcBilateral: String = "abcdefghijklmnñopqrstuvwxyz .123"
        val abcBilateral_len: Int = abcBilateral.length

        var criptograma:String = ""
        frase_len = frase.length

        for (i in 0..frase_len - 1) {
            for (j in 0..abcBilateral_len - 1) {
                if (frase[i] == abcBilateral[j]) {
                    orden_natural[i]=j
                    orden_cripto[i]=(j - 3+ abcBilateral_len) % abcBilateral_len
                    orden_criptoMenos[i]=(j + 3) % abcBilateral_len
                }
            }
        }

        criptograma = ""
        for (i in frase.indices step 2){
            if (i+1>=frase.length){
                criptograma = criptograma + abcBilateral[orden_cripto[i]]
            }else{
                criptograma = criptograma + abcBilateral[orden_cripto[i]]
                criptograma = criptograma + abcBilateral[orden_criptoMenos[i+1]]
            }
        }
        tvResultado.text=criptograma
    }

    //Crptación Venegere
    fun criptVenegere(frase : String) {
        var clave = "run"
        var frase_len: Int = 0
        var clave_len: Int = 0

        var orden_criptoFrase = IntArray(1000)
        var orden_criptoClave = IntArray(1000)
        var orden_cripto=IntArray(1000)

        val abcVenegere: String = "abcdefghijklmnñopqrstuvwxyz ."
        val abcVenegere_len: Int = abcVenegere.length

        frase_len = frase.length
        clave_len=clave.length

        for (i in 0..frase_len -1) {
            for (j in 0..abcVenegere_len -1) {
                if (frase[i] == abcVenegere[j]) {
                    orden_criptoFrase[i]=j
                }
            }
        }

        for (i in 0..frase_len -1) {
            val letraClave = i % clave_len
            for (j in 0..abcVenegere_len -1) {
                if (clave[letraClave]== abcVenegere[j]){
                    orden_criptoClave[i]=j
                }
            }

        }

        //for (i in 0..frase_len - 1) {
          //  orden_cripto[i]=(orden_criptoFrase[i]+orden_criptoClave[i]) % abcVenegere_len
        //}

        for (i in 0..frase_len - 1) {
            if (i%3==0){
                orden_cripto[i]=(orden_criptoFrase[i]+orden_criptoClave[i]) % abcVenegere_len
                if (orden_cripto[i]>=abcVenegere_len){
                    orden_cripto[i]=orden_cripto[i]-abcVenegere_len
                }
            }
            if (i%3==1){
                orden_cripto[i]=(orden_criptoFrase[i]+orden_criptoClave[i]) % abcVenegere_len
                if (orden_cripto[i]>=abcVenegere_len){
                    orden_cripto[i]=orden_cripto[i]-abcVenegere_len
                }
            }
            if (i%3==2){
                orden_cripto[i]=(orden_criptoFrase[i]+orden_criptoClave[i]) % abcVenegere_len
                if (orden_cripto[i]>=abcVenegere_len){
                    orden_cripto[i]=orden_cripto[i]-abcVenegere_len
                }
            }
        }

        var criptograma = ""
        for (i in 0..frase_len - 1) {
            criptograma = criptograma + abcVenegere[orden_cripto[i]]
        }
        tvResultado.text=criptograma
    }
    fun desencriptVenegere(frase : String) {
        var clave = "run"
        var frase_len: Int = 0
        var clave_len: Int = 0

        var orden_criptoFrase = IntArray(1000)
        var orden_criptoClave = IntArray(1000)
        var orden_cripto=IntArray(1000)

        val abcVenegere: String = "abcdefghijklmnñopqrstuvwxyz ."
        val abcVenegere_len: Int = abcVenegere.length

        frase_len = frase.length
        clave_len=clave.length

        for (i in 0..frase_len -1) {
            for (j in 0..abcVenegere_len -1) {
                if (frase[i] == abcVenegere[j]) {
                    orden_criptoFrase[i]=j
                }
            }
        }

        for (i in 0..frase_len -1) {
            val letraClave = i % clave_len
            for (j in 0..abcVenegere_len -1) {
                if (clave[letraClave]== abcVenegere[j]){
                    orden_criptoClave[i]=j
                }
            }
        }

        for (i in 0..frase_len - 1) {
          orden_cripto[i]=(orden_criptoFrase[i]-orden_criptoClave[i] + abcVenegere_len) %abcVenegere_len
        }

        var criptograma = ""
        for (i in 0..frase_len - 1) {
            criptograma = criptograma + abcVenegere[orden_cripto[i]]
        }
        tvResultado.text=criptograma
    }

    fun criptogramaAFrase(){
        etFrase.setText(tvResultado.text)
    }

    fun textoAFrase(){
        etFrase.setText("fall seven times stand up eight")
    }



}