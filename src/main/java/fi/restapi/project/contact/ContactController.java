package fi.restapi.project.contact;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    private final ContactRepository contacts;

    public ContactController(ContactRepository contacts) {
        this.contacts = contacts;
    }

    @GetMapping
    public List<Contact> all(@RequestParam(required = false) String email) {
        return email == null ? contacts.findAll() : contacts.findByEmailIgnoreCase(email);
    }

    @GetMapping("/{id}")
    public Contact one(@PathVariable Long id) {
        return contacts.findById(id).orElseThrow();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contact create(@RequestBody Contact contact) {
        contact.setId(null);
        return contacts.save(contact);
    }

    @PutMapping("/{id}")
    public Contact update(@PathVariable Long id, @RequestBody Contact input) {
        Contact contact = contacts.findById(id).orElseThrow();
        contact.setEmail(input.getEmail());
        contact.setReference(input.getReference());
        return contacts.save(contact);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        contacts.deleteById(id);
    }
}
