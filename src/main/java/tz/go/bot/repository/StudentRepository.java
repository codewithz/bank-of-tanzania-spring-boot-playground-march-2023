package tz.go.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tz.go.bot.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
