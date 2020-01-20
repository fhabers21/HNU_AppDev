package com.example.felix.extraschicht_v10;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GoogleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_google, container, false);

        WebView webView = (WebView)v.findViewById(R.id.WebViewGoogle);
        webView.getSettings().setJavaScriptEnabled(true); //JavaScript aktivieren
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.google.com/");
        return v;

    }
}
