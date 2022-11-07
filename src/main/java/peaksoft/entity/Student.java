package peaksoft.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Enumerated
    private studyFormat studyFormat;

    @ManyToOne(cascade = {CascadeType.REMOVE,CascadeType.REFRESH})
    @JoinColumn(name = "groups_id")
    private Group groups;

    @Transient
    private Long groupsId;
}

enum studyFormat{
    ONLINE,
    OFFLINE;
}