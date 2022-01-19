package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import spring.models.Address;
import spring.models.District;
import spring.models.School;
import spring.repositories.AddRep;
import spring.repositories.DistRep;
import spring.repositories.SchlRep;

@SpringBootApplication
public class Application {

    @Autowired

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Component
    public static class DataLoader implements ApplicationRunner {

        @Autowired
        private SchlRep schlRep;
        @Autowired
        private AddRep addRep;
        @Autowired
        private DistRep distRep;

        public void run(ApplicationArguments args) {
            District d = new District();
            distRep.save(d);
            Address a1 = new Address("Малышева", d);
            Address a2 = new Address("Ленина", d);
            addRep.save(a1);
            addRep.save(a2);
            schlRep.save(new School(12, a1));
            schlRep.save(new School(13, a2));
        }
    }

}
