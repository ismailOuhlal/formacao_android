package com.iamageo.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class AgendaMigrations {

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `sobrenome` TEXT, `idade` TEXT, `telefone` TEXT)");
            //database.execSQL("ALTER TABLE aluno ADD COLUMN telefone TEXT");
        }
    };



    static final Migration[] TODAS_MIGRATIONS = {MIGRATION_2_3};


}
