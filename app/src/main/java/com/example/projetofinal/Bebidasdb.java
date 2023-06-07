package com.example.projetofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Bebidasdb extends SQLiteOpenHelper {
    protected static final String TABELA_BEBIDAS = "TABELA_BEBIDAS";
    protected static final String BEBIDAS_ID = "ID";
    protected static final String BEBIDAS_NOME = "BEBIDAS_NOME";
    protected static final String VALOR_BEBIDAS = "VALOR_BEBIDAS";

    public Bebidasdb(@Nullable Context context) {
        super(context, "Bebidasdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String statement = "CREATE TABLE " + TABELA_BEBIDAS +
                " (" + BEBIDAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BEBIDAS_NOME + " TEXT, " + VALOR_BEBIDAS + " INT)";

        db.execSQL(statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean adicionarBebidas(Bebidas bebidas) {

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(BEBIDAS_NOME, bebidas.getNomeBebidas());
        contentValues.put(VALOR_BEBIDAS, bebidas.getValorBebidas());
        long inserirSucedido = db.insert(TABELA_BEBIDAS, null, contentValues);

        db.close();

        return inserirSucedido != -1;
    }

    public boolean atualizarBebidas(Bebidas bebidas) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(BEBIDAS_NOME, bebidas.getNomeBebidas());
        contentValues.put(VALOR_BEBIDAS, bebidas.getValorBebidas());
        contentValues.put(BEBIDAS_ID, bebidas.getIdBebidas());


        long atualizarSucedido = db.update(TABELA_BEBIDAS, contentValues, BEBIDAS_ID + "=" + bebidas.getIdBebidas(), null);

        db.close();

        return atualizarSucedido != -1;
    }

    public List<Bebidas> getListaBebidas() {

        List<Bebidas> listaBebidas = new ArrayList<>();

        String queryStatement = "SELECT * FROM " + TABELA_BEBIDAS;

        SQLiteDatabase db = this.getReadableDatabase();

        try (Cursor cursor = db.rawQuery(queryStatement, null)) {


            if (cursor.moveToFirst()) {
                do {
                    int bebidasId = cursor.getInt(0);
                    String bebidasNome = cursor.getString(1);
                    int bebidasValor = cursor.getInt(2);

                    Bebidas bebidas = new Bebidas(bebidasId, bebidasNome, bebidasValor);
                    listaBebidas.add(bebidas);
                } while (cursor.moveToNext());
            } else {

            }
        }
        db.close();

        return listaBebidas;
    }

    public boolean excluirBebidas(Bebidas bebidas) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString =
                "DELETE FROM " + TABELA_BEBIDAS + " WHERE " + BEBIDAS_ID + " = " + bebidas.getIdBebidas();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }

    }
}
