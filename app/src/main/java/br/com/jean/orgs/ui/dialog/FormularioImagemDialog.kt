package br.com.jean.orgs.ui.dialog

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import br.com.jean.orgs.databinding.FormularioImagensBinding
import br.com.jean.orgs.extensions.carregarImagem
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

    fun mostrar(quandoImagemCarregada: (url: String, imageLoader: ImageLoader) -> Unit) {
        val binding = FormularioImagensBinding.inflate(LayoutInflater.from(context))
        val btCarregar = binding.formularioImagensButtonCarregar
        val etUrl = binding.formularioImagemTextInputLayoutUrl
        val imagemFormulario = binding.formularioImagensImageView

        btCarregar.setOnClickListener {
            val url = etUrl.editText?.text.toString()
            imagemFormulario.carregarImagem(url, imageLoader)
        }

        val builder = AlertDialog.Builder(context)
        builder.setView(binding.root)
        builder.setPositiveButton("Confimar") { _, _ ->
            val url = etUrl.editText?.text.toString()
            quandoImagemCarregada(url, imageLoader)
        }
        builder.setNegativeButton("Cancelar") { _, _ -> }
        builder.show()
    }

}