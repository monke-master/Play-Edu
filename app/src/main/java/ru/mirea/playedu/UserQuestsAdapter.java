package ru.mirea.playedu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class UserQuestsAdapter extends RecyclerView.Adapter<UserQuestsAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView deadlineTextView;
        public TextView priceTextView;
        public LinearLayout categoryLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.title_txt);
            deadlineTextView = (TextView) itemView.findViewById(R.id.deadline_txt);
            priceTextView = (TextView) itemView.findViewById(R.id.price_txt);
            categoryLayout = (LinearLayout) itemView.findViewById(R.id.category_layout);
        }
    }
    private List<UserQuestsModel> mUserQuests;

    public UserQuestsAdapter(List<UserQuestsModel> userQuests) {
        mUserQuests = userQuests;
    }

    @Override
    public UserQuestsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.view_user_task, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UserQuestsAdapter.ViewHolder holder, int position) {
        UserQuestsModel userQuest = mUserQuests.get(position);

        TextView titleTextView = holder.titleTextView;
        titleTextView.setText(userQuest.getTitle_txt());
        TextView deadlineTextView = holder.deadlineTextView;
        deadlineTextView.setText(userQuest.getTitle_txt());
        TextView priceTextView = holder.priceTextView;
        priceTextView.setText(userQuest.getPrice_txt());
        LinearLayout categoryLayout = holder.categoryLayout;
        categoryLayout.setBackground(userQuest.getColor());
    }

    @Override
    public int getItemCount() {
        return mUserQuests.size();
    }
}
