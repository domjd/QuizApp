package uk.dom.quizapp.presenters;

import android.content.Context;
import android.util.Log;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import uk.dom.quizapp.R;
import uk.dom.quizapp.models.Category;
import uk.dom.quizapp.tools.DBManager;

/**
 * Created by Dom on 10/12/2016.
 */
public class DatabasePresenter implements IDatabasePresenter {

    private DBManager dbManager;

    public DatabasePresenter(Context context) {
        dbManager = new DBManager(context); setupCategories();
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

            entries.add(new BarEntry(0, new float[]{ correct, wrong }));
        }

        return entries;
    }

    @Override
    public List<PieEntry> getPieChartValues() {
        int correctAnswers = 0;
        int wrongAnswers = 0;

        for (Category c: returnCategories()) {
            correctAnswers += c.getCorrectAnswerTotal();
            wrongAnswers += c.getCorrectAnswerTotal();
        }

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(correctAnswers, "Correct Answers"));
        entries.add(new PieEntry(wrongAnswers, "Wrong Answers"));

        return entries;
    }

    @Override
    public boolean checkIsDataNull() {
        int correctAnswers = 0;
        int wrongAnswers = 0;

        for (Category c: returnCategories()) {
            correctAnswers += c.getCorrectAnswerTotal();
            wrongAnswers += c.getCorrectAnswerTotal();
        }

        if((correctAnswers == 0) && (wrongAnswers ==0)) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void setupCategories() {
        Log.v("PRESENTER: ", "setting up cats");
        Category c = new Category("Entertainment", R.drawable.comedy, 11, false);
        dbManager.addCategory(c);
        c = new Category("Geography", R.drawable.planet, 22, false);
        dbManager.addCategory(c);
        c = new Category("Arts", R.drawable.palette, 25, false);
        dbManager.addCategory(c);
        c = new Category("Sports", R.drawable.medal,21, false);
        dbManager.addCategory(c);
    }
}
