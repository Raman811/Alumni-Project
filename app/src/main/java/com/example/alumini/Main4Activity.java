package com.example.alumini;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {
RecyclerView recyclerView;
TextView textView;
List<Database1>  list;
    DatabaseReference reference;
SearchView searchView;
    ImageView imageView;
    FirebaseDatabase database;
    Recyclerviewadapter_mianlist mianlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
recyclerView=findViewById(R.id.mainlist_recyclerview);
textView=findViewById(R.id.studentdetail);
//searchView=findViewById(R.id.searchview);
        Bundle extras = getIntent().getExtras();
        final String value1 = extras.getString("course");
        textView.setText(value1+"  Students");
        imageView=findViewById(R.id.backarrow_imageview3);
        //reference = FirebaseDatabase.getInstance().getReference("student_data");
        database = FirebaseDatabase.getInstance();
imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
    }
});
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("List upload");
dialog.show();
                reference=database.getReference("student_data");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        list=new ArrayList<>();
//Firebase_dataenter dataenter=dataSnapshot.child("BA").getValue(Firebase_dataenter.class);

                        for (DataSnapshot child : dataSnapshot.child(value1).getChildren()) {

                            Database1 database=child.getValue(Database1.class);
                            list.add(database);
                        }
                        Recyclerviewadapter_mianlist recyclerviewadapter_mianlist = new Recyclerviewadapter_mianlist(list,getApplicationContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        dialog.dismiss();
                        recyclerView.setAdapter(recyclerviewadapter_mianlist);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
//        SearchManager manager= (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
//searchView.setMaxWidth(Integer.MAX_VALUE);
//searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        newText=newText.toLowerCase();
//        List<Database1> list=new ArrayList<>();
//        for(Database1 database1:list)
//        {
//            String name=database1.getFirstname().toLowerCase();
//            if(name.contains(newText))
//            {
//list.add(database1);
//
//            }
//        }
//mianlist.Searchlist(list);
//        return false;
//    }
//});




}

}

