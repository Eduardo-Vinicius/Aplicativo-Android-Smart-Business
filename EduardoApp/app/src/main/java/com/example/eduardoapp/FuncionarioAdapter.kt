package com.example.eduardoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class FuncionarioAdapter (
    val funcionarios: List<Funcionario>,
    val onClick: (Funcionario) -> Unit): RecyclerView.Adapter<FuncionarioAdapter.FuncionarioViewHolder>() {
    // ViewHolder com os elementos da tela
    class FuncionarioViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg : ImageView
        var cardProgress: ProgressBar
        var cardView: CardView
        init {
            cardNome = view.findViewById<TextView>(R.id.cardNome)
            cardImg = view.findViewById<ImageView>(R.id.cardImg)
            cardProgress = view.findViewById<ProgressBar>(R.id.cardProgress)
            cardView = view.findViewById<CardView>(R.id.card_disciplinas)
        }
    }
    // Quantidade de disciplinas na lista
    override fun getItemCount() = this.funcionarios.size
    // inflar layout do adapter
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): FuncionarioViewHolder {
// infla view no adapter
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_funcionario, parent, false)
// retornar ViewHolder
        val holder = FuncionarioViewHolder(view)
        return holder
    }

    // bind para atualizar Views com os dados
    override fun onBindViewHolder(holder: FuncionarioViewHolder, position: Int) {
        val context = holder.itemView.context
// recuperar objeto disciplina
        val func = funcionarios[position]
// atualizar dados de disciplina
        holder.cardNome.text = func.nome
        holder.cardProgress.visibility = View.VISIBLE
// download da imagem
        Picasso.with(context).load(func.foto).fit().into(holder.cardImg,

            object: com.squareup.picasso.Callback{
                override fun onSuccess() {
                    holder.cardProgress.visibility = View.GONE
                }
                override fun onError() {
                    holder.cardProgress.visibility = View.GONE
                }
            })

// adiciona evento de clique
        holder.itemView.setOnClickListener {onClick(func)}
    }
}