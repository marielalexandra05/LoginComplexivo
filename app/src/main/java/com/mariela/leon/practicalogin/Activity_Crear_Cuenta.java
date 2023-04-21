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

public class Activity_Crear_Cuenta extends AppCompatActivity {

    EditText correo, password, repeatpassword;
    Button ingre, cancelar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        mAuth = FirebaseAuth.getInstance(); //intancia del firebase
        nextPage();
    }

    //Verificacion del correo para q no se repita
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }


    private void nextPage() {

        correo = (EditText) findViewById(R.id.txtEmailC);
        password = findViewById(R.id.txtContra);
        repeatpassword = findViewById(R.id.txtConfirmacionContra);
        ingre = (Button) findViewById(R.id.btnResgistrar);
        cancelar = (Button) findViewById(R.id.btnCancelar);
        Toast.makeText(Activity_Crear_Cuenta.this, "recibir!"+correo.getText().toString()+ password.getText().toString(),
                Toast.LENGTH_SHORT).show();
        ingre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creacion();
            }
        });
    }

    private void creacion(){

        Toast.makeText(Activity_Crear_Cuenta.this, "entre!"+correo.getText().toString()+ password.getText().toString(),
                Toast.LENGTH_SHORT).show();

        if(password.getText().toString().equals(repeatpassword.getText().toString())){
            mAuth.createUserWithEmailAndPassword(correo.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent creation = new Intent(Activity_Crear_Cuenta.this, MainActivity.class);
                                startActivity(creation);
                            } else {
                                Toast.makeText(Activity_Crear_Cuenta.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else{
            Toast.makeText(Activity_Crear_Cuenta.this, "No coinciden las contraseñas.",
                    Toast.LENGTH_SHORT).show();
        }
    }

}