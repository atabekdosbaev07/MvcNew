package peaksoft.dao.impl;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.dao.GroupDAO;
import peaksoft.dao.StudentDAO;
import peaksoft.entity.Group;
import peaksoft.entity.Student;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private final GroupDAO groupDAO;

    @Autowired
    public StudentDaoImpl(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = entityManager.createQuery("from Student", Student.class).getResultList();
        Comparator<Student> comparator = ((o1, o2) -> (int) (o1.getId() - o2.getId()));
        students.sort(comparator);
        return students;
    }

    @Override
    public void addStudent(Student student,Long groupId) {
        Group group = groupDAO.getGroupById(groupId);
        student.setGroups(group);
        entityManager.persist(student);
    }

    @Override
    public Student getStudentById(Long id) {
        Student student;
              student = entityManager.find(Student.class, id);
        return student;
    }

    @Override
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    public void deleteStudent(Student student) {
        entityManager.remove(entityManager.contains(student) ? student : entityManager.merge(student));
    }
}