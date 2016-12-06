package uk.dom.quizapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

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

public class MainActivity extends AppCompatActivity {

    PieChart pieChart;

    RecyclerView categoryRecycler;
    LinearLayoutManager linearLayoutManager;
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();
        yValues.add(new BarEntry(0, new float[]{ 60, 40 }));
        yValues.add(new BarEntry(0, new float[]{ 70, 30 }));
        yValues.add(new BarEntry(0, new float[]{ 40, 60 }));
        yValues.add(new BarEntry(0, new float[]{ 80, 20 }));

        setupPieChart();

        categoryRecycler = (RecyclerView) findViewById(R.id.category_recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        categoryAdapter = new CategoryAdapter(yValues);
        categoryRecycler.setLayoutManager(linearLayoutManager);
        categoryRecycler.setAdapter(categoryAdapter);

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

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(18.5f, "Correct Answers"));
        entries.add(new PieEntry(26.7f, "Wrong Answers"));

        PieDataSet set = new PieDataSet(entries, "");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setSliceSpace(8);
        set.setSelectionShift(5);
        set.setDrawValues(false);


        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh
    }
}
