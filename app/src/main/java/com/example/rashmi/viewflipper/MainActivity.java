package com.example.rashmi.viewflipper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewFlipper mViewFlipper;
    private Context mContext;
    private float initialX;
    Button mPlay, mPause;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlay = (Button) findViewById(R.id.play);
        mPause = (Button) findViewById(R.id.pause);
        mContext = this;
        mViewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        mPlay.setOnClickListener(this);
        mPause.setOnClickListener(this);


    }

    @Override
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialX = touchevent.getX();
                break;
            case MotionEvent.ACTION_UP:
                float finalX = touchevent.getX();
                if (initialX > finalX) {
                    if (mViewFlipper.getDisplayedChild() == 1)
                        break;
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right));

                    mViewFlipper.showNext();
                } else {
                    if (mViewFlipper.getDisplayedChild() == 0)
                        break;

                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.up));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.down));
                    mViewFlipper.showPrevious();
                }
                break;
        }
        return false;
        }

    @Override
    public void onClick(View v) {
        if (v==mPlay){
            mPlay.setVisibility(View.INVISIBLE);
            mViewFlipper.setAutoStart(true);
            mViewFlipper.setFlipInterval(1000);
            mViewFlipper.startFlipping();
            Toast.makeText(MainActivity.this, "Automatic view flipping has started", Toast.LENGTH_SHORT).show();
            startService(new Intent(this, AudioService.class));
            mPause.setVisibility(View.VISIBLE);


        }else if (v==mPause){
            mViewFlipper.stopFlipping();
            mPlay.setVisibility(View.VISIBLE);
            mPause.setVisibility(View.INVISIBLE);
          Toast.makeText(MainActivity.this, "Automatic view flipping has stopped", Toast.LENGTH_SHORT).show();
          stopService(new Intent(this,AudioService.class));

          }

    }
}
