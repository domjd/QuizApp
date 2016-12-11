package uk.dom.quizapp.adapters;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import uk.dom.quizapp.R;
import uk.dom.quizapp.models.Category;
import uk.dom.quizapp.tools.CategoryListener;

/**
 * Created by Dom on 05/12/2016.
 */
public class CategoryChooserAdapter extends RecyclerView.Adapter<CategoryChooserAdapter.ViewHolder> {

    private List<Category> dataset;
    private final CategoryListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView categoryName;
        ImageView categoryImage;

        public ViewHolder(View v) {
            super(v);

            categoryName = (TextView)v.findViewById(R.id.category_chooser_name);
            categoryImage = (ImageView)v.findViewById(R.id.category_chooser_image);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(listener != null)
                listener.onItemClick(view, getAdapterPosition());
        }
    }

    public CategoryChooserAdapter(List<Category> dataset, CategoryListener listener) {
        this.dataset = dataset;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_chooser_card,parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.categoryName.setText(dataset.get(position).getName());
        holder.categoryImage.setImageResource(dataset.get(position).getImage());

        if(dataset.get(position).isLocked() == true){
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);  //0 means grayscale
            ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
            holder.categoryImage.setColorFilter(cf);
            holder.categoryImage.setAlpha(0.5f);   // 128 = 0.5
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
