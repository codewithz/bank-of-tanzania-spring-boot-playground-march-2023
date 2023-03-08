package tz.go.bot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tz.go.bot.model.Customer;
import tz.go.bot.repository.CustomerRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringBootPlaygroundApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootPlaygroundApplication.class, args);
	}

		@Bean
		CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
			return args -> {
				Customer tom=new Customer(
						0,"Tom","tom@gmail.com",
						"Current",99887744L, LocalDate.now());

				customerRepository.save(tom);

				Customer alex=new Customer(
						0,"Alex","alex@gmail.com",
						"Current",99887733L, LocalDate.now());

				Customer mike=new Customer(
						0,"Mike","mike@gmail.com",
						"Savings",99887733L, LocalDate.parse("2021-01-05"));

				customerRepository.saveAll(List.of(alex,mike));

				System.out.println("------------------------------------------");
				System.out.println("Number of students: "+customerRepository.count());
				System.out.println("------------------------------------------");

				Optional<Customer> optionalCustomer=customerRepository.findById(2);
				if(optionalCustomer.isPresent()){
					System.out.println(optionalCustomer.get());
				}

				Optional<Customer> optionalCustomer1=customerRepository.findById(5);
				if(optionalCustomer1.isEmpty()){
					System.out.println("Customer with id 5 not found");
				}

				List<Customer> customers=customerRepository.findAll();
				for(Customer c:customers){
					System.out.println(c);
				}

				System.out.println("--------- Updating Tom--------");

				Optional<Customer> optionalCustomer2=customerRepository.findById(1);

				if(optionalCustomer2.isPresent()){
					Customer tomCopy=optionalCustomer2.get();
					System.out.println(tomCopy);
					tomCopy.setName("Thomas");
					tomCopy.setEmail("thomas@gmail.com");
					customerRepository.save(tomCopy);
				}

				System.out.println("-------- Custom Queries ------------");

				List<Customer> customersByAccountType=
						customerRepository.findCustomersByAccountType("Current");

				for(Customer customer:customersByAccountType){
					System.out.println(customer);

				}
				System.out.println("------ Customers containing something in name");
				List<Customer> customersByNameContaining=
						customerRepository.findCustomersByNameIsContaining("x");

				for(Customer customer:customersByNameContaining){
					System.out.println(customer);
				}

				System.out.println("------ Customers whose account were created after a particular date--------");
				List<Customer> customersAfterDate=
						customerRepository.
								findCustomersByAccountCreationDateAfter(LocalDate.parse("2022-01-01"));

				for(Customer customer:customersAfterDate){
					System.out.println(customer);
				}

		};

	}


}
