package uk.dom.quizapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import uk.dom.quizapp.R;
import uk.dom.quizapp.models.LocalQuestion;
import uk.dom.quizapp.models.Question;

/**
 * Created by Dom on 07/12/2016.
 */
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    List<LocalQuestion> wrongQuestions;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView question;
        TextView userAnswer;
        TextView correctAnswer;

        public ViewHolder(View view) {
            super(view);
            question = (TextView) view.findViewById(R.id.summary_question);
            userAnswer = (TextView) view.findViewById(R.id.user_answer);
            correctAnswer = (TextView) view.findViewById(R.id.correct_answer);
        }
    }

    public QuestionAdapter(List<LocalQuestion> wrongQuestions) {
        this.wrongQuestions = wrongQuestions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_card,parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (android.os.Build.VERSION.SDK_INT >= 24) {
            holder.question.setText(Html.fromHtml(wrongQuestions.get(position).getQuestion(), Html.FROM_HTML_MODE_LEGACY));
            holder.userAnswer.setText(Html.fromHtml(wrongQuestions.get(position).getUserAnswer(), Html.FROM_HTML_MODE_LEGACY));
            holder.correctAnswer.setText(Html.fromHtml(wrongQuestions.get(position).getCorrectAnswer(), Html.FROM_HTML_MODE_LEGACY));

        } else {
            holder.question.setText(Html.fromHtml(wrongQuestions.get(position).getQuestion()));
            holder.userAnswer.setText(Html.fromHtml(wrongQuestions.get(position).getUserAnswer()));
            holder.correctAnswer.setText(Html.fromHtml(wrongQuestions.get(position).getCorrectAnswer()));
        }



    }

    @Override
    public int getItemCount() {
        return wrongQuestions.size();
    }
}
