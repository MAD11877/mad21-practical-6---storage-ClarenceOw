package sg.edu.np.madpractical;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserListViewHolder> {
    ArrayList<User> data;
    public UserAdapter(ArrayList<User> input){
        data = input;

    }
    public UserListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item;
        if (data.get(viewType).getName().endsWith("7")){
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerendwith7, parent,false);
        }
        else{
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent,false);
        }
        ImageView img = item.findViewById(R.id.imageView2);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Main", "Button Pressed");
                POPOUT(viewType);
            }
        });

        return new UserListViewHolder(item);
    }
    public void onBindViewHolder(UserListViewHolder holder, int position){
        User s = data.get(position);
        holder.txt1.setText(s.getName());
        holder.txt2.setText(s.getDescription());
    }
    public int getItemCount(){
        return data.size();
    }
    @Override
    public int getItemViewType(int position){
        return position;
    }

    private void POPOUT(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.context);
        Log.v("Main","Crash");
        builder.setTitle("Profile");
        User user = data.get(position);
        builder.setMessage(user.getName());
        builder.setCancelable(false);
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ListActivity.context, MainActivity.class);
                Bundle extras = new Bundle();
                extras.putString("Name", user.getName());
                extras.putBoolean("Followed", user.isFollowed());
                extras.putString("Description", user.getDescription());
                extras.putInt("Id", user.getId());
                intent.putExtras(extras);
                ListActivity.context.startActivity(intent);

            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}


