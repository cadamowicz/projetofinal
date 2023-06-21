package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Pgbeb extends AppCompatActivity {

    Spinner spinnerBebida;
    TextView sub2;
    Button b4;
    int totalpaghamb=0, totalgeral=0;
    String bebidaSelecionada, bebidaEscolhida;
    String valorBebInteiro;

    ImageView imagembebida;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgbeb);

        sub2 = findViewById(R.id.sub2);
        spinnerBebida = findViewById(R.id.spinner_Bebida);
        b4=findViewById(R.id.b4);
        imagembebida=findViewById(R.id.imagembebida);

        Intent intent1 = getIntent();
        String valorpaghamb = intent1.getStringExtra("totalpaglanche");
        String lancheEscolhido = intent1.getStringExtra("oplanche");
        totalpaghamb = Integer.parseInt(valorpaghamb);


        Bebidasdb bebidasdb=new Bebidasdb(this);

        List<Bebidas> listaBebidasBD = new ArrayList<>();
        listaBebidasBD= bebidasdb.getListaBebidas();
        String[] listaBebidas=new String[listaBebidasBD.size()];
        int[] listaBebidasValor = new int[listaBebidasBD.size()];


        for (int i=0; i < listaBebidas.length;i++) {
            listaBebidas[i] = listaBebidasBD.get(i).getNomeBebidas() +"  " + "R$ " + listaBebidasBD.get(i).getValorBebidas() + ",00";
            listaBebidasValor[i] = listaBebidasBD.get(i).getValorBebidas();

        }

        ArrayAdapter adapterSpinnerBeb =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listaBebidas);
        spinnerBebida.setAdapter(adapterSpinnerBeb);

        spinnerBebida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                bebidaSelecionada = String.valueOf(spinnerBebida.getItemIdAtPosition(i));
                bebidaEscolhida=spinnerBebida.getSelectedItem().toString();


                int op= Integer.parseInt(bebidaSelecionada);

                for (int j=0; j < listaBebidasValor.length;j++) {

                    if (op == j) {

                        bebidaSelecionada = "R$" + listaBebidasValor[j] + ",00";

                        totalgeral=listaBebidasValor[j]+totalpaghamb;
                        valorBebInteiro=String.valueOf(listaBebidasValor[j]);
                        sub2.setText( bebidaSelecionada);

                        if (op == 0){
                            imagembebida.setImageResource(R.drawable.cocalata);
                        }
                        if (op == 1){
                            imagembebida.setImageResource(R.drawable.coca600);
                        }
                        if (op == 2){
                            imagembebida.setImageResource(R.drawable.delvale);
                        }
                        if (op == 3){
                            imagembebida.setImageResource(R.drawable.aguasemgas);
                        }
                        if (op == 4){
                            imagembebida.setImageResource(R.drawable.aguacomgas);
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(Pgbeb.this, Pgfinal.class);
                intent2.putExtra("totalgeralpedido",String.valueOf(totalgeral));
                intent2.putExtra("oplanche1",lancheEscolhido );
                intent2.putExtra("opbebidas",bebidaEscolhida);
                startActivity(intent2);

            }
        });


    }
}