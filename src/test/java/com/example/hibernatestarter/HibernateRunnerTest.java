package com.example.hibernatestarter;

import com.example.hibernatestarter.Models.User;

import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.*;

class HibernateRunnerTest {
    @Test
    void checkReflectionAPI() throws IllegalAccessException, SQLException {
        User user = User.builder()
//                .username("Ivan")
//                .lastname("Ivanov")
//                .birthDate(LocalDate.of(2002, 1, 29))
//                .age(20)
                .build();

        String sql = """ 
                insert
                into
                %s
                (%s)
                values
                (%s)
                """;
        String tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))//стягиваем значение в аннотации table класса user
                .map(tableAnnotation -> tableAnnotation.schema() + " " + tableAnnotation.name())
                .orElse(user.getClass().getName());


        Field[] declaredFields = user.getClass().getDeclaredFields();//задекларированные поля
        String columnNames = Arrays.stream(declaredFields)//собирает все элементы потока в одну строку разделяя ,
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName()))
                .collect(joining(", "));

        String columnValues = Arrays.stream(declaredFields)
                .map(field -> "?")//мапим количество наших зареганых филдов в количество вопросов в запросе
                .collect(Collectors.joining(", "));

        System.out.println(sql.formatted(tableName,columnNames,columnValues));//отформатированный sql запрос

        Connection connection = null;
        PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName,columnNames,columnValues));
        for(Field declaratedField : declaredFields){
            declaratedField.setAccessible(true);
            preparedStatement.setObject(1,declaratedField.get(user));
        }

    }


}