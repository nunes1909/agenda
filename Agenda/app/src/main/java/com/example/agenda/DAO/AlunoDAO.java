package com.example.agenda.DAO;

import androidx.annotation.Nullable;

import com.example.agenda.model.Aluno;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO implements Serializable {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeId = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeId);
        alunos.add(aluno);
        atualizaId();
    }

    private void atualizaId() {
        contadorDeId++;
    }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);
        if (alunoEncontrado != null) {
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            // este set é um metodo de arraylist
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscaAlunoPeloId(Aluno aluno) {
        for (Aluno a :
                alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno aluno) {
        Aluno alunoDevolvido = buscaAlunoPeloId(aluno);
        if (alunoDevolvido != null) {
            alunos.remove(alunoDevolvido);
        }
    }
}
