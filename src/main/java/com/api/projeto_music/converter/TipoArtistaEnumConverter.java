package com.api.projeto_music.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.api.projeto_music.enums.TipoArtistaEnum;


@Converter(autoApply = true)
public class TipoArtistaEnumConverter implements AttributeConverter<TipoArtistaEnum, String> {

    @Override
    public String convertToDatabaseColumn(TipoArtistaEnum attribute) {
     return attribute.getTipo();  
    }

    @Override
    public TipoArtistaEnum convertToEntityAttribute(String value) {
        if (value==null) {
            return null;
        }
        for(TipoArtistaEnum tipo : TipoArtistaEnum.values()) {
            if (tipo.getTipo().equals(value)) {
                return tipo;
            }
        }
       throw new IllegalArgumentException("O Valor " + value + " não é um tipo de artista válido"); 
    }
    
}
