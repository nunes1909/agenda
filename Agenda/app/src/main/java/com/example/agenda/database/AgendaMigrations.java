package com.example.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.agenda.model.TipoTelefone;

public class AgendaMigrations {
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // O Room não permite a exclusão de uma coluna, e por isso será necessário realizar os seguintes passos:

            // Criar nova tabela com as infos desejadas
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo`" +
                    " (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`telefone` TEXT, " +
                    "`email` TEXT)"
            );
            // Copiar dados da tabela antiga para a nova
            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefone, email)" +
                    "SELECT id, nome, telefone, email FROM Aluno"
            );
            // Remoção da tabela antiga
            database.execSQL("DROP TABLE Aluno");
            // Renomear a tabela nova com o nome da tabela antiga
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };

    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN momentoCadastro INTEGER");
        }
    };

    public static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`telefoneFixo` TEXT, " +
                    "`telefoneCelular` TEXT, " +
                    "`email` TEXT, " +
                    "`momentoCadastro` INTEGER)");

            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefoneFixo, email, momentoCadastro)" +
                    "SELECT id, nome, telefone, email, momentoCadastro FROM Aluno"
            );

            database.execSQL("DROP TABLE Aluno");

            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };
    public static final Migration[] TODAS_MIGRATIONS = {
            MIGRATION_1_2,
            MIGRATION_2_3,
            MIGRATION_3_4,
            MIGRATION_4_5, new Migration(5, 6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //inicio procedimento aluno
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`email` TEXT, " +
                    "`momentoCadastro` INTEGER)");

            database.execSQL("INSERT INTO Aluno_novo (id, nome, email, momentoCadastro)" +
                    "SELECT id, nome, email, momentoCadastro FROM Aluno");

            //inicio procedimento telefone
            database.execSQL("CREATE TABLE IF NOT EXISTS `Telefone` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`numero` TEXT, " +
                    "`tipo` TEXT, " +
                    "`alunoId` INTEGER NOT NULL, FOREIGN KEY(`alunoId`) REFERENCES `Aluno`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");

            database.execSQL("INSERT INTO Telefone (numero, alunoId)" +
                    "SELECT telefoneFixo, id FROM Aluno");

            //enviando um array com o tipo que queremos definir na coluna tipo
            database.execSQL("UPDATE Telefone SET tipo = ?",
                    new TipoTelefone[]{
                            TipoTelefone.FIXO
                    }
            );
            //fim procedimento telefone

            database.execSQL("DROP TABLE Aluno");

            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
            //fim procedimento aluno
        }
    }
    };
}
