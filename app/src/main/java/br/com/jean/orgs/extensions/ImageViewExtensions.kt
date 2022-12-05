package br.com.jean.orgs.extensions

import android.widget.ImageView
import br.com.jean.orgs.R
import coil.ImageLoader
import coil.load

fun ImageView.tentaCarregarImagem(
    url: String? = null,
    fallback: Int = R.drawable.imagem_padrao
) {
    load(url) {
        fallback(fallback)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}