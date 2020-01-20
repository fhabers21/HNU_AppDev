package com.example.felix.extraschicht_v10;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import static com.example.felix.extraschicht_v10.EventsFregment.EXTRA_EVENT;
import static com.example.felix.extraschicht_v10.EventsFregment.EXTRA_INFO;

public class EventDetailFregment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_event, container, false);


        String event_name = getActivity().getIntent().getStringExtra(EXTRA_EVENT);
        String event_firma = getActivity().getIntent().getStringExtra(EXTRA_INFO);

        TextView event_detail_name = (TextView)v.findViewById(R.id.text_view_event_name);
        TextView event_detail_firma = (TextView)v.findViewById(R.id.text_view_event_firma);


        event_detail_name.setText(event_name);
        event_detail_firma.setText(event_firma);


        return v;
    }
}
