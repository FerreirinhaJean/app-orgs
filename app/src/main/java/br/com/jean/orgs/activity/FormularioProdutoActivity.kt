package br.com.jean.orgs.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import br.com.jean.orgs.R
import br.com.jean.orgs.dao.ProdutoDao
import br.com.jean.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.jean.orgs.model.Produto
import com.google.android.material.textfield.TextInputLayout
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    private val dao = ProdutoDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configuraBotaoSalvar()
        val produtoImagem = binding.activityFormularioProdutoImagem

        produtoImagem.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setPositiveButton("Confimar") { _, _ -> }
            builder.setNegativeButton("Cancelar") { _, _ -> }
            builder.setView(R.layout.formulario_imagens)
            builder.show()
        }
    }

    private fun configuraBotaoSalvar() {
        val etNomeProduto = binding.activityFormularioProdutoNome
        val etDescricaoProduto = binding.activityFormularioProdutoDescricao
        val etPrecoProduto = binding.activityFormularioProdutoPreco
        val btSalvar = binding.btSalvar

        btSalvar.setOnClickListener {
            val produto = criaProduto(etNomeProduto, etDescricaoProduto, etPrecoProduto)
            dao.adicionar(produto)
            finish()
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
            nome = nome,
            descricao = descricao,
            valor = valor
        )
        return produto
    }

}