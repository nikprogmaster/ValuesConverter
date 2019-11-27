package com.example.valuesconverter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public enum Values {

    SQUARE(R.string.square, Arrays.asList(Unit.SQ_CENTIMETRES, Unit.SQ_KILOMETRES, Unit.SQ_METRES)),
    CURRENCY(R.string.currency, Collections.<Unit>emptyList()),
    LENGTH(R.string.length, Arrays.asList(Unit.KILOMETRE, Unit.METRE, Unit.CENTIMETRE, Unit.MILLIMETRE)),
    SPEED(R.string.speed, Arrays.asList(Unit.METREPERSEC, Unit.KMPERHOUR, Unit.MILLSPERHOUR)),
    WEIGHT(R.string.weight, Arrays.asList(Unit.KILLOGRAMM, Unit.GRAMM, Unit.FUNT)),
    COOKING(R.string.cooking, Collections.<Unit>emptyList()),
    DATA(R.string.data, Collections.<Unit>emptyList()),
    ENERGY(R.string.energy, Collections.<Unit>emptyList()),
    FUEL(R.string.fuel, Collections.<Unit>emptyList()),
    PRESSURE(R.string.pressure, Collections.<Unit>emptyList());

    @StringRes
    private final int mTitleStringResourceId;

    private final List<Unit> units;

    Values(@StringRes int titleStringResourceId, List<Unit> units) {
        mTitleStringResourceId = titleStringResourceId;
        this.units = units;
    }

    /**
     * Каким строковым ресурсом можно отобразить пользователю соответствующую константу
     */
    @StringRes
    public int getTitleStringResourceId() {
        return mTitleStringResourceId;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public double calculate (Context context, String from, String to, double value){
        Unit fromUnit = this.searchByStr(context, from);
        Unit toUnit = this.searchByStr(context, to);
        double result = 0.0;
        if (fromUnit!=null && toUnit!=null){
            result = value*fromUnit.getConversionToBase()*toUnit.getConbersionFromBase();
        }
        return result;
    }

    public double calculateRever (Context context, String from, String to, double value){
        Unit fromUnit = this.searchByStr(context, from);
        Unit toUnit = this.searchByStr(context, to);
        double result = 0.0;
        if (fromUnit!=null && toUnit!=null){
            result = value*fromUnit.getConbersionFromBase()*toUnit.getConversionToBase();
        }
        return result;
    }

    @Nullable
    public Unit searchByStr(Context context, String s){
        for (Unit i: this.getUnits()){
            if (s.equals(context.getString(i.getLabelRecourse()))){
                return i;
            }
        }
        return null;
    }
}