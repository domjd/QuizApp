package uk.dom.quizapp.tools;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import uk.dom.quizapp.models.Quiz;

/**
 * Created by Dom on 06/12/2016.
 */
public interface QuizService {
    @GET("/api.php?amount=10&category=9&difficulty=medium&type=multiple")
    Call<Quiz> getQuiz(@Query("token") String token);

    @GET("/api_token.php?command=request")
    Call<String> getSessionToken();
}
