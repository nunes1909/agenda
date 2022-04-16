package com.example.agenda.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.model.Telefone;

public class BuscaPrimeiroTelefoneDoAlunoTask extends AsyncTask<Void, Void, Telefone> {
    private final TelefoneDAO dao;
    private final Aluno aluno;
    private final PrimeiroTelefoneEncontradoListener listener;

    public BuscaPrimeiroTelefoneDoAlunoTask(TelefoneDAO dao, Aluno aluno, PrimeiroTelefoneEncontradoListener listener) {
        this.dao = dao;
        this.aluno = aluno;
        this.listener = listener;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return dao.buscaPrimeiroTelefoneDoAluno(aluno.getId());
    }

    @Override
    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        listener.quandoEncontrado(primeiroTelefone);
    }

    public interface PrimeiroTelefoneEncontradoListener{
        void quandoEncontrado(Telefone telefoneEncontrado);
    }
}
