package tz.go.bot.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Enrolment")
@Table(name="enrolment")
public class Enrolment {
    @EmbeddedId
    private EnrolmentID id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(
            name = "student_id",
            foreignKey = @ForeignKey(name = "enrolment_student_id_fk")
    )
    private Student student;
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(
            name = "course_id",
            foreignKey = @ForeignKey(name = "enrolment_course_id_fk")
    )
    private Course course;
    @Column(
            name="created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

    public Enrolment() {
    }

    public Enrolment(EnrolmentID id, Student student, Course course, LocalDateTime createdAt) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.createdAt = createdAt;
    }

    public EnrolmentID getId() {
        return id;
    }

    public void setId(EnrolmentID id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Enrolment{" +
                "id=" + id +
                ", student=" + student +
                ", course=" + course +
                ", createdAt=" + createdAt +
                '}';
    }
}
