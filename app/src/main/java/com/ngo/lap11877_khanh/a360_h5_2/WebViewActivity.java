package com.ngo.lap11877_khanh.a360_h5_2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Copyright (C) 2018, VNG Corporation.
 *
 * @author Hien Nguyen
 * @since 20/05/2018
 */
public final class WebViewActivity  extends AppCompatActivity {

    private static final String DEFAULT_MAIN_URL = "http://h5.360game.vn/";

    private static final String ARGS_URL = "key_url";

    @BindView(R.id.progress_bar_web_view)
    ProgressBar mProgressBar;

    @BindView(R.id.web_view)
    ZWebView mWebView;

    private Unbinder mUnbinder;

    private String mMainUrl = DEFAULT_MAIN_URL;

    public static Intent intentFor(Context context, String url) {
        final Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(ARGS_URL, url);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_view);
        mUnbinder = ButterKnife.bind(this);

        updateData(savedInstanceState);
        mWebView.loadUrl(mMainUrl);

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressBar.setProgress(newProgress);
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);
                mProgressBar.setProgress(0);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveData(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        updateData(savedInstanceState);
        mWebView.loadUrl(mMainUrl);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    @Override
    protected void onPause() {
        mWebView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        mWebView.setWebChromeClient(null);
        mWebView.setWebViewClient(null);

        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }

        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mWebView.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private void updateData(Bundle bundle) {
        final Intent intent = getIntent();
        String url = null;
        if (intent != null) {
            url = intent.getStringExtra(ARGS_URL);
        } else if (bundle != null) {
            url = bundle.getString(ARGS_URL);
        }
        if (!TextUtils.isEmpty(url)) {
            mMainUrl = url;
        }
    }

    private void saveData(Bundle bundle) {
        if (bundle != null) {
            bundle.putString(ARGS_URL, mMainUrl);
        }
    }
}
