package com.example.agenda.asynctask;

import com.example.agenda.database.dao.AlunoDAO;
import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.model.Telefone;
import com.example.agenda.model.TipoTelefone;

import java.util.List;

public class EditaAlunoTask extends BaseAlunoComTelefoneTask {

    private final AlunoDAO alunoDAO;
    private final TelefoneDAO telefoneDAO;
    private final Aluno aluno;
    private final Telefone telefoneCelular;
    private final Telefone telefoneFixo;
    private final List<Telefone> telefonesDoAluno;

    public EditaAlunoTask(AlunoDAO alunoDAO, TelefoneDAO telefoneDAO,
                          Aluno aluno, Telefone telefoneCelular, Telefone telefoneFixo,
                          List<Telefone> telefonesDoAluno, FinalizadaListener listener) {
        super(listener);
        this.alunoDAO = alunoDAO;
        this.telefoneDAO = telefoneDAO;
        this.aluno = aluno;
        this.telefoneCelular = telefoneCelular;
        this.telefoneFixo = telefoneFixo;
        this.telefonesDoAluno = telefonesDoAluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.edita(aluno);
        vinculaAlunoComTelefone(aluno.getId(), telefoneFixo, telefoneCelular);
        atualizaIdsTelefones(telefoneFixo, telefoneCelular);
        telefoneDAO.atualiza(telefoneFixo, telefoneCelular);
        return null;
    }

    private void atualizaIdsTelefones(Telefone telefoneFixo, Telefone telefoneCelular) {
        for (Telefone telefone :
                telefonesDoAluno) {
            if (telefone.getTipo() == TipoTelefone.FIXO) {
                telefoneFixo.setId(telefone.getId());
            } else {
                telefoneCelular.setId(telefone.getId());
            }
        }
    }
}
