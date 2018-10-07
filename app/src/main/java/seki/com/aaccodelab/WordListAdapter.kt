package seki.com.aaccodelab

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class WordListAdapter(context: Context)
    : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    var words: List<Word> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount() = words.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (words.isNotEmpty()) {
            val current: Word = words[position]
            holder.wordItemView.text = current.word
        } else {
            holder.wordItemView.text = "No Word"
        }
    }

    class WordViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
    }
}