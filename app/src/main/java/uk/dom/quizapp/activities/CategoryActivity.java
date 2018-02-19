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

import java.util.List;

import uk.dom.quizapp.R;
import uk.dom.quizapp.adapters.CategoryChooserAdapter;
import uk.dom.quizapp.models.Category;
import uk.dom.quizapp.presenters.DatabasePresenter;
import uk.dom.quizapp.tools.CategoryListener;

public class CategoryActivity extends AppCompatActivity implements CategoryListener{

    RecyclerView categoryChooserRecycler;
    GridLayoutManager gridLayoutManager;
    CategoryChooserAdapter adapter;

    List<Category> categories;

    DatabasePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setTitle("Choose a category");

        presenter = new DatabasePresenter(this);

        categoryChooserRecycler = (RecyclerView) findViewById(R.id.category_chooser_recycler);
        gridLayoutManager = new GridLayoutManager(this, 2);
        adapter = new CategoryChooserAdapter(presenter.returnCategories(), this);
        categoryChooserRecycler.setLayoutManager(gridLayoutManager);
        categoryChooserRecycler.setAdapter(adapter);


    }

    @Override
    public void onItemClick(View view, int position) {
        Intent i = new Intent(this, QuizActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("categoryID",presenter.returnCategories().get(position).getId());
        i.putExtras(bundle);
        startActivity(i);
    }
}
