package br.com.jean.orgs.ui.dialog

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.jean.orgs.databinding.FormularioImagensBinding
import br.com.jean.orgs.extensions.tentaCarregarImagem
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

class FormularioImagemDialog(
    val context: Context
) {

    val imageLoader: ImageLoader = ImageLoader.Builder(context).components {
        if (Build.VERSION.SDK_INT >= 28)
            add(ImageDecoderDecoder.Factory())
        else
            add(GifDecoder.Factory())
    }.build()

    fun mostrar(
        urlPadrao: String? = null,
        quandoImagemCarregada: (url: String, imageLoader: ImageLoader) -> Unit
    ) {
        FormularioImagensBinding.inflate(LayoutInflater.from(context)).apply {
            urlPadrao?.let {
                formularioImagensImageView.tentaCarregarImagem(it)
                formularioImagemTextInputLayoutUrl.editText?.setText(it)
            }

            val btCarregar = formularioImagensButtonCarregar
            val etUrl = formularioImagemTextInputLayoutUrl
            val imagemFormulario = formularioImagensImageView

            btCarregar.setOnClickListener {
                val url = etUrl.editText?.text.toString()
                imagemFormulario.tentaCarregarImagem(url)
            }

            val builder = AlertDialog.Builder(context)
            builder.setView(root)
            builder.setPositiveButton("Confimar") { _, _ ->
                val url = etUrl.editText?.text.toString()
                quandoImagemCarregada(url, imageLoader)
            }
            builder.setNegativeButton("Cancelar") { _, _ -> }
            builder.show()
        }
    }

}