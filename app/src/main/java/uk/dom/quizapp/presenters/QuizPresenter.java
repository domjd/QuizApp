package uk.dom.quizapp.presenters;

import retrofit2.Call;
import retrofit2.Response;
import uk.dom.quizapp.models.QuizInteractor;
import uk.dom.quizapp.tools.OnQuizInteractorFinshedListener;
import uk.dom.quizapp.ui.QuizView;

/**
 * Created by Dom on 06/12/2016.
 */
public class QuizPresenter implements IQuizPresenter, OnQuizInteractorFinshedListener {

    private QuizView view;
    private QuizInteractor quizInteractor;

    public QuizPresenter(QuizView view) {
        this.view = view;
        this.quizInteractor = new QuizInteractor(this);
    }

    @Override
    public void loadQuiz() {
        quizInteractor.loadQuiz();
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
