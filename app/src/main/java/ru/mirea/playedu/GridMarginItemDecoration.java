package ru.mirea.playedu;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// Класс для отступов между элементами табличного RecyclerView
public class GridMarginItemDecoration extends RecyclerView.ItemDecoration {

    // Отступы
    private int left, right, bottom, top;
    // Количество элементов
    private int itemsCount;
    // Количество столбцов
    private int spansCount;

    public GridMarginItemDecoration(int left, int right, int bottom, int top, int itemsCount, int spansCount) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        this.itemsCount = itemsCount;
        this.spansCount = spansCount;
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // Отступ слева, если элемент не первый в строке
        if (parent.getChildAdapterPosition(view) % spansCount == 0) outRect.left = left;
        // Отступ справа, если элемент не последний в строке
        if (parent.getChildAdapterPosition(view) % spansCount != spansCount - 1) outRect.right = right;
        // Отступ сверху, если элемент не в первой строке
        if (parent.getChildAdapterPosition(view) / 4 != 0) outRect.top = top;
        // Отступ снизу, если элемент не в последней строке
        if (parent.getChildAdapterPosition(view) / 4 != itemsCount / spansCount) outRect.bottom = bottom;

    }
}