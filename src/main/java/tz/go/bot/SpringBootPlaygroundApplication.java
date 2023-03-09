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
import tz.go.bot.model.Student;
import tz.go.bot.model.StudentIdCard;
import tz.go.bot.repository.CustomerRepository;
import tz.go.bot.repository.StudentIdCardRepository;
import tz.go.bot.repository.StudentRepository;

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
		CommandLineRunner commandLineRunner(StudentRepository studentRepository,
											StudentIdCardRepository studentIdCardRepository){
			return args -> {
			Faker faker=new Faker();

			String firstName=faker.name().firstName();
			String lastName=faker.name().lastName();
			String email=String.format("%s.%s@gmail.com",firstName,lastName);
			int age=faker.number().numberBetween(18,25);

			Student student=new Student(firstName,lastName,email,age);

			StudentIdCard studentIdCard=new StudentIdCard("123456789",student);

			System.out.println("Saving ID Card");
			studentIdCardRepository.save(studentIdCard);

				System.out.println("----------Fetching a student---------");

				Optional<Student> optionalStudent=studentRepository.findById(1L);

				if(optionalStudent.isPresent()){
					System.out.println(optionalStudent.get());
				}

				System.out.println("------ Fetch an ID Card ------------");

				Optional<StudentIdCard> optionalStudentIdCard=studentIdCardRepository.findById(1L);
				if(optionalStudentIdCard.isPresent()){
					System.out.println(optionalStudentIdCard.get());
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
