package com.example.proiectbanca;

public class Clienti {
    private String cnp;
    private String username;
    private String password;
    private String soldRON;
    private String soldEURO;

    public String getCnp() {
        return cnp;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSoldRON() {
        return soldRON;
    }

    public String getSoldEURO() {
        return soldEURO;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSoldRON(String soldRON) {
        this.soldRON = soldRON;
    }

    public void setSoldEURO(String soldEURO) {
        this.soldEURO = soldEURO;
    }

    public Clienti( String username, String soldRON, String soldEURO) {
        this.username = username;
        this.soldRON = soldRON;
        this.soldEURO = soldEURO;
    }
}
