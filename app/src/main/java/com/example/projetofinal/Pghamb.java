package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Pghamb extends AppCompatActivity {

    Spinner spinnerBurg;
    TextView sub1;
    Button b3;
    String lancheSelecionado,lancheop;
    String valorHambInteiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pghamb);

        sub1 = findViewById(R.id.sub2);
        spinnerBurg = findViewById(R.id.spinner_Bebida);
        b3=findViewById(R.id.b4);

        Lanchesdb lanchesdb=new Lanchesdb(this);

        List<Lanches> listaLanchesBD = new ArrayList<>();
        listaLanchesBD= lanchesdb.getListaLanches();
        String[] listaLanches=new String[listaLanchesBD.size()];
        int[] listaLanchesValor = new int[listaLanchesBD.size()];


        for (int i=0; i < listaLanches.length;i++) {
            listaLanches[i] = listaLanchesBD.get(i).getNomeLanches() +"  " + "R$ " + listaLanchesBD.get(i).getValorLanches() + ",00";
            listaLanchesValor[i] = listaLanchesBD.get(i).getValorLanches();

        }

        ArrayAdapter adapterSpinnerBurg =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listaLanches);
        spinnerBurg.setAdapter(adapterSpinnerBurg);

        spinnerBurg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                lancheSelecionado = String.valueOf(spinnerBurg.getItemIdAtPosition(i));
                lancheop = spinnerBurg.getSelectedItem().toString();

                int op= Integer.parseInt(lancheSelecionado);


                for (int j=0; j < listaLanchesValor.length;j++) {

                    if (op == j) {
                        lancheSelecionado = "R$" + listaLanchesValor[j] + ",00";
                        valorHambInteiro=String.valueOf(listaLanchesValor[j]);
                        sub1.setText(lancheSelecionado);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(Pghamb.this, Pgbeb.class);
                intent1.putExtra("totalpaglanche",valorHambInteiro );
                intent1.putExtra("oplanche",lancheop );
                startActivity(intent1);

            }
        });


    }






}