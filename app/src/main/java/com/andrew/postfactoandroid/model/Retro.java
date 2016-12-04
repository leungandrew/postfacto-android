package com.andrew.postfactoandroid.model;

import java.io.Serializable;
import java.util.List;

public class Retro implements Serializable {

    public String id;
    public String name;
    public List<RetroItem> items;

    public enum Category {
        HAPPY,
        MEH,
        SAD
    }
}
