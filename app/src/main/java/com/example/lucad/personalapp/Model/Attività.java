package com.example.lucad.personalapp.Model;

import com.example.lucad.personalapp.Model.Enum.Tipo;

/**
 * Created by LucaD on 05/03/2017.
 */

public class Attività {
    private int ID;
    private String tipo;
    private String data;
    private String appunti;
    private int saldo;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTipo() {
       return tipo;
    }

    public void setTipo(String tipo) {
       this.tipo=tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAppunti() {
        return appunti;
    }

    public void setAppunti(String appunti) {
        this.appunti = appunti;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
