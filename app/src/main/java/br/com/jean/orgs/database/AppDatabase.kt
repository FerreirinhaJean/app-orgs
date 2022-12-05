package br.com.jean.orgs.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.jean.orgs.database.converter.Converters
import br.com.jean.orgs.database.dao.ProdutoDao
import br.com.jean.orgs.model.Produto

@Database(entities = [Produto::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao() :ProdutoDao
}