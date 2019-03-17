package ng.crankshatf.restmvc.repositories;

import ng.crankshatf.restmvc.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
