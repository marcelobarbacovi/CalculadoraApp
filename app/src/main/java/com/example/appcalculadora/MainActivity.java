package com.example.appcalculadora;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appcalculadora.database.DataBaseHelper;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;





public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // declaração das variaveis
    private Button numero0, numero1, numero2, numero3, numero4, numero5, numero6,
            numero7, numero8, numero9, porcentagem, ponto, parenteses1,parenteses2,
            dividir, multiplicar, diminuir, somar, igual;

    private ImageButton apagar;

    private TextView calculo, resultado;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarComponente();

        numero0.setOnClickListener(this);
        numero1.setOnClickListener(this);
        numero2.setOnClickListener(this);
        numero3.setOnClickListener(this);
        numero4.setOnClickListener(this);
        numero5.setOnClickListener(this);
        numero6.setOnClickListener(this);
        numero7.setOnClickListener(this);
        numero8.setOnClickListener(this);
        numero9.setOnClickListener(this);
        ponto.setOnClickListener(this);
        porcentagem.setOnClickListener(this);
        parenteses1.setOnClickListener(this);
        parenteses2.setOnClickListener(this);
        dividir.setOnClickListener(this);
        multiplicar.setOnClickListener(this);
        diminuir.setOnClickListener(this);
        somar.setOnClickListener(this);


        // metodo apagar para o button, onde pega o total da string  e vai diminuindo 1
        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView calculo=findViewById(R.id.text_calculo);
                String string = calculo.getText().toString();

                if(!string.isEmpty()){
                byte var0 = 0;
                int var1= string.length()-1;
                    String textCalculo = string.substring(var0,var1);
                    calculo.setText(textCalculo);


                }
                resultado.setText("  ");
            }
        });

        // realiza o calculo por  meio da classe Expression Builder Evaluate, foi necessario implementar uma biblioteca:
     //implementation 'net.objecthunter:exp4j:0.4.8'

        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                    Expression expressao = new ExpressionBuilder(calculo.getText().toString()).build();
                    double result = expressao.evaluate();
                    long longResultado = (long)result;
                    if(result ==(double)longResultado){
                        resultado.setText((CharSequence) String.valueOf(longResultado));

                    }else{
                        resultado.setText((CharSequence) String.valueOf(result));
                    }
                }catch (Exception e){


                }
            adicionar();

            }
        });


    }


// identificação dos id dos campos da calculadora dentro das variaveis
    private void iniciarComponente(){
        numero0 = findViewById(R.id.button_0);
        numero1 = findViewById(R.id.button_1);
        numero2 = findViewById(R.id.button_2);
        numero3 = findViewById(R.id.button_3);
        numero4 = findViewById(R.id.button_4);
        numero5 = findViewById(R.id.button_5);
        numero6 = findViewById(R.id.button_6);
        numero7 = findViewById(R.id.button_7);
        numero8 = findViewById(R.id.button_8);
        numero9 = findViewById(R.id.button_9);
        porcentagem = findViewById(R.id.button_porcentagem);
        parenteses1 = findViewById(R.id.button_parentes1);
        parenteses2 = findViewById(R.id.button_parentes2);
        ponto = findViewById(R.id.button_ponto);
        dividir = findViewById(R.id.button_dividir);
        multiplicar = findViewById(R.id.button_mutiplicar);
        diminuir = findViewById(R.id.button_diminuir);
        somar = findViewById(R.id.button_somar);
        igual = findViewById(R.id.button_igual);
        calculo = findViewById(R.id.text_calculo);
        resultado = findViewById(R.id.text_resultado);
        apagar=findViewById(R.id.Button_apagar);


    }
    // metodo para concatnar valores para fazer as operação
    public void AcrescentarCalculo(String string, boolean limpar_dados){

        // adicona os valores dentro do calculo

        // primeiros if não deixa concatenar no ultimo if concatena

        if(resultado.getText().equals("")){
            calculo.setText(" ");
        }
        if (limpar_dados){
            resultado.setText(" ");
            calculo.append(string);
        }else{
            calculo.append(resultado.getText());
            calculo.append(string);
            resultado.setText(" ");

        }
    }

  // identifica quando clica no botão da calculadora o valor atribuido a cada botão
    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.button_0:
                AcrescentarCalculo("0", true);
                break;

            case R.id.button_1:
                AcrescentarCalculo("1", true);
                break;

            case R.id.button_2:
                AcrescentarCalculo("2", true);
                break;
            case R.id.button_3:
                AcrescentarCalculo("3", true);
                break;

            case R.id.button_4:
                AcrescentarCalculo("4", true);
                break;

            case R.id.button_5:
                AcrescentarCalculo("5", true);
                break;
            case R.id.button_6:
                AcrescentarCalculo("6", true);
                break;
            case R.id.button_7:
                AcrescentarCalculo("7", true);
                break;
            case R.id.button_8:
                AcrescentarCalculo("8", true);
                break;
            case R.id.button_9:
                AcrescentarCalculo("9", true);
                break;
            case R.id.button_ponto:
                AcrescentarCalculo(".", true);
                break;
            case R.id.button_porcentagem:
                AcrescentarCalculo("%", true);
                break;
            case R.id.button_parentes1:
                AcrescentarCalculo("(", true);
                break;
            case R.id.button_parentes2:
                AcrescentarCalculo(")", true);
                break;

            case R.id.button_somar:
                AcrescentarCalculo("+", false);
                break;
            case R.id.button_diminuir:
                AcrescentarCalculo("-", false);
                break;
            case R.id.button_dividir:
                AcrescentarCalculo("/", false);
                break;
            case R.id.button_mutiplicar:
                AcrescentarCalculo("*", false);
                break;


        }



    }
    // valida dos campos se estão vazios do adcionar medicos
    private void adicionar(){


            // intacnia o banco de dados depois grava dentro dos atributos da classe Historico para gravar dentro do banco de dados
            DataBaseHelper databaseHelper = new DataBaseHelper(getApplicationContext());
            Historico h = new Historico();
            h.setExpressao(calculo.getText().toString());
            h.setResultado(resultado.getText().toString());


            //chama o metodo de inserção no bando de dados da classe DataBaseHelper pegando os dados inseridos no atributos acima
            databaseHelper.createHistorico(h);




    }
    // configuração do menu para aparecer na activity principal

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


// intent para navegar entre a tela da calculadora para o histporico por meio do menu

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_Historico:
                Intent intent = new Intent(getApplicationContext(), HistoricoLista.class);

                startActivity(intent);

                return true;




            default:
                break;
        }

        return false;
    }

}
