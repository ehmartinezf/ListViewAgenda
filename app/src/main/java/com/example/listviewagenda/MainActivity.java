package com.example.listviewagenda;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final ArrayList<Contacte> datosE = new ArrayList<>();
    AdaptadorContactes adaptador;
    int posicio;
    int seguentId = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        datosE.add(new Contacte(1, "Juan", "García", "Calle Mayor 12", "600123456", "juan@gmail.com", "15/03/1990", "Google", "Amigo"));
        datosE.add(new Contacte(2, "María", "López", "Calle Barcelona 25", "611234567", "maria@gmail.com", "20/06/1995", "Microsoft", "Compañera de trabajo"));
        datosE.add(new Contacte(3, "Pedro", "Martínez", "Avenida Cataluña 8", "622345678", "pedro@gmail.com", "10/01/1988", "Amazon", "Vecino"));
        datosE.add(new Contacte(4, "Laura", "Fernández", "Calle Sol 3", "633456789", "laura@gmail.com", "05/12/1992", "Apple", "Amiga"));
        datosE.add(new Contacte(5, "Carlos", "Sánchez", "Plaza España 10", "644567890", "carlos@gmail.com", "22/09/1991", "Samsung", "Familia"));

        adaptador = new AdaptadorContactes(this);
        ListView contactes = findViewById(R.id.contactes);
        Button btnAfegir = findViewById(R.id.btnAfegir);

        contactes.setAdapter(adaptador);

        contactes.setOnItemClickListener((parent, view, position, id) -> {
            obrirContacte(ContacteActivity.VEURE, position);
        });

        btnAfegir.setOnClickListener(v -> {
            obrirContacte(ContacteActivity.AFEGIR, -1);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void obrirContacte(int mode, int position) {
        posicio = position;
        Intent intent = new Intent(this, ContacteActivity.class);
        intent.putExtra("mode", mode);
        if (position >= 0) {
            intent.putExtra("contacte", datosE.get(position));
        }
        startActivityForResult(intent, mode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == ContacteActivity.AFEGIR) {
            Contacte nou = (Contacte) data.getSerializableExtra("contacte");
            nou.setId(seguentId);
            seguentId++;
            datosE.add(nou);
        } else if (requestCode == ContacteActivity.MODIFICAR) {
            Contacte modificat = (Contacte) data.getSerializableExtra("contacte");
            datosE.set(posicio, modificat);
        } else if (requestCode == ContacteActivity.ESBORRAR) {
            datosE.remove(posicio);
        }

        adaptador.notifyDataSetChanged();
    }

    class AdaptadorContactes extends ArrayAdapter<Contacte> {

        Activity context;

        public AdaptadorContactes(Activity context) {
            super(context, R.layout.listitem_contacte, datosE);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_contacte, null);

            Contacte contacte = datosE.get(position);

            TextView lblNom = item.findViewById(R.id.lblNom);
            TextView lblTelefon = item.findViewById(R.id.lblTelefon);
            TextView lblEmail = item.findViewById(R.id.lblEmail);
            ImageButton btnEditar = item.findViewById(R.id.btnEditar);
            ImageButton btnEsborrar = item.findViewById(R.id.btnEsborrar);
            ImageButton btnTrucar = item.findViewById(R.id.btnTrucar);
            ImageButton btnCorreu = item.findViewById(R.id.btnCorreu);

            lblNom.setText(contacte.getNom() + " " + contacte.getCognom());
            lblTelefon.setText(contacte.getTelefon());
            lblEmail.setText(contacte.getEmail());

            btnEditar.setOnClickListener(v -> {
                obrirContacte(ContacteActivity.MODIFICAR, position);
            });

            btnEsborrar.setOnClickListener(v -> {
                obrirContacte(ContacteActivity.ESBORRAR, position);
            });

            btnTrucar.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + contacte.getTelefon()));
                startActivity(intent);
            });

            btnCorreu.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + contacte.getEmail()));
                startActivity(intent);
            });

            return item;
        }
    }
}
