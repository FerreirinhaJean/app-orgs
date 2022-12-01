package br.com.jean.orgs.ui.recyclerview.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.jean.orgs.R
import br.com.jean.orgs.databinding.ActivityMainBinding
import br.com.jean.orgs.databinding.ProdutoItemBinding
import br.com.jean.orgs.extensions.carregarImagem
import br.com.jean.orgs.model.Produto
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
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

            val visibilidade = if (produto.urlImagem != null)
                View.VISIBLE
            else
                View.GONE

            imagem.visibility = visibilidade
            imagem.load(produto.urlImagem) {
                fallback(R.drawable.erro)
                error(R.drawable.erro)
            }
        }

        private fun formataValorBr(valor: BigDecimal): String? {
            val format = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            return format.format(valor)
        }

    }


}
