package com.example.agenda.asynctask;

import android.os.AsyncTask;

import com.example.agenda.database.dao.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;

public class RemoveAlunoTask extends AsyncTask<Void, Void, Void> {
    private final AlunoDAO dao;
    private final ListaAlunosAdapter adapter;
    private Aluno aluno;

    public RemoveAlunoTask(AlunoDAO dao, ListaAlunosAdapter adapter, Aluno aluno) {
        this.dao = dao;
        this.adapter = adapter;
        this.aluno = aluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.remove(aluno);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        //tornei o adapter atributo de classe, e chamei o metodo remove
        //esse é um metodo do proprio adapter, e ele remove automaticamente as infos
        adapter.remove(aluno);
    }
}
