package com.example.juana.pasedelista;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Alumno> alumnos;
    private ArrayList<Alumno> alumnos2;
    private ArrayList<String> matriculas1;
    private ArrayList<String> matriculas2;
    private ArrayList<Integer> asistencia1;
    private ArrayList<Integer> asistencia2;
    private int grupo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navegacion);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Enviando correo...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                switch (grupo){
                    case 1 : enviarCorreo(matriculas1, asistencia1); break;
                    case 2 : enviarCorreo(matriculas2, asistencia2); break;
                }

            }
        });
        alumnos = new ArrayList<>();
        alumnos.add(new Alumno(R.drawable.abdulabkbar, "Abdul Akbar", "15130156", true));
        alumnos.add(new Alumno(R.drawable.aldrin, "Aldrin y las Ardillas", "15130100", true));
        alumnos.add(new Alumno(R.drawable.andrea, "La Compa Andy", "15130128", true));
        alumnos.add(new Alumno(R.drawable.arcineda, "Bob el Constructor", "15130101", true));
        alumnos.add(new Alumno(R.drawable.dianita, "La Dianita loca", "15130102", true));
        alumnos.add(new Alumno(R.drawable.carlangas, "Carlangas Jimenez", "15130103", true));
        alumnos.add(new Alumno(R.drawable.ilde, "El Beffo del Teletoni", "15130104", true));
        alumnos.add(new Alumno(R.drawable.isai, "Sasuke Uchiha", "15130105", true));
        alumnos.add(new Alumno(R.drawable.lazcano, "Majin buu el Rosa", "15130106", true));
        alumnos.add(new Alumno(R.drawable.luis_mario, "Shivy Romero", "15130107", true));

        alumnos2 = new ArrayList<>();
        alumnos2.add(new Alumno(R.drawable.natalia, "Naty ", "15130200", true));
        alumnos2.add(new Alumno(R.drawable.romi, "El Negrote", "15130201", true));
        alumnos2.add(new Alumno(R.drawable.shary, "Shary Ibarra", "15130202", true));
        alumnos2.add(new Alumno(R.drawable.teletoni, "Chetin el Rata", "15130153", true));
        alumnos2.add(new Alumno(R.drawable.victor, "Voc Ruiz", "15130203", true));
        alumnos2.add(new Alumno(R.drawable.vince, "Vince blue", "15130204", true));
        alumnos2.add(new Alumno(R.drawable.vince2, "El compa Vince", "15130205", true));
        alumnos2.add(new Alumno(R.drawable.yopli, "Adolfo Ruiz", "15130258", true));
        alumnos2.add(new Alumno(R.drawable.isai, "Sasuke Uhicha 2", "15130206", true));
        alumnos2.add(new Alumno(R.drawable.shary, "Shary Ibarra 2", "15130207", true));

        agregarMatriculas();
        agregarAsistencias();

        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView listaAlumnos = findViewById(R.id.rv_listaGrupos);
        listaAlumnos.setLayoutManager(llm);
        AdaptadorAlumnos alumnoAdaptador = new AdaptadorAlumnos(alumnos, asistencia1);
        listaAlumnos.setAdapter(alumnoAdaptador);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.agregar_correo) {
            obtenerEmail();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navegacion_grupo1:
                    grupo = 1;
                    LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    RecyclerView listaAlumnos = findViewById(R.id.rv_listaGrupos);
                    listaAlumnos.setLayoutManager(llm);
                    AdaptadorAlumnos alumnoAdaptador = new AdaptadorAlumnos(alumnos, asistencia1);
                    listaAlumnos.setAdapter(alumnoAdaptador);
                    Log.d("GRUPO 1", "111111111111111");
                    return true;
                case R.id.navegacion_grupo2:
                    grupo = 2;
                    LinearLayoutManager llm2 = new LinearLayoutManager(MainActivity.this);
                    llm2.setOrientation(LinearLayoutManager.VERTICAL);
                    RecyclerView listaAlumnos2 = findViewById(R.id.rv_listaGrupos);
                    listaAlumnos2.setLayoutManager(llm2);
                    AdaptadorAlumnos alumnoAdaptador2 = new AdaptadorAlumnos(alumnos2, asistencia2);
                    listaAlumnos2.setAdapter(alumnoAdaptador2);
                    Log.d("GRUPO 2", "222222222222");
                    return true;
            }
            return false;
        }
    };

    public void obtenerEmail(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setTitle("Ingrese email:");
        final View dialoglayout = inflater.inflate(R.layout.modelo_email, null);
        builder.setView(dialoglayout)
                // Add action buttons
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText etEmail = dialoglayout.findViewById(R.id.tidt_email);
                        String email = etEmail.getText().toString();
                        Log.d("EMAIL: ", ""+email);
                        SharedPreferences preferences  = getSharedPreferences("Email", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("email", email);
                        editor.commit();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void agregarMatriculas(){
        //grupo retrasados 1
        matriculas1 = new ArrayList<>();
        matriculas2 = new ArrayList<>();
        matriculas1.add("15130156");
        matriculas1.add("15130100");
        matriculas1.add("15130128");
        matriculas1.add("15130101");
        matriculas1.add("15130102");
        matriculas1.add("15130103");
        matriculas1.add("15130104");
        matriculas1.add("15130105");
        matriculas1.add("15130106");
        matriculas1.add("15130107");
        //Grupo retrasados 2
        matriculas2.add("15130200");
        matriculas2.add("15130201");
        matriculas2.add("15130202");
        matriculas2.add("15130153");
        matriculas2.add("15130203");
        matriculas2.add("15130204");
        matriculas2.add("15130205");
        matriculas2.add("15130258");
        matriculas2.add("15130206");
        matriculas2.add("15130207");
    }

    public void agregarAsistencias(){
        asistencia1 = new ArrayList<>();
        asistencia2 = new ArrayList<>();
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
    }

    public void enviarCorreo(ArrayList<String> matriculas, ArrayList<Integer> asistecias){
        Intent intent = new Intent(Intent.ACTION_SEND);

        SharedPreferences preferences  = getSharedPreferences("Email", Context.MODE_PRIVATE);
        String emailGuardado = preferences.getString("email", "nada");

        String[] to = {emailGuardado};
        String[] cc = {""};
        String contenido = "";

        for (int i = 0; i < 10; i++) {
            contenido = contenido.concat(matriculas.get(i) + " --- "+ asistecias.get(i)+"\n");
        }

        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_CC, cc);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Programa Pase Lista 15130128");
        intent.putExtra(Intent.EXTRA_TEXT,"Lista asistencias: \n"+contenido);
        try {
            startActivity(Intent.createChooser(intent, "How to send mail?"));
        } catch (android.content.ActivityNotFoundException ex) {

        }
    }

}
