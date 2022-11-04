package peaksoft.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="group_name")
    private String groupName;
    @Column(name = "date_of_start")
    private int dateOfStart;
    @Column(name = "date_of_finish")
    private int dateOfFinish;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinTable(name = "groups_courses", joinColumns = @JoinColumn(name = "groups_id")
            , inverseJoinColumns = @JoinColumn(name = "courses_id"))
    private List<Course> course;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.REFRESH}, mappedBy = "groups")
    private List<Student> students;

    @Transient
    private Long courseId;

}
