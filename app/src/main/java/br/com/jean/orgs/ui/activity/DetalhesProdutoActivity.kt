package br.com.jean.orgs.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import br.com.jean.orgs.R
import br.com.jean.orgs.database.AppDatabase
import br.com.jean.orgs.databinding.ActivityDetalhesProdutoBinding
import br.com.jean.orgs.extensions.formataParaMoedaBrasileira
import br.com.jean.orgs.extensions.tentaCarregarImagem
import br.com.jean.orgs.model.Produto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetalhesProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }
    private val produtoDao by lazy {
        AppDatabase.getInstance(this).produtoDao()
    }
    private var produto: Produto? = null
    private var id: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    private fun tentaCarregarProduto() {
        id = intent.getLongExtra(CHAVE_ID_PRODUTO, 0L)
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            produto = produtoDao.buscarPorId(id)
            produto?.let {
                preencheCampos(it)
            } ?: finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_detalhes_produto_editar -> {
                val intent = Intent(this, FormularioProdutoActivity::class.java).apply {
                    putExtra(CHAVE_ID_PRODUTO, produto?.id)
                }
                startActivity(intent)
            }
            R.id.menu_detalhes_produto_remover -> {
                lifecycleScope.launch {
                    produto?.let { produtoDao.deletar(it) }
                    finish()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }


    private fun preencheCampos(produtoCarregado: Produto) {
        with(binding) {
            activityDetalhesProdutoImagem.tentaCarregarImagem(produtoCarregado.urlImagem)
            activityDetalhesProdutoNome.text = produtoCarregado.nome
            activityDetalhesProdutoDescricao.text = produtoCarregado.descricao
            activityDetalhesProdutoValor.text =
                produtoCarregado.valor.formataParaMoedaBrasileira()
        }
    }
}