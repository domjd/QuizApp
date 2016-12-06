package uk.dom.quizapp.activities;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Response;
import uk.dom.quizapp.R;
import uk.dom.quizapp.fragments.QuestionFragment;
import uk.dom.quizapp.models.Question;
import uk.dom.quizapp.models.Quiz;
import uk.dom.quizapp.presenters.QuizPresenter;
import uk.dom.quizapp.tools.QuizCallBack;
import uk.dom.quizapp.ui.QuizView;
import uk.dom.quizapp.ui.QuizViewPager;

public class QuizActivity extends AppCompatActivity implements QuizCallBack, QuizView {

    private QuizViewPager quizPager;
    private QuizPagerAdapter quizPagerAdapter;

    private QuizPresenter presenter;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        progressDialog = ProgressDialog.show(this, "Loading Quiz", null);

        presenter = new QuizPresenter(this);
        presenter.loadQuiz();

        quizPager = (QuizViewPager) findViewById(R.id.quiz_pager);
        quizPagerAdapter = new QuizPagerAdapter(getSupportFragmentManager());

    }

    @Override
    public void onAnswerReceived(String answer) {
        quizPager.setCurrentItem(quizPager.getCurrentItem() + 1, true);
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
