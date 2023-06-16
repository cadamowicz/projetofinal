package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Pgfinal extends AppCompatActivity {

    TextView bebidatotal,valortotalpedido,lanchetotal;
    Button b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgfinal);

        bebidatotal=findViewById(R.id.bebidatotal);
        valortotalpedido=findViewById(R.id.valortotalpedido);
        lanchetotal=findViewById(R.id.lanchetotal);
        b5=findViewById(R.id.b5);

        Intent intent2 = getIntent();
        String lanchefinal = intent2.getStringExtra("oplanche1");
        lanchetotal.setText(lanchefinal);
        String totalfinal = intent2.getStringExtra("totalgeralpedido");
        valortotalpedido.setText("R$"+ totalfinal + ",00");
        String bebidafinal = intent2.getStringExtra("opbebidas");
        bebidatotal.setText(bebidafinal);

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent3 = new Intent(Pgfinal.this, Principal.class);
                startActivity(intent3);

            }
        });

    }

}