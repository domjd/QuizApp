package uk.dom.quizapp.tools;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Dom on 09/12/2016.
 */
public interface SessionTokenListener {
    void onSessionTokenGeneratedSuccess(Call sessionToken, Response response);
    void onSessionTokenGeneratedFailure(Call sessionToken, Throwable t);
}
