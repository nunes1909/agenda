package com.example.agenda.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();

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
        View viewCriada = LayoutInflater
                .from(parent.getContext())
                //esse false significa que não somos nos quem vamos fazer o processo de criar a
                //view e adicionar o layout na viewgroup, mas sim é o adapter que vai fazer
                .inflate(R.layout.item_aluno, parent, false);

        Aluno alunoDevolvido = alunos.get(position);
        TextView campoNome = viewCriada.findViewById(R.id.item_aluno_nome);
        campoNome.setText(alunoDevolvido.getNome());
        TextView campoTelefone = viewCriada.findViewById(R.id.item_aluno_telefone);
        campoTelefone.setText(alunoDevolvido.getTelefone());

        return viewCriada;
    }

    public void clear() {
        alunos.clear();
    }

    public void addAll(List<Aluno> alunos) {
        this.alunos.addAll(alunos);
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
    }
}
