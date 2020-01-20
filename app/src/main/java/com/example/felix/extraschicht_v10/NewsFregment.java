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

public class NewsFregment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        WebView webView = (WebView)v.findViewById(R.id.WebViewTwitter);
        webView.getSettings().setJavaScriptEnabled(true); //JavaScript aktivieren
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/twitter.html");
        return v;
    }
}
