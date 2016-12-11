package uk.dom.quizapp.tools;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import uk.dom.quizapp.models.Quiz;
import uk.dom.quizapp.models.SessionToken;

/**
 * Created by Dom on 06/12/2016.
 */
public interface QuizService {
    @GET("/api.php?amount=10&type=multiple")
    Call<Quiz> getQuiz(@Query("category")int category);

    @GET("/api_token.php?command=request")
    Call<SessionToken> getSessionToken();
}
