package com.example.musiclyricsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musiclyricsapp.databinding.LyricsHistoryListItemBinding
import song_database.SongLyrics

class SongHistoryAdapter(private val list:List<SongLyrics> ):
    RecyclerView.Adapter<SongHistoryAdapter.Adapter>() {
    private var isExpandable:Boolean=false
    class Adapter(view: View):RecyclerView.ViewHolder(view) {
       var listItemBinding:LyricsHistoryListItemBinding = DataBindingUtil.bind(view)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter {
        val inflater=LayoutInflater.from(parent.context)
        val binder=DataBindingUtil.inflate<LyricsHistoryListItemBinding>(inflater,R.layout.lyrics_history_list_item,parent,false)
        return Adapter(binder.root)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Adapter, position: Int) {
        holder.listItemBinding.apply {
            historyArtist.text=list[position].artist
            historySong.text=list[position].song
            historySongText.text=list[position].lyrics

            expandableView.visibility=if(isExpandable){
                View.VISIBLE
            } else{
                View.GONE
            }

            recyclerArrow.setOnClickListener {
                val deg = if (recyclerArrow.rotation == 180F){
                    0F
                } else {
                    180F
                }
                recyclerArrow.animate().rotation(deg).interpolator =
                    AccelerateDecelerateInterpolator()

                isExpandable=!isExpandable
                notifyItemChanged(position)
            }
        }

    }
}