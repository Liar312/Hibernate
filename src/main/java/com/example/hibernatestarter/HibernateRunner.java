package com.example.hibernatestarter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HibernateRunner {
    public static void main(String[] args) throws SQLException {
//        Connection connection=
//                DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Nthvbynjh2015");
        Configuration configuration = new Configuration();
        configuration.configure();//объявляем конфигурационный файл по умолчанию будет искаться в resources

        try(SessionFactory sessionFactory = configuration.buildSessionFactory()){//создаем factory только одну на все приложение
            Session session = sessionFactory.openSession();
            System.out.println("ok");//равносильно закомментированному выше коду но представляет больший рабочий функционал

        }
    }
}
