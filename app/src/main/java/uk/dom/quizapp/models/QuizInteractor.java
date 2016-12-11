package uk.dom.quizapp.models;

import android.util.Log;

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

    private OnQuizInteractorFinshedListener quizListener;

    public QuizInteractor(OnQuizInteractorFinshedListener listener) {
        this.quizListener = listener;
    }

    private Retrofit retrofit(){
        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://www.opentdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return rf;
    }

    public void generateSessionToken(){
        QuizService quizService = retrofit().create(QuizService.class);
        Call<SessionToken> token = quizService.getSessionToken();
        token.enqueue(new Callback<SessionToken>() {
            @Override
            public void onResponse(Call<SessionToken> call, Response<SessionToken> response) {
                //quizListener.onSessionTokenGeneratedSuccess(call,response);

            }

            @Override
            public void onFailure(Call<SessionToken> call, Throwable t) {
                //quizListener.onSessionTokenGeneratedFailure(call, t);
            }
        });
    }


    public void loadQuiz(int categoryID){
        QuizService quizService = retrofit().create(QuizService.class);
        Call<Quiz> quiz = quizService.getQuiz(categoryID);
        quiz.enqueue(QuizInteractor.this);
    }

    @Override
    public void onResponse(Call<Quiz> call, Response<Quiz> response) {
        quizListener.onNetworkSuccess(call, response);
    }

    @Override
    public void onFailure(Call<Quiz> call, Throwable t) {
        quizListener.onNetworkFailure(call, t);
    }
}
