package com.example.appgui.logicalnodes.common;


import com.example.appgui.common.GenDataObject;

/** Абстрактный класс для создания узлов **/
public abstract class LN extends GenDataObject {


    /** Основная функция узла **/
    public abstract void process();
}
