package br.com.jean.orgs.database.dao

import androidx.room.*
import br.com.jean.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM produto")
    suspend fun buscaTodos(): List<Produto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvar(produto: Produto)

    @Delete
    suspend fun deletar(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id = :idProduto")
    suspend fun buscarPorId(idProduto: Long): Produto?
}