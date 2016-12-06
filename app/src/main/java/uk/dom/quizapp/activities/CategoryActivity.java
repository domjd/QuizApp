package uk.dom.quizapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import uk.dom.quizapp.R;
import uk.dom.quizapp.adapters.CategoryChooserAdapter;
import uk.dom.quizapp.models.Category;
import uk.dom.quizapp.tools.CategoryListener;

public class CategoryActivity extends AppCompatActivity implements CategoryListener{

    RecyclerView categoryChooserRecycler;
    GridLayoutManager gridLayoutManager;
    CategoryChooserAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Choose a category");

        List<Category> categories = new ArrayList<>();
        Category c = new Category("Entertainment", R.drawable.comedy);
        categories.add(c);
        c = new Category("Geography", R.drawable.planet);
        categories.add(c);
        c = new Category("Arts", R.drawable.palette);
        categories.add(c);
        c = new Category("Sports", R.drawable.medal);
        categories.add(c);

        categoryChooserRecycler = (RecyclerView) findViewById(R.id.category_chooser_recycler);
        gridLayoutManager = new GridLayoutManager(this, 2);
        adapter = new CategoryChooserAdapter(categories, this);
        categoryChooserRecycler.setLayoutManager(gridLayoutManager);
        categoryChooserRecycler.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent i = new Intent(this, QuizActivity.class);
        startActivity(i);
    }
}
