package com.example.morsecodeconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextInputLayout tilText;
    TextView tvOutput;
    char[] letters;
    String[] morse_code;
    String except_letter;
    String action;
    ImageView ivCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        letters = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.', ',', '?', '@', '!', '&', '(', ')', '=', '+', '/', ':', '\'', '\"', ' '};
        morse_code = new String[]{".-", "-...", "-.-.", "-..", ".",
                "..-.", "--.", "....", "..", ".---",
                "-.-", ".-..", "--", "-.", "---",
                ".--.", "--.-", ".-.", "...", "-",
                "..-", "...-", ".--", "-..-", "-.--",
                "--..", ".----", "..---", "...--",
                "....--", ".....", "-....", "--..",
                "---..", "----.", "-----", ".-.-.-",
                "--..--", "..--..", ".--.-.", "-.-.--",
                ".-...", "-.--.", "-.--.-", "-...-",
                ".-.-.", "-..-.", "---...", ".----.", ".-..-.", "/"};

        except_letter = "#$<`~[*]{}%^;>";

        tilText = findViewById(R.id.tilText);
        tvOutput = findViewById(R.id.tvOutput);
        ivCopy = findViewById(R.id.ivCopy);

        ivCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Text", tvOutput.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity.this, "Copied Text", Toast.LENGTH_LONG).show();
            }
        });

        action = getIntent().getStringExtra("action");

        if (action.equals("textToMorsecode")) {
            tilText.setHint("Enter text");

            tilText.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    tvOutput.setText(englishTextToMorseCode(letters, morse_code, charSequence.toString()));
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        } else if (action.equals("morsecodeToText")) {
            tilText.setHint("Enter morse code ");

            tilText.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    tvOutput.setText(morseCodeToEnglishText(letters, morse_code, charSequence.toString()));
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

        tvOutput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (tvOutput.getText().toString().isEmpty()) {
                    tvOutput.setText("Output here.....");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public static String englishTextToMorseCode(char[] lettes, String[] morse_code, String str) {
        char[] ch = str.toCharArray();
        String result = "";
        for (int i = 0; i < ch.length; i++) {
            for (int j = 0; j < lettes.length; j++) {
                if (ch[i] == lettes[j]) {
                    result += morse_code[j] + " ";
                }
            }
        }
        return result;
    }

    public static String morseCodeToEnglishText(char[] letters, String[] morse_code, String str) {
        String result = "";
        String[] str1 = str.split(" ");
        for (int i = 0; i < str1.length; i++) {
            for (int j = 0; j < morse_code.length; j++) {
                if (str1[i].equals(morse_code[j])) {
                    result += letters[j];
                }
            }
        }
        return result;
    }
}