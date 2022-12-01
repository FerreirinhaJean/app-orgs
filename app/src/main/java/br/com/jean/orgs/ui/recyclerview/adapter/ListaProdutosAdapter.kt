package br.com.jean.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.jean.orgs.R
import br.com.jean.orgs.databinding.ActivityMainBinding
import br.com.jean.orgs.databinding.ProdutoItemBinding
import br.com.jean.orgs.model.Produto
import coil.load
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class ListaProdutosAdapter(
    produtos: List<Produto>,
    private val context: Context
) : RecyclerView.Adapter<ListaProdutosAdapter.MyViewHolder>() {

    private val produtos = produtos.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ProdutoItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val produto = produtos.get(position)
        holder.vincula(produto)
    }

    override fun getItemCount(): Int {
        return produtos.size
    }

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

    class MyViewHolder(
        binding: ProdutoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        val nomeProduto = binding.tvNomeProduto
        val descricaoProduto = binding.tvDescricaoProduto
        val precoProduto = binding.tvPrecoProduto
        val imagem = binding.imageView

        fun vincula(produto: Produto) {
            nomeProduto.text = produto.nome
            descricaoProduto.text = produto.descricao
            precoProduto.text = formataValorBr(produto.valor)
            imagem.load("https://s2.glbimg.com/rl2qA0jqm8CYvBh7ZTnAu8NG6ds=/0x0:1999x3000/924x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_b58693ed41d04a39826739159bf600a0/internal_photos/bs/2021/N/1/8FdLsPS2G4uiUV4a8MOg/abacaxi.jpg")
        }

        private fun formataValorBr(valor: BigDecimal): String? {
            val format = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            return format.format(valor)
        }

    }


}
