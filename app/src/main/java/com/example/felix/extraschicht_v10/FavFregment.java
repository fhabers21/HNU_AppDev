package com.example.felix.extraschicht_v10;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;

public class FavFregment extends Fragment {

    private SQLiteDatabase favDatabase;
    private ArrayList<ExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private FavAdaper mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav, container, false);

        DatabaseHelper dbhelper = new DatabaseHelper(getActivity());
        favDatabase = dbhelper.getWritableDatabase();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewFav);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new FavAdaper(getActivity(),getAllItems());
        recyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                removeItem((long)viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);

        setHasOptionsMenu(true);

        return view;

    }

    private void removeItem(long id) {
        favDatabase.delete(EventContract.EventEntry.DB_TABLE,
                EventContract.EventEntry._ID + "=" + id , null);

        mAdapter.swapCursor(getAllItems());
    }


    private Cursor getAllItems() {
        return favDatabase.query(
                EventContract.EventEntry.DB_TABLE,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.back_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.nav_settings).setVisible(false);
        MenuItem favItem = menu.findItem(R.id.menu_back);

        favItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new EventsFregment());
                fr.commit();
                return true;
            }
        });
    }

}


