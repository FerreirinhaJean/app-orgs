package br.com.jean.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import br.com.jean.orgs.R
import br.com.jean.orgs.databinding.ActivityDetalhesProdutoBinding
import br.com.jean.orgs.extensions.formataParaMoedaBrasileira
import br.com.jean.orgs.extensions.tentaCarregarImagem
import br.com.jean.orgs.model.Produto

class DetalhesProdutoActivity : AppCompatActivity() {

    private val TAG = "DetalhesProdutoActivity"

    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    private fun tentaCarregarProduto() {
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            preencheCampos(produtoCarregado)
        } ?: finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_detalhes_produto_editar -> {
                Log.i(TAG, "onOptionsItemSelected: editar")
            }
            R.id.menu_detalhes_produto_remover -> {
                Log.i(TAG, "onOptionsItemSelected: remover")
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