package org.sid.contacts.web;

import org.sid.contacts.dao.ContactRepository;
import org.sid.contacts.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("*")
public class ContactRestService {
    @Autowired
    private ContactRepository contactRepository;
    //@RequestMapping(value = "/contacts",method = RequestMethod.GET) ou ce qui suit à la ligne
    @GetMapping(value = "/contacts")
    public List<Contact> getContacts(){
        return contactRepository.findAll();
    }
    @GetMapping(value = "/contacts/{id}")
    public Contact getContact(@PathVariable Long id){
        return contactRepository.findById(id).orElse(null);
    }

    @GetMapping(value = "/chercherContacts")
    public Page<Contact> chercher(@RequestParam(name = "mc", defaultValue = "") String mc,
                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "5") int size){
        return contactRepository.chercher("%"+mc+"%", PageRequest.of(page, size));
    }

    @RequestMapping(value = "/contacts",method = RequestMethod.POST)
    public Contact save(@RequestBody Contact c){
        return contactRepository.save(c);
    }

    @DeleteMapping(value = "/contacts/{id}")
    public boolean supprimer(@PathVariable Long id){
        contactRepository.deleteById(id);
        return true;
    }

    @PutMapping (value = "/contacts/{id}")
    public Contact save(@PathVariable Long id, @RequestBody Contact c){
        c.setId(id);
        return contactRepository.save(c);
    }

}
