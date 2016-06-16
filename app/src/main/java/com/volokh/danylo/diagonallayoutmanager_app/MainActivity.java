package com.volokh.danylo.diagonallayoutmanager_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> GOT_Characters = new ArrayList<>(Arrays.asList(
            "Hodor",
            "Tyrion",
            "Ariya",
            "John",
            "Ned",
            "Jame",
            "Oberin Martell",
            "Bran",
            "Missandei",
            "The Mountain",
            "The Hound",
            "The Mad King"

    ));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new SampleAdapter(this, GOT_Characters));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
