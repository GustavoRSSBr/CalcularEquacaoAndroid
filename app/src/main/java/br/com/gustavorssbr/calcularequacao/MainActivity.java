/*
 *@author:Gustavo Rodrigues Santos Silva
 * RA: 1110481922011
 */
package br.com.gustavorssbr.calcularequacao;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etValorA;
    private EditText etValorB;
    private EditText etValorC;
    private TextView tvX1;
    private TextView tvX2;
    private TextView tvDelta;
    private TextView tvInvalido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etValorA = findViewById(R.id.etValorA);
        etValorA.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etValorB = findViewById(R.id.etValorB);
        etValorB.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etValorC = findViewById(R.id.etValorC);
        etValorC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Button btnCalcular = findViewById(R.id.btnCalcular);
        tvX1 = findViewById(R.id.tvX1);
        tvX1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvX2 = findViewById(R.id.tvX2);
        tvX2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvDelta = findViewById(R.id.tvDelta);
        tvDelta.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvInvalido = findViewById(R.id.tvInvalido);
        tvInvalido.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        btnCalcular.setOnClickListener(op -> calcular());
    }

    private void calcular() {
        try {
            double valorA = Double.parseDouble(etValorA.getText().toString());
            double valorB = Double.parseDouble(etValorB.getText().toString());
            double valorC = Double.parseDouble(etValorC.getText().toString());

            if (valorA == 0) {
                tvInvalido.setVisibility(View.VISIBLE);
                tvInvalido.setText(R.string.tv_invalido_nao_equacao);
                tvDelta.setVisibility(View.GONE);
                tvX1.setVisibility(View.GONE);
                tvX2.setVisibility(View.GONE);
                return;
            }

            double delta = (valorB * valorB) - (4 * valorA * valorC);

            if (delta < 0) {
                tvInvalido.setVisibility(View.VISIBLE);
                tvInvalido.setText(R.string.tv_invalido_nao_tem_raiz);
                tvDelta.setVisibility(View.GONE);
                tvX1.setVisibility(View.GONE);
                tvX2.setVisibility(View.GONE);
            } else {
                double x1 = (-valorB + Math.sqrt(delta)) / (2 * valorA);
                double x2 = (-valorB - Math.sqrt(delta)) / (2 * valorA);

                String resDelta = getString(R.string.delta) + " " + delta;
                tvDelta.setText(resDelta);
                String resX1 = getString(R.string.x1) + " " + x1;
                tvX1.setText(resX1);
                String resX2 = getString(R.string.x2) + " " + x2;
                tvX2.setText(resX2);

                tvInvalido.setVisibility(View.GONE);
                tvDelta.setVisibility(View.VISIBLE);
                tvX1.setVisibility(View.VISIBLE);
                tvX2.setVisibility(View.VISIBLE);
            }
        } catch (NumberFormatException e) {
            tvInvalido.setVisibility(View.VISIBLE);
            tvInvalido.setText(R.string.tv_invalido_dado);
            tvDelta.setVisibility(View.GONE);
            tvX1.setVisibility(View.GONE);
            tvX2.setVisibility(View.GONE);
        } finally {
            etValorA.setText("");
            etValorB.setText("");
            etValorC.setText("");
        }
    }
}
