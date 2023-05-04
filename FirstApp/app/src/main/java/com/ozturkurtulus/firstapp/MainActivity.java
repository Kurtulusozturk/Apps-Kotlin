package com.ozturkurtulus.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //onCreate uygulama başlamadan çalışan class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //View yazdığımız zaman xml dosyası ile haberleşir. Kırmızı ise alt+enter ile import et. View sınıf view obj ismi.
    public void changeImage(View view) {
        ImageView imageView = findViewById(R.id.imageView); //imageview classından bir obj tanımlayıp bizim xml dosyasında ki imageview eşitliyoruz
        imageView.setImageResource(R.drawable.views2); // imageview görselini şu yap
    }

}