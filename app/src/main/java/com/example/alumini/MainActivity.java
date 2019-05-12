package com.example.alumini;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText phoneno_edittext, otp_edittext, email_edittext, password_edittext, confirmpassword_edittext;
    Button pnone_verificaton, otp_verification;
    FirebaseAuth firebaseAuth;

   ImageView imageView;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    TextView textView;
    String Codesent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneno_edittext = findViewById(R.id.edittext_phoneno);
        otp_edittext = findViewById(R.id.edittext_otp);
        pnone_verificaton = findViewById(R.id.phone_button);
        textView = findViewById(R.id.textview_resendotp);
        textView.setOnClickListener(this);
        pnone_verificaton.setOnClickListener(this);
        otp_verification = findViewById(R.id.otp_button);
        imageView=findViewById(R.id.backarrow_imageview);
        imageView.setOnClickListener(this);
        email_edittext = findViewById(R.id.edittext_username);
        password_edittext = findViewById(R.id.edittextpassword);
        confirmpassword_edittext = findViewById(R.id.edittext_confirmpassword);
        otp_verification.setOnClickListener(this);
        FirebaseApp.initializeApp(getApplicationContext());
        firebaseAuth = FirebaseAuth.getInstance();
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setCancelable(true);
        dialog.setMessage("Please wait");
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(MainActivity.this, "Exception Occured" + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                Toast.makeText(MainActivity.this, "Code sent", Toast.LENGTH_SHORT).show();
                Codesent = s;
            }
        };

    }

    void verificationSigninCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(Codesent, code);
        signinWithPhoneauthCrendatial(credential);

    }

    void signinWithPhoneauthCrendatial(PhoneAuthCredential credential) {

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    startActivity(new Intent(getApplicationContext(), Main3Activity.class
                    ));
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.phone_button) {
            String phoneno = phoneno_edittext.getText().toString();
            if (phoneno.isEmpty()) {
                phoneno_edittext.setError("Enter Your Phone no:");
                return;
            }
            if (phoneno.length() < 10) {
                phoneno_edittext.setError("Enter correct phoneno");
                phoneno_edittext.requestFocus();
                return;

            }
            sendVerificationcode(phoneno);
        }
        if (id == R.id.otp_button) {

createUserwithEmailandPassword();

        }
        if (id == R.id.textview_resendotp) {
            String phoneno = phoneno_edittext.getText().toString();
            if (phoneno.isEmpty()) {
                phoneno_edittext.setError("Enter Your Phone no:");
                return;
            }
            if (phoneno.length() < 10) {
                phoneno_edittext.setError("Enter correct phoneno");
                phoneno_edittext.requestFocus();
                return;

            }

            sendVerificationcode(phoneno);

        }

        if(id==R.id.backarrow_imageview)
        {
            startActivity(new Intent(this,Main2Activity.class));
            finish();
        }

    }

    void sendVerificationcode(String phoneno) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + phoneno,
                60,
                TimeUnit.SECONDS,
                this, mCallbacks
        );

    }

    void createUserwithEmailandPassword() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String phoneno = phoneno_edittext.getText().toString();
        String email = email_edittext.getText().toString().trim();
        String password = password_edittext.getText().toString().trim();
        String reenter_password = confirmpassword_edittext.getText().toString().trim();
        if (email.isEmpty()) {
            email_edittext.setError("Enter email first");
        } else {
            if (email.matches(emailPattern)) {

                if (password.isEmpty()) {
                    password_edittext.setError("Enter password first");
                } else {

                    if (reenter_password.isEmpty()) {
                        confirmpassword_edittext.setError("Confirm your password");
                    } else {


                        if (password.length() >= 8) {
                            if (password.equals(reenter_password)) {
                                if (phoneno.isEmpty()) {
                                    phoneno_edittext.setError("Enter Your Phone no:");
                                    return;
                                }
                                if (phoneno.length() < 10) {
                                    phoneno_edittext.setError("Enter correct phoneno");
                                    phoneno_edittext.requestFocus();
                                    return;

                                }
                                String code = otp_edittext.getText().toString();
                                if (code.isEmpty()) {
                                    otp_edittext.setError("Enter Your OTP");
                                    return;
                                }
                                if (code.length() < 6) {
                                    otp_edittext.setError("Enter correct OTP");
                                    otp_edittext.requestFocus();
                                    return;

                                }
                                createuser(email, password);

                                verificationSigninCode(code);


                            } else {
                                Toast.makeText(this, "Password didnot match", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "Password atleast 8 char", Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            } else {
                email_edittext.setError("Enter correct Email");
                //Toast.makeText(this, "Enter Correct Email", Toast.LENGTH_SHORT).show();
            }
        }


    }

    void createuser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    Toast.makeText(MainActivity.this, "DAta successfully enter", Toast.LENGTH_SHORT).show();
                    email_edittext.setText("");
                    password_edittext.setText("");
                    confirmpassword_edittext.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Data not entererd", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}