package com.mystartup.customizedlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageSwitcher;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements ListViewAdapter.OnAnimalImageClicked{
    private ListView mListView;
    private ListViewAdapter mListViewAdapter;
    Ima

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListViewAdapter = new ListViewAdapter(this,MainActivity.this);
        mListView = findViewById(R.id.list_view);
        mListView.setAdapter(mListViewAdapter);
    }

    @Override
    public void switchToNextActivity() {
        Intent intent = new Intent(MainActivity.this,Animals.class);
        startActivity(intent);

    }
}