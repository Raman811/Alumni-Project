package com.example.alumini;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView; List<Dataholder_recyclerview> list;
    Button adduser,logout;
    String text[] = {"B-tech", "BSC", "BCA","BA", "MA", "M-tech", "MBA", "Diploma",};
    int imageId[] = {R.drawable.newimage, R.drawable.photo,R.drawable.photo, R.drawable.photo, R.drawable.newimage, R.drawable.photo, R.drawable.newimage, R.drawable.photo};
LinearLayout linearLayout;
ImageView backbutton;
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        recyclerView = findViewById(R.id.recyclerview);
        linearLayout=findViewById(R.id.mainlayout);
        adduser=findViewById(R.id.Addstudent_button);
        logout=findViewById(R.id.Logoutbutton);
        backbutton=findViewById(R.id.backarrow_imageview2);
        backbutton.setOnClickListener(this);
        adduser.setOnClickListener(this);
        logout.setOnClickListener(this);
        FirebaseApp.initializeApp(getApplicationContext());
        firebaseAuth=FirebaseAuth.getInstance();
        list = new ArrayList<>();
        for (int i = 0; i < imageId.length; i++) {
            Dataholder_recyclerview dataholder = new Dataholder_recyclerview(imageId[i], text[i]);
            list.add(dataholder);

        }
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        Recyclerview_Adapter adapter = new Recyclerview_Adapter(list,getApplicationContext(),linearLayout);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.Addstudent_button)
        {

        startActivity(new Intent(getApplicationContext(),Addstudent.class));

        }
        if(id==R.id.Logoutbutton)
        {
            firebaseAuth.signOut();
startActivity(new Intent(getApplicationContext(),Main2Activity.class));
finish();
        }
        if(id==R.id.backarrow_imageview2)
        {
            finish();
        }
    }
}
