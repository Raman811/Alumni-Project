package com.example.alumini;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
TextView textView;
    EditText emial_edittext,password_edittext;
    Button login_button;
    FirebaseAuth firebaseAuth;
    TextView ForgetPassword;
    AlertDialog.Builder builder;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView=findViewById(R.id.signup_textview);
        textView.setOnClickListener(this);
        emial_edittext=findViewById(R.id.email_edittext1);
        ForgetPassword=findViewById(R.id.forget_password);
        ForgetPassword.setOnClickListener(this);
        password_edittext=findViewById(R.id.password_edittext1);
        login_button=findViewById(R.id.login_button);
        login_button.setOnClickListener(this);
       FirebaseApp.initializeApp(getApplicationContext());
        firebaseAuth = FirebaseAuth.getInstance();
        builder = new AlertDialog.Builder(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user=firebaseAuth.getCurrentUser();
        updateUI(user);
    }

    void  login_user()
    {

        String email=emial_edittext.getText().toString();
        String password=password_edittext.getText().toString();
        if(email.isEmpty())
        {
            emial_edittext.setError("Enter email First");

            return;

        }
        if(email.matches(emailPattern))
        {
            if(password.isEmpty())
            {
                password_edittext.setError("Enter Your pasword");
                return;
            }
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        FirebaseUser user=firebaseAuth.getCurrentUser();
                        updateUI(user);
                        Toast.makeText(Main2Activity.this, "Login Successfully Done", Toast.LENGTH_SHORT).show();
                        emial_edittext.setText("");
                        password_edittext.setText("");
                    }
                    else {
                        Toast.makeText(Main2Activity.this, "Check Email and password", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
      else
        {
            Toast.makeText(this, "Enter Corrert Email", Toast.LENGTH_SHORT).show();
        }






    }
    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.signup_textview)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        if(id==R.id.forget_password)
        {
            final String s=emial_edittext.getText().toString();
            if(s.isEmpty())
            {
                emial_edittext.setError("Enter email First");
            }


            else {

                if (s.matches(emailPattern)) {


                    builder.setMessage("Do you want to Change Your Password ?")
                            .setCancelable(false)
                            .setPositiveButton("reset password", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {


                                    firebaseAuth.sendPasswordResetEmail(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Main2Activity.this, "We have sent you instructions to reset your password in your gmail!", Toast.LENGTH_SHORT).show();



                                            } else {
                                                Toast.makeText(Main2Activity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(), "Failed to reset password",
                                            Toast.LENGTH_LONG).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();

                    alert.setTitle("Forget Password");
                    alert.show();
                }
                else
                {
                    Toast.makeText(this, "Enter Correct Email", Toast.LENGTH_SHORT).show();
            }
        }



    }
        if(id == R.id.login_button)
        {

       login_user();
        }
    }
    void updateUI(FirebaseUser firebaseUser)
    {
        if(firebaseUser!=null)
        {

            startActivity(new Intent(getApplicationContext(),Main3Activity.class));
               finish();
        }
        else
        {
           // Toast.makeText(this, "Not Login", Toast.LENGTH_SHORT).show();
        }
    }
}
