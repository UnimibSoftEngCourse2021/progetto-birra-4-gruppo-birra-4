package com.example.brewdayapplication;

public class Note {
    private String testoProblemi;
    private String testoUtenti;

    public Note(String testoProblemi, String testoUtenti) {
        this.testoProblemi = testoProblemi;
        this.testoUtenti = testoUtenti;
    }

    public String getTestoProblemi() {
        return testoProblemi;
    }

    public void setTestoProblemi(String testoProblemi) {
        this.testoProblemi = testoProblemi;
    }

    public String getTestoUtenti() {
        return testoUtenti;
    }

    public void setTestoUtenti(String testoUtenti) {
        this.testoUtenti = testoUtenti;
    }

}
