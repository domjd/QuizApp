package uk.dom.quizapp.models;

import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.dom.quizapp.tools.OnQuizInteractorFinshedListener;
import uk.dom.quizapp.tools.QuizService;

/**
 * Created by Dom on 06/12/2016.
 */
public class QuizInteractor implements Callback<Quiz> {

    private OnQuizInteractorFinshedListener listener;
    String sessionToken;

    public QuizInteractor(OnQuizInteractorFinshedListener listener) {
        this.listener = listener;
    }

    private Retrofit retrofit(){
        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://www.opentdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return rf;
    }

    public void getSessionToken(){
        QuizService quizService = retrofit().create(QuizService.class);
        Call<String> token = quizService.getSessionToken();
        token.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.v("TOKEN:", sessionToken);
                sessionToken = (String) response.body();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void loadQuiz(){
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                getSessionToken();
            }
        },0,6,TimeUnit.HOURS);
        QuizService quizService = retrofit().create(QuizService.class);
        Call<Quiz> quiz = quizService.getQuiz(sessionToken);
        quiz.enqueue(this);
    }

    @Override
    public void onResponse(Call<Quiz> call, Response<Quiz> response) {
        listener.onNetworkSuccess(call, response);
    }

    @Override
    public void onFailure(Call<Quiz> call, Throwable t) {
        listener.onNetworkFailure(call, t);
    }
}
