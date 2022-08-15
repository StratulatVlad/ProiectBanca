package com.example.proiectbanca;

public class Tranzactii {
    private String id;
    private String cnp;
    private String tip_tranzactie;
    private String suma;
    private String valuta;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getTip_tranzactie() {
        return tip_tranzactie;
    }

    public void setTip_tranzactie(String tip_tranzactie) {
        this.tip_tranzactie = tip_tranzactie;
    }

    public String getSuma() {
        return suma;
    }

    public void setSuma(String suma) {
        this.suma = suma;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public Tranzactii( String tip_tranzactie, String suma, String valuta) {
        this.tip_tranzactie = tip_tranzactie;
        this.suma = suma;
        this.valuta = valuta;
    }
    public Tranzactii(String cnp, String tip_tranzactie, String suma, String valuta) {
        this.cnp = cnp;
        this.tip_tranzactie = tip_tranzactie;
        this.suma = suma;
        this.valuta = valuta;
    }
}
