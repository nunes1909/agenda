package com.example.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.asynctask.BuscaPrimeiroTelefoneDoAlunoTask;
import com.example.agenda.database.AgendaDatabase;
import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;
    private final TelefoneDAO dao;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
        dao = AgendaDatabase.getInstance(context).getTelefoneDAO();
    }

    //esse metodo representa o tamanho, ou, a quantidade de itens
    @Override
    public int getCount() {
        return alunos.size();
    }

    //esse metodo devolve o item da lista pela posicao
    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    //esse metodo devolve o id do item da lista
    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    //esse metodo representa a view que vai ser exibida em cada item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criaView(parent);
        Aluno alunoDevolvido = alunos.get(position);
        vincula(viewCriada, alunoDevolvido);
        return viewCriada;
    }

    private void vincula(View viewCriada, Aluno aluno) {
        TextView campoNome = viewCriada.findViewById(R.id.item_aluno_nome);
        campoNome.setText(aluno.getNome());
        TextView campoTelefone = viewCriada.findViewById(R.id.item_aluno_telefone);

        new BuscaPrimeiroTelefoneDoAlunoTask(dao, aluno, telefoneEncontrado -> {
            campoTelefone.setText(telefoneEncontrado.getNumero());
        }).execute();
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater
                .from(parent.getContext())
                //esse false significa que não somos nos quem vamos fazer o processo de criar a
                //view e adicionar o layout na viewgroup, mas sim é o adapter que vai fazer
                .inflate(R.layout.item_aluno, parent, false);
    }

    public void atualiza(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
