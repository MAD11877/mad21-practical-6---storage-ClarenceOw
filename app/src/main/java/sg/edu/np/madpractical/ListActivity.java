package sg.edu.np.madpractical;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    private final static String TAG = "Main ListActivity";
    public static Context context;
    ArrayList<User> uList = new ArrayList<>();
    MyDBHandler DBHandler = new MyDBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        for (int i=0;i<20;i++){
            User NewUser = new User("Name-" + randomOTP(),"Description " + randomOTP(),i+1, randomfollow());
            //DBHandler.addUser(NewUser);
        }
        RecyclerView recyclerView = findViewById(R.id.UserRV);
        UserAdapter UAdapter = new UserAdapter(DBHandler.getUser());
        LinearLayoutManager uLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(uLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(UAdapter);

    }

    private int randomOTP(){
        Random ran = new Random();
        int value = ran.nextInt(1000000000);
        return value;
    }

    private boolean randomfollow(){
        Random ran = new Random();
        return ran.nextBoolean();
    }


}