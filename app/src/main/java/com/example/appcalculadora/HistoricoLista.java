package com.example.appcalculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appcalculadora.database.DataBaseHelper;

public class HistoricoLista extends AppCompatActivity {
    private DataBaseHelper databaseHelper;
    private Historico h;
    Button voltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_lista);

        /* intent para navegar da tela histporico para a principal da calculadora
        * por meio de um botão voltar

         */
        voltar = findViewById(R.id.button_voltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
            }
        });

        // instancia a classe de banco de dados
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
        ListView lv = findViewById(R.id.list_View_listar_historico);
        dataBaseHelper.getAllHistorico(getApplicationContext(),lv);

        // configura o listView para a tela histórico com um adapter
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvId = view.findViewById(R.id.textViewIdListarHistorico);
                Bundle b = new Bundle();
                b.putInt("id", Integer.parseInt(tvId.getText().toString()));

            }
        });



    }


}