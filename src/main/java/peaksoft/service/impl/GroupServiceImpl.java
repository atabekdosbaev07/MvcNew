package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.GroupDAO;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.service.GroupService;

import java.util.List;
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDAO groupDAO;

    @Autowired
    public GroupServiceImpl(GroupDAO courseDao) {
        this.groupDAO = courseDao;
    }

    @Override
    public List<Group> getAllGroup() {
        return groupDAO.getAllGroup();
    }

    @Override
    public void addGroup(Group course,Long courseId) {
        groupDAO.addGroup(course,courseId);
    }

    @Override
    public Group getGroupById(Long id) {
        return groupDAO.getGroupById(id);
    }

    @Override
    public void updateGroup(Group course) {
        groupDAO.updateGroup(course);
    }

    @Override
    public void deleteGroup(Group course) {
        groupDAO.deleteGroup(course);
    }
}
