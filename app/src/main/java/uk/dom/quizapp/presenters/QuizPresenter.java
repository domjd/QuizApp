package uk.dom.quizapp.presenters;

import android.content.Context;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Response;
import uk.dom.quizapp.models.Quiz;
import uk.dom.quizapp.models.QuizInteractor;
import uk.dom.quizapp.models.SessionToken;
import uk.dom.quizapp.models.SessionTokenManager;
import uk.dom.quizapp.tools.OnQuizInteractorFinshedListener;
import uk.dom.quizapp.ui.QuizView;
import uk.dom.quizapp.tools.SessionTokenListener;

/**
 * Created by Dom on 06/12/2016.
 */
public class QuizPresenter implements IQuizPresenter, OnQuizInteractorFinshedListener {

    private QuizView view;
    private QuizInteractor quizInteractor;
    private int categoryID;

    private Context quizContext;

    public QuizPresenter(Context quizContext, QuizView view) {
        this.view = view;
        this.quizInteractor = new QuizInteractor(this);
        this.quizContext = quizContext;
    }

    public Context getQuizContext() {
        return quizContext;
    }

    @Override
    public void loadQuiz(int categoryID) {
        quizInteractor.loadQuiz(categoryID);
    }

    @Override
    public void onNetworkSuccess(Call quiz, Response response) {
        view.onQuizLoadedSuccess(quiz, response);
    }

    @Override
    public void onNetworkFailure(Call quiz, Throwable t) {
        view.onQuizLoadedFailure(quiz, t);
    }
}
