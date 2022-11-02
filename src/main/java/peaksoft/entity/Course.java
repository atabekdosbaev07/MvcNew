package peaksoft.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "course_name")
        private String courseName;

        @Column(name = "duration_month")
        private String durationMonth;

        @ManyToOne(cascade = {CascadeType.REMOVE,CascadeType.REFRESH})
        @JoinColumn(name = "companies_id")
        private Company company;


        @Transient
        private Long companyId;

}

