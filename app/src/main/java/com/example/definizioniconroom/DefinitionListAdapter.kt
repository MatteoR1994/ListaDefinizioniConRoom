package com.example.definizioniconroom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class DefinitionListAdapter(private val context: Context) :
    RecyclerView.Adapter<DefinitionListAdapter.DefinitionViewHolder>() {

    private var definitionList: List<Definition> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionViewHolder { //viewHolder oggetto che si ricorda come
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)//è la view e quale dato
        return DefinitionViewHolder(itemView)  //va in quale cella
    }

    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) { // prende i dati nella x posizione della lista
        val definition = definitionList[position] //li passa nella x view della recycleview
        holder.setData(definition.word, definition.description, position) //bind=legare,vincolare
    }

    override fun getItemCount(): Int = definitionList.size

    fun setDefinitions(definitions: List<Definition>) { //viene chiamato sull'adapter da fuori x dirgli che è cambiata la lista di dati
        definitionList = definitions
        notifyDataSetChanged() // devo riaggiornare la lista sul display
    }

    inner class DefinitionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var pos: Int = 0

        fun setData(dWord: String, dDefinition: String, position: Int) {
            itemView.wordText.text= dWord
            itemView.definitionText.text = dDefinition
            this.pos = position
        }
    }
}