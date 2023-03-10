package tz.go.bot;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import tz.go.bot.model.*;
import tz.go.bot.repository.CourseRepository;
import tz.go.bot.repository.CustomerRepository;
import tz.go.bot.repository.StudentIdCardRepository;
import tz.go.bot.repository.StudentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
											CourseRepository courseRepository){
			return args -> {

				generateRandomCourses(courseRepository);

//				Optional<Course> optionalCourse1=courseRepository.findById(1L);
//				Course course1=optionalCourse1.isPresent()?optionalCourse1.get():null;
//
//
//				Optional<Course> optionalCourse2=courseRepository.findById(9L);
//				Course course2=optionalCourse2.isPresent()?optionalCourse2.get():null;


				Faker faker=new Faker();

				String firstName=faker.name().firstName();
				String lastName=faker.name().lastName();
				String email=String.format("%s.%s@gmail.com",firstName,lastName);
				int age=faker.number().numberBetween(18,25);

				Book book1=new Book("Clean Code", LocalDateTime.now().minusDays(4));
				Book book2=new Book("Head First Java",LocalDateTime.now());
				Book book3=new Book("Master Spring Data JPA",LocalDateTime.now().minusMonths(6));

				Student student=new Student(firstName,lastName,email,age);

				student.addBook(book1);
				student.addBook(book2);
				student.addBook(book3);

//				student.enrolToCourse(course1);
//				student.enrolToCourse(course2);

				StudentIdCard studentIdCard=new StudentIdCard("123456789",student);

				student.setStudentIdCard(studentIdCard);

				studentRepository.save(student);

				System.out.println("----------Fetching a student---------");

				Optional<Student> optionalStudent=studentRepository.findById(1L);

				if(optionalStudent.isPresent()){
					Student student1=optionalStudent.get();
					System.out.println("-- Lazy Loading of Books ----------");
					List<Book> books=student1.getBooks();

					for(Book b:books){
						System.out.println(b);
					}

//					List<Course> courses=student1.getCourses();
//					for(Course c:courses){
//						System.out.println(c);
//					}
				}

				System.out.println("----------- Updating the student with course ---------");

				Student student1=optionalStudent.isPresent()?optionalStudent.get():null;

				Optional<Course> optionalCourse2=courseRepository.findById(9L);
				Course course2=optionalCourse2.isPresent()?optionalCourse2.get():null;

				Enrolment e=new Enrolment(new EnrolmentID(student1.getId(),course2.getId()),
						student1,course2,LocalDateTime.now()
						);

				student1.addEnrolment(e);

				studentRepository.save(student1);



//				System.out.println("------ Fetch an ID Card ------------");
//
//				Optional<StudentIdCard> optionalStudentIdCard=studentIdCardRepository.findById(1L);
//				if(optionalStudentIdCard.isPresent()){
//					System.out.println(optionalStudentIdCard.get());
//				}
//
//				System.out.println("----- Deleting a student-----------");
			//	studentRepository.deleteById(1l);
			//	studentIdCardRepository.deleteById(1l);


		};

	}

	private void generateRandomCourses(CourseRepository courseRepository){

		Faker faker=new Faker();

		for(int i=1;i<=20;i++){
			String department=i%2==0?"IT":"Finance";
			String courseName=faker.educator().course();

			Course course=new Course(courseName,department);
			courseRepository.save(course);
		}

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
