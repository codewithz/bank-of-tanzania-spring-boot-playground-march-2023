package tz.go.bot.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EnrolmentID implements Serializable {

    @Column(name="student_id")
    private Long studentId;
    @C
    private Long courseId;

}
