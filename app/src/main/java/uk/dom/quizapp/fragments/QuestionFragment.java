package uk.dom.quizapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import uk.dom.quizapp.R;
import uk.dom.quizapp.models.Question;
import uk.dom.quizapp.tools.QuizCallBack;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

    QuizCallBack quizCallBack;
    Question question;
    List<String> answers;


    public QuestionFragment() {
        // Required empty public constructor
    }

    public void setQuizCallBack(QuizCallBack quizCallBack){
        this.quizCallBack = quizCallBack;
    }

    public void setQuestion(Question question){
        this.question = question;
    }

    public void setAnswers(List<String> answers){
        this.answers = answers;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question, container, false);

        TextView questionView = (TextView) v.findViewById(R.id.question_text);
        final Button answerA = (Button) v.findViewById(R.id.answer_a);
        final Button answerB = (Button) v.findViewById(R.id.answer_b);
        final Button answerC = (Button) v.findViewById(R.id.answer_c);
        final Button answerD = (Button) v.findViewById(R.id.answer_d);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            questionView.setText(Html.fromHtml(question.getQuestion(), Html.FROM_HTML_MODE_LEGACY));

            answerA.setText(Html.fromHtml(answers.get(0), Html.FROM_HTML_MODE_LEGACY));
            answerB.setText(Html.fromHtml(answers.get(1), Html.FROM_HTML_MODE_LEGACY));
            answerC.setText(Html.fromHtml(answers.get(2), Html.FROM_HTML_MODE_LEGACY));
            answerD.setText(Html.fromHtml(answers.get(3), Html.FROM_HTML_MODE_LEGACY));

        } else {
            questionView.setText(Html.fromHtml(question.getQuestion()));

            answerA.setText(Html.fromHtml(answers.get(0)));
            answerB.setText(Html.fromHtml(answers.get(1)));
            answerC.setText(Html.fromHtml(answers.get(2)));
            answerD.setText(Html.fromHtml(answers.get(3)));
        }

        answerA.setText(answers.get(0));
        answerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizCallBack.onAnswerReceived(answerA.getText().toString());
            }
        });

        answerB.setText(answers.get(1));
        answerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizCallBack.onAnswerReceived(answerB.getText().toString());
            }
        });

        answerC.setText(answers.get(2));
        answerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizCallBack.onAnswerReceived(answerC.getText().toString());
            }
        });

        answerD.setText(answers.get(3));
        answerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizCallBack.onAnswerReceived(answerD.getText().toString());
            }
        });

        return v;
    }

}
