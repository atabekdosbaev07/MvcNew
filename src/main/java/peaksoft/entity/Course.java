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
        private Integer durationMonth;

        @ManyToOne(cascade = {CascadeType.REMOVE,CascadeType.REFRESH})
        @JoinColumn(name = "companies_id")
        private Company company;

        @Transient
        private Long companyId;


        @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
        @JoinTable(name = "groups_courses", joinColumns = @JoinColumn(name = "courses_id")
                , inverseJoinColumns = @JoinColumn(name = "groups_id"))
        private List<Group> groups;


        @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
        @JoinTable(name = "teachers_courses", joinColumns = @JoinColumn(name = "courses_id")
                , inverseJoinColumns = @JoinColumn(name = "teachers_id"))
        private Teacher teacher;

}

