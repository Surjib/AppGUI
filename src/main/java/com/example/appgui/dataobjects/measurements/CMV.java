package com.example.appgui.dataobjects.measurements;


import com.example.appgui.common.GenDataObject;
import com.example.appgui.common.Quality;
import com.example.appgui.common.Timestamp;
import com.example.appgui.common.Vector;

/** Комплексное измеряемое значение **/
public class CMV extends GenDataObject {

    private Vector cVal = new Vector();

    private Quality q = new Quality();

    private Timestamp t = new Timestamp();



    public Vector getcVal() {
        return cVal;
    }

    public void setcVal(Vector cVal) {
        this.cVal = cVal;
    }

    public Quality getQ() {
        return q;
    }

    public void setQ(Quality q) {
        this.q = q;
    }

    public Timestamp getT() {
        return t;
    }

    public void setT(Timestamp t) {
        this.t = t;
    }
}


