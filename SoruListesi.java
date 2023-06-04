package com.zulalvarol.bilgioyunu;

public class SoruListesi {
    private String sorular, secenekA, secenekB, secenekC, secenekD, cevap;
    private String kullaniciSecimi;

    public SoruListesi(String sorular, String secenekA, String secenekB, String secenekC, String secenekD, String cevap, String kullaniciSecimi) {
        this.sorular = sorular;
        this.secenekA = secenekA;
        this.secenekB = secenekB;
        this.secenekC = secenekC;
        this.secenekD = secenekD;
        this.cevap = cevap;
        this.kullaniciSecimi = kullaniciSecimi;
    }

    public String getSorular() {
        return sorular;
    }

    public String getSecenekA() {
        return secenekA;
    }

    public String getSecenekB() {
        return secenekB;
    }

    public String getSecenekC() {
        return secenekC;
    }

    public String getSecenekD() {
        return secenekD;
    }

    public String getCevap() {
        return cevap;
    }

    public void setKullaniciSecimi(String kullaniciSecimi) {
        this.kullaniciSecimi = kullaniciSecimi;
    }

    public String getKullaniciSecimi() {
        return kullaniciSecimi;
    }
}
