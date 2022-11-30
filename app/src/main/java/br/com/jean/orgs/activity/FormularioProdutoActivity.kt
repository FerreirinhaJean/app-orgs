package br.com.jean.orgs.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import br.com.jean.orgs.R
import br.com.jean.orgs.dao.ProdutoDao
import br.com.jean.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {

    private val dao = ProdutoDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configuraBotaoSalvar()
    }

    private fun configuraBotaoSalvar() {
        val etNomeProduto = findViewById<EditText>(R.id.etNomeProduto)
        val etDescricaoProduto = findViewById<EditText>(R.id.etDescricaoProduto)
        val etPrecoProduto = findViewById<EditText>(R.id.etPrecoProduto)
        val btSalvar = findViewById<Button>(R.id.btSalvar)

        btSalvar.setOnClickListener {
            val produto = criaProduto(etNomeProduto, etDescricaoProduto, etPrecoProduto)
            dao.adicionar(produto)
            finish()
        }
    }

    private fun criaProduto(
        etNomeProduto: EditText,
        etDescricaoProduto: EditText,
        etPrecoProduto: EditText
    ): Produto {
        val nome = etNomeProduto.text.toString()
        val descricao = etDescricaoProduto.text.toString()
        val preco = etPrecoProduto.text.toString()

        val valor = if (preco.isBlank())
            BigDecimal.ZERO
        else
            BigDecimal(preco)

        val produto = Produto(
            nome = nome,
            descricao = descricao,
            valor = valor
        )
        return produto
    }

}