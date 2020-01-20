package com.example.felix.extraschicht_v10;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.support.v7.widget.SearchView;


import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class CompFregment extends Fragment {
    private ArrayList<ExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_companys, container, false);

        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.ic_school, "Hochschule Neu-Ulm", "University of Applied Sciences"));
        mExampleList.add(new ExampleItem(R.drawable.ic_business, "Muster AG", "Maschinenbau & Anlagentechnik"));
        mExampleList.add(new ExampleItem(R.drawable.ic_business, "Muster GmbH", "Frästechnik"));
        mExampleList.add(new ExampleItem(R.drawable.ic_school, "Stadt Neu-Ulm", "150 Jahre Neu-Ulm"));
        mExampleList.add(new ExampleItem(R.drawable.ic_business, "Muster AG", "Maschinenbau & Anlagentechnik"));
        mExampleList.add(new ExampleItem(R.drawable.ic_business, "Muster GmbH", "Frästechnik"));
        mExampleList.add(new ExampleItem(R.drawable.ic_business, "Muster AG", "Maschinenbau & Anlagentechnik"));
        mExampleList.add(new ExampleItem(R.drawable.ic_business, "Muster GmbH", "Frästechnik"));
        mExampleList.add(new ExampleItem(R.drawable.ic_business, "Muster AG", "Maschinenbau & Anlagentechnik"));
        mExampleList.add(new ExampleItem(R.drawable.ic_business, "Muster GmbH", "Frästechnik"));
        mExampleList.add(new ExampleItem(R.drawable.ic_business, "Muster AG", "Maschinenbau & Anlagentechnik"));
        mExampleList.add(new ExampleItem(R.drawable.ic_business, "Muster GmbH", "Frästechnik"));
        mExampleList.add(new ExampleItem(R.drawable.ic_business, "Muster AG", "Maschinenbau & Anlagentechnik"));
        mExampleList.add(new ExampleItem(R.drawable.ic_business, "Muster GmbH", "Frästechnik"));
        mExampleList.add(new ExampleItem(R.drawable.ic_business, "Muster AG", "Maschinenbau & Anlagentechnik"));
        mExampleList.add(new ExampleItem(R.drawable.ic_business, "Muster GmbH", "Frästechnik"));

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {
                ExampleItem currentItem = mExampleList.get(i);
                Fragment selectedFragment = null;

                switch (currentItem.getText1()) {

                    case "Hochschule Neu-Ulm":
                        selectedFragment = new HNUFragment();
                        break;

                    case "Muster AG":
                        selectedFragment = new GoogleFragment();
                        break;

                    case "Stadt Neu-Ulm":
                        selectedFragment = new NeuUlmFragment();
                        break;

                    case "Muster GmbH":
                        selectedFragment = new GoogleFragment();
                        break;
                }

                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
            }
        });

        setHasOptionsMenu(true);


        return view;




    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.nav_settings).setVisible(false);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (android.support.v7.widget.SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}

