package br.com.jean.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.jean.orgs.database.AppDatabase
import br.com.jean.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.jean.orgs.extensions.tentaCarregarImagem
import br.com.jean.orgs.model.Produto
import br.com.jean.orgs.ui.dialog.FormularioImagemDialog
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    private val produtoDao by lazy {
        AppDatabase.getInstance(this).produtoDao()
    }

    private val scope = CoroutineScope(IO)

    private var url: String? = null
    private var idProduto = 0L

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

        tentaCarregarProduto()
    }

    override fun onResume() {
        super.onResume()
        scope.launch {
            produtoDao.buscarPorId(idProduto)?.let {
                title = "Atualizar Produto"
                withContext(Dispatchers.Main) {
                    preencheCampos(it)
                }
            }
        }
    }

    private fun tentaCarregarProduto() {
        idProduto = intent.getLongExtra(CHAVE_ID_PRODUTO, 0L)
    }

    private fun preencheCampos(produto: Produto) {
        url = produto.urlImagem
        binding.activityFormularioProdutoNome.editText
            ?.setText(produto.nome)
        binding.activityFormularioProdutoDescricao.editText
            ?.setText(produto.descricao)
        binding.activityFormularioProdutoPreco.editText
            ?.setText(produto.valor.toPlainString())
        binding.activityFormularioProdutoImagem.tentaCarregarImagem(produto.urlImagem)
    }

    private fun configuraBotaoSalvar() {
        val etNomeProduto = binding.activityFormularioProdutoNome
        val etDescricaoProduto = binding.activityFormularioProdutoDescricao
        val etPrecoProduto = binding.activityFormularioProdutoPreco
        val btSalvar = binding.btSalvar

        btSalvar.setOnClickListener {
            val produto = criaProduto(etNomeProduto, etDescricaoProduto, etPrecoProduto)

            scope.launch {
                produtoDao.salvar(produto)
                finish()
            }
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
            id = idProduto,
            nome = nome,
            descricao = descricao,
            valor = valor,
            urlImagem = url
        )
        return produto
    }

}