package com.volokh.danylo.diagonallayoutmanager_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.volokh.danylo.diagonallayoutmanager.DiagonalLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<GOT_hero> GOT_Characters = new ArrayList<>(Arrays.asList(
//            new GOT_hero("Hodor", R.drawable.hodor),
//            new GOT_hero("Tyrion", R.drawable.tyrion),
//            new GOT_hero("Arya", R.drawable.arya),
            new GOT_hero("Jon", R.drawable.jon),
//            new GOT_hero("Ned", R.drawable.ned),
            new GOT_hero("Jaime", R.drawable.jaime),
            new GOT_hero("Oberyn Martell", R.drawable.oberyn_martell),
//            new GOT_hero("Bran", R.drawable.bran_stark),
            new GOT_hero("Missandei", R.drawable.missandei),
            new GOT_hero("The Mountain", R.drawable.the_mountain),
            new GOT_hero("The Hound", R.drawable.the_dog),
            new GOT_hero("The Mad King", R.drawable.mad_king)

    ));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new GOTAdapter(this, GOT_Characters));

        recyclerView.setLayoutManager(new DiagonalLayoutManager(this));

    }
}
