package swingmvclab;

import java.io.Serializable;

/*
 * Egy hallgató adatait tároló osztály.
 * 
 * NE MÓDOSÍTSD!
 */
public class Student implements Serializable {

    // Hallgató neve
    private String name;

    // Hallgató Neptun-kódja.
    private String neptun;

    // Van-e aláírása.
    private boolean signature;

    // A megszerzett osztályzat.
    private int grade;

    // Hallgató nevének lekérdezése. 
    public String getName() {
        return name;
    }
    
    // Hallgató nevének beállítása.
    public void setName(String name) {
        this.name = name;
    }
    
    // Hallgató Neptun-kódjának lekérdezése.
    public String getNeptun() {
        return neptun;
    }
    
    // Hallgató Neptun-kódjának beállítása.
    public void setNeptun(String neptun) {
        this.neptun = neptun;
    }
    
    // Aláírás meglétének lekérdezése.
    public Boolean hasSignature() {
        return signature;
    }
    
    // Aláírás meglétének beállítása.
    public void setSignature(Boolean signature) {
        this.signature = signature;
    }

    // Osztályzat lekérdezése.
    public Integer getGrade() {
        return grade;
    }
    
    // Osztályzat beállítása.
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    // Hallgató létrehozása
    public Student(String name, String neptun, Boolean signature, Integer grade) {
        this.name = name;
        this.neptun = neptun;
        this.signature = signature;
        this.grade = grade;
    }
    
}
