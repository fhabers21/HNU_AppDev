package com.example.felix.extraschicht_v10;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExampleAdapterEvents extends RecyclerView.Adapter<ExampleAdapterEvents.ExampleViewHolder> implements Filterable {
    private ArrayList<ExampleItem> mExampleList;
    private ArrayList<ExampleItem> mExampleListFull;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onStarClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;

    }


    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageViewEvents;
        public TextView mTextViewEvents;
        public TextView mTextViewEvents2;
        public ImageView mStarImage;

        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageViewEvents = itemView.findViewById(R.id.imageViewItemEvents);
            mTextViewEvents = itemView.findViewById(R.id.textViewItemEvents);
            mTextViewEvents2 = itemView.findViewById(R.id.textViewItemEvents2);
            mStarImage = itemView.findViewById(R.id.imageViewStarBorder);
            mStarImage.setTag(1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {

                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);

                        }

                    }
                }
            });

            mStarImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {

                       int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onStarClick(position);



                        }

                 }

                }
            });
        }
    }

    public ExampleAdapterEvents(ArrayList<ExampleItem> exampleList) {
        mExampleList = exampleList;
        mExampleListFull =  new ArrayList<>(exampleList);

    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.example_item_events, viewGroup, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);

        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        ExampleItem currentItem = mExampleList.get(i);

        exampleViewHolder.mImageViewEvents.setImageResource(currentItem.getImageResource());
        exampleViewHolder.mTextViewEvents.setText(currentItem.getText1());
        exampleViewHolder.mTextViewEvents2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
   private Filter exampleFilter = new Filter() {
       @Override
       protected FilterResults performFiltering(CharSequence constraint) {
           ArrayList<ExampleItem> filteredList = new ArrayList<>();

           if (constraint == null ||constraint.length() == 0) {
               filteredList.addAll(mExampleListFull);

           } else {
               String filterPattern = constraint.toString().toLowerCase().trim();

               for (ExampleItem item : mExampleListFull) {

                   if(item.getText1().toLowerCase().contains(filterPattern)) {
                       filteredList.add(item);

                   }

               }

           }

           FilterResults results = new FilterResults();
           results.values = filteredList;

           return results;
       }

       @Override
       protected void publishResults(CharSequence constraint, FilterResults results) {
            mExampleList.clear();
            mExampleList.addAll((ArrayList)results.values);
            notifyDataSetChanged();
       }
   };

}
