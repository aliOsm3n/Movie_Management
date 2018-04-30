package com.example.aliothman.movie_management;

public class Movie {
    int	id;
    String img ;
    String tittle;
    String date ;

    public Movie(int id, String img, String tittle, String date ) {
        this.id = id;
        this.img = img;
        this.tittle = tittle;
        this.date = date;
    }

    public Movie( String img, String tittle, String date ) {
        this.img = img;
        this.tittle = tittle;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getTittle() {
        return tittle;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
