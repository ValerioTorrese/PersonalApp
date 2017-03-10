package com.example.lucad.personalapp.Model;


import org.json.JSONException;
import org.json.JSONObject;

public  class Students{
    private String username;
    private String password;
    private String email;
    private String nome;
    private String url;

    private static final String KEY_USER="Username";
    private static final String KEY_PASSWORD="Password";
    private static final String KEY_NOME="nome";
    private static final String KEY_EMAIL="email";
    private static final String KEY_AVATAR="avatar";

    public Students(JSONObject json){
        try {
            nome=json.getString(KEY_NOME);
            email=json.getString(KEY_EMAIL);
            username=json.getString(KEY_USER);
            password=json.getString(KEY_PASSWORD);
            url=json.getString(KEY_AVATAR);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
