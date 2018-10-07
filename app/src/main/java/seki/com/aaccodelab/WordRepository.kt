package seki.com.aaccodelab

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class WordRepository(application: Application) {
    private val db: WordRoomDatabase = WordRoomDatabase.getDatabase(application)
    private val wordDao: WordDao = db.wordDao()
    val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    fun insert(word: Word) {
        InsertAsyncTask(wordDao).execute(word)
    }

    private class InsertAsyncTask(val dao: WordDao): AsyncTask<Word, Unit, Unit>() {
        override fun doInBackground(vararg params: Word) {
            dao.insert(params[0])
        }
    }
}