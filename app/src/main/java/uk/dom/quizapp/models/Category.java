package uk.dom.quizapp.models;

import android.graphics.Bitmap;

/**
 * Created by Dom on 05/12/2016.
 */
public class Category {


    private int id;
    private String name;
    private int image;
    private int correctAnswerTotal;
    private int wrongAnswerTotal;
    private boolean isLocked;

    public Category(String name, int image, int id, boolean isLocked) {
        this.id = id;
        this.name = name;
        this.image = image;
        setLocked(isLocked);
        setCorrectAnswerTotal(0);
        setWrongAnswerTotal(0);
    }

    public Category(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getCorrectAnswerTotal() {
        return correctAnswerTotal;
    }

    public void setCorrectAnswerTotal(int correctAnswerTotal) {
        this.correctAnswerTotal = correctAnswerTotal;
    }

    public int getWrongAnswerTotal() {
        return wrongAnswerTotal;
    }

    public void setWrongAnswerTotal(int wrongAnswerTotal) {
        this.wrongAnswerTotal = wrongAnswerTotal;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
