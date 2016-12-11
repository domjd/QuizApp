package uk.dom.quizapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import uk.dom.quizapp.adapters.CategoryAdapter;
import uk.dom.quizapp.R;
import uk.dom.quizapp.presenters.DatabasePresenter;

public class MainActivity extends AppCompatActivity {

    private PieChart pieChart;

    private RecyclerView categoryRecycler;
    private LinearLayoutManager linearLayoutManager;
    private CategoryAdapter categoryAdapter;

    private DatabasePresenter presenter;

    private LinearLayout noDataLayout;
    private LinearLayout pieLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        noDataLayout = (LinearLayout) findViewById(R.id.no_data_layout);

        presenter = new DatabasePresenter(this);

        TextView noDataText = (TextView) findViewById(R.id.no_data_text);
        ImageView noDataImage = (ImageView) findViewById(R.id.no_data_image);

        if(presenter.checkIsDataNull() == true){
            CardView summaryCard = (CardView) findViewById(R.id.pie_card);
            CardView statsCard = (CardView) findViewById(R.id.category_stats_card);

            summaryCard.setVisibility(View.INVISIBLE);
            statsCard.setVisibility(View.INVISIBLE);

            noDataText.setVisibility(View.VISIBLE);
            noDataImage.setVisibility(View.VISIBLE);
        }

        else{

            noDataText.setVisibility(View.INVISIBLE);
            noDataImage.setVisibility(View.VISIBLE);
            setupPieChart();

            categoryRecycler = (RecyclerView) findViewById(R.id.category_recycler);
            linearLayoutManager = new LinearLayoutManager(this);
            categoryAdapter = new CategoryAdapter(presenter.getCategoryValues());
            categoryRecycler.setLayoutManager(linearLayoutManager);
            categoryRecycler.setAdapter(categoryAdapter);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent i = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(i);
            }
        });
    }

    private void setupPieChart(){
        Description description = new Description();
        description.setText("");

        pieChart = (PieChart) findViewById(R.id.chart);
        pieChart.setTransparentCircleRadius(0);
        pieChart.setUsePercentValues(true);
        pieChart.setDescription(description);
        pieChart.isDrawSlicesUnderHoleEnabled();
        //pieChart.setCenterText("Quiz Stats");
        pieChart.isDrawCenterTextEnabled();
        pieChart.setMinimumWidth(500);
        pieChart.setMinimumHeight(500);
        pieChart.setTransparentCircleRadius(58f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(50);
        pieChart.setDrawSlicesUnderHole(true);
        pieChart.setRotationEnabled(true);
        pieChart.animateXY(1000, 1000);
        pieChart.setDrawEntryLabels(false);

        Legend legend = pieChart.getLegend();
        legend.setFormSize(10f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setTextSize(14f);
        legend.setDrawInside(false);

        PieDataSet set = new PieDataSet(presenter.getPieChartValues(), "");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setSliceSpace(8);
        set.setSelectionShift(5);
        set.setDrawValues(false);


        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh*/
    }
}
