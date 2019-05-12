package com.example.alumini;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Recyclerviewadapter_mianlist extends RecyclerView.Adapter<Recyclerviewadapter_mianlist.Viewholder> {
    List<Database1> list;
    Context context;

    public Recyclerviewadapter_mianlist(List<Database1> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.studentlist_recyclerview,viewGroup,false);

        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, final int i) {
      viewholder.Name.setText(list.get(i).getFirstname());
      viewholder.lastname.setText(list.get(i).getLastname());
      viewholder.Gender.setText(list.get(i).getGender());
        final String    ss="https://firebasestorage.googleapis.com/v0/b/alumini-b878a.appspot.com/o/-LcVYlKlstKteAy-2lOC?alt=media&token=ace87687-f9fc-4913-b079-b7d12186a38a";

      String s=list.get(i).getUrl();
   if(s.isEmpty()) {
       viewholder.imageView.setImageResource(R.drawable.male);
    }
    else
   {
       Glide.with(context).load(list.get(i).getUrl()).into(viewholder.imageView);
   }

      viewholder.linearLayout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(context,Main5Activity.class);
              intent.putExtra("Firstname",list.get(i).getFirstname());

              intent.putExtra("Lastname",list.get(i).getLastname());
              intent.putExtra("Fathername",list.get(i).getFathername());
              intent.putExtra("Mothername",list.get(i).getMothername());
              intent.putExtra("Mobile",list.get(i).getMobileno());
              intent.putExtra("Address",list.get(i).getAddress());
              intent.putExtra("Course",list.get(i).getCourse());
              intent.putExtra("Dob",list.get(i).getDob());
              intent.putExtra("Gender",list.get(i).getGender());

                  intent.putExtra("Url", list.get(i).getUrl());
                  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              context.startActivity(intent);
          }
      });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class  Viewholder extends RecyclerView.ViewHolder{

         TextView Name,Gender,lastname;
LinearLayout linearLayout;
ImageView imageView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.firstname_textview);
            lastname=itemView.findViewById(R.id.lastname_textview);
            Gender=itemView.findViewById(R.id.gender_textview);
            linearLayout=itemView.findViewById(R.id.leinerlayout_studentlist);
            imageView=itemView.findViewById(R.id.studnetlist_circularimageview);
        }
    }



void Searchlist(List<Database1> database1s)
{
    list=new ArrayList<>();
    list.addAll(database1s);
    notifyDataSetChanged();
}

}
