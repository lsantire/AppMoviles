package com.ewents.lab1.modelo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ewents.lab1.R;
import com.ewents.lab1.modelo.Cliente;
import com.ewents.lab1.modelo.PlazoFijo;

public class MainActivity extends AppCompatActivity {

    private PlazoFijo pf;
    private Cliente cliente;

    private Button btnHacerPF;
    private ToggleButton togAccion;
    private TextView tvDiasSeleccionados, tvIntereses, tvMensaje;
    private EditText editMail, editCuit, editMonto;
    private SeekBar seekDias;
    private Switch swAvisarVencimiento;
    private RadioButton optDolar, optPeso;
    private CheckBox chkAceptoTerminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pf = new PlazoFijo(getResources().getStringArray(R.array.tasas));
        cliente = new Cliente();

        btnHacerPF = (Button) findViewById(R.id.btnHacerPF);
        togAccion = (ToggleButton) findViewById(R.id.togAccion);
        tvDiasSeleccionados = (TextView) findViewById(R.id.tvDiasSeleccionados);
        tvIntereses = (TextView) findViewById(R.id.tvIntereses);
        tvMensaje = (TextView) findViewById(R.id.tvMensaje);
        editMail = (EditText) findViewById(R.id.editMail);
        editCuit = (EditText) findViewById(R.id.editCuit);
        editMonto = (EditText) findViewById(R.id.editMonto);
        seekDias = (SeekBar) findViewById(R.id.seekDias);
        swAvisarVencimiento = (Switch) findViewById(R.id.swAvisarVencimiento);
        optDolar = (RadioButton) findViewById(R.id.optDolar);
        optPeso = (RadioButton) findViewById(R.id.optPeso);
        chkAceptoTerminos = (CheckBox) findViewById(R.id.chkAceptoTerminos);
        btnHacerPF.setEnabled(false);
    }

    this.seekDias.OnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()

    {
        @Override
        public void onProgressChanged (SeekBar seekBar,int i, boolean b){
            // actualizar el textview de dias
            // setear los dias en el plazo fijo
            // actualizar el caluclo de los intereses pagados}
        @Override
        public void onStartTrackingTouch (SeekBar seekBar){
            // no hacer nada
        }
        @Override
        public void onStopTrackingTouch (SeekBar seekBar){
            // no hacer nada
        }


    })
    }

}
