package com.example.projetofinal;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Lanchesdb extends SQLiteOpenHelper {

    protected static final String TABELA_LANCHES = "TABELA_LANCHES";
    protected static final String LANCHES_ID = "ID";
    protected static final String LANCHES_NOME = "LANCHES_NOME";
    protected static final String VALOR_LANCHES = "VALOR_LANCHES";

    public Lanchesdb(@Nullable Context context) {
        super(context, "Lanchesdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String statement = "CREATE TABLE " + TABELA_LANCHES +
                " (" + LANCHES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LANCHES_NOME + " TEXT, " + VALOR_LANCHES + " INT)";

        db.execSQL(statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean adicionarLanches(Lanches lanches) {

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(LANCHES_NOME, lanches.getNomeLanches());
        contentValues.put(VALOR_LANCHES, lanches.getValorLanches());
        long inserirSucedido = db.insert(TABELA_LANCHES, null, contentValues);

        db.close();

        return inserirSucedido != -1;
    }

    public boolean atualizarLanches(Lanches lanches) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(LANCHES_NOME, lanches.getNomeLanches());
        contentValues.put(VALOR_LANCHES, lanches.getValorLanches());
        contentValues.put(LANCHES_ID, lanches.getIdLanches());


        long atualizarSucedido = db.update(TABELA_LANCHES, contentValues, LANCHES_ID + "=" + lanches.getIdLanches(), null);

        db.close();

        return atualizarSucedido != -1;
    }

    public List<Lanches> getListaLanches() {

        List<Lanches> listaLanches = new ArrayList<>();

        String queryStatement = "SELECT * FROM " + TABELA_LANCHES;

        SQLiteDatabase db = this.getReadableDatabase();

        try (Cursor cursor = db.rawQuery(queryStatement, null)) {


            if (cursor.moveToFirst()) {
                do {
                    int lanchesId = cursor.getInt(0);
                    String lanchesNome = cursor.getString(1);
                    int lanchesValor = cursor.getInt(2);

                    Lanches lanches = new Lanches(lanchesId, lanchesNome, lanchesValor);
                    listaLanches.add(lanches);
                } while (cursor.moveToNext());
            } else {

            }
        }
        db.close();

        return listaLanches;
    }

    public boolean excluirLanches(Lanches lanches) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString =
                "DELETE FROM " + TABELA_LANCHES + " WHERE " + LANCHES_ID + " = " + lanches.getIdLanches();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }

    }


}
