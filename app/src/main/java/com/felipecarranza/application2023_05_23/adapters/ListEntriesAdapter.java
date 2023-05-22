package com.felipecarranza.application2023_05_23.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.felipecarranza.application2023_05_23.R;
import com.felipecarranza.application2023_05_23.ShowActivity;
import com.felipecarranza.application2023_05_23.entities.Entries;

import java.util.ArrayList;

public class ListEntriesAdapter extends RecyclerView.Adapter<ListEntriesAdapter.EntryViewHolder> {

    ArrayList<Entries> entries;

    public ListEntriesAdapter(ArrayList<Entries> entries) {
        this.entries = entries;
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     */
    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_entries, null, false);
        return new EntryViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder holder, int position) {
        holder.lbl_created_at.setText(entries.get(position).getCreated_at());
        holder.lbl_title.setText(entries.get(position).getTitle());
        // holder.lbl_content.setText(entries.get(position).getContent());
        holder.lbl_content.setText(entries.get(position).getShortContent());
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return entries.size();
    }

    public class EntryViewHolder extends RecyclerView.ViewHolder {
        TextView lbl_created_at, lbl_title, lbl_content;
        public EntryViewHolder(@NonNull View itemView) {
            super(itemView);
            lbl_created_at = itemView.findViewById(R.id.lbl_created_at);
            lbl_title = itemView.findViewById(R.id.lbl_title);
            lbl_content = itemView.findViewById(R.id.lbl_content);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ShowActivity.class);
                    intent.putExtra("id", entries.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
