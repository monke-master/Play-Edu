package ru.mirea.playedu.view.dialog;

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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ru.mirea.playedu.callbacks.OnSelectColorFilterCallback;
import ru.mirea.playedu.model.Category;
import ru.mirea.playedu.model.UserTask;
import ru.mirea.playedu.viewmodel.AddTaskViewModel;
import ru.mirea.playedu.view.adapter.ColorAdapter;
import ru.mirea.playedu.Constants;
import ru.mirea.playedu.DimensionManager;
import ru.mirea.playedu.HorizontalMarginItemDecoration;
import ru.mirea.playedu.R;

// Диалог добавления задачи
public class AddTaskDialog extends DialogFragment implements OnSelectColorFilterCallback {

    private View view;
    private AddTaskViewModel viewModel;
    private int taskColor;
    private String title;
    private int price;
    private Date deadlineDate;
    private Date creationDate;
    private Category category;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = requireActivity().getLayoutInflater().inflate(
                R.layout.dialog_add_task,
                null,
                false);

        // Фон диалога
        getDialog().getWindow().setBackgroundDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.shape_dialog));

        // Привязка viewmodel
        viewModel = new ViewModelProvider(this).get(AddTaskViewModel.class);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog alert = builder.create();
        // Получение виджетов
        view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_add_task, null, false);
        EditText priceEditTxt = view.findViewById(R.id.price_edit_txt);
        RecyclerView recyclerView = view.findViewById(R.id.colors_list);
        EditText dateEditTxt = view.findViewById(R.id.date_edit_txt);
        EditText titleEditTxt = view.findViewById(R.id.title_edit_txt);
        EditText listEditTxt = view.findViewById(R.id.list_edit_txt);
        Button addBtn = (Button) view.findViewById(R.id.add_btn);


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
        taskColor = colors[0];
        recyclerView.setAdapter(new ColorAdapter(colors, this));
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


        // Сбор данных по форме задания
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = titleEditTxt.getText().toString();
                price = Integer.parseInt(priceEditTxt.getText().toString());
                deadlineDate = calendar.getTime();
                creationDate = Calendar.getInstance().getTime();
                category = viewModel.createCategory(listEditTxt.getText().toString());
                UserTask userTask = new UserTask(title, category, true, price, deadlineDate, creationDate, taskColor);
                viewModel.addTask(userTask);
                getParentFragmentManager().setFragmentResult("requestKey", Bundle.EMPTY);
                alert.cancel();
            }
        });

        builder.setView(view);
        return builder.create();
    }

    @Override
    public void execute(int color) {
        taskColor = color;
    }
}