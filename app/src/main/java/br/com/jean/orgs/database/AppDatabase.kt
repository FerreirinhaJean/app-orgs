package br.com.jean.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.jean.orgs.database.converter.Converters
import br.com.jean.orgs.database.dao.ProdutoDao
import br.com.jean.orgs.model.Produto

@Database(entities = [Produto::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao

    companion object {
        @Volatile
        private lateinit var db: AppDatabase

        fun getInstance(context: Context): AppDatabase {
            if (::db.isInitialized) return db
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "orgs.db"
            ).build().also {
                db = it
            }
        }
    }


}