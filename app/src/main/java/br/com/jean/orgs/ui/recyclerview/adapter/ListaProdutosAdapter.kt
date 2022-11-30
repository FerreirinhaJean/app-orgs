package br.com.jean.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.jean.orgs.R
import br.com.jean.orgs.model.Produto

class ListaProdutosAdapter(
    produtos: List<Produto>,
    private val context: Context
) : RecyclerView.Adapter<ListaProdutosAdapter.MyViewHolder>() {

    private val produtos = produtos.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.produto_item, parent, false)

        return MyViewHolder(view)
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
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val nomeProduto = itemView.findViewById<TextView>(R.id.tvNomeProduto)
        val descricaoProduto = itemView.findViewById<TextView>(R.id.tvDescricaoProduto)
        val precoProduto = itemView.findViewById<TextView>(R.id.tvPrecoProduto)

        fun vincula(produto: Produto) {
            nomeProduto.text = produto.nome
            descricaoProduto.text = produto.descricao
            precoProduto.text = produto.valor.toPlainString()
        }

    }


}
