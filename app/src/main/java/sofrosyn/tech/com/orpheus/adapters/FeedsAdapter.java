package sofrosyn.tech.com.orpheus.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import sofrosyn.tech.com.orpheus.R;
import sofrosyn.tech.com.orpheus.modals.Feeds;

import java.util.List;

public class FeedsAdapter extends
        RecyclerView.Adapter<FeedsAdapter.MyViewHolder> {

    private List<Feeds> feedsList;
    private Context context;

    public FeedsAdapter(List<Feeds> feedsList, Context context){
        this.feedsList =feedsList;
        this.context = context;
    }


    @NonNull
    @Override
    public FeedsAdapter.MyViewHolder
        onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feeds_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void
        onBindViewHolder(MyViewHolder holder, int position) {

        Feeds feeds = feedsList.get(position);
        holder.subject.setText(feeds.getSubject());
        holder.message.setText(feeds.getMessage());
        holder.time.setText(feeds.getDate());
    }

    @Override
    public int getItemCount() {
return feedsList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView subject,message,time;

        public MyViewHolder(View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.subject_feed);
            message=  itemView.findViewById(R.id.message_feed);
            time= itemView.findViewById(R.id.date_feed);

        }
    }
}
