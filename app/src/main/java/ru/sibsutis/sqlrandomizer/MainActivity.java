package ru.sibsutis.sqlrandomizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private String generateName(boolean isMale)
    {
        String name="";

        if(isMale)
        {
            String [] names = {"Артём"
                    ,"Вадим"
                    ,"Владислав"
                    ,"Михаил"
                    ,"Матвей"
                    ,"Герман"
                    ,"Андрей"
                    ,"Артур"
                    ,"Николай"
                    ,"Илья"
                    ,"Руслан"
                    ,"Данил"
                    ,"Даниил"};
            String [] surnames = {"Астраханцев"
                    ,"Гребенщиков"
                    ,"Захаров"
                    ,"Козин"
                    ,"Переломов"
                    ,"Сафонов"
                    ,"Шаповалов"
                    ,"Захаров"
                    ,"Сарветников"
                    ,"Андропов"
                    ,"Берианидзе"
                    ,"Потапов"
                    ,"Потанин"
                    ,"Андреев"
                    ,"Кузьмин"
                    ,"Мокроусов"
                    ,"Браун"
                    ,"Бонд"
                    ,"Дорофеев"
                    ,"Галанцев"};
            int index = getRandomNumber(0,surnames.length-1);
            name+=surnames[index]+" ";
            index = getRandomNumber(0,names.length-1);
            name+=names[index];
        }
        else
        {
            String [] names = {"Дарья"
                    ,"Наталья"
                    ,"Валерия"
                    ,"Кристина"
                    ,"Елизавета"
                    ,"Яна"
                    ,"Анастасия"
                    ,"Виктория"
                    ,"Алёна"
                    ,"Людмила"
                    ,"Джульетта"
                    ,"Гермиона"
                    ,"Трисс"};
            String [] surnames = {"Астраханцева"
                    ,"Гребенщикова"
                    ,"Захарова"
                    ,"Козина"
                    ,"Переломова"
                    ,"Сафонова"
                    ,"Шаповалова"
                    ,"Захарова"
                    ,"Сарветникова"
                    ,"Андропова"
                    ,"Берианидзе"
                    ,"Потапова"
                    ,"Потанина"
                    ,"Андреева"
                    ,"Кузьмина"
                    ,"Мокроусова"
                    ,"Браун"
                    ,"Бонд"
                    ,"Дорофеева"
                    ,"Галанцева"};
            int index = getRandomNumber(0,surnames.length-1);
            name+=surnames[index]+" ";
            index = getRandomNumber(0,names.length-1);
            name+=names[index];
        }
        return name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.wtf("name is ",generateName(false));
        Log.wtf("name is ",generateName(true));
    }
}