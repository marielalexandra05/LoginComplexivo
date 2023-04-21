package com.mariela.leon.practicalogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText us, password;
    Button ingresar, crear;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        nextPage();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();//consulta la administracion de usuarios en firebase
    }


    private void nextPage() {

        us = (EditText) findViewById(R.id.txtEmail);
        password = (EditText) findViewById(R.id.txtContraseña);
        ingresar = (Button) findViewById(R.id.btnIniciaS);
        crear = (Button) findViewById(R.id.btnCrearC);
        //String usuarios = us.getText().toString();
        //Toast.makeText(getApplicationContext(),usuarios, Toast.LENGTH_LONG).show();

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent next = new Intent( getApplicationContext(),Menu.class);
                //next.putExtra("users", usuarios );
                //Toast.makeText(getApplicationContext(),"recibir", Toast.LENGTH_LONG).show();
                //startActivity(next);
                ingreso();
            }
        });

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent( getApplicationContext(),Activity_Crear_Cuenta.class);
                startActivity(next);
            }
        });

    }


    private void ingreso(){
        Toast.makeText(MainActivity.this, us.getText().toString() + password.getText().toString(),
                Toast.LENGTH_SHORT).show();
        mAuth.signInWithEmailAndPassword(us.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(MainActivity.this, ""+task,
                                Toast.LENGTH_SHORT).show();
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent creation = new Intent(MainActivity.this, Activity_Crud.class);
                            startActivity(creation);
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}