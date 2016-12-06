package uk.dom.quizapp.ui;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Dom on 06/12/2016.
 */
public interface QuizView {
    void onQuizLoadedSuccess(Call quiz, Response response);
    void onQuizLoadedFailure(Call quiz, Throwable t);
}
