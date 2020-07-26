package com.oe.nik.krujzgergely.ui.lyrics

import android.content.Context
import android.os.Build
import android.view.*
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.databinding.RecyclerItemLyricsModelBinding
import com.oe.nik.krujzgergely.models.LyricsModel
import java.util.*

class LyricsActivityAdapter(private var context: Context, private var LyricsList : MutableList<LyricsModel>)
    : RecyclerView.Adapter<LyricsActivityAdapter.ViewHolder>()
{
    companion object { lateinit var currentLyrics : LyricsModel }

    private var backUpListForTheSearchField = mutableListOf<LyricsModel>()

    private val layoutinflater : LayoutInflater = LayoutInflater.from(context)

    private lateinit var listener: IonLyricsClickEvent

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ViewHolder
    {
        when(context) {is IonLyricsClickEvent -> listener = context as IonLyricsClickEvent }

        val recyclerItemLyricsModelBinding =
            RecyclerItemLyricsModelBinding.inflate(layoutinflater, parent, false)

        return ViewHolder(recyclerItemLyricsModelBinding.root, recyclerItemLyricsModelBinding)
    }

    override fun getItemCount(): Int = LyricsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.setData(LyricsList[position])
        holder.setOptionsMenu(LyricsList[position])
    }

    fun swapData(lyricsList: List<LyricsModel>)
    {
        clearDataFromTheLists()
        addDataToTheLists(lyricsList)
        notifyDataSetChanged()
    }

    private fun clearDataFromTheLists()
    {
        this.LyricsList.clear()
        this.backUpListForTheSearchField.clear()
    }

    private fun addDataToTheLists(lyricsList: List<LyricsModel>)
    {
        this.LyricsList.addAll(lyricsList)
        this.backUpListForTheSearchField.addAll(lyricsList)
    }

    fun getSearchedDataFromTheList(searchedData : String)
    {
        LyricsList.clear()
        backUpListForTheSearchField.forEach {LyricsModel ->
            val lowerCaseLyricsTitle = lowerCaseStringData(LyricsModel.title)
            val lowerCaseLyricsPerformer = lowerCaseStringData(LyricsModel.performer)
            val lowerCaseSearchData = lowerCaseStringData(searchedData)
            if (checkIfPerformerOrTitleContainsSearchedText(lowerCaseLyricsPerformer,lowerCaseLyricsTitle,lowerCaseSearchData))
            {
                LyricsList.add(LyricsModel)
                notifyDataSetChanged()
            }
        }
    }

    fun noItemFoundInLyricsListWhenSearched()
    {
        LyricsList.clear()
        LyricsList.addAll(backUpListForTheSearchField)
        notifyDataSetChanged()
    }

    private fun lowerCaseStringData(data : String) : String { return data.toLowerCase(Locale.getDefault()) }

    private fun checkIfPerformerOrTitleContainsSearchedText(performer : String, title : String, searchedData: String) : Boolean
    {
        return (performer.contains(searchedData) || title.contains(searchedData))
    }

    inner class ViewHolder(private var view: View, private val recyclerItemLyricsModelBinding: RecyclerItemLyricsModelBinding)
        : RecyclerView.ViewHolder(view), PopupMenu.OnMenuItemClickListener, View.OnClickListener {

        private val songTypeImageview: ImageView = view.findViewById<ImageView>(R.id.song_image)
        private val recycleritemlyricsmodelgridlayout : GridLayout = view.findViewById(R.id.recycleritemlyricsmodelgridlayout)
        private val optionsMenu = view.findViewById<TextView>(R.id.textViewOptions)
        private var currentLyricsModel : LyricsModel? = null

        fun setOptionsMenu(lyricsModel: LyricsModel?)
        {
            currentLyricsModel = lyricsModel
            optionsMenu.setOnClickListener(this)
        }

        fun setData(lyricsModel: LyricsModel?)
        {
            recycleritemlyricsmodelgridlayout.setOnClickListener {

            currentLyrics = lyricsModel!!

            listener.onLyricsSelected()}

            setImage(songTypeImageview, lyricsModel!!.song_type)

            recyclerItemLyricsModelBinding.lyricsModel = lyricsModel
        }

        fun setImage(song_imageview: ImageView, song_type: String)
        {
            when(song_type)
            {
                "JAZZ" -> Glide.with(context).load(R.drawable.jazz).into(song_imageview)
                "HIPHOP" -> Glide.with(context).load(R.drawable.hiphop).into(song_imageview)
                "ROCK" -> Glide.with(context).load(R.drawable.rock).into(song_imageview)
                "METAL" -> Glide.with(context).load(R.drawable.metal).into(song_imageview)
                "PUNK" -> Glide.with(context).load(R.drawable.punk).into(song_imageview)
                "POP" -> Glide.with(context).load(R.drawable.pop).into(song_imageview)
                "COUNTRY" -> Glide.with(context).load(R.drawable.country).into(song_imageview)
                else -> Glide.with(context).load(R.drawable.opera).into(song_imageview)
            }
        }

        override fun onClick(v: View?)
        {
            val popupMenu = PopupMenu(view.context,view)
            popupMenu.inflate(R.menu.recycler_item_menu_options)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                popupMenu.setForceShowIcon(true)
            }
            popupMenu.show()
            popupMenu.setOnMenuItemClickListener(this)
        }

        override fun onMenuItemClick(item: MenuItem?): Boolean
        {
            when(item!!.itemId)
            {
                R.id.UpdateLyricsOption ->
                {
                    currentLyrics = currentLyricsModel!!
                    listener.onUpdateOptionsClicked()
                    return true
                }
                R.id.DeleteLyricsOption ->
                {
                    currentLyrics = currentLyricsModel!!
                    listener.onDeleteOptionsClicked(currentLyrics)
                    return true
                }
                else -> return false
            }
        }
    }
}