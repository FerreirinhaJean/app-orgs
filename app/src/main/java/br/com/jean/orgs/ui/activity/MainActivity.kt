package br.com.jean.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.jean.orgs.databinding.ActivityMainBinding
import br.com.jean.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import br.com.jean.orgs.dao.ProdutosDao

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val dao = ProdutosDao()
    private val adapter = ListaProdutosAdapter(this, dao.buscaTodos())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
    }

    private fun configuraRecyclerView() {
        val rvListaProdutos: RecyclerView = binding.rvListaProdutos
        rvListaProdutos.adapter = adapter
        adapter.quandoClicaNoItem = {
            val intent = Intent(this, DetalhesProdutoActivity::class.java)
                .putExtra(
                    CHAVE_PRODUTO,
                    it
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