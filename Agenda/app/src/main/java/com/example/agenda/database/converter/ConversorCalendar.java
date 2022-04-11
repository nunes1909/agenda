package com.example.agenda.database.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConversorCalendar {

    @TypeConverter
    public Long paraLong(Calendar momento) {
        if (momento != null){
            return momento.getTimeInMillis();
        }
        return null;
    }

    @TypeConverter
    public Calendar paraCalendar(Long momento) {
        Calendar momentoAtual = Calendar.getInstance();
        if (momento != null) {
            momentoAtual.setTimeInMillis(momento);
        }
        return momentoAtual;
    }
}
