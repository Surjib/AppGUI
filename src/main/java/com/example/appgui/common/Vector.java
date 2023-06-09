package com.example.appgui.common;

/** Вектор*/
public class Vector extends GenDataObject {

    private AnalogueValue mag = new AnalogueValue(); // Амплитуда

    private AnalogueValue ang = new AnalogueValue(); // Угол


    /* Метод, определяющий длину и угол вектора из известных проекций*/






    public AnalogueValue getMag() {
        return mag;
    }

    public void setMag(AnalogueValue mag) {
        this.mag = mag;
    }

    public AnalogueValue getAng() {
        return ang;
    }

    public void setAng(AnalogueValue ang) {
        this.ang = ang;
    }
}
