package com.example.musiclyricsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musiclyricsapp.databinding.LyricsHistoryListItemBinding
import kotlinx.coroutines.runBlocking
import song_database.DbAccess
import song_database.SongLyrics
import java.lang.reflect.Method


class SongHistoryAdapter(private val list: ArrayList<SongLyrics>) :
    RecyclerView.Adapter<SongHistoryAdapter.Adapter>() {

    private var isExpandable: Boolean = false

    private lateinit var context: Context

    class Adapter(view: View) : RecyclerView.ViewHolder(view) {
        var listItemBinding: LyricsHistoryListItemBinding = DataBindingUtil.bind(view)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter {
        val inflater = LayoutInflater.from(parent.context)
        context = LayoutInflater.from(parent.context).context
        //data binding
        val binder = DataBindingUtil.inflate<LyricsHistoryListItemBinding>(
            inflater,
            R.layout.lyrics_history_list_item,
            parent,
            false
        )
        //apply function listen to our view
        return Adapter(binder.root).listen { pos, _ ->
            showMenu(binder.root, R.menu.popup_menu, pos)
        }
    }

    private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnLongClickListener {
            event.invoke(adapterPosition, itemViewType)
            true
        }
        return this
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Adapter, position: Int) {
        holder.listItemBinding.apply {
            historyArtist.text = list[position].artist
            historySong.text = list[position].song
            historySongText.text = list[position].lyrics
            expandableView.visibility = if (isExpandable) {
                View.VISIBLE
            } else {
                View.GONE
            }

            recyclerArrow.setOnClickListener {
                isExpandable = !isExpandable
                notifyItemChanged(position)
                recyclerArrow.rotation = if (!isExpandable) 180f else 0f
            }
        }

    }

    private fun showMenu(v: View, @MenuRes menuRes: Int, position: Int) {
        val popup = PopupMenu(context, v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        runCatching {
            val method: Method = popup.menu.javaClass.getDeclaredMethod(
                "setOptionalIconsVisible",
                Boolean::class.javaPrimitiveType
            )
            method.isAccessible = true
            method(popup.menu, true)
        }.getOrThrow()

        popup.setOnMenuItemClickListener {
            runBlocking {
                DbAccess.getDatabase().lyricsDao().deleteLyricsSong(list.removeAt(position))
            }
            notifyDataSetChanged()
            notifyItemRemoved(position)
            true
        }
        popup.setOnDismissListener {
            popup.dismiss()
        }
        popup.show()
    }
}