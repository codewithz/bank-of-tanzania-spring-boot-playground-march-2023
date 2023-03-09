package tz.go.bot;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import tz.go.bot.model.Customer;
import tz.go.bot.repository.CustomerRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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


				generateRandomCustomers(customerRepository);
				System.out.println("----------- Native Queries --------------------");
				Optional<Customer> optionalCustomer=customerRepository.
						selectCustomerByEmail("tom@gmail.com","Tom");
				if(optionalCustomer.isPresent()){
					System.out.println(optionalCustomer.get());
				}

				List<Customer> customersWithNameAndAccountType=
						customerRepository
								.selectCustomerWithNameLikeAndAccountType("A","Current");

				for(Customer c:customersWithNameAndAccountType){
					System.out.println(c);
				}

				System.out.println("---------- Sorting --------------------");
				sortCustomers(customerRepository);

				System.out.println("------- Paging -------------");

				PageRequest pageRequest=PageRequest.of(0,25);
				Page<Customer> page=customerRepository.findAll(pageRequest);
				System.out.println(page);
				List<Customer> customerListByPage=page.toList();

				for(Customer c:customerListByPage){
					System.out.println(c);
				}

				System.out.println("------------------------------------------");
				int sizePerPage=25;
				for(int pageNumber=0;pageNumber<page.getTotalPages();pageNumber++){
					PageRequest pageRequest1=PageRequest.of(pageNumber,sizePerPage);

					System.out.println("------- Displaying Page Number: " +
							""+(pageNumber+1)+"/"+(page.getTotalPages())+" ------");

					Page<Customer> page1=customerRepository.findAll(pageRequest1);
					List<Customer> customersByPage=page1.toList();

					for(Customer c:customersByPage){
						System.out.println(c);
					}


				}



		};

	}

	private void generateRandomCustomers(CustomerRepository customerRepository){

		Faker faker=new Faker();

		for(int index=1;index<=100;index++){
			String name=faker.name().fullName();
			String email=String.format("%s@gmail.com",name);
			String accountType=index%2==0?"Savings":"Current";
			long contact=faker.number().numberBetween(99887755L,999888555L);
			Date accountCreationDate=faker.date().between(new Date(),new Date(2015,01,01));
			LocalDate accountCreationLocalDate=
					LocalDate.ofInstant(accountCreationDate.toInstant(), ZoneId.systemDefault());

			Customer customer=new Customer(0,name,email,accountType,contact,accountCreationLocalDate);
			customerRepository.save(customer);
		}
	}

	private void sortCustomers(CustomerRepository customerRepository){

		Sort sort=Sort.by("name").ascending();
		List<Customer> customersListSorted=customerRepository.findAll(sort);

		for(Customer c:customersListSorted){
			System.out.println(c);
		}



	}


}
