package ng.crankshatf.restmvc.bootstrap;

import ng.crankshatf.restmvc.domain.Category;
import ng.crankshatf.restmvc.domain.Customer;
import ng.crankshatf.restmvc.domain.Vendor;
import ng.crankshatf.restmvc.repositories.CategoryRepository;
import ng.crankshatf.restmvc.repositories.CustomerRepository;
import ng.crankshatf.restmvc.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");
        Category dried = new Category();
        dried.setName("Dried");
        Category fresh = new Category();
        fresh.setName("Fresh");
        Category exotic = new Category();
        exotic.setName("Exotic");
        Category nuts = new Category();
        nuts.setName("Nuts");
        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);
        System.out.println("Category Data Loaded = " + categoryRepository.count());
    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("Michelle");
        customer1.setLastname("Obama");
        customerRepository.save(customer1);
        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstname("Hillary");
        customer2.setLastname("Clinton");
        customerRepository.save(customer2);
        System.out.println("Customer Data Loaded = " + customerRepository.count());
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Microsoft");
        vendorRepository.save(vendor1);
        Vendor vendor2 = new Vendor();
        vendor2.setName("Google");
        vendorRepository.save(vendor2);
        System.out.println("Vendor Data Loaded = " + vendorRepository.count());
    }
}
