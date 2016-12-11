package uk.dom.quizapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import uk.dom.quizapp.R;
import uk.dom.quizapp.adapters.QuestionAdapter;
import uk.dom.quizapp.models.LocalQuestion;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizEndFragment extends Fragment {

    private List<LocalQuestion> wrongQuestions;

    private RecyclerView questionRecycler;
    private LinearLayoutManager linearLayoutManager;
    private QuestionAdapter questionAdapter;

    private TextView score;


    public QuizEndFragment() {
        // Required empty public constructor
    }

    public void setWrongQuestions(List<LocalQuestion> wrongQuestions) {
        this.wrongQuestions = wrongQuestions;
    }

    public List<LocalQuestion> getWrongQuestions() {
        return wrongQuestions;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_quiz_end, container, false);

        questionRecycler = (RecyclerView) v.findViewById(R.id.question_recycler);
        linearLayoutManager = new LinearLayoutManager(v.getContext());
        questionRecycler.setLayoutManager(linearLayoutManager);
        questionAdapter = new QuestionAdapter(getWrongQuestions());
        questionRecycler.setAdapter(questionAdapter);

        score = (TextView) v.findViewById(R.id.score_text);
        int scoreValue = 10 - getWrongQuestions().size();
        score.setText(String.valueOf(scoreValue) + "/10");


        // Inflate the layout for this fragment
        return v ;
    }

}
