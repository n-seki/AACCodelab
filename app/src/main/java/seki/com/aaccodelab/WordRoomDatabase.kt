package seki.com.aaccodelab

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        private var INSTANCE: WordRoomDatabase? = null
        fun getDatabase(context: Context): WordRoomDatabase {
            if (INSTANCE == null) {
                INSTANCE = synchronized(WordRoomDatabase::class.java) {
                    Room.databaseBuilder(context.applicationContext,
                            WordRoomDatabase::class.java, "word_database")
                            .addCallback(sRoomDatabase)
                            .build()
                }
            }
            return INSTANCE!!
        }

        private val sRoomDatabase: RoomDatabase.Callback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }

        class PopulateDbAsync(db: WordRoomDatabase): AsyncTask<Unit, Unit, Unit>() {

            private val dao = db.wordDao()

            override fun doInBackground(vararg params: Unit) {
                dao.deleteAll()
                val word1 = Word("Hello")
                dao.insert(word1)
                val word2 = Word("World")
                dao.insert(word2)
            }
        }
    }
}