package com.example.tipscalculation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEditValor;
    private TextView mTextPorcentagem;
    private TextView mTextGorgeta;
    private TextView mTextTotal;
    private SeekBar mSeekbarGorjeta;
    private double mPorcentagem = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditValor = findViewById(R.id.editText_value);
        mTextGorgeta = findViewById(R.id.textView_valor_gorjeta);
        mTextPorcentagem = findViewById(R.id.textView_porcentagem);
        mTextTotal = findViewById(R.id.textView_valor_total);
        mSeekbarGorjeta = findViewById(R.id.seekBar_porcentagem);

        //listener seekbar
        mSeekbarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPorcentagem = progress;
                mTextPorcentagem.setText(Math.round(mPorcentagem) + " %");
                calculation();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    public void calculation(){
        String valorRecuperado = mEditValor.getText().toString();
        if(valorRecuperado == null || valorRecuperado.equals("")){
            Toast.makeText(
                    getApplicationContext(),
                    R.string.erro_valor,
                    Toast.LENGTH_LONG
            ).show();
            mTextGorgeta.setText(R.string.zero);
            mTextTotal.setText(R.string.zero);
        }else {
            //converter string para double
            double valorDigitado = Double.parseDouble(valorRecuperado);
            //calculo gorjeta total
            double gorjeta = valorDigitado * (mPorcentagem/100);
            //limita double a 2 casas decimais
            String resultado = String.format("%.2f", gorjeta);
            mTextGorgeta.setText(resultado + " €");
            //calculo valor total
            double total = gorjeta + valorDigitado;
            String resultadoTotal = String.format("%.2f", total);
            mTextTotal.setText(resultadoTotal + " €");

        }

    }
}