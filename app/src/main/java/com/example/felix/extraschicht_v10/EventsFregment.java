package com.example.felix.extraschicht_v10;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventsFregment extends Fragment {

    public SQLiteDatabase favDatabase;
    private ArrayList<ExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private ExampleAdapterEvents mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RequestQueue mRequestQueue;

    public static final String EXTRA_EVENT = "event_name";
    public static final String EXTRA_INFO = "event_info";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        mExampleList = new ArrayList<>();

        mRecyclerView = view.findViewById(R.id.recyclerViewEvents);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new ExampleAdapterEvents(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mRequestQueue = Volley.newRequestQueue(getActivity());
        parseJSON();

        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        favDatabase = dbHelper.getWritableDatabase();

        mAdapter.setOnItemClickListener(new ExampleAdapterEvents.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onStarClick(int position) {

                String titel = mExampleList.get(position).getText1();
                String firma = mExampleList.get(position).getText2();
                int IMG = mExampleList.get(position).getImageResource();
                ContentValues cv = new ContentValues();
                cv.put(EventContract.EventEntry.TITEL, titel);
                cv.put(EventContract.EventEntry.FIRMA, firma);
                cv.put(EventContract.EventEntry.IMG, IMG);

                favDatabase.insert(EventContract.EventEntry.DB_TABLE, null, cv);

            }
        });

        setHasOptionsMenu(true);

        return view;


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fav_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.nav_settings).setVisible(false);
        MenuItem favItem = menu.findItem(R.id.ic_star);

        favItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new FavFregment());
                fr.commit();
                return true;
            }
        });
    }

    private void parseJSON() {
        String url = "Removed due to privacy";

        JsonObjectRequest reqeust = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("dbdata");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject dbata = jsonArray.getJSONObject(i);

                                String eventName = dbata.getString("event_name");
                                String eventInfo = dbata.getString("event_info");

                                mExampleList.add(new ExampleItem(R.drawable.ic_event_note, eventName, eventInfo));

                            }
                            mAdapter = new ExampleAdapterEvents(mExampleList);
                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.setOnItemClickListener(new ExampleAdapterEvents.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {

                                    Fragment newFragment = new EventDetailFregment();
                                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                    transaction.replace(R.id.fragment_container, newFragment);
                                    transaction.addToBackStack(null);
                                    transaction.commit();
                                }

                                @Override
                                public void onStarClick(int position) {
                                    String titel = mExampleList.get(position).getText1();
                                    String firma = mExampleList.get(position).getText2();
                                    int IMG = mExampleList.get(position).getImageResource();
                                    ContentValues cv = new ContentValues();
                                    cv.put(EventContract.EventEntry.TITEL, titel);
                                    cv.put(EventContract.EventEntry.FIRMA, firma);
                                    cv.put(EventContract.EventEntry.IMG, IMG);

                                    favDatabase.insert(EventContract.EventEntry.DB_TABLE, null, cv);

                                }
                            });


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(reqeust);


    }

}

