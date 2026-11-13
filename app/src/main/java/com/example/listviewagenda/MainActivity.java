package com.example.listviewagenda;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends Activity {
    String[] datos = new String[]{"Juan García - 600123456", "María López - 611234567", "Pedro Martínez - 622345678", "Laura Fernández - 633456789", "Carlos Sánchez - 644567890"};
    final Contacte[] datosE = new Contacte[]{

            new Contacte(1, "Juan", "García", "Calle Mayor 12", "600123456", "juan@gmail.com", "15/03/1990", "Google", "Amigo"),

            new Contacte(2, "María", "López", "Calle Barcelona 25", "611234567", "maria@gmail.com", "20/06/1995", "Microsoft", "Compañera de trabajo"),

            new Contacte(3, "Pedro", "Martínez", "Avenida Cataluña 8", "622345678", "pedro@gmail.com", "10/01/1988", "Amazon", "Vecino"),

            new Contacte(4, "Laura", "Fernández", "Calle Sol 3", "633456789", "laura@gmail.com", "05/12/1992", "Apple", "Amiga"),

            new Contacte(5, "Carlos", "Sánchez", "Plaza España 10", "644567890", "carlos@gmail.com", "22/09/1991", "Samsung", "Familia")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, datos);

        ListView contactes = (ListView) findViewById(R.id.contactes);
        contactes.setAdapter(adaptador);

        contactes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}