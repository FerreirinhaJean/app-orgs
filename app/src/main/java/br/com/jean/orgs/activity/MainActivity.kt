package br.com.jean.orgs.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.jean.orgs.R
import br.com.jean.orgs.model.Produto
import br.com.jean.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.math.BigDecimal

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener{
            val intent = Intent(this, FormularioProdutoActivity::class.java)
            startActivity(intent)
        }
    }

}