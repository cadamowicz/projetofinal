package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ptNomeLanche;
    EditText ptValorLanche;
    EditText ptIdLanche;
    Button bAddLanche;
    Button bAtualizar;
    Button bListaLanche;
    Button pgbebidas;
    ListView lv_lanches;

    ArrayAdapter lancheArrayAdapter;

    Lanchesdb lanchesdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ptNomeLanche = findViewById(R.id.ptNomeLanche);
        ptValorLanche = findViewById(R.id.ptValorBebidas);
        ptIdLanche = findViewById(R.id.ptIdBebidas);
        bAddLanche = findViewById(R.id.bAddBebida);
        bAtualizar = findViewById(R.id.bAtualizar);
        bListaLanche = findViewById(R.id.bListaBebida);
        lv_lanches = findViewById(R.id.lv_bebidas);
        pgbebidas = findViewById(R.id.pgbebidas);

        lanchesdb = new Lanchesdb(MainActivity.this);
        mostrarLanchesNaListView(lanchesdb);
        bAddLanche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Lanches lanches = null;

                try {
                    lanches = new Lanches(-1,
                            ptNomeLanche.getText().toString(),
                            Integer.parseInt(ptValorLanche.getText().toString()));
                    boolean sucesso = lanchesdb.adicionarLanches(lanches);


                    mostrarLanchesNaListView(lanchesdb);
                    Toast.makeText(MainActivity.this, "Sucesso:" + sucesso, Toast.LENGTH_SHORT).show();

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Erro na conversão de uma String para int: Valor não corresponde a número!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Erro na criação do lanche!", Toast.LENGTH_LONG).show();

                }


            }
        });
        bListaLanche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mostrarLanchesNaListView(lanchesdb);

                Toast.makeText(MainActivity.this, "Lista de lanches preenchida com sucesso", Toast.LENGTH_SHORT).show();

            }
        });
        lv_lanches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                System.out.println("Captou click na lista!");
                Lanches lancheClicado = (Lanches) parent.getItemAtPosition(position);
                boolean excluiu = lanchesdb.excluirLanches(lancheClicado);

                mostrarLanchesNaListView(lanchesdb);

                Toast.makeText(MainActivity.this, "Lanche excluído(" + excluiu + "):" + lancheClicado.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        bAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Lanches lanches = null;

                try {
                    lanches = new Lanches(Integer.parseInt(ptIdLanche.getText().toString()), ptNomeLanche.getText().toString(), Integer.parseInt(ptValorLanche.getText().toString()));

                    boolean sucesso = lanchesdb.atualizarLanches(lanches);

                    mostrarLanchesNaListView(lanchesdb);
                    Toast.makeText(MainActivity.this, "Sucesso:" + sucesso, Toast.LENGTH_SHORT).show();

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Erro na conversão de uma String para int: Valor não corresponde a número!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Erro na criação do lanche!", Toast.LENGTH_LONG).show();
                    lanches = new Lanches(-1, "erro", 0);
                }

            }
        });

        pgbebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);

            }
        });

    }

    private void mostrarLanchesNaListView(Lanchesdb lanchesdb) {
        lancheArrayAdapter = new ArrayAdapter<Lanches>(MainActivity.this,
                android.R.layout.simple_list_item_1, lanchesdb.getListaLanches());
        lv_lanches.setAdapter(lancheArrayAdapter);
    }


}