package br.com.jean.orgs.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.jean.orgs.R
import br.com.jean.orgs.model.Produto
import br.com.jean.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvListaProdutos: RecyclerView = findViewById(R.id.rvListaProdutos)
        rvListaProdutos.adapter = ListaProdutosAdapter(
            listOf(
                Produto(
                    "Salada de Frutas",
                    "Abacaxi, banana e maça",
                    BigDecimal("20.0")
                ),
                Produto(
                    "Salada de Frutas2",
                    "Abacaxi, banana e maça2",
                    BigDecimal("22.0")
                )
            ),
            this
        )


        rvListaProdutos.layoutManager = LinearLayoutManager(this)
    }

}