package tz.go.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tz.go.bot.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
}
