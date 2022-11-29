package br.com.jean.orgs.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import br.com.jean.orgs.R
import br.com.jean.orgs.ui.recyclerview.adapter.ListaProdutosAdapter

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvListaProdutos: RecyclerView = findViewById(R.id.rvListaProdutos)
        rvListaProdutos.adapter = ListaProdutosAdapter()

//        val tvNomeProduto: TextView = findViewById(R.id.tvNomeProduto)
//        val tvDescricaoProduto: TextView = findViewById(R.id.tvDescricaoProduto)
//        val tvPrecoProduto: TextView = findViewById(R.id.tvPrecoProduto)
//
//        tvNomeProduto.text = "Cesta de Frutas"
//        tvDescricaoProduto.text = "Uva, abacaxi e banana"
//        tvPrecoProduto.text = "R$ 20,00"
    }

}