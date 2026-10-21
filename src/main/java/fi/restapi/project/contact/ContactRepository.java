package fi.restapi.project.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByEmailIgnoreCase(String email);
}
