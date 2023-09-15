package com.example.array_json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private ArrayList<Model>mData;
    Context context;


    public CustomAdapter(Context context,ArrayList<Model>mData){

        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(view); // pass the view to View Holder
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
          final Model model = mData.get(position);
          holder.tv1.setText(model.getTitle());
          holder.tv2.setText(model.getPrice());
          holder.tv3.setText(model.getDescription());
          holder.tv4.setText(model.getCategory());

          Glide.with(context)
                .asBitmap()
                .load(model.getImage())
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv1,tv2,tv3,tv4;
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = (TextView)itemView.findViewById(R.id.name);
            tv2 = (TextView)itemView.findViewById(R.id.name1);
            tv3 = (TextView) itemView.findViewById(R.id.name2);
            tv4 = (TextView) itemView.findViewById(R.id.name3);
            img = (ImageView)itemView.findViewById(R.id.img1);
            // Iterate through each TextView
            for (TextView textView : new TextView[]{tv1, tv2, tv3,tv4}) {
                // Get the layout parameters of the TextView
                ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();

                // Set the height of the TextView dynamically
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

                // Apply the updated layout parameters to the TextView
                textView.setLayoutParams(layoutParams);
            }
        }
    }
}
