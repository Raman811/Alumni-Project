package com.example.alumini;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import id.zelory.compressor.Compressor;

public class Addstudent extends AppCompatActivity implements View.OnClickListener {
    TextView date;
    Uri filePath;
    ImageView imageView, imageview_backicon;
    DatePickerDialog.OnDateSetListener mDateSetlistener;
    EditText firstname, lastname, fathername, mothername, address, mobileno;
    RadioGroup radioGroup;
    List<Dataholder> list;
    RadioButton radioButton;
    StorageReference storageReference;
    DatabaseReference reference;
    DatabaseReference databaseReference;
    Button chooseimage, adduser;
    String course;
    Spinner spinner;
    String z;
    Arrayadapter_new arrayadapter_spinner;
    String Spinner_list[] = {"Choose", "BCA", "BA", "MA", "M-tech", "MBA", "Diploma", "B-tech","BSC"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);
        date = findViewById(R.id.date_textview);
        firstname = findViewById(R.id.edittext_firstName);
        lastname = findViewById(R.id.edittext_lastname);
        fathername = findViewById(R.id.edittext_fathername);
        mothername = findViewById(R.id.edittext_mothername);
        address = findViewById(R.id.edittext_address);
        mobileno = findViewById(R.id.edittext_moblieno);
        radioGroup = findViewById(R.id.radiogroup);
        adduser = findViewById(R.id.mybutton_add);
      //  storageReference = FirebaseStorage.getInstance().getReference();
        reference = FirebaseDatabase.getInstance().getReference("student_data");
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        spinner = findViewById(R.id.spinner);


        imageview_backicon = findViewById(R.id.backarrow_imageview11);
        imageview_backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list = new ArrayList<>();
        for (int i = 0; i < Spinner_list.length; i++) {
            Dataholder adapter = new Dataholder(Spinner_list[i]);
            list.add(adapter);

        }


        arrayadapter_spinner = new Arrayadapter_new(getApplicationContext(), list);
        spinner.setAdapter(arrayadapter_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                course = list.get(position).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        chooseimage = findViewById(R.id.choose_image);
        imageView = findViewById(R.id.gallary_imageview);
        chooseimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterData();
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(Addstudent.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetlistener, year, month, day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });

        mDateSetlistener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                String date1 = day + "-" + month + "-" + year;
                date.setText(date1);


            }
        };


    }



    void enterData() {

        final String myname, namelast, myaddress, fathersname, mothersname, mymobileno;
        myname = firstname.getText().toString();
        namelast = lastname.getText().toString();
        myaddress = address.getText().toString();
        fathersname = fathername.getText().toString();
        mothersname = mothername.getText().toString();
        mymobileno = mobileno.getText().toString();
        final String dob = date.getText().toString();
        int id = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(id);




        if(myname.isEmpty())
        {
            firstname.setError("Enter Name");
return;
        }
        else if(fathersname.isEmpty())
        {
            fathername.setError("Enter father name");
            return;
        }
        else if(mothersname.isEmpty())
        {
            mothername.setError("Enter Mother name");
return;
        }
        else if(myaddress.isEmpty())
        {
            address.setError("Enter Address");
            return;
        }
        else if(mymobileno.isEmpty())
        {
            mobileno.setError("Enter mobile no");
            return;
        }
else if(dob.isEmpty())
        {
            Toast.makeText(this, "Enter Your Dob", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(course=="Choose")
        {
            Toast.makeText(this, "Select Management First", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            final String gender;
            final String i = reference.push().getKey();
            try {
                gender = radioButton.getText().toString();
            }
            catch (Exception e)
            {
                Toast.makeText(this, "Select gender first", Toast.LENGTH_SHORT).show();
                return;
            }
            if (filePath != null) {

                //  Database1 database1 = new Database1(myname, namelast, fathersname, mothersname, myaddress, mymobileno, dob, gender, course, z);
                final ProgressDialog dialog = new ProgressDialog(this);
                dialog.setTitle("Uploaded");
                StorageReference reference = storageReference.child(i);
                //  StorageReference ref = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(filepath));
                reference.putFile(filePath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        dialog.dismiss();

                        UploadTask.TaskSnapshot uri = task.getResult();

                        Task<Uri> name = uri.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                try {


                                    z = task.getResult().toString();
                                    Database1 database1 = new Database1(myname, namelast, fathersname, mothersname, myaddress, mymobileno, dob, gender, course, z);
                                    uploadData(database1, i);
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(Addstudent.this, ""+e, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        dialog.show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialog.dismiss();
                        Toast.makeText(Addstudent.this, "Failure occured" + e, Toast.LENGTH_SHORT).show();
                    }
                });

            } else {


                Database1 database1 = new Database1(myname, namelast, fathersname, mothersname, myaddress, mymobileno, dob, gender, course, "");
                uploadData(database1, i);
                //  Firebase_dataenter firebase_dataenter=new Firebase_dataenter(myname,namelast,fathersname,mothersname,myaddress,mymobileno,dob,gender,course);//        reference.setValue(firebase_dataenter);


            }

        }





           }

    void uploadData(Database1 database1, String i) {
        reference.child(course).child(i).setValue(database1);
        Toast.makeText(this, "Data successfully entered", Toast.LENGTH_SHORT).show();
        firstname.setText("");
        mothername.setText("");
        mobileno.setText("");
        lastname.setText("");
        address.setText("");
        fathername.setText("");
        imageView.setImageResource(R.drawable.male);
        date.setText("");
        spinner.setAdapter(arrayadapter_spinner);


    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            if (filePath != null) {


                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "Choose Image First", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
int id=v.getId();
//if(id==R.id.backarrow_imageview11)
//{
//    finish();
//}
    }
}
