package com.example.listviewagenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContacteActivity extends AppCompatActivity {

    public static final int AFEGIR = 1;
    public static final int MODIFICAR = 2;
    public static final int ESBORRAR = 3;
    public static final int VEURE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacte);

        TextView lblTitol = findViewById(R.id.lblTitol);
        EditText etNom = findViewById(R.id.etNom);
        EditText etCognom = findViewById(R.id.etCognom);
        EditText etAdreca = findViewById(R.id.etAdreca);
        EditText etTelefon = findViewById(R.id.etTelefon);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etDataNaixement = findViewById(R.id.etDataNaixement);
        EditText etEmpresa = findViewById(R.id.etEmpresa);
        EditText etNotes = findViewById(R.id.etNotes);
        Button btnAcceptar = findViewById(R.id.btnAcceptar);
        Button btnCancelar = findViewById(R.id.btnCancelar);

        int mode = getIntent().getIntExtra("mode", AFEGIR);
        Contacte contacte = (Contacte) getIntent().getSerializableExtra("contacte");

        if (contacte != null) {
            etNom.setText(contacte.getNom());
            etCognom.setText(contacte.getCognom());
            etAdreca.setText(contacte.getAdreca());
            etTelefon.setText(contacte.getTelefon());
            etEmail.setText(contacte.getEmail());
            etDataNaixement.setText(contacte.getDataNaixement());
            etEmpresa.setText(contacte.getEmpresa());
            etNotes.setText(contacte.getNotes());
        }

        if (mode == AFEGIR) {
            lblTitol.setText(R.string.titol_afegir);
        } else if (mode == MODIFICAR) {
            lblTitol.setText(R.string.titol_modificar);
        } else if (mode == ESBORRAR) {
            lblTitol.setText(R.string.titol_esborrar);
        } else {
            lblTitol.setText(R.string.titol_veure);
        }

        if (mode == ESBORRAR || mode == VEURE) {
            etNom.setEnabled(false);
            etCognom.setEnabled(false);
            etAdreca.setEnabled(false);
            etTelefon.setEnabled(false);
            etEmail.setEnabled(false);
            etDataNaixement.setEnabled(false);
            etEmpresa.setEnabled(false);
            etNotes.setEnabled(false);
        }

        if (mode == VEURE) {
            btnCancelar.setVisibility(View.GONE);
        }

        btnAcceptar.setOnClickListener(v -> {
            if (mode == AFEGIR || mode == MODIFICAR) {
                String nom = etNom.getText().toString().trim();

                if (nom.isEmpty()) {
                    etNom.setError(getString(R.string.error_nom));
                    return;
                }

                Contacte resultat = new Contacte(
                        nom,
                        etCognom.getText().toString().trim(),
                        etAdreca.getText().toString().trim(),
                        etTelefon.getText().toString().trim(),
                        etEmail.getText().toString().trim(),
                        etDataNaixement.getText().toString().trim(),
                        etEmpresa.getText().toString().trim(),
                        etNotes.getText().toString().trim());

                if (contacte != null) {
                    resultat.setId(contacte.getId());
                }

                Intent data = new Intent();
                data.putExtra("contacte", resultat);
                setResult(RESULT_OK, data);
            } else {
                setResult(RESULT_OK);
            }

            finish();
        });

        btnCancelar.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
