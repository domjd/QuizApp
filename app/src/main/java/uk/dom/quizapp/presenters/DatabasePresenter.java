package uk.dom.quizapp.presenters;

import android.content.Context;
import android.util.Log;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import uk.dom.quizapp.R;
import uk.dom.quizapp.models.Category;
import uk.dom.quizapp.models.DBManager;

/**
 * Created by Dom on 10/12/2016.
 */
public class DatabasePresenter implements IDatabasePresenter {

    private DBManager dbManager;

    public DatabasePresenter(Context context) {
        dbManager = new DBManager(context);
    }

    @Override
    public void addCategoryToDatabase(Category category) {
        dbManager.addCategory(category);
    }

    @Override
    public List<Category> returnCategories() {
        if(dbManager.returnCategories().size() == 0){
            setupCategories();
            return dbManager.returnCategories();
        }
        else{
            return dbManager.returnCategories();
        }
    }

    @Override
    public void resetTable() {
        dbManager.resetTable();
    }

    @Override
    public Category returnCategory(int categoryID) {
        return dbManager.returnCategory(categoryID);
    }

    @Override
    public ArrayList<BarEntry> getCategoryValues() {
        List<Category> categories = returnCategories();
        ArrayList<BarEntry> entries = new ArrayList<>();

        for (Category c: categories) {
                int correct = c.getCorrectAnswerTotal();
                int wrong = c.getWrongAnswerTotal();

                entries.add(new BarEntry(0, new float[]{correct, wrong}));
        }

        return entries;
    }

    @Override
    public void updateCategory(Category category, int correct_answer, int wrong_answers) {
        dbManager.updateCategory(category, correct_answer, wrong_answers);
    }

    @Override
    public List<PieEntry> getPieChartValues() {
        int correctAnswers = 0;
        int wrongAnswers = 0;

        for (Category c: dbManager.returnCategories()) {
            correctAnswers += c.getCorrectAnswerTotal();
            wrongAnswers += c.getWrongAnswerTotal();
        }

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(correctAnswers, "Correct Answers"));
        entries.add(new PieEntry(wrongAnswers, "Wrong Answers"));

        Log.v("PIE correct answers: ", String.valueOf(correctAnswers));
        Log.v("PIE wrong answers: ", String.valueOf(wrongAnswers));

        return entries;
    }

    @Override
    public boolean checkIsDataNull() {

        for (Category c: dbManager.returnCategories()) {
            Log.v("C correct answers: ", String.valueOf(c.getCorrectAnswerTotal()));
            Log.v("C wrong answers: ", String.valueOf(c.getWrongAnswerTotal()));
            if (c.getCorrectAnswerTotal() > 0 || c.getWrongAnswerTotal() > 0) {
                return false;
            }

            else{
                return true;
            }
        }

        return true;
    }

    @Override
    public int getMarks() {
        return dbManager.returnMarks();
    }

    @Override
    public int getCoins() {
        return dbManager.returnCoins();
    }

    @Override
    public void setupCategories() {
        Log.v("PRESENTER: ", "setting up cats");
        Category c = new Category("Film", R.drawable.film, 11, false);
        dbManager.addCategory(c);
        c = new Category("Geography", R.drawable.planet, 22, false);
        dbManager.addCategory(c);
        c = new Category("Arts", R.drawable.palette, 25, false);
        dbManager.addCategory(c);
        c = new Category("Sports", R.drawable.medal,21, false);
        dbManager.addCategory(c);
        c = new Category("Video Games", R.drawable.gamepad, 15, true);
        dbManager.addCategory(c);
        c = new Category("Politics", R.drawable.capitol, 24, true);
        dbManager.addCategory(c);
        c = new Category("Animals", R.drawable.competition, 27, true);
        dbManager.addCategory(c);
        c = new Category("History", R.drawable.history,23, true);
        dbManager.addCategory(c);
    }
}
