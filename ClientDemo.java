
package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Date;
import java.util.Scanner;

public class ClientDemo {

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Course.class);

        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Scanner sc = new Scanner(System.in);

        System.out.println("1. Insert Course");
        System.out.println("2. View Course by ID");
        int choice = sc.nextInt();

        if(choice == 1) {
            sc.nextLine();
            System.out.println("Enter Course Name:");
            String name = sc.nextLine();

            System.out.println("Enter Description:");
            String desc = sc.nextLine();

            System.out.println("Enter Status:");
            String status = sc.nextLine();

            Course c = new Course(name, desc, new Date(), status);
            session.save(c);
            System.out.println("Course Inserted Successfully");
        }
        else if(choice == 2) {
            System.out.println("Enter Course ID:");
            int id = sc.nextInt();

            Course c = session.get(Course.class, id);

            if(c != null) {
                System.out.println("ID: " + c.getId());
                System.out.println("Name: " + c.getName());
                System.out.println("Description: " + c.getDescription());
                System.out.println("Date: " + c.getDate());
                System.out.println("Status: " + c.getStatus());
            } else {
                System.out.println("Course Not Found");
            }
        }

        tx.commit();
        session.close();
        factory.close();
    }
}
