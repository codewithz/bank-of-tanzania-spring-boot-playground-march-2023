package tz.go.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tz.go.bot.model.StudentIdCard;

@Repository
public interface StudentIdCardRepository extends JpaRepository<StudentIdCard,Long> {
}
