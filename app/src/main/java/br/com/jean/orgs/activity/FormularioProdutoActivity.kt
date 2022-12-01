package br.com.jean.orgs.activity

import android.os.Build.VERSION.SDK_INT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import br.com.jean.orgs.R
import br.com.jean.orgs.dao.ProdutoDao
import br.com.jean.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.jean.orgs.databinding.FormularioImagensBinding
import br.com.jean.orgs.extensions.carregarImagem
import br.com.jean.orgs.model.Produto
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import com.google.android.material.textfield.TextInputLayout
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    private var url: String? = null
    private val dao = ProdutoDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val imageLoader = ImageLoader.Builder(this).components {
            if (SDK_INT >= 28)
                add(ImageDecoderDecoder.Factory())
            else
                add(GifDecoder.Factory())
        }.build()

        configuraBotaoSalvar()
        val produtoImagem = binding.activityFormularioProdutoImagem

        produtoImagem.setOnClickListener {
            val bindingFormularioImagem = FormularioImagensBinding.inflate(layoutInflater)
            val btCarregar = bindingFormularioImagem.formularioImagensButtonCarregar
            val etUrl = bindingFormularioImagem.formularioImagemTextInputLayoutUrl
            val imagemFormulario = bindingFormularioImagem.formularioImagensImageView

            btCarregar.setOnClickListener {
                url = etUrl.editText?.text.toString()
                imagemFormulario.carregarImagem(url, imageLoader)
            }

            val builder = AlertDialog.Builder(this)
            builder.setView(bindingFormularioImagem.root)
            builder.setPositiveButton("Confimar") { _, _ ->
                binding.activityFormularioProdutoImagem.carregarImagem(url, imageLoader)
            }
            builder.setNegativeButton("Cancelar") { _, _ -> }
            builder.show()
        }
    }

    private fun configuraBotaoSalvar() {
        val etNomeProduto = binding.activityFormularioProdutoNome
        val etDescricaoProduto = binding.activityFormularioProdutoDescricao
        val etPrecoProduto = binding.activityFormularioProdutoPreco
        val btSalvar = binding.btSalvar

        btSalvar.setOnClickListener {
            val produto = criaProduto(etNomeProduto, etDescricaoProduto, etPrecoProduto)
            dao.adicionar(produto)
            finish()
        }
    }

    private fun criaProduto(
        etNomeProduto: TextInputLayout,
        etDescricaoProduto: TextInputLayout,
        etPrecoProduto: TextInputLayout
    ): Produto {
        val nome = etNomeProduto.editText?.text.toString()
        val descricao = etDescricaoProduto.editText?.text.toString()
        val preco = etPrecoProduto.editText?.text.toString()

        val valor = if (preco.isBlank())
            BigDecimal.ZERO
        else
            BigDecimal(preco)

        val produto = Produto(
            nome = nome,
            descricao = descricao,
            valor = valor,
            urlImagem = url
        )
        return produto
    }

}