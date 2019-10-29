package com.example.beaconscanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    // Servidor
    ServidorFake servidorFake;

    Button botonLogearse;

    TextInputLayout inputEmailLayout;
    TextInputLayout inputPassLayout;

    // Para recordar que se ha logeado
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        servidorFake = new ServidorFake(this);
        TextView registrateaqui = findViewById(R.id.registrateaqui);
        inputPassLayout = findViewById(R.id.texto_password_layout);
        inputEmailLayout = findViewById(R.id.texto_email_layout);
        botonLogearse = findViewById(R.id.botonLogin);

        final TextInputLayout inputPassLayout = findViewById(R.id.texto_password_layout);
        final TextInputEditText inputEmail = findViewById(R.id.texto_email);
        final TextInputEditText inputPass = findViewById(R.id.texto_pass);

        // Primero comprobamos si ya hizo login anteriormente
        // Recogemos las preferencias de la app
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        String email = loginPreferences.getString("email", "");
                String pass = loginPreferences.getString("pass", "");

        Log.d("pruebas", " preferencias: " + email);
        Log.d("pruebas", " preferencias: " + pass);

        // Si se ha rellenado hacemos el login
        if (validarSiEstanVacios(email, pass)) {
            servidorFake.comprobarUsuarioPorEmail(email, pass);
        }

        registrateaqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegistrarUsuarioActivity.class);
                startActivity(i);
            }
        });


        botonLogearse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logearse();
            }
        });


        inputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inputEmailLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        inputEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    inputEmail.setText(null);
                }
            }
        });

        inputPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inputPassLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        inputPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    inputPass.setText(null);
                }
            }
        });

    }


    // ---------------------------------------------------------------------------
    // -> logearse() ->
    // ---------------------------------------------------------------------------
    public void logearse() {
        inputPassLayout.setError(null);
        inputEmailLayout.setError(null);
        TextInputEditText inputEmail = findViewById(R.id.texto_email);
        TextInputEditText inputPass = findViewById(R.id.texto_pass);
        String email = inputEmail.getText().toString();
        String pass = inputPass.getText().toString();
        if (validarSiEstanVacios(email, pass)) {
            loginPrefsEditor.putString("email", email);
            loginPrefsEditor.putString("pass", pass);
            loginPrefsEditor.commit();
            servidorFake.comprobarUsuarioPorEmail(email, pass);
        }
    }

    // ---------------------------------------------------------------------------
    // -> errorLogin() ->
    // ---------------------------------------------------------------------------
    public void errorLogin() {
        TextInputLayout inputEmailLayout = findViewById(R.id.texto_email_layout);
        inputEmailLayout.setError("Contraseña y/o e-mail incorrectos");

        TextInputLayout inputPassLayout = findViewById(R.id.texto_password_layout);
        inputPassLayout.setError("Contraseña y/o e-mail incorrectos");
    }

    // ---------------------------------------------------------------------------
    // email, pass -> validarSiEstanVacios() -> boolean
    // ---------------------------------------------------------------------------
    private boolean validarSiEstanVacios(String email, String pass) {
        if (email.equals("")) {
            inputEmailLayout.setError(getString(R.string.completar));
            return false;
        }
        else if (pass.equals("")) {
            inputPassLayout.setError(getString(R.string.completar));
            return false;
        }
        else {
            return true;
        }
    }

}