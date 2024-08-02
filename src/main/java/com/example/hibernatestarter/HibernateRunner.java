package com.example.hibernatestarter;

import com.example.hibernatestarter.Models.Role;
import com.example.hibernatestarter.Models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) throws SQLException {
//        Connection connection=
//                DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Nthvbynjh2015");
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);//мапим сущность
        configuration.configure();//объявляем конфигурационный файл по умолчанию будет искаться в resources

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {//создаем factory только одну на все приложение
            Session session = sessionFactory.openSession();//равносильно закомментированному выше коду но представляет больший рабочий функционал
            session.beginTransaction();//вручуную должны открывать и закрывать транзакции

            User user = User.builder()
                    .username("Ivan")
                    .lastname("Ivanov")
                    .birthDate(LocalDate.of(2002, 1, 29))
                    .age(20)
                    .role(Role.ADMIN)
                    .build();
            session.save(user);
            session.getTransaction().commit();//rollback для отката
        }
    }
}
