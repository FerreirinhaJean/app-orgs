package br.com.jean.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.jean.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM produto")
    fun buscaTodos()

    @Insert
    fun salvar(produto: Produto)


}