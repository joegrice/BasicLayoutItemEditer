package com.example.jg413.layoutitemeditor;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static android.support.v7.app.ActionBar.LayoutParams;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = (LinearLayout) findViewById(R.id.linearLayout);
        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(onClick());
        Button removeButton = (Button) findViewById(R.id.removeButton);
        removeButton.setOnClickListener(onRemoveClick());
        Button highlightButton = (Button) findViewById(R.id.highlightButton);
        highlightButton.setOnClickListener(onHighLightClick());
        Button randomiseButton = (Button) findViewById(R.id.randomiseButton);
        randomiseButton.setOnClickListener(onRandomiseClick());
    }

    private View.OnClickListener onHighLightClick() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mLayout.getChildCount() > 0) {
                    Random r = new Random();
                    TextView randomView = (TextView) mLayout.getChildAt(r.nextInt((mLayout.getChildCount() - 1) + 1));
                    randomView.setTextColor(GenerateRandomColor());
                }
            }
        };
    }

    private int GenerateRandomColor() {
        Random rand = new Random();
        return Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    private ArrayList<View>GetAllViewsInLinearList() {
        ArrayList<View> allViews = new ArrayList<>();
        for (int i = 0; i < mLayout.getChildCount(); i++) {
            allViews.add(mLayout.getChildAt(i));
        }
        return allViews;
    }


    private View.OnClickListener onRandomiseClick() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList<View> views = GetAllViewsInLinearList();
                Collections.shuffle(views);
                mLayout.removeAllViews();
                for (View view : views) {
                    mLayout.addView(view);
                }
            }
        };
    }

    private View.OnClickListener onClick() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mLayout.addView(createNewTextView());
            }
        };
    }

    private View.OnClickListener onRemoveClick() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mLayout.getChildCount() > 0) {
                    mLayout.removeView(mLayout.getChildAt(mLayout.getChildCount() - 1));
                }
            }
        };
    }

    private TextView createNewTextView() {
        final LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setText("Hello World");
        return textView;
    }
}
