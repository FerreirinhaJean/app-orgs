package br.com.jean.orgs.database.dao

import androidx.room.Database
import br.com.jean.orgs.dao.ProdutosDao
import br.com.jean.orgs.model.Produto

@Database(entities = [Produto::class], version = 1)
abstract class AppDatabase {
    abstract fun produtoDao(): ProdutosDao
}