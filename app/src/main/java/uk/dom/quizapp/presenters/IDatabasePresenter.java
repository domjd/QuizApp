package uk.dom.quizapp.presenters;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import uk.dom.quizapp.models.Category;

/**
 * Created by Dom on 10/12/2016.
 */
public interface IDatabasePresenter {
    void addCategoryToDatabase(Category category);
    List<Category> returnCategories();
    Category returnCategory(int categoryID);
    ArrayList<BarEntry> getCategoryValues();
    List<PieEntry> getPieChartValues();
    void setupCategories();
    boolean checkIsDataNull();
}
