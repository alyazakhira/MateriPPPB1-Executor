package com.example.executorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    TextView firstTextView;
    TextView secondTextView;
    Button firstButton;
    Button secondButton;
    Executor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        executor = Executors.newSingleThreadExecutor();

        firstTextView = findViewById(R.id.first_text);
        secondTextView = findViewById(R.id.second_text);
        firstButton = findViewById(R.id.first_btn);
        secondButton = findViewById(R.id.second_btn);

//        click listener
        firstButton.setOnClickListener(view -> {
            firstTextView.setText("First text is clicked");

//                executor
            executor.execute(new Runnable() {

//                    on other thread
                @Override
                public void run() {
                    try{
                        Thread.sleep(10000);
                    } catch (InterruptedException e){

                    }

//                        process on UI thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            firstTextView.setText("DONE");
                        }
                    });
                }
            });
//                end of executor
        });
//        end of click listener

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secondTextView.setText("Second text is clicked");
            }
        });
    }
}