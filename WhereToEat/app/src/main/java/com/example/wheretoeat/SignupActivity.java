package com.example.wheretoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText sUsername, sPassword;
    Button sButton;
    TextView sloginRedirectText;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        sUsername = findViewById(R.id.signup_username);
        sPassword = findViewById(R.id.signup_password);
        sButton = findViewById(R.id.signup_button);
        sloginRedirectText = findViewById(R.id.loginRedirectText);

        sButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");

                String username = sUsername.getText().toString();
                String password = sPassword.getText().toString();

                UserHelperClass helperClass = new UserHelperClass(username, password);

                reference.child(username).setValue(helperClass);

                sUsername.setText("");
                sPassword.setText("");

                Toast.makeText(getApplicationContext(), "Account Created!", Toast.LENGTH_LONG).show();

                Intent intent  = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        });

        sloginRedirectText.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }));

    }
}