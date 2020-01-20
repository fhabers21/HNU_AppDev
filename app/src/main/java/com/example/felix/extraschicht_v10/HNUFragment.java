package com.example.felix.extraschicht_v10;

import android.content.Intent;
import android.net.Uri;
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
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class HNUFragment extends Fragment {

    private ArrayList<ExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private FloatingActionButton fabFacebook;
    private FloatingActionButton fabTwitter;
    private FloatingActionButton fabMail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_hnu, container, false);

        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.ic_book_, "Wer sind wir?", "Beginn: 11:00 Uhr Dauer: 45 Min."));
        mExampleList.add(new ExampleItem(R.drawable.ic_book_, "Studium im Ausland", "Beginn: 12:00 Uhr Dauer: 30 Min."));
        mExampleList.add(new ExampleItem(R.drawable.ic_book_, "Mustervortrag", "Beginn: 00:00 Uhr Dauer:x Min."));
        mExampleList.add(new ExampleItem(R.drawable.ic_book_, "Mustervortrag", "Beginn: 00:00 Uhr  Dauer:x Min."));

        mRecyclerView = v.findViewById(R.id.recyclerViewhnu);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        fabFacebook = (FloatingActionButton)v.findViewById(R.id.floatingActionFacebook);
        fabFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://www.facebook.com/HochschuleNeuUlm/"));
                startActivity(browserIntent);
            }
        });

        fabTwitter = (FloatingActionButton)v.findViewById(R.id.floatingActionTwitter);
        fabTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://twitter.com/IMUKler?s=17"));
                startActivity(browserIntent);
            }
        });

        fabMail = (FloatingActionButton)v.findViewById(R.id.floatingActionMail);
        fabMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "info@hs-neu-ulm.de" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                intent.putExtra(Intent.EXTRA_TEXT, "mail body");
                startActivity(Intent.createChooser(intent, ""));
            }
        });


        return v;
    }
}
