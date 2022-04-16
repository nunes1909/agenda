package com.example.agenda.database.converter;

import androidx.room.TypeConverter;

import com.example.agenda.model.TipoTelefone;

public class ConversorTipoTelefone {

    @TypeConverter
    public String paraString(TipoTelefone tipo){
        return tipo.name();
    }

    @TypeConverter
    public TipoTelefone paraTelefone(String tipo){
        if (tipo != null){
            return TipoTelefone.valueOf(tipo);
        }
        return TipoTelefone.FIXO;
    }
}
