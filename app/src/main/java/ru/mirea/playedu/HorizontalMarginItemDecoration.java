package ru.mirea.playedu;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


// Класс для отступов между элементами горизонтального RecyclerView
public class HorizontalMarginItemDecoration extends RecyclerView.ItemDecoration {

    private int left, right, bottom, top;
    private int spaceSize = 0;
    private int itemsCount;

    // Каждый отступ задается отдельно
    public HorizontalMarginItemDecoration(int left, int right, int bottom, int top, int itemsCount) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        this.itemsCount = itemsCount;
    }

    // Все отступы равны параметру
    public HorizontalMarginItemDecoration(int spaceSize, int itemsCount) {
        this.spaceSize = spaceSize;
        this.itemsCount = itemsCount;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (spaceSize != 0) {
            if (parent.getChildAdapterPosition(view) == 0) outRect.left = spaceSize;
            outRect.top = spaceSize;
            outRect.right = spaceSize;
            outRect.bottom = spaceSize;
        } else {
            if (parent.getChildAdapterPosition(view) == 0) outRect.left = left;
            if (parent.getChildAdapterPosition(view) != itemsCount - 1) outRect.right = right;
            outRect.top = top;
            outRect.bottom = bottom;
        }

    }
}
