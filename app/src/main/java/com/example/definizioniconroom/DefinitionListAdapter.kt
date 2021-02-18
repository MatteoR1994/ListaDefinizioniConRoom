package com.example.definizioniconroom

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class DefinitionListAdapter(private val context: Context, private val listener: OnDeleteClickListener) :
    RecyclerView.Adapter<DefinitionListAdapter.DefinitionViewHolder>() {

    private var definitionList: List<Definition> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionViewHolder { //viewHolder oggetto che si ricorda come
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)//è la view e quale dato
        return DefinitionViewHolder(itemView)  //va in quale cella
    }

    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) { // prende i dati nella x posizione della lista
        val definition = definitionList[position] //li passa nella x view della recycleview
        holder.setData(definition.word, definition.description, position) //bind=legare,vincolare
        holder.setListeners(position)
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
        fun setListeners(position: Int){ //un oggetto di classe interna vede le variabili della esterna
            itemView.deleteImg.setOnClickListener {
                listener.onDeleteDefinition(definitionList[position],position)
            }
            itemView.editImg.setOnClickListener{ // prende i parametri per l'update
                val intent = Intent(context,UpdateActivity::class.java)
                intent.putExtra("definizione",definitionList[position].description)
                intent.putExtra("parola", definitionList[position].word)
                intent.putExtra("id", definitionList[position].id)
                (context as Activity).startActivityForResult(intent,MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE)
            }
        } // viene assegnato come ascoltatore del click sull'img l'oggetto che è stato passato al costruttore dell'adapter col nome listener
    }
}