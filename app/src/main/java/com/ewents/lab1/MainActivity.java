package com.ewents.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

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
    private RadioGroup optMoneda;
    private RadioButton optDolar, optPeso;
    private CheckBox chkAceptoTerminos;
    private Toast toastTerminosCondiciones,toastBtnHacerPF;

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

        editMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                pf.setCorreoElectronico(s.toString());
            }
        });

        editCuit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // no hacer nada
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // no hacer nada
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Acá habría que hacer algo con el cliente del PF.
            }
        });

        //ACA VA LISTENER DE optMoneda

        editMonto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // no hacer nada
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // no hacer nada
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()){
                    pf.setMonto(0.0);
                    tvDiasSeleccionados.setText("");
                    tvIntereses.setText("");
                }
                else{
                    pf.setMonto(Double.valueOf(s.toString()));
                    tvDiasSeleccionados.setText(String.valueOf(pf.getDias()) + getResources().getString(R.string.diasPF));
                    tvIntereses.setText(String.format("$%.2f", pf.intereses()));
                }
            }
        });

        seekDias.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int cantDias, boolean b) {
                cantDias+=10;
                tvDiasSeleccionados.setText(String.valueOf(cantDias) + getResources().getString(R.string.diasPF));
                pf.setDias(cantDias);
                tvIntereses.setText(String.format("$%.2f", pf.intereses()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // no hacer nada
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // no hacer nada
            }

        });

        //ACA VA LISTENER DE swAvisarVencimiento

        //ACA VA LISTENER DE togAccion

        chkAceptoTerminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btnHacerPF.setEnabled(isChecked);
                if (!isChecked) {
                    toastTerminosCondiciones = Toast.makeText(getApplicationContext(), getResources().getString(R.string.toastTerminosCondiciones), Toast.LENGTH_SHORT);
                    toastTerminosCondiciones.show();
                }
            }
        });

        btnHacerPF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String errores = "";
                if(editMail.getText().toString().isEmpty()) errores+=getResources().getString(R.string.mailIncompleto);
                if(editCuit.getText().toString().isEmpty()) errores+=getResources().getString(R.string.cuitIncompleto);
                if(editMonto.getText().toString().isEmpty() || Integer.valueOf(editMonto.getText().toString())<=0)
                    errores+=getResources().getString(R.string.montoIncorrecto);
                if(errores.isEmpty())
                {
                    toastBtnHacerPF = Toast.makeText(getApplicationContext(),getResources().getString(R.string.toastPFValido),Toast.LENGTH_SHORT);
                    toastBtnHacerPF.getView().setBackgroundColor(getResources().getColor(R.color.colorCorrecto,getTheme()));
                    tvMensaje.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    tvMensaje.setText(pf.toString());
                }
                else
                {
                    toastBtnHacerPF = Toast.makeText(getApplicationContext(),getResources().getString(R.string.toastPFInvalido),Toast.LENGTH_SHORT);
                    toastBtnHacerPF.getView().setBackgroundColor(getResources().getColor(R.color.colorIncorrecto,getTheme()));
                    tvMensaje.setTextColor(getResources().getColor(R.color.colorIncorrecto));
                    tvMensaje.setText(errores);
                }
                toastBtnHacerPF.show();
            }
        });
    }
}
