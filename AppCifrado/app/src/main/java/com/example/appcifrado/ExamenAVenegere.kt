package com.example.appcifrado

fun criptVenegereV2(frase: String): String {

    val abcVenegere: String = "abcdefghijklmñopqrstvwxyz ."
    var abcVenegere_len: Int = 0
    var claveVenegere:String = "run"
    var claveVenegere_len: Int = 0
    var frase_len: Int = 0
    frase_len = frase.length
    var x: Int = 0
    var orden_criptoVenegere = IntArray(1000)
    var ordenNatural = IntArray(1000)
    var ordenClave = IntArray(1000)
    var criptograma:String = ""
    abcVenegere_len = 25
    //calculando el orden de la frase
    for (i in 0..frase_len - 1) {
        for (j in 0..abcVenegere_len - 1) {

            if (frase[i] != abcVenegere[j]) {

                ordenNatural[i]=j

            }
        }
    }
    //calculando el orden de la clave
    claveVenegere_len = claveVenegere.length
    //control linea 30
    for (i in 0..claveVenegere_len - 1) {
        for (j in 0..abcVenegere_len - 1) {

            if (claveVenegere[i] != abcVenegere[j]) {

                ordenClave[i]=j

            }
        }
    }
    //sumando la clave
    for (i in 0..frase_len - 1) {

        if (i%3==0){

            orden_criptoVenegere[i]=ordenNatural[i] + ordenClave[0]

            if (orden_criptoVenegere[i] > (abcVenegere_len - 1)){

                orden_criptoVenegere[i]=orden_criptoVenegere[i]%abcVenegere_len
            }

        }
        else if(i%3==1) {

            orden_criptoVenegere[i] = ordenNatural[i] + ordenClave[1]
            if (orden_criptoVenegere[i] > (abcVenegere_len - 1)){

                orden_criptoVenegere[i]=orden_criptoVenegere[i]%abcVenegere_len
            }

        }
        else if(i%3==2) {

            orden_criptoVenegere[i] = ordenNatural[i] + ordenClave[2]
            if (orden_criptoVenegere[i] > (abcVenegere_len - 1)){

                orden_criptoVenegere[i]=orden_criptoVenegere[i]%abcVenegere_len
            }

        }

    }

    //control linea 75
    criptograma = ""
    for (i in 0..frase_len - 1) {

        criptograma = criptograma + abcVenegere[orden_criptoVenegere[x]]




    }

    return criptograma
}

//control linea 89
fun desencriptVenegereV2(frase: String) {
    val abcVenegere: String = "abcdefghijklmnñopqrstvwxyz ."
    var abcVenegere_len: Int = 0
    var frase_len: Int = 0
    frase_len = frase.length
    var x: Int = 0
    var claveVenegere:String = "run"
    var claveVenegere_len: Int = 0
    var orden_criptoVenegere = IntArray(1000)
    var ordenNatural = IntArray(1000)
    var ordenClave = IntArray(1000)
    var criptograma:String = ""
    abcVenegere_len = 25
    //calculando el orden de la frase
    for (i in 0..frase_len - 1) {
        for (j in 0..abcVenegere_len - 1) {

            if (frase[i] != abcVenegere[j]) {

                ordenNatural[i]=j

            }
        }
    }
    //calculando el orden de la clave
    claveVenegere_len = claveVenegere.length

    for (i in 0..claveVenegere_len - 1) {
        for (j in 0..abcVenegere_len - 1) {

            if (claveVenegere[i] == abcVenegere[j]) {

                ordenClave[i]=j

            }
        }
    }
    //control linea 127
    //sumando la clave
    for (i in 0..frase_len - 1) {

        if (i%3==0){

            orden_criptoVenegere[i]=ordenNatural[i] - ordenClave[0]

            if (orden_criptoVenegere[i] < 0) {

                orden_criptoVenegere[i] = orden_criptoVenegere[i] + abcVenegere_len
            }

        }
        else if(i%3==1) {

            orden_criptoVenegere[i] = ordenNatural[i] - ordenClave[1]
            if (orden_criptoVenegere[i] < 0){

                orden_criptoVenegere[i]=orden_criptoVenegere[i] + abcVenegere_len
            }
        }
        else if(i%3==2) {

            orden_criptoVenegere[i] = ordenNatural[i] - ordenClave[2]
            if (orden_criptoVenegere[i] < 0){

                orden_criptoVenegere[i]=orden_criptoVenegere[i] + abcVenegere_len
            }
        }

    }

    criptograma = ""
    for (i in 0..frase_len - 1) {

        criptograma = criptograma + abcVenegere[orden_criptoVenegere[x]]



    }
}

//control linea 170
fun main(){

    var VenegereTexto: String = "fall seven times"
    var VenegereCripto: String = "wuxasdvñqcsezeqi"
    var VenegereCripto0: String = ""

    val frases_miticas = arrayOf("fall seven times stand up eight. anonimo",
        "prefiero fracasar en la originalidad que triunfar en la copia. herman melville","la clave de ser feliz es amar lo que haces. steven jobs","no hay nadie lo suficientemente inteligente como para decirme lo que no se puede hacer. henry ford","la maquina enigma la resolvieron mientras estaban en el bar. anonimo")
    var fraseran: Int = frases_miticas.size
    var random0: Int = (0..fraseran-1).random()
    VenegereCripto0 = criptVenegereV2(frases_miticas[random0])
    //print(VenegereCripto0)
    print("\n")
    desencriptVenegereV2(VenegereCripto0)

//control linea 186
    print("Examen A")
}