package com.example.agenda.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
public class Aluno implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String nome, email;
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

    public String getNome() {
        return nome;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return nome;
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
