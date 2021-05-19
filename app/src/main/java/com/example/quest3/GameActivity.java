package com.example.quest3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    private TextView textMain;
    private Button mainMenu;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button read;
    protected static final int RESULT_SPEECH = 1;
    public int i;
    public int j;
    //Два массива с текстами сюжета
    private final Object[] Ending = new Object[]{R.string.Endingdeath};
    private final Object[] Arraystories = new Object[]{R.string.s1, R.string.s2, R.string.s3, R.string.s4, R.string.s5, R.string.s6, R.string.s7, R.string.s8, R.string.SLetter, R.string.SBook, R.string.SNote, R.string.s11, R.string.s12, R.string.s13, R.string.s14, R.string.SBathroom, R.string.SKitchen, R.string.s17, R.string.s18, R.string.end};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //обработчики
        setContentView(R.layout.activity_game);
        textMain = findViewById(R.id.textStory);
        mainMenu = findViewById(R.id.mainMenu);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        read = findViewById(R.id.noLook);
        i = 0;
        j = 0;
        //нажатие на экран, смена текста
        textMain.setOnClickListener(view -> {
            try {
                transition(Arraystories[i]);
                i++;
            } catch (Exception e) {
                Log.e("Error", toString());
            }
        });
        pressing();
    }

    // Вид текста, появление различных объектов на разных этапах игры
    public void transition(Object o) {
        textMain.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textMain.setBackgroundResource(R.drawable.blue102);
        textMain.setTextSize(24);
        textMain.setTextColor(Color.WHITE);
        if (i == 3) {
            textMain.setBackgroundResource(R.drawable.bg2); //картинка черепа, ГГ умер
        }
        setMainMenu();
        setButton1();
        setButton2();
        setButton3();
        setRead();
        if (i == 7 || i == 8 || i == 9 || i == 10) { //первое разветвление. на выбор что можно посмотреть. Пересматривать объекты можно много раз
            button1.setVisibility(View.VISIBLE);
            button1.setClickable(true);
            button2.setVisibility(View.VISIBLE);
            button2.setClickable(true);
            button3.setVisibility(View.VISIBLE);
            button3.setClickable(true);
            read.setVisibility(View.VISIBLE);
            read.setClickable(true);
            button1.setTextColor(Color.WHITE);
            button1.setText("Письмо");
            button2.setTextColor(Color.WHITE);
            button2.setText("Записку");
            button3.setTextColor(Color.WHITE);
            button3.setText("Книгу");
            read.setTextColor(Color.WHITE);
            read.setText("Читать дальше");
        }
        if (i == 14) {
            button1.setVisibility(View.VISIBLE);
            button1.setClickable(true);
            button2.setVisibility(View.VISIBLE);
            button2.setClickable(true);
            button1.setTextColor(Color.WHITE);
            button1.setText("На кухню");
            button2.setTextColor(Color.WHITE);
            button2.setText("В ванную");
        }
        if (i == 19) {
            // голосовой обработчик
            Intent intent = new Intent(
                    RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-RUS");
            try {
                startActivityForResult(intent, RESULT_SPEECH);
            } catch (ActivityNotFoundException a) {
                Toast.makeText(getApplicationContext(),
                        "Текст не распознан",
                        Toast.LENGTH_SHORT).show();
            }
        }
        if (i == 19) {
            // при прохождении игры можно вернутся на главный экран
            textMain.setBackgroundResource(R.drawable.map);
            mainMenu.setTextColor(Color.WHITE);
            mainMenu.setText("На главный экран");
            mainMenu.setVisibility(View.VISIBLE);
            mainMenu.setClickable(true);
        }
        textMain.setText((Integer) o);
    }

   // ьетод для кнопок
    public void pressing() {
        mainMenu.setOnClickListener(view -> {
            final Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
        });

        button1.setOnClickListener(view -> {
            if (i == 8) {
                transition(Arraystories[8]);
                setButton1();
            }
            if (i == 15) {
                transition(Arraystories[16]);
                setButton1();
                i = 17;
            }
        });

        button2.setOnClickListener(view -> {
            if (i == 8) {
                transition(Arraystories[10]);
                setButton2();
            }
            if (i == 15) {
                transition(Arraystories[15]);
                setButton1();
                i = 17;
            }
        });


        button3.setOnClickListener(view -> {
            transition(Arraystories[9]);
            setButton3();
        });

        read.setOnClickListener(view -> {
            i = 12;
            transition(Arraystories[11]);
            setRead();
        });

    }

    public void setButton1() {
        button1.setVisibility(View.GONE);
        button1.setClickable(false);
    }

    public void setButton2() {
        button2.setVisibility(View.GONE);
        button2.setClickable(false);
    }

    public void setButton3() {
        button3.setVisibility(View.GONE);
        button3.setClickable(false);
    }

    public void setRead() {
        read.setVisibility(View.GONE);
        read.setClickable(false);
    }

    public void setMainMenu() {
        mainMenu.setVisibility(View.GONE);
        mainMenu.setClickable(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (!text.contains("карта")) {
                        textMain.setText((Integer) Ending[j]);
                        Toast.makeText(getApplicationContext(), "Неправльное слово!", Toast.LENGTH_LONG).show();
                        textMain.setBackgroundResource(R.drawable.blue102);
                    }
                }
                break;
            }
        }
    }


}