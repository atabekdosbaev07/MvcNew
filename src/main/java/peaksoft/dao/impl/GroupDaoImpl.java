package peaksoft.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.dao.CourseDao;
import peaksoft.dao.GroupDAO;
import peaksoft.entity.Course;
import peaksoft.entity.Group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class GroupDaoImpl implements GroupDAO {
    @PersistenceContext
    private EntityManager entityManager;

    private final CourseDao courseDao;

    @Autowired
    public GroupDaoImpl( CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Group> getAllGroup() {
        List<Group>companies=entityManager.createQuery("FROM Group",Group.class).getResultList();
        Comparator<Group> comparator=((o1, o2)->(int)(o1.getId()-o2.getId()));
        companies.sort(comparator);
        return companies;
    }

    @Override
    public void addGroup(Group group, Long courseId) {
        Course course = courseDao.getCourseById(courseId);
        group.setCourse(Collections.singletonList(course));
        entityManager.persist(group);
    }

    @Override
    public Group getGroupById(Long id) {
        Group company;
        company=entityManager.find(Group.class,id);
        return company;
    }

    @Override
    public void updateGroup(Group company) {
        entityManager.merge(company);
    }

    @Override
    public void deleteGroup(Group company) {
        entityManager.remove(entityManager.contains(company)?company:entityManager.merge(company));
    }
}
