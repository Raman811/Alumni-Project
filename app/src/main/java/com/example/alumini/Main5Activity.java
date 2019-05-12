package com.example.alumini;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Main5Activity extends AppCompatActivity implements View.OnClickListener {
String Firstname,Lastname,Mothername,Fathername,Dob,Mobile,Course,Address,Gender,url;
ImageView imageView,back_imageview,mainprofiileimageview,imageview2;
TextView First,Last,mobile,dob,course,father,mother,address,gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Bundle bundle=getIntent().getExtras();
        imageView=findViewById(R.id.imageview_profile);
        imageview2=findViewById(R.id.imageview_profile1);
        imageview2.setOnClickListener(this);
        Firstname=bundle.getString("Firstname");
        Lastname=bundle.getString("Lastname");
        Mothername=bundle.getString("Mothername");
        Fathername=bundle.getString("Fathername");
        url=bundle.getString("Url");
        Dob=bundle.getString("Dob");
back_imageview=findViewById(R.id.backarrow_imageview_profile);
        Mobile=bundle.getString("Mobile");
        back_imageview.setOnClickListener(this);
        Course=bundle.getString("Course");
        Address=bundle.getString("Address");
        Gender=bundle.getString("Gender");
        gender=findViewById(R.id.gender);
        First=findViewById(R.id.Firstname_profile);
        Last=findViewById(R.id.Lastname_profile);
        mobile=findViewById(R.id.Mobile);
        dob=findViewById(R.id.Dob);
        course=findViewById(R.id.Course);
        father=findViewById(R.id.father);
        mother=findViewById(R.id.mother);
        address=findViewById(R.id.address);
      mainprofiileimageview=findViewById(R.id.main_profile_imageview);
      if(url.isEmpty())
      {

      }
      else
      {
          Glide.with(getApplicationContext()).load(url).into(mainprofiileimageview);

      }
        First.setText(Firstname);
        Last.setText(Lastname);
        mobile.setText(Mobile);
        dob.setText(Dob);
        course.setText(Course);
        father.setText(Fathername);
        mother.setText(Mothername);
        address.setText(Address);
        gender.setText(Gender);
        imageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.imageview_profile)
        {
            Uri u = Uri.parse("tel:" + Mobile);
            Intent callIntent = new Intent(Intent.ACTION_DIAL, u);
            try {


                startActivity(callIntent);
            }catch (Exception e)
            {
                Toast.makeText(this, "Exception occured", Toast.LENGTH_SHORT).show();
            }

        }
        if(id==R.id.backarrow_imageview_profile)
        {

            finish();

        }
        if(id==R.id.imageview_profile1)
        {

            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("smsto:"+Mobile));
                     // sendIntent.setData(Uri.parse("sms:"));
               startActivity(sendIntent);

        }
    }
}
