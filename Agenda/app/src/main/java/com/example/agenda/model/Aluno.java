package com.example.agenda.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;

@Entity
public class Aluno implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String nome;
    private String telefone;
    private String email;
    private Calendar momentoCadastro = Calendar.getInstance();

    public Calendar getMomentoCadastro() {
        return momentoCadastro;
    }

    public void setMomentoCadastro(Calendar momentoCadastro) {
        this.momentoCadastro = momentoCadastro;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nome + "\n" + telefone;
    }


    public boolean temIdValido() {
        return id > 0;
    }

    public String getNomeCompleto() {
        return nome;
    }
//
//    public String dataFormatada(){
//        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
//        return formatador.format(momentoCadastro.getTime());
//    }
}
