package com.scryptonite.prateek.habba18_meeting;


import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar pd;
    WebView web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        web_view = (WebView) findViewById(R.id.webview);
        pd = (ProgressBar)findViewById(R.id.progressBar2);
        renderWebPage("http://mdmaentertainment.com/habba18/meeting.php");
    }

    protected void renderWebPage(String urlToRender){
        web_view.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                // Do something on page loading started
                // Visible the progressbar
                pd.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url){
                // Do something when page loading finished
                pd.setVisibility(View.INVISIBLE);
                //Toast.makeText(getApplicationContext(),"Page Loaded.",Toast.LENGTH_SHORT).show();
            }

        });

        web_view.setWebChromeClient(new WebChromeClient(){
            /*
                public void onProgressChanged (WebView view, int newProgress)
                    Tell the host application the current progress of loading a page.

                Parameters
                    view : The WebView that initiated the callback.
                    newProgress : Current page loading progress, represented by an integer
                        between 0 and 100.
            */
            public void onProgressChanged(WebView view, int newProgress){
                // Update the progress bar with page loading progress
                pd.setProgress(newProgress);
                if(newProgress == 100){
                    // Hide the progressbar
                    pd.setVisibility(View.INVISIBLE);
                }
            }
        });

        // Enable the javascript
        web_view.getSettings().setJavaScriptEnabled(true);
        // Render the web page
        web_view.loadUrl(urlToRender);
    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 2 * 1000);

        }

    }
}
