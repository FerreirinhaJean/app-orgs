package br.com.jean.orgs.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Entity
@Parcelize
class Produto(
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val urlImagem: String? = null
) : Parcelable
