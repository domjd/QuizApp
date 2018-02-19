package uk.dom.quizapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Response;
import uk.dom.quizapp.R;
import uk.dom.quizapp.fragments.QuestionFragment;
import uk.dom.quizapp.fragments.QuizEndFragment;
import uk.dom.quizapp.models.Category;
import uk.dom.quizapp.models.LocalQuestion;
import uk.dom.quizapp.models.Question;
import uk.dom.quizapp.models.Quiz;
import uk.dom.quizapp.presenters.DatabasePresenter;
import uk.dom.quizapp.presenters.QuizPresenter;
import uk.dom.quizapp.tools.QuizCallBack;
import uk.dom.quizapp.ui.QuizView;
import uk.dom.quizapp.ui.QuizViewPager;

public class QuizActivity extends AppCompatActivity implements QuizCallBack, QuizView {

    private QuizViewPager quizPager;
    private QuizPagerAdapter quizPagerAdapter;

    private ArrayList<LocalQuestion> wrongQuestions;

    private QuizPresenter presenter;

    private DatabasePresenter dbPresenter;

    private ProgressDialog progressDialog;

    private int categoryID;

    private Button homeButton;
    private Button questionSummaryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        progressDialog = ProgressDialog.show(this, "Loading Quiz", null);

        Intent i = getIntent();
        categoryID = i.getExtras().getInt("categoryID");

        presenter = new QuizPresenter(getApplicationContext(), this);
        dbPresenter = new DatabasePresenter(this);

        presenter.loadQuiz(categoryID);

        quizPager = (QuizViewPager) findViewById(R.id.quiz_pager);
        quizPagerAdapter = new QuizPagerAdapter(getSupportFragmentManager());

        wrongQuestions = new ArrayList<>();
        questionSummaryButton = (Button) findViewById(R.id.question_summary_button);
        homeButton = (Button) findViewById(R.id.home_button);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(QuizActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        questionSummaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(QuizActivity.this, QuestionSummary.class);
                i.putExtra("question_array", (Serializable) wrongQuestions);
                startActivity(i);
            }
        });

    }

    @Override
    public void onAnswerReceived(int questionNumber, String question, String userAnswer, String correctAnswer) {

        if(userAnswer != correctAnswer){
            LocalQuestion localQuestion = new LocalQuestion(question,userAnswer,correctAnswer);
            wrongQuestions.add(localQuestion);
        }

        else{
            MediaPlayer snd = MediaPlayer.create(this, R.raw.ding);
            snd.start();
        }
        Log.v("current pager item: ", String.valueOf(quizPager.getCurrentItem()));

        if(quizPager.getCurrentItem() < 9)
            quizPager.setCurrentItem(quizPager.getCurrentItem() + 1, true);

        else{
            quizPager.setVisibility(View.INVISIBLE);
            CardView endCard = (CardView) findViewById(R.id.end_card);
            TextView scoreText = (TextView)findViewById(R.id.score_text);

            int score = 10 - wrongQuestions.size();

            scoreText.setText(String.valueOf(score) + " /10");

            endCard.setVisibility(View.VISIBLE);

            Category c = dbPresenter.returnCategory(categoryID);

            Log.v("Category NAME: ", c.getName());

            int correct = score;
            int wrong = wrongQuestions.size();

            dbPresenter.updateCategory(c, correct, wrong);
        }

    }

    @Override
    public void onQuizLoadedSuccess(Call quiz, Response response) {
        Quiz loadedQuiz = (Quiz) response.body();
        Log.v("Quiz response code: ", loadedQuiz.getResponseCode().toString());
        List<Question> questions = loadedQuiz.getResults();

        for(int i = 0; i < 10; i++){
            QuestionFragment questionFragment = new QuestionFragment();
            questionFragment.setQuizCallBack(this);
            questionFragment.setQuestion(questions.get(i));

            List<String> answers = questions.get(i).getIncorrectAnswers();
            answers.add(questions.get(i).getCorrectAnswer());
            long seed = System.nanoTime();
            Collections.shuffle(answers, new Random(seed));
            questionFragment.setAnswers(answers);

            quizPagerAdapter.addFragment(questionFragment);
        }
        quizPager.setAdapter(quizPagerAdapter);
        progressDialog.dismiss();
    }

    @Override
    public void onQuizLoadedFailure(Call quiz, Throwable t) {

    }

    class QuizPagerAdapter extends FragmentPagerAdapter{
        List<Fragment> fragmentList = new ArrayList<>();

        public QuizPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment){
            fragmentList.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }
}
