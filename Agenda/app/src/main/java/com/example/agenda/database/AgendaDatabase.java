package com.example.agenda.database;

import static com.example.agenda.database.AgendaMigrations.TODAS_MIGRATIONS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.agenda.database.converter.ConversorCalendar;
import com.example.agenda.database.converter.ConversorTipoTelefone;
import com.example.agenda.database.dao.AlunoDAO;
import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.model.Telefone;

@Database(entities = {Aluno.class, Telefone.class}, version = 6, exportSchema = false)
@TypeConverters({ConversorCalendar.class, ConversorTipoTelefone.class})
public abstract class AgendaDatabase extends RoomDatabase {

    private static final String NOME_BANCO_DADOS = "agenda.db";

    public abstract AlunoDAO getAlunoDAO();
    public abstract TelefoneDAO getTelefoneDAO();

    public static AgendaDatabase getInstance(Context context) {
        return Room
                .databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DADOS)
                //esse metodo permite a execução do banco na thread principal
                //.allowMainThreadQueries()

                //esse metodo destoi a versao anterior do db, e refaz com a nova versão
                //so deve ser usado quando o app estiver sendo usado somente quando nao
                //existir a versão de produção
                //.fallbackToDestructiveMigration()

                //metodo responsavel por informar a solução que atenda toda a alteração que foi feita
                //primeiro argumento (1) é a versao anterior
                //segundo argumento (2) é a versão atual do db
                .addMigrations(TODAS_MIGRATIONS)
                .build();
    }

}
