package uk.dom.quizapp.models;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;
import uk.dom.quizapp.presenters.QuizPresenter;
import uk.dom.quizapp.tools.SessionTokenListener;

/**
 * Created by Dom on 09/12/2016.
 */
public class SessionTokenManager {

    private QuizPresenter presenter;

    public SessionTokenManager(QuizPresenter presenter) {
        this.presenter = presenter;
    }

    public void setupSessionToken() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(presenter.getQuizContext());

        if(checkLocalToken()) {
            SessionToken token = new SessionToken();
            token.setToken(sharedPreferences.getString("session_token", null));
            //presenter.setSessionToken(token);
        }

        else {
            //presenter.generateSessionToken();
        }
    }

    public Boolean checkLocalToken(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(presenter.getQuizContext());

        long sessionTokenAge = System.currentTimeMillis() - sharedPreferences.getLong("session_token_time", 0);
        long sessionTokenAgeHours = TimeUnit.HOURS.convert(sessionTokenAge,TimeUnit.MILLISECONDS);

        Log.v("TOKEN AGE:", Long.toString(sessionTokenAgeHours));

        if((sharedPreferences.contains("session_token")) && (sessionTokenAgeHours < 6)){
            SessionToken sessionToken = new SessionToken();
            sessionToken.setToken(sharedPreferences.getString("session_token", null));
            //presenter.setSessionToken(sessionToken);
            return true;
        }
        else{
            return false;
        }
    }

    public void onSessionTokenGeneratedSuccess(SessionToken sessionToken) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(presenter.getQuizContext());

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("session_token", sessionToken.getToken());
        editor.putLong("session_token_time", System.currentTimeMillis());
        editor.commit();

        Log.v("current session token: ", sessionToken.getToken());

        //presenter.setSessionToken(sessionToken);
    }

    public void onSessionTokenGeneratedFailure() {

    }
}
