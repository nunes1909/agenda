package com.example.agenda.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.agenda.database.AgendaDatabase;
import com.example.agenda.database.dao.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {
    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;
    private Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter();
        dao = AgendaDatabase.getInstance(context).getRoomAlunoDAO();
    }

    public void confirmaRemocao(@NonNull MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo =
                                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                        removeAluno(alunoEscolhido);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

    public void removeAluno(Aluno alunoEscolhido) {
        dao.remove(alunoEscolhido);
        //tornei o adapter atributo de classe, e chamei o metodo remove
        //esse é um metodo do proprio adapter, e ele remove automaticamente as infos
        adapter.remove(alunoEscolhido);
    }

    public void configuraAdapter(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(adapter);
    }
}
