package com.example.felix.extraschicht_v10;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FavAdaper extends RecyclerView.Adapter<FavAdaper.EventViewHolder> {

    private Context favContext;
    private Cursor favCursor;

    public FavAdaper(Context context, Cursor cursor) {
        favContext = context;
        favCursor = cursor;

    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageViewFav;
        public TextView titelText;
        public TextView firmaText;
        public ImageView mStarImageFav;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageViewFav = itemView.findViewById(R.id.imageViewItemFav);
            titelText = itemView.findViewById(R.id.textViewItemFav);
            firmaText = itemView.findViewById(R.id.textViewItemFav2);

        }
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(favContext);
        View view = inflater.inflate(R.layout.example_item_fav, viewGroup, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int i) {

        if (!favCursor.moveToPosition(i)) {
            return;

        }
        String titelText = favCursor.getString(favCursor.getColumnIndex(EventContract.EventEntry.TITEL));
        String firmaText = favCursor.getString(favCursor.getColumnIndex(EventContract.EventEntry.FIRMA));
        String img = favCursor.getString(favCursor.getColumnIndex(EventContract.EventEntry.IMG));
        long id = favCursor.getLong(favCursor.getColumnIndex(EventContract.EventEntry._ID));

        eventViewHolder.titelText.setText(titelText);
        eventViewHolder.firmaText.setText(firmaText);
        eventViewHolder.mImageViewFav.setImageResource(Integer.parseInt(img));
        eventViewHolder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return favCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (favCursor != null) {
            favCursor.close();

        }

        favCursor = newCursor;

        if (newCursor != null){
            notifyDataSetChanged();
        }

    }
}

