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

public class MainActivity2 extends AppCompatActivity {
    EditText ptNomeBebidas;
    EditText ptValorBebidas;
    EditText ptIdBebidas;
    Button bAddBebida;
    Button bAtualizar;
    Button bListaBebida;
    ListView lv_bebidas;

    ArrayAdapter bebidaArrayAdapter;

    Bebidasdb bebidasdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ptNomeBebidas = findViewById(R.id.ptNomeBebidas);
        ptValorBebidas = findViewById(R.id.ptValorBebidas);
        ptIdBebidas = findViewById(R.id.ptIdBebidas);
        bAddBebida = findViewById(R.id.bAddBebida);
        bAtualizar = findViewById(R.id.bAtualizar);
        bListaBebida = findViewById(R.id.bListaBebida);
        lv_bebidas = findViewById(R.id.lv_bebidas);

        bebidasdb = new Bebidasdb(MainActivity2.this);
        mostrarBebidasNaListView(bebidasdb);

        bAddBebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bebidas bebidas = null;

                try {
                    bebidas = new Bebidas(-1,
                            ptNomeBebidas.getText().toString(),
                            Integer.parseInt(ptValorBebidas.getText().toString()));
                    boolean sucesso = bebidasdb.adicionarBebidas(bebidas);


                    mostrarBebidasNaListView(bebidasdb);
                    Toast.makeText(MainActivity2.this, "Sucesso:" + sucesso, Toast.LENGTH_SHORT).show();

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity2.this, "Erro na conversão de uma String para int: Valor não corresponde a número!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity2.this, "Erro na criação da Bebida!", Toast.LENGTH_LONG).show();

                }


            }
        });
        bListaBebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mostrarBebidasNaListView(bebidasdb);

                Toast.makeText(MainActivity2.this, "Lista de bebidas preenchida com sucesso", Toast.LENGTH_SHORT).show();

            }
        });
        lv_bebidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                System.out.println("Captou click na lista!");
                Bebidas bebidaClicado = (Bebidas) parent.getItemAtPosition(position);
                boolean excluiu = bebidasdb.excluirBebidas(bebidaClicado);

                mostrarBebidasNaListView(bebidasdb);

                Toast.makeText(MainActivity2.this, "Bebida excluída(" + excluiu + "):" + bebidaClicado.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        bAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bebidas bebidas = null;

                try {
                    bebidas = new Bebidas(Integer.parseInt(ptIdBebidas.getText().toString()), ptNomeBebidas.getText().toString(), Integer.parseInt(ptValorBebidas.getText().toString()));

                    boolean sucesso = bebidasdb.atualizarBebidas(bebidas);

                    mostrarBebidasNaListView(bebidasdb);
                    Toast.makeText(MainActivity2.this, "Sucesso:" + sucesso, Toast.LENGTH_SHORT).show();

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity2.this, "Erro na conversão de uma String para int: Valor não corresponde a número!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity2.this, "Erro na criação da Bebida!", Toast.LENGTH_LONG).show();
                    bebidas = new Bebidas(-1, "erro", 0);
                }

            }
        });
    }
    private void mostrarBebidasNaListView(Bebidasdb bebidasdb) {
        bebidaArrayAdapter = new ArrayAdapter<Bebidas>(MainActivity2.this,
                android.R.layout.simple_list_item_1, bebidasdb.getListaBebidas());
        lv_bebidas.setAdapter(bebidaArrayAdapter);
    }
}