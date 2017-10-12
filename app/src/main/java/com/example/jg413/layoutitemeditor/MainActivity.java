package com.example.jg413.layoutitemeditor;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.StringTokenizer;

import static android.support.v7.app.ActionBar.LayoutParams;

public class MainActivity extends AppCompatActivity {

    final Context context = this;
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
        Button removeXButton = (Button) findViewById(R.id.removeXButton);
        removeXButton.setOnClickListener(onRemoveXClick());
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

    private ArrayList<View> GetAllViewsInLinearList() {
        ArrayList<View> allViews = new ArrayList<>();
        for (int i = 0; i < mLayout.getChildCount(); i++) {
            allViews.add(mLayout.getChildAt(i));
        }
        return allViews;
    }

    private View.OnClickListener onRemoveXClick() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.removex_prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                alertDialogBuilder.setView(promptsView);

                final EditText userInput = promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        if (userInput.getText().toString().matches("\\d+") &&
                                                Integer.parseInt(userInput.getText().toString()) < mLayout.getChildCount()) {
                                            int val = Integer.parseInt(userInput.getText().toString());
                                            for (int i = 0; i < val; i++) {
                                                mLayout.removeView(mLayout.getChildAt(mLayout.getChildCount() - 1));
                                            }
                                        } else {
                                            Toast toast = Toast.makeText(context, String.format("Please input a value below %d", mLayout.getChildCount()), Toast.LENGTH_SHORT);
                                            toast.show();
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();

            }
        };
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
