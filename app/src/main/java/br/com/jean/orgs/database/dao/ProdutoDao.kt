package br.com.jean.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.jean.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM produto")
    fun buscaTodos(): List<Produto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvar(produto: Produto)

    @Delete
    fun deletar(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id = :idProduto")
    fun buscarPorId(idProduto: Long): Produto?
}