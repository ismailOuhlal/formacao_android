package com.iamageo.agenda.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.iamageo.agenda.model.Aluno;

import static com.iamageo.agenda.database.AgendaMigrations.TODAS_MIGRATIONS;

@Database(entities = {Aluno.class}, version = 1, exportSchema = false)

public abstract class AgendaDatabase extends RoomDatabase {

    private static final String AGENDA_DB = "agenda.db";

    public abstract  RoomAlunoDAO getRoomAlunoDAO();

    public static AgendaDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AgendaDatabase.class, AGENDA_DB)
                //.allowMainThreadQueries()
                //.addMigrations(TODAS_MIGRATIONS)
                .build();

    }

}
