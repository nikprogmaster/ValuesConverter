package com.example.valuesconverter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

public enum Unit {
    SQ_KILOMETRES(R.string.sq_kilom, 1000000.0, 0.000001),
    SQ_METRES(R.string.sq_metres, 1.0, 1.0),
    SQ_CENTIMETRES(R.string.sq_centimetres, 0.0001, 10000.0),
    KILOMETRE(R.string.kilometr, 1000.0, 0.001),
    METRE(R.string.metr, 1.0, 1.0),
    CENTIMETRE(R.string.centimetr, 0.01, 100.0),
    MILLIMETRE(R.string.millimetr, 0.001, 1000),
    METREPERSEC(R.string.mpS, 1.0, 1.0),
    KMPERHOUR(R.string.kmpS,  0.277778,3.6 ),
    MILLSPERHOUR(R.string.milpS, 1.60934,0.621),
    KILLOGRAMM(R.string.killo, 1.0, 1.0),
    GRAMM(R.string.gramm, 0.001, 1000),
    FUNT(R.string.funt, 0.454, 2.20462);


    @StringRes
    private int labelRecourse;
    private double conversionToBase;
    private double conbersionFromBase;

    Unit(@StringRes int labelRecourse, double conversionToBase, double conbersionFromBase) {
        this.labelRecourse = labelRecourse;
        this.conbersionFromBase = conbersionFromBase;
        this.conversionToBase = conversionToBase;
    }

    public int getLabelRecourse() {
        return labelRecourse;
    }

    public double getConversionToBase() {
        return conversionToBase;
    }

    public double getConbersionFromBase() {
        return conbersionFromBase;
    }


}
