package sg.edu.np.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main Activity";
    private MyDBHandler DBHandler = new MyDBHandler(this, null, null, 1);
    private User Number1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //User Number1 = new User("MAD", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua", 1, false);
//        TextView Desc1 = findViewById(R.id.Description);
//        Desc1.setText(Number1.getDescription());
        TextView name = findViewById(R.id.Name);
        TextView description = findViewById(R.id.Description);
        Button button = findViewById(R.id.follow);
        Intent receivedData = getIntent();
        //Number1 = new User(receivedData.getStringExtra("Name"), receivedData.getStringExtra("Description"), receivedData.getIntExtra("Id",0), receivedData.getBooleanExtra("Followed",false));
        Number1 = DBHandler.findUser(receivedData.getStringExtra("Name"));
        name.setText(Number1.getName());
        description.setText(Number1.getDescription());
        Log.v("Check", "" + Number1.isFollowed());
        if (Number1.isFollowed()){
            button.setText("Unfollow");
            Log.v(TAG, "Unfollowing!");
        }
        else{
            button.setText("Follow");
            Log.v(TAG, "Following!");
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Button Clicked!");
                if (Number1.isFollowed()){
                    Number1.setFollowed(false);
                    button.setText("Follow");
                    Log.v(TAG, "Unfollowing!");
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Number1.setFollowed(true);
                    button.setText("Unfollow");
                    Log.v(TAG, "Following!");
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "App Started!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "App Resume!");
    }
    @Override

    protected void onPause(){
        super.onPause();
        Log.v(TAG, "App Paused!");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "App Stopped!");
        DBHandler.updateUser(Number1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "App Destroy!");
    }
}