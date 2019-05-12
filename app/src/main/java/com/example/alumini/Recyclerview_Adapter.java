package com.example.alumini;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Recyclerview_Adapter extends RecyclerView.Adapter<Recyclerview_Adapter.Viewholder> {
 List<Dataholder_recyclerview>  list;
 Context context;
    LinearLayout linearLayout;

    public Recyclerview_Adapter(List<Dataholder_recyclerview> list, Context context, LinearLayout linearLayout) {
        this.list = list;
        this.context = context;
        this.linearLayout = linearLayout;
    }

    @NonNull
    @Override

    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_layout2,viewGroup,false);
       Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder viewholder, final int i) {
     //  viewholder.imageView.setImageResource(list.get(i).getImgid());
       viewholder.textView.setText(list.get(i).getText());
       final int a=i;

       viewholder.cardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              Intent intent=new Intent(context,Main4Activity.class);
              intent.putExtra("course",list.get(a).getText());
              intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

           }
       });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Viewholder extends RecyclerView.ViewHolder
    {
ImageView imageView;
TextView textView;
 LinearLayout cardView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
          //  imageView=itemView.findViewById(R.id.imageview);
            textView=itemView.findViewById(R.id.firstname_textview1);
            cardView=itemView.findViewById(R.id.leinerlayout_main);
        }
    }
}
