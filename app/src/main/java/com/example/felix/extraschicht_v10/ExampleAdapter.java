package com.example.felix.extraschicht_v10;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> implements Filterable {
    private ArrayList<ExampleItem> mExampleList;
    private ArrayList<ExampleItem> mExampleListFull;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int posotion);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;

    }


    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageViewItem);
            mTextView1 = itemView.findViewById(R.id.textViewItem);
            mTextView2 = itemView.findViewById(R.id.textViewItem2);

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
        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList) {
        mExampleList = exampleList;
        mExampleListFull =  new ArrayList<>(exampleList);

    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.example_item, viewGroup, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);

        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        ExampleItem currentItem = mExampleList.get(i);

        exampleViewHolder.mImageView.setImageResource(currentItem.getImageResource());
        exampleViewHolder.mTextView1.setText(currentItem.getText1());
        exampleViewHolder.mTextView2.setText(currentItem.getText2());
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
