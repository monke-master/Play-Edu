package ru.mirea.playedu;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

// Диалог добавления задачи
public class AddTaskDialog extends DialogFragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = requireActivity().getLayoutInflater().inflate(
                R.layout.dialog_add_task,
                null,
                false);

        // Фон диалога
        getDialog().getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_dialog));
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Получение виджетов
        view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_add_task, null, false);
        EditText priceEditTxt = view.findViewById(R.id.price_edit_txt);
        RecyclerView recyclerView = view.findViewById(R.id.colors_list);
        EditText dateEditTxt = view.findViewById(R.id.date_edit_txt);

        // Ввод награды
        priceEditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // Отслеживание неправильного ввода: награда не должна превышать 100 монет
            // В противном случае высветится уведомление об ошибке
            @Override
            public void afterTextChanged(Editable editable) {
                if (!priceEditTxt.getText().toString().isEmpty() && Integer.parseInt(priceEditTxt.getText().toString()) > 100) {
                    priceEditTxt.setError(getString(R.string.price_error_too_much));
                }
            }
        });

        // Отображение подсказки - нужного формата даты в виде сегодняшней даты
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateEditTxt.setHint(dateFormat.format(calendar.getTime()));

        // Выбор даты дедлайна при нажатии на dateEditText
        dateEditTxt.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    requireContext(),
                    new DatePickerDialog.OnDateSetListener() {
                        // Установка выбранной даты в поле dateEditTxt
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            calendar.set(year, month - 1, day);
                            dateEditTxt.setText(dateFormat.format(calendar.getTime()));
                        }
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            // Установка сегодняшней даты минимальной при выборе даты дедлайна
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();
        });

        // Настройка RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false));
        int[] colors = Constants.getCategoryColors(requireContext());
        recyclerView.setAdapter(new ColorAdapter(colors));
        // Рассчет отступов между цветами
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int margin =  getResources().getDimensionPixelOffset(R.dimen.default_margin);
        recyclerView.addItemDecoration(
                new HorizontalMarginItemDecoration(
                        0,
                        DimensionManager.calcHorizontalMargin(
                                metrics.widthPixels - margin,
                                getResources().getDimensionPixelOffset(R.dimen.color_btn_size),
                                colors.length
                        ),
                        0,
                        0,
                        colors.length));

        builder.setView(view);
        return builder.create();
    }

}