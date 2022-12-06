package br.com.jean.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import br.com.jean.orgs.database.AppDatabase
import br.com.jean.orgs.databinding.ActivityMainBinding
import br.com.jean.orgs.model.Produto
import br.com.jean.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val adapter = ListaProdutosAdapter(this)
    private val handlerException by lazy {
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Toast.makeText(this@MainActivity, "Deu erro", Toast.LENGTH_LONG).show()
        }
    }

    private val produtoDao by lazy {
        AppDatabase.getInstance(this).produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch(handlerException) {
            val produtos = produtoDao.buscaTodos()
            adapter.atualiza(produtos)
        }
    }

    private fun configuraRecyclerView() {
        val rvListaProdutos: RecyclerView = binding.rvListaProdutos
        rvListaProdutos.adapter = adapter
        adapter.quandoClicaNoItem = {
            val intent = Intent(this, DetalhesProdutoActivity::class.java).putExtra(
                CHAVE_ID_PRODUTO, it.id
            )

            startActivity(intent)
        }
    }

    private fun configuraFab() {
        val floatingActionButton = binding.floatingActionButton
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, FormularioProdutoActivity::class.java)
            startActivity(intent)
        }
    }
}