package com.zulalvarol.bilgioyunu;

import java.util.ArrayList;
import java.util.List;

public class SoruBankasi {

    private static List<SoruListesi> sorular(){
        final List<SoruListesi> sorulistesi = new ArrayList<>();

        final SoruListesi soru1 = new SoruListesi("Bilgiler geçici olarak hangi bellek üzerinde tutulur?", "Memory Card", "Hard Disk", "Rom", "Ram", "Ram", "");
        final SoruListesi soru2 = new SoruListesi("Aşağıdakilerden hangisi ana donanım birimi değildir?", "Anakart", "Ram", "Ekran Kartı", "CD Rom", "CD Rom", "");

        sorulistesi.add(soru1);
        sorulistesi.add(soru2);

        return sorulistesi;
    }

    /*public static String getSorular(String sorular) {
        return sorular;
    }*/

    //public static List<SoruListesi> getSorular(String basla) {
        //return sorular();
    //}




    public static List<SoruListesi> getSorular() {
        return sorular();
    }
}
