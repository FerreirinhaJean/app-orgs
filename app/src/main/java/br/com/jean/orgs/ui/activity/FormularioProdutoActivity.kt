package br.com.jean.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.jean.orgs.dao.ProdutosDao
import br.com.jean.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.jean.orgs.extensions.tentaCarregarImagem
import br.com.jean.orgs.model.Produto
import br.com.jean.orgs.ui.dialog.FormularioImagemDialog
import com.google.android.material.textfield.TextInputLayout
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    private var url: String? = null
    private val dao = ProdutosDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar Produto"


        configuraBotaoSalvar()
        val produtoImagem = binding.activityFormularioProdutoImagem

        produtoImagem.setOnClickListener {
            FormularioImagemDialog(this).mostrar(url) { urlImagem, loader ->
                url = urlImagem
                binding.activityFormularioProdutoImagem.tentaCarregarImagem(url)
            }
        }
    }

    private fun configuraBotaoSalvar() {
        val etNomeProduto = binding.activityFormularioProdutoNome
        val etDescricaoProduto = binding.activityFormularioProdutoDescricao
        val etPrecoProduto = binding.activityFormularioProdutoPreco
        val btSalvar = binding.btSalvar

        btSalvar.setOnClickListener {
            val produto = criaProduto(etNomeProduto, etDescricaoProduto, etPrecoProduto)
            dao.adiciona(produto)
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