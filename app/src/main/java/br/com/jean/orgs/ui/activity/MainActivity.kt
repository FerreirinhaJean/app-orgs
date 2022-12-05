package br.com.jean.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import br.com.jean.orgs.dao.ProdutosDao
import br.com.jean.orgs.database.AppDatabase
import br.com.jean.orgs.database.dao.ProdutoDao
import br.com.jean.orgs.databinding.ActivityMainBinding
import br.com.jean.orgs.model.Produto
import br.com.jean.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

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
        val db = AppDatabase.getInstance(this)
        adapter.atualiza(db.produtoDao().buscaTodos())
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