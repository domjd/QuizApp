package uk.dom.quizapp.tools;

/**
 * Created by Dom on 06/12/2016.
 */
public interface QuizCallBack {
    void onAnswerReceived(int questionNumber, String question, String userAnswer, String correctAnswer);
}
