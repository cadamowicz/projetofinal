package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ptNomeULanche;
    EditText ptValorLanche;
    EditText ptIdLanche;
    Button bAddLanche;
    Button bAtualizar;
    Button bListaLanche;
    ListView lv_lanches;

    ArrayAdapter lancheArrayAdapter;

    Lanchesdb lanchesdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ptNomeULanche = findViewById(R.id.ptNomeULanche);
        ptValorLanche = findViewById(R.id.ptValorBebidas);
        ptIdLanche = findViewById(R.id.ptIdBebidas);
        bAddLanche = findViewById(R.id.bAddBebida);
        bAtualizar = findViewById(R.id.bAtualizar);
        bListaLanche = findViewById(R.id.bListaBebida);
        lv_lanches = findViewById(R.id.lv_bebidas);

        lanchesdb = new Lanchesdb(MainActivity.this);
        mostrarLanchesNaListView(lanchesdb);
        bAddLanche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Lanches lanches = null;

                try {
                    lanches = new Lanches(-1,
                            ptNomeULanche.getText().toString(),
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
                    lanches = new Lanches(Integer.parseInt(ptIdLanche.getText().toString()), ptNomeULanche.getText().toString(), Integer.parseInt(ptValorLanche.getText().toString()));

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
    }

    private void mostrarLanchesNaListView(Lanchesdb lanchesdb) {
        lancheArrayAdapter = new ArrayAdapter<Lanches>(MainActivity.this,
                android.R.layout.simple_list_item_1, lanchesdb.getListaLanches());
        lv_lanches.setAdapter(lancheArrayAdapter);
    }




}