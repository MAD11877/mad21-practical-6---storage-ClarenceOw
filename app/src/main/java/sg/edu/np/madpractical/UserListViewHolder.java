package sg.edu.np.madpractical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class UserListViewHolder extends RecyclerView.ViewHolder {
    TextView txt1;
    TextView txt2;
    public UserListViewHolder(View itemView){
        super(itemView);
        txt1 = itemView.findViewById(R.id.userName);
        txt2 = itemView.findViewById(R.id.userDescription);
    }
}
