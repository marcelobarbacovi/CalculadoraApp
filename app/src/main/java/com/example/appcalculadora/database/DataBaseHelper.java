package com.example.appcalculadora.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.appcalculadora.Historico;
import com.example.appcalculadora.R;

public class DataBaseHelper extends SQLiteOpenHelper {

    // criar os atributos do banco de dados e das tabelas
    private static final String DATABASE_NAME = "calculadora";
    private static final String TABLE_HISTORICO = "historico";


// Criação das tabelas do Banco de dados

    private static final String CREATE_TABLE_HISTORICO = "CREATE TABLE " + TABLE_HISTORICO + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "expressao DOUBLE, " +
            "resultado DOUBLE);";





    private static final String DROP_TABLE_HISTORICO = "DROP TABLE IF EXISTS " + TABLE_HISTORICO;


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_HISTORICO);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_TABLE_HISTORICO);
        onCreate(db);

    }

    /* Início crud calculadora
     *  metodo de inserção da tabela histórico
     * */
    public long createHistorico(Historico h) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("expressao", h.getExpressao());
        cv.put("resultado", h.getResultado());
        long id = db.insert(TABLE_HISTORICO , null, cv);
        db.close();
        return id;
    }



    // metodo para recuperar os dados da tabela historico
    public void getAllHistorico(Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "expressao", "resultado",};
        Cursor data = db.query(TABLE_HISTORICO, columns, null, null,
                null, null, "expressao");
        int[] to = {R.id.textViewIdListarHistorico, R.id.textViewExpressaoListarHistorico,
                R.id.textViewResultadoListarHistorico};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.historico_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }


}
// Fim CRUD hISTÓRICO