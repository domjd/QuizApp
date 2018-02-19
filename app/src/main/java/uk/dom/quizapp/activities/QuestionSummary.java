package uk.dom.quizapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import uk.dom.quizapp.R;
import uk.dom.quizapp.adapters.QuestionAdapter;
import uk.dom.quizapp.models.LocalQuestion;

public class QuestionSummary extends AppCompatActivity {

    private RecyclerView questionRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_summary);

        getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        Intent i = getIntent();
        List<LocalQuestion> questionArray = (List<LocalQuestion>) i.getSerializableExtra("question_array");

        questionRecycler = (RecyclerView) findViewById(R.id.question_summary_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        QuestionAdapter adapter = new QuestionAdapter(questionArray);

        questionRecycler.setLayoutManager(layoutManager);
        questionRecycler.setAdapter(adapter);
    }
}
