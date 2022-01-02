package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import spring.model.Address;
import spring.model.District;
import spring.model.School;

@SpringBootApplication
public class Application {

    @Autowired

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Component
    public class DataLoader implements ApplicationRunner {

        @Autowired
        private SchoolRepository schoolRepository;
        @Autowired
        private AddressRepository addressRepository;
        @Autowired
        private DistrictRepository districtRepository;

        public void run(ApplicationArguments args) {
            District d = new District();
            districtRepository.save(d);
            Address a1 = new Address("Улица 1", d);
            Address a2 = new Address("Улица 2", d);
            addressRepository.save(a1);
            addressRepository.save(a2);
            schoolRepository.save(new School(42, a1));
            schoolRepository.save(new School(45, a2));
        }
    }

}
