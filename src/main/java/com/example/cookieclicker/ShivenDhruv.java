package com.example.cookieclicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class ShivenDhruv extends AppCompatActivity {

    TextView textView, upgrade;
    ConstraintLayout constraintLayout;
    TextView plusOne;
    ImageView nanoTech;

    MediaPlayer mediaPlayer;
    ImageView marvelButton;
    int cookies;
    private boolean autoClickEnabled = false;
    private void startAutoClick()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cookies++;
                    textView.setText(cookies + " arc reactors");
                    if (cookies >= 50)
                    {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run()
                            {
                                upgrade.setText("UPGRADE: ENABLED");
                            }
                        });
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.layout);
        mediaPlayer = MediaPlayer.create(this, R.raw.marvelclick);
        marvelButton = findViewById(R.id.marvelbutton);
        textView = findViewById(R.id.textView2);
        upgrade = findViewById(R.id.textView);
        textView.setTextColor(Color.BLUE);
        upgrade.setTextColor(Color.BLUE);
        marvelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusOne = new TextView(getApplicationContext());
                plusOne.setId(View.generateViewId());
                plusOne.setText(" +1");
                plusOne.setTextColor(Color.BLACK);
                Log.d("Tag", "Click");
                cookies++;
                if(cookies>=50)
                {
                    upgrade.setText("UPGRADE: ENABLED");

                }
                textView.setText(cookies + " arc reactors");
                textView.setTextColor(Color.BLUE);
                if(cookies >= 50  && !autoClickEnabled)
                {
                    upgrade.setText("UPGRADE: ENABLED");
                    nanoTech = new ImageView(getApplicationContext());
                    nanoTech.setId(View.generateViewId());
                    nanoTech.setImageResource(R.drawable.nanotecharcreactor);
                    ConstraintLayout.LayoutParams imageViewParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT_PERCENT * 300, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT_PERCENT * 300);
                    nanoTech.setLayoutParams(imageViewParams);
                    constraintLayout.addView(nanoTech);
                    nanoTech.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                               if(cookies >= 50 )
                               {
                                   final ScaleAnimation animation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
                                   animation.setDuration(400);
                                   nanoTech.startAnimation(animation);
                                   cookies -= 50;
                                   autoClickEnabled = true;
                                   startAutoClick();
                                   upgrade.setText("UPGRADE: ENABLED");
                                   upgrade.setEnabled(false);

                               }

                               else
                               {
                                   autoClickEnabled = false;
                                   nanoTech.setVisibility(View.INVISIBLE);
                                   constraintLayout.removeView(nanoTech);
                                   upgrade.setText("UPGRADE: DISABLED");
                               }
                        }
                    });
                }
                else if (cookies<50)
                {
                    upgrade.setText("UPGRADE: DISABLED");
                }
                marvelButton.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        Log.d("Tag", "Touch");
                        final ScaleAnimation animation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
                        animation.setDuration(400);
                        marvelButton.startAnimation(animation);
                        mediaPlayer.start();
                        plusOne.setX(event.getX());
                        plusOne.setY(event.getY());
                        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 500, -1000);
                        translateAnimation.setDuration(1000);
                        constraintLayout.removeView(plusOne);
                        plusOne.startAnimation(translateAnimation);
                        return false;
                    }
                });
                ConstraintLayout.LayoutParams textViewParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                plusOne.setLayoutParams(textViewParams);
                constraintLayout.addView(plusOne);
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, -1000, -1000);
                translateAnimation.setDuration(1000);
                plusOne.startAnimation(translateAnimation);
            }
        });
          if (cookies<50)
        {
            upgrade.setText("UPGRADE: DISABLED");
        }
    }
}