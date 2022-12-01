package br.com.jean.orgs.extensions

import android.widget.ImageView
import br.com.jean.orgs.R
import coil.ImageLoader
import coil.load

fun ImageView.carregarImagem(url: String? = null, imageLoader: ImageLoader) {
    load(url, imageLoader) {
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(R.drawable.imagem_padrao)
    }
}