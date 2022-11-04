package peaksoft.dao.impl;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.dao.CourseDao;
import peaksoft.dao.TeacherDAO;
import peaksoft.entity.Course;
import peaksoft.entity.Teacher;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class TeacherDaoImpl implements TeacherDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private final CourseDao courseDao;

    @Autowired
    public TeacherDaoImpl( CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Teacher> getAllTeacher() {
        List<Teacher> teacher = entityManager.createQuery("from Teacher",Teacher.class).getResultList();
        Comparator<Teacher> comparator=((o1, o2)->(int)(o1.getId()-o2.getId()));
        teacher.sort(comparator);
        return teacher;
    }

    @Override
    public void addTeacher(Teacher teacher, Long courseId) {
        Course course = courseDao.getCourseById(courseId);
        teacher.setCourse(course);
        entityManager.persist(teacher);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        Teacher teacher;
        teacher=entityManager.find(Teacher.class,id);
        return teacher;
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        entityManager.merge(teacher);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        entityManager.remove(entityManager.contains(teacher)?teacher:entityManager.merge(teacher));
    }
}
