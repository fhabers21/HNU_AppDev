package com.example.felix.extraschicht_v10;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AusbFregment extends Fragment {

    private ArrayList<ExampleItem> mExampleList;
    public SQLiteDatabase favDatabase;

    private RecyclerView mRecyclerView;
    private ExampleAdapterEvents mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RequestQueue mRequestQueue;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ausbildung, container, false);

        mExampleList = new ArrayList<>();

        mRecyclerView = view.findViewById(R.id.recyclerViewAusb);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new ExampleAdapterEvents(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        mRequestQueue = Volley.newRequestQueue(getActivity());
        parseJSON();

        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        favDatabase = dbHelper.getWritableDatabase();


        return view;

    }

    private void parseJSON() {
        String url = "http://extraschicht01-felixhaberstock170886.codeanyapp.com/getAusb.php";

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
