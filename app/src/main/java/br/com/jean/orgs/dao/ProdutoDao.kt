package br.com.jean.orgs.dao

import br.com.jean.orgs.model.Produto

class ProdutoDao() {

    fun adicionar(produto: Produto) {
        Companion.produtos.add(produto)
    }

    fun obterTodos(): List<Produto> {
        return Companion.produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>()
    }


}