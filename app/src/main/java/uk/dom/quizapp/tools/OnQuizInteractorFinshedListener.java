package uk.dom.quizapp.tools;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Dom on 06/12/2016.
 */
public interface OnQuizInteractorFinshedListener {
    void onNetworkSuccess(Call quiz, Response response);
    void onNetworkFailure(Call quiz, Throwable t);
}
