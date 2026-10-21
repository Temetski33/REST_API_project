package fi.restapi.project.product;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SupplierAddressRepository extends JpaRepository<SupplierAddress, Long> {
    List<SupplierAddress> findBySupplierId(Long supplierId);
}
