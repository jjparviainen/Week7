package com.example.ett15084.texteditor;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    EditText text;
    Context context = null;
    EditText fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        System.out.println("Tiedosto sijaint:" + context.getFilesDir());

        text = findViewById(R.id.editText);
        fileName = findViewById(R.id.fileName);
    }

        public void readFile(View v){
            try {
                DataInputStream textFileStream = new DataInputStream(context.openFileInput(fileName.getText().toString()));
                Scanner scan = new Scanner(textFileStream);
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    text.setText(line);
                }
            }
            catch (IOException e) {
                Log.e("IOException", "Virhe syötteessä/tiedostoa ei löydy");
            }
    }

        public void writeFile(View v) {
            try {
                OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(fileName.getText().toString(), Context.MODE_PRIVATE));
                text = findViewById(R.id.editText);
                osw.write(text.getText().toString());
                osw.close();
            }

            catch (IOException e) {
                Log.e("IOException", "Virhe syötteessä/tiedostoa ei löydy");
            }

        }
}
