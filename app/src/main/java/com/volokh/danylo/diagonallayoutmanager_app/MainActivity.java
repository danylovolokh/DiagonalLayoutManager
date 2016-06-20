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
            new GOT_hero("Hodor", R.drawable.hodor),
            new GOT_hero("I drink, and I know things", R.drawable.tyrion),
            new GOT_hero("I'm Arya Stark of Winterfell", R.drawable.arya),
            new GOT_hero("My watch has ended", R.drawable.jon),
            new GOT_hero("The man who passes the sentence should swing the sword", R.drawable.ned),
            new GOT_hero("There are no men like me. Only me", R.drawable.jaime),
            new GOT_hero("Do you know why I have come all the way to this stinking, shit-pile of a city?", R.drawable.oberyn_martell),
            new GOT_hero("My dreams are different", R.drawable.bran_stark),
            new GOT_hero("Valar morghulis", R.drawable.missandei),
            new GOT_hero("Elia Martell! I killed her children! Then I raped her! Then I smashed her head in... like this", R.drawable.the_mountain),
            new GOT_hero("You don't know the things I've done", R.drawable.the_dog),
            new GOT_hero("I am Daenerys Stormborn of the blood of old Valyria and I will take what is mine! With fire and blood", R.drawable.daenerys_targaryen),
            new GOT_hero("You know nothing Jon Snow", R.drawable.ingrid),
            new GOT_hero("Time for the bedding ceremony!", R.drawable.joffrey),
            new GOT_hero("I've won every battle, but I'm losing this war", R.drawable.rob_stark)
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
