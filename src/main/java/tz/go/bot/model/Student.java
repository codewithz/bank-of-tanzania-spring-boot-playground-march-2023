package tz.go.bot.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Represents the Java class
@Entity(name="Student")
//Represents the db table
@Table(name="student",
        uniqueConstraints = {
                @UniqueConstraint(name="student_email_unique",columnNames = {"email"}),
        })
public class Student {
    //Representign Primary Key
    @Id
    //For creating a sequence for table student
    @SequenceGenerator(
            name="student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    //For getting the generated value
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            name="id",
            updatable = false
    )
    private Long id;
    @Column(
            name="first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(
            name="last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;
    @Column(
            name="email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;
    @Column(
            name="age",
            nullable = false
    )
    private Integer age;

    @OneToOne(
            mappedBy = "student",
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            orphanRemoval = true,
            fetch=FetchType.LAZY
    )
    private StudentIdCard studentIdCard;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY

    )
    private List<Book> books=new ArrayList<>();

//    @ManyToMany(
//            cascade = CascadeType.MERGE
//    )
//    @JoinTable(
//            name = "enrolment",
//            joinColumns = @JoinColumn(
//                    name = "student_id",
//                    foreignKey = @ForeignKey(name = "enrolment_student_id_fk")
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "course_id",
//                    foreignKey = @ForeignKey(name = "enrolment_course_id_fk")
//
//            )
//    )
//    private List<Course> courses=new ArrayList<>();

    @OneToMany(
            mappedBy = "student",
            cascade = {CascadeType.MERGE}
    )
    private List<Enrolment> enrolments=new ArrayList<>();

    public Student() {
    }

    public Student(String firstName, String lastName, String email, Integer age) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public StudentIdCard getStudentIdCard() {
        return studentIdCard;
    }

    public void setStudentIdCard(StudentIdCard studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

    public List<Book> getBooks(){
        return  this.books;
    }

    public void addBook(Book book){
        if(!books.contains(book)){
            this.books.add(book);
            book.setStudent(this);
        }
    }

    public void removeBook(Book book){
        if(this.books.contains(book)){
            this.books.remove(book);
            book.setStudent(null);
        }
    }

    //Handling Course Mappings

//    public List<Course> getCourses(){
//        return this.courses;
//    }
//
//    public void enrolToCourse(Course course){
//        courses.add(course);
//        course.getStudents().add(this);
//    }
//
//    public void unEnrolFromCourse(Course course){
//        courses.remove(course);
//        course.getStudents().remove(this);
//    }

    //Handle Enrolment Mappings

    public List<Enrolment> getEnrolments(){

        return  this.enrolments;
    }

    public void addEnrolment(Enrolment enrolment){
        if(!enrolments.contains(enrolment)){
            enrolments.add(enrolment);
        }
    }

    public void removeEnrolment(Enrolment enrolment){
        if(enrolments.contains(enrolment)){
            enrolments.remove(enrolment);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
              //  ", studentIdCard=" + studentIdCard +
                '}';
    }
}
