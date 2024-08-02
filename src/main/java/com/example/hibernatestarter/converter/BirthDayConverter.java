package com.example.hibernatestarter.converter;

import com.example.hibernatestarter.Models.BirthDay;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.util.Optional;

@Converter(autoApply = true)//помечаем как конвертер к использованию
public class BirthDayConverter implements AttributeConverter<BirthDay, Date>{
    @Override
    public Date convertToDatabaseColumn(BirthDay birthDay) {
        return Optional.ofNullable(birthDay)
                .map(BirthDay::birthDate)
                .map(Date::valueOf)
                .orElse(null);
    }

    @Override
    public BirthDay convertToEntityAttribute(Date date) {
        return Optional.ofNullable(date)//каждый этап если мы получаем результат вызываем следующий этап
                .map(Date::toLocalDate)//например здесь на объекте Date мы вызываем метод toLocalDate в который передаем полученное на прошлом шаге
                .map(BirthDay::new)
                .orElse(null);
    }
}