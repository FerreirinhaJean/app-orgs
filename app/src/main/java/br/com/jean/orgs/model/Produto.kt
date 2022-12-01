package br.com.jean.orgs.model

import java.math.BigDecimal

class Produto(
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val urlImagem: String? = null
) {

}
