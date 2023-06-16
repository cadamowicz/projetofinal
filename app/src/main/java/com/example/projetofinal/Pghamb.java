package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Pghamb extends AppCompatActivity {

    Spinner spinnerBurg;

    TextView sub1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pghamb);

        sub1 = findViewById(R.id.sub1);
        spinnerBurg = findViewById(R.id.spinner_burg);


        Lanchesdb lanchesdb=new Lanchesdb(this);

        List<Lanches> listaLanchesBD = new ArrayList<>();
        listaLanchesBD= lanchesdb.getListaLanches();
        String[] listaLanches=new String[listaLanchesBD.size()];
        String[] listaLanchesValor=new String[listaLanchesBD.size()];

        for (int i=0; i < listaLanches.length;i++) {
            listaLanches[i] = listaLanchesBD.get(i).getNomeLanches() +"  " + "R$ " + listaLanchesBD.get(i).getValorLanches() + ",00";
        }

        for (int i=0; i < listaLanchesValor.length;i++) {
            listaLanchesValor[i] = listaLanchesBD.get(i).getValorLanches() + ",00";
        }


        ArrayAdapter adapterSpinnerBurg =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listaLanches);
        spinnerBurg.setAdapter(adapterSpinnerBurg);


        spinnerBurg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String lancheSelecionado = String.valueOf(spinnerBurg.getItemIdAtPosition(i));

                int op= Integer.parseInt(lancheSelecionado);

                for (int j=0; j < listaLanchesValor.length;j++) {

                    if (op == j) {
                        lancheSelecionado = listaLanchesValor[j];
                        sub1.setText(lancheSelecionado);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }




}