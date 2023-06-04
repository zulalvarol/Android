package com.zulalvarol.bilgioyunu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SoruActivity extends AppCompatActivity {
    private TextView soruSayisi;
    private TextView sorular;
    private AppCompatButton secenekA, secenekB, secenekC, secenekD;
    private  AppCompatButton btnSonraki = findViewById(R.id.btnSonraki);
    private Timer sure;
    private int toplamSure = 1;
    private  int saniye = 0;
    private List<SoruListesi> sorulistesi;
    private int gecerliSoru = 0;
    private String kullanicininsectigi = "";

    final Button baslaBtn = findViewById(R.id.baslaBtn);

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soru);

        final TextView sure = findViewById(R.id.timer);
        //final TextView basla = findViewById(R.id.baslaBtn);

        final String[] getSecilen = {getIntent().getStringExtra("Başla")};

        soruSayisi = findViewById(R.id.soruSayisi);
        sorular = findViewById(R.id.sorular);
        secenekA = findViewById(R.id.secenekA);
        secenekB = findViewById(R.id.secenekB);
        secenekC = findViewById(R.id.secenekC);
        secenekD = findViewById(R.id.secenekD);
        btnSonraki = findViewById(R.id.btnSonraki);

        sorulistesi = SoruBankasi.getSorular();

        soruSayisi.setText((gecerliSoru+1) + "/" + sorulistesi.size());
        sorular.setText(sorulistesi.get(0).getSorular());
        secenekA.setText(sorulistesi.get(0).getSecenekA());
        secenekB.setText(sorulistesi.get(0).getSecenekB());
        secenekC.setText(sorulistesi.get(0).getSecenekC());
        secenekD.setText(sorulistesi.get(0).getSecenekD());


        startTimer(sure);
        //sorular.setText((getCurrentFocus().getId()) + "/" + SoruListesi.size());

        secenekA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kullanicininsectigi.isEmpty()){
                    kullanicininsectigi = secenekA.getText().toString();
                    tiklanan();
                    sorulistesi.get(gecerliSoru).setKullaniciSecimi(kullanicininsectigi);
                }
            }
        });

        secenekB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kullanicininsectigi.isEmpty()){
                    kullanicininsectigi = secenekB.getText().toString();
                    tiklanan();
                    sorulistesi.get(gecerliSoru).setKullaniciSecimi(kullanicininsectigi);
                }
            }
        });

        secenekC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kullanicininsectigi.isEmpty()){
                    kullanicininsectigi = secenekC.getText().toString();
                    tiklanan();
                    sorulistesi.get(gecerliSoru).setKullaniciSecimi(kullanicininsectigi);
                }
            }
        });

        secenekD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kullanicininsectigi.isEmpty()){
                    kullanicininsectigi = secenekD.getText().toString();
                    tiklanan();
                    sorulistesi.get(gecerliSoru).setKullaniciSecimi(kullanicininsectigi);
                }

            }
        });

        btnSonraki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soruDegistir();
            }
        });
    }

    private void soruDegistir(){
        gecerliSoru++;
        if (gecerliSoru+1 == sorulistesi.size()){
            btnSonraki.setText("Sınavı Göneder");
        }

        if (gecerliSoru < sorulistesi.size()){
            kullanicininsectigi = "";
            secenekA.setBackground(Drawable.createFromPath("#00FF00"));
            secenekB.setBackground(Drawable.createFromPath("#00FF00"));
            secenekC.setBackground(Drawable.createFromPath("#00FF00"));
            secenekD.setBackground(Drawable.createFromPath("#00FF00"));

            soruSayisi.setText((gecerliSoru+1) + "/" + sorulistesi.size());
            sorular.setText(sorulistesi.get(gecerliSoru).getSorular());
            secenekA.setText(sorulistesi.get(gecerliSoru).getSecenekA());
            secenekB.setText(sorulistesi.get(gecerliSoru).getSecenekB());
            secenekC.setText(sorulistesi.get(gecerliSoru).getSecenekC());
            secenekD.setText(sorulistesi.get(gecerliSoru).getSecenekD());

        }
        else {
            Intent intent = new Intent(SoruActivity.this, SinavSonuclari.class);
            intent.putExtra("doğru: ", getDogruCevap());
            intent.putExtra("yanlış: ", getYanlisCevap());
            startActivity(intent);
            finish();
        }
    }

    private void startTimer(TextView timerTextView){
        sure = new Timer();
        sure.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(saniye == 0){
                    toplamSure--;
                    saniye = 59;
                }
                else if (saniye == 0 && toplamSure == 0){
                    sure.purge();
                    sure.cancel();
                    Toast.makeText(SoruActivity.this, "zaman doldu", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SoruActivity.this, SinavSonuclari.class);
                    intent.putExtra("Doğru", getDogruCevap());
                    intent.putExtra("Yanlış Cevap", getYanlisCevap());
                    startActivity(intent);
                    finish();
                }
                else{
                    saniye--;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String sonDakika = String.valueOf(toplamSure);
                        String sonSaniye = String.valueOf(saniye);

                        if (sonDakika.length() == 1){
                            sonDakika = "0" + sonDakika;
                        }
                        if(sonSaniye.length() == 1){
                            sonSaniye = "0" + sonSaniye;
                        }
                        timerTextView.setText(sonDakika + ":" +sonSaniye);
                    }
                });
            }
        }, 1000, 1000);
    }

    private int getDogruCevap(){
        int dogruCevap = 0;
        for (int i=0; i<sorulistesi.size(); i++){
            final String getKullaniciSecimi = sorulistesi.get(i).getKullaniciSecimi();
            final String getCevap = sorulistesi.get(i).getCevap();

            if(getKullaniciSecimi.equals(getCevap)){
                dogruCevap++;
            }
        }
        return dogruCevap;
    }

    private int getYanlisCevap(){
        int yanlisCevap = 0;
        for (int i=0; i<sorulistesi.size(); i++){
            final String getKullaniciSecimi = sorulistesi.get(i).getKullaniciSecimi();
            final String getCevap = sorulistesi.get(i).getCevap();
            if(!getKullaniciSecimi.equals(getCevap)){
                yanlisCevap++;
            }
        }
        return yanlisCevap;
    }

    @Override
    public void onBackPressed() {
        sure.purge();
        sure.cancel();
        startActivity(new Intent(SoruActivity.this, MainActivity.class));
        finish();
    }

    private void tiklanan(){
        final String getCevap = sorulistesi.get(gecerliSoru).getCevap();

        if (secenekA.getText().toString().equals(getCevap)){
            secenekA.setBackground(Drawable.createFromPath("#00FF00"));
        }
        if (secenekB.getText().toString().equals(getCevap)){
            secenekA.setBackground(Drawable.createFromPath("#00FF00"));
        }
        if (secenekC.getText().toString().equals(getCevap)){
            secenekA.setBackground(Drawable.createFromPath("#00FF00"));
        }
        if (secenekD.getText().toString().equals(getCevap)){
            secenekA.setBackground(Drawable.createFromPath("#00FF00"));
        }
    }

}