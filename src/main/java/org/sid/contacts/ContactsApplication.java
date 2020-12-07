package org.sid.contacts;

import org.sid.contacts.dao.ContactRepository;
import org.sid.contacts.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class ContactsApplication implements CommandLineRunner {
    @Autowired
    private ContactRepository contactRepository;
    public static void main(String[] args) {
        SpringApplication.run(ContactsApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        contactRepository.save(new Contact("Hassani","Mohammed",df.parse("01/01/2001"),"has@gmail.com",777748731,"mphoto.jpeg"));
        contactRepository.save(new Contact("Diallo","Oumar",df.parse("11/02/2003"),"ou@gmail.com",787748731,"ophoto.jpeg"));
        contactRepository.save(new Contact("Hathie","Dabakh",df.parse("28/08/2000"),"dab@gmail.com",767748731,"dphoto.jpeg"));
        contactRepository.findAll().forEach(c->{
            System.out.println(c.getNom());
        });
    }
}
