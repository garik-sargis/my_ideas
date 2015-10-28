package com.gs.android.mythoughts.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gs.android.mythoughts.R;

public class IdeaDetailsActivity extends AppCompatActivity {

    public static void start(Context context, long ideaId) {
        Intent intent = new Intent(context, IdeaDetailsActivity.class)
                .putExtra("ideaId", ideaId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_details);
    }
}
