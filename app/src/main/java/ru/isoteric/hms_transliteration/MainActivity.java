package ru.isoteric.hms_transliteration;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    TextView setTransliteration;

    String newEditTextStr;
    String oldEditTextStr;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeNoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        editText =  findViewById(R.id.editText);
        editText.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent event) {
                // TODO Auto-generated method stub
                if (view.getId() ==R.id.editText) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction()&MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_UP:
                            view.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });

        setTransliteration = findViewById(R.id.setTransliteration);
        setTransliteration.setVisibility(View.INVISIBLE);

    }


    public void onClickConvertBtn(View view)
    {
        if (editText.getText().toString().equals("")) {
            setTransliteration.setVisibility(View.INVISIBLE);
            Toast.makeText(this, R.string.enter_text, Toast.LENGTH_SHORT).show();
        } else {
            theConverter();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            startActivity(new Intent(getApplicationContext(), AboutAppActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


    public void theConverter (){

        oldEditTextStr = editText.getText().toString();

        newEditTextStr = oldEditTextStr
                .replace('қ','к')
                .replace('ә','а')
                .replace('і','i')
                .replace('ң','н')
                .replace('ғ','г')
                .replace('ү','у')
                .replace('ұ','у')
                .replace('ө','о')
                .replace('һ','х')
                .replace('Қ','К')
                .replace('Ә','А')
                .replace('І','I')
                .replace('Ң','Н')
                .replace('Ғ','Г')
                .replace('Ү','У')
                .replace('Ұ','У')
                .replace('Ө','О')
                .replace('Һ','Х');

        setTransliteration.setVisibility(View.VISIBLE);
        setTransliteration.setText(newEditTextStr);

        //Копирование текста в буфер обмена при нажатии на текст
        setTransliteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("",newEditTextStr);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getApplicationContext(), R.string.text_is_copied_to_the_clipboard, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
