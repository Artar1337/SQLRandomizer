package ru.sibsutis.sqlrandomizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    DBHelper dbHelper;
    TableLayout table;
    Button bCreate;


    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static float getRandomFloat(int min, int max) {
        Random random = new Random();
        return (float) random.nextInt(max - min)
                + min + random.nextFloat();
    }

    public static boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    private String generateName(boolean isMale) {
        String name = "";

        if (isMale) {
            String[] names = {"Артём"
                    , "Вадим"
                    , "Владислав"
                    , "Михаил"
                    , "Матвей"
                    , "Герман"
                    , "Андрей"
                    , "Артур"
                    , "Николай"
                    , "Илья"
                    , "Руслан"
                    , "Данил"
                    , "Даниил"};
            String[] surnames = {"Астраханцев"
                    , "Гребенщиков"
                    , "Захаров"
                    , "Козин"
                    , "Переломов"
                    , "Сафонов"
                    , "Шаповалов"
                    , "Захаров"
                    , "Сарветников"
                    , "Андропов"
                    , "Берианидзе"
                    , "Потапов"
                    , "Потанин"
                    , "Андреев"
                    , "Кузьмин"
                    , "Мокроусов"
                    , "Браун"
                    , "Бонд"
                    , "Дорофеев"
                    , "Галанцев"};
            int index = getRandomNumber(0, surnames.length - 1);
            name += surnames[index] + " ";
            index = getRandomNumber(0, names.length - 1);
            name += names[index];
        } else {
            String[] names = {"Дарья"
                    , "Наталья"
                    , "Валерия"
                    , "Кристина"
                    , "Елизавета"
                    , "Яна"
                    , "Анастасия"
                    , "Виктория"
                    , "Алёна"
                    , "Людмила"
                    , "Джульетта"
                    , "Гермиона"
                    , "Трисс"};
            String[] surnames = {"Астраханцева"
                    , "Гребенщикова"
                    , "Захарова"
                    , "Козина"
                    , "Переломова"
                    , "Сафонова"
                    , "Шаповалова"
                    , "Захарова"
                    , "Сарветникова"
                    , "Андропова"
                    , "Берианидзе"
                    , "Потапова"
                    , "Потанина"
                    , "Андреева"
                    , "Кузьмина"
                    , "Мокроусова"
                    , "Браун"
                    , "Бонд"
                    , "Дорофеева"
                    , "Галанцева"};
            int index = getRandomNumber(0, surnames.length - 1);
            name += surnames[index] + " ";
            index = getRandomNumber(0, names.length - 1);
            name += names[index];
        }
        return name;
    }

    private String createRandomRecord(int ID) {
        String name = generateName(getRandomBoolean());
        float weight = getRandomFloat(50, 90);
        int height = getRandomNumber(150, 210);
        int age = getRandomNumber(17, 30);

        String numberAsString = String.format("%.1f", weight);

        return "(" + ID + ",'"
                + name + "'," + numberAsString + ","
                + height + "," + age + ")";
    }

    private void generateDataBase() {
        for (int i = 1; i < 21; i++) {
            String query = createRandomRecord(i);
            db.execSQL("INSERT INTO students" +
                    "('id','name','weight','height','age') VALUES " +
                    query + ";");
        }
    }

    private void addColumnToRow(String string, TableRow row) {
        TextView textview = new TextView(this);
        textview.setTextColor(getResources().getColor(R.color.black));
        textview.setBackgroundColor(getResources().getColor(R.color.table_color));
        textview.setText(string);
        row.addView(textview);
    }

    public void createTable() {
        table.setShrinkAllColumns(true);
        table.setStretchAllColumns(true);
        TableRow initRow = new TableRow(this);
        addColumnToRow("№", initRow);
        addColumnToRow("ФИ", initRow);
        addColumnToRow("Вес", initRow);
        addColumnToRow("Рост", initRow);
        addColumnToRow("Возраст", initRow);
        table.addView(initRow);

        dbHelper = new DBHelper(MainActivity.this);
        db = dbHelper.getWritableDatabase();
        db.delete("students", null, null);
        generateDataBase();
        Cursor c;
        c = db.rawQuery("Select * from students ORDER BY age,name", null);
        if (c.moveToFirst()) {
            do {
                TableRow row = new TableRow(this);

                int ColIndex = c.getColumnIndex("id");
                addColumnToRow(c.getString(ColIndex), row);
                ColIndex = c.getColumnIndex("name");
                addColumnToRow(c.getString(ColIndex), row);
                ColIndex = c.getColumnIndex("weight");
                addColumnToRow(c.getString(ColIndex), row);
                ColIndex = c.getColumnIndex("height");
                addColumnToRow(c.getString(ColIndex), row);
                ColIndex = c.getColumnIndex("age");
                addColumnToRow(c.getString(ColIndex), row);

                table.addView(row);
            } while (c.moveToNext());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bCreate = (Button) findViewById(R.id.ButtonCreate);
        table = findViewById(R.id.TableLayout);
        View.OnClickListener operationListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table.removeAllViewsInLayout();
                createTable();
            }
        };
        bCreate.setOnClickListener(operationListener);
        createTable();
    }

}