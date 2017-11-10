package com.example.aincrad.akasztfa;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button buttonPlus, buttonMinus, buttonTippel;
    private TextView textViewBetu, textViewKitalalando;
    private ImageView img_Akasztofa;
    private int betu;
    private AlertDialog.Builder szeretneUjJatekot;
    private String szoveg;
    private String kitalalando;
    private int probalkozasokSzama =13;

    String[] quotes = new String[] {"KANCSO", "AUTO", "BEKA","KEDVES","HAL","KIFLI","KALAP","MACSKA","KRUMPLI","AKASZTOFA"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        ujJatek();
    }

    private void ujJatek(){

        this.img_Akasztofa.setImageResource(R.drawable.akasztofa00);
        this.probalkozasokSzama = 13;
        this.szoveg = quotes[(int) (Math.random() * quotes.length)];
        Toast.makeText(getApplicationContext(),szoveg+"",Toast.LENGTH_LONG).show();

        this.betu = 65;
        this.textViewBetu.setText(String.valueOf((char)betu));
        int i =0;
        this.kitalalando ="";
        while (i < szoveg.length()){
            this.kitalalando+="_ ";
            i++;
        }
        this.textViewKitalalando.setText(kitalalando+"");

    }

    private void Init(){

        this.img_Akasztofa = (ImageView)findViewById(R.id.imageView);

        this.buttonMinus = (Button)findViewById(R.id.button_Minus);
        this.buttonPlus = (Button)findViewById(R.id.button_Plus);
        this.buttonTippel = (Button)findViewById(R.id.button_Tippel);

        this.textViewBetu = (TextView)findViewById(R.id.textView_Betu);
        this.textViewKitalalando = (TextView)findViewById(R.id.textView_Ktalalando);



        this.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnclick(v,-1);

            }
        });



        this.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnclick(v,+1);

            }
        });



        this.buttonTippel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (talalatE()){
                    talalatokCsereje();
                    ellenorzes();
                }else {
                    if (probalkozasokSzama>0) {
                        lekapcsol(probalkozasokSzama--);
                    }else {szeretneUjJatekot.setTitle("Ez most nem jött össze!").show();}
                }

            }
        });


        this.szeretneUjJatekot = new AlertDialog.Builder(MainActivity.this);
        this.szeretneUjJatekot.setMessage("Újra játszanál?");

        this.szeretneUjJatekot.setPositiveButton("Igen", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                ujJatek();

            }
        });
        this.szeretneUjJatekot.setNegativeButton("Nem", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                finish();

            }
        });
        this.szeretneUjJatekot.setCancelable(false);
        this.szeretneUjJatekot.create();

    }
    public void ellenorzes(){

        int i =0;
        String seged="";
        char[] kitalalandoTomb = kitalalando.toCharArray();
        while (i < szoveg.length()) {
            seged+= kitalalandoTomb[(i * 2)];
            i++;
        }

        if (szoveg.equals(seged)){
            szeretneUjJatekot.setTitle("Gratulálok! Nyertél!").show();
        }
    }

    private void lekapcsol(int i){
        switch (i){
            case 13: img_Akasztofa.setImageResource(R.drawable.akasztofa01); break;
            case 12: img_Akasztofa.setImageResource(R.drawable.akasztofa02);break;
            case 11: img_Akasztofa.setImageResource(R.drawable.akasztofa03);break;
            case 10: img_Akasztofa.setImageResource(R.drawable.akasztofa04);break;
            case 9: img_Akasztofa.setImageResource(R.drawable.akasztofa05);break;
            case 8: img_Akasztofa.setImageResource(R.drawable.akasztofa06);break;
            case 7: img_Akasztofa.setImageResource(R.drawable.akasztofa07); break;
            case 6: img_Akasztofa.setImageResource(R.drawable.akasztofa08);break;
            case 5: img_Akasztofa.setImageResource(R.drawable.akasztofa09);break;
            case 4: img_Akasztofa.setImageResource(R.drawable.akasztofa10);break;
            case 3: img_Akasztofa.setImageResource(R.drawable.akasztofa11);break;
            case 2: img_Akasztofa.setImageResource(R.drawable.akasztofa12);break;
            case 1: img_Akasztofa.setImageResource(R.drawable.akasztofa13); break;

        }
    }

    private boolean talalatE(){
        int i =0;
        while (i < szoveg.length() && (char)this.betu != szoveg.charAt(i)){
            i++;
        }
        if (i<szoveg.length()){
            return true;
        }else{
            return false;
        }
    }
    public void talalatokCsereje(){
        int i =0;
        while (i < szoveg.length()){
            if (szoveg.charAt(i)==(char)betu){

                char[] kitalalandoTomb = kitalalando.toCharArray();
                kitalalandoTomb[i*2] = (char)betu;
                kitalalando = String.valueOf(kitalalandoTomb);

            }
            i++;
        }
        this.textViewKitalalando.setText(kitalalando+"");

    }

    public void buttonOnclick(View v, int i){
        int asciiErtek = betu +i;
        if (asciiErtek<65){

            betu=90;

        }else if (asciiErtek>90){

            betu=65;
        }else {
            betu=asciiErtek;
        }
        this.textViewBetu.setText(String.valueOf((char)betu));
    }
}
