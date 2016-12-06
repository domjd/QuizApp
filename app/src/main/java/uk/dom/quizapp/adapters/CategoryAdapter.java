package uk.dom.quizapp.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import uk.dom.quizapp.R;

/**
 * Created by Dom on 05/12/2016.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<BarEntry> dataSet;
    List<String> categoryNames;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView categoryName;
        TextView categoryStat;
        HorizontalBarChart categoryChart;

        public ViewHolder(View v) {
            super(v);
            categoryName = (TextView) v.findViewById(R.id.category_name);
            categoryStat = (TextView) v.findViewById(R.id.category_stat);
            categoryChart = (HorizontalBarChart) v.findViewById(R.id.category_chart);
        }
    }

    public CategoryAdapter(List<BarEntry> dataSet) {
        this.dataSet = dataSet;

        categoryNames = new ArrayList<>();

        categoryNames.add("Entertainment");
        categoryNames.add("Geography");
        categoryNames.add("Art");
        categoryNames.add("Sports");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.categoryName.setText(categoryNames.get(position));

        float[] values = dataSet.get(position).getYVals();
        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();
        yValues.add(dataSet.get(position));

        holder.categoryStat.setText(String.format ("%.0f", values[0]) + "% answers correct");

        setupChart(holder.categoryChart, yValues);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    private void setupChart(HorizontalBarChart horizontalBarChart,ArrayList<BarEntry> yValues){
        horizontalBarChart.getDescription().setEnabled(false);
        horizontalBarChart.getLegend().setEnabled(false);
        horizontalBarChart.setMaxVisibleValueCount(0);
        horizontalBarChart.getXAxis().setDrawGridLines(false);
        horizontalBarChart.getXAxis().setEnabled(false);

        YAxis leftAxis = horizontalBarChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setEnabled(false);
        YAxis rightAxis = horizontalBarChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawAxisLine(false);
        rightAxis.setEnabled(false);

        horizontalBarChart.setPinchZoom(false);
        horizontalBarChart.setDrawMarkers(false);
        horizontalBarChart.setDrawBorders(false);
        horizontalBarChart.setDrawGridBackground(false); //Do not display grid background
        horizontalBarChart.animateY(1000);


        //Disable all interaction with the chart
        horizontalBarChart.setHighlightPerTapEnabled(false);
        horizontalBarChart.setHighlightPerDragEnabled(false);
        horizontalBarChart.setDoubleTapToZoomEnabled(false);
        horizontalBarChart.setDrawMarkers(false);

        BarDataSet set = new BarDataSet(yValues, "");
        set.setDrawValues(true); // This doesn't seem to work
        set.setValueTextColor(Color.BLACK);
        set.setValueTextSize(12f);
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setDrawValues(false);

        BarData data = new BarData(set);
        data.setDrawValues(true);  // just in case
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);

        horizontalBarChart.setData(data);
        horizontalBarChart.invalidate();
    }
}
