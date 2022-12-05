package br.com.jean.orgs.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
class Produto(
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val urlImagem: String? = null
) : Parcelable
