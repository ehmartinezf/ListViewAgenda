package com.example.listviewagenda;

public class Contacte {

    private int id;
    private String nom;
    private String cognom;
    private String adreca;
    private String telefon;
    private String email;
    private String dataNaixement;
    private String empresa;
    private String notes;

    public Contacte(int id, String nom, String cognom, String adreca, String telefon, String email, String dataNaixement, String empresa, String notes) {

        this.id = id;
        this.nom = nom;
        this.cognom = cognom;
        this.adreca = adreca;
        this.telefon = telefon;
        this.email = email;
        this.dataNaixement = dataNaixement;
        this.empresa = empresa;
        this.notes = notes;
    }

    public Contacte(String nom, String cognom, String adreca, String telefon, String email, String dataNaixement, String empresa, String notes) {
        this(0, nom, cognom, adreca, telefon, email, dataNaixement, empresa, notes);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(String dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return nom + " " + cognom;
    }
}
