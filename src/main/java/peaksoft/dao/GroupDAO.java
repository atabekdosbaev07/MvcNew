package peaksoft.dao;


import peaksoft.entity.Group;

import java.util.List;

public interface GroupDAO {

    List<Group> getAllGroup();

    void addGroup(Group course,Long courseId);

    Group getGroupById(Long id);

    void updateGroup(Group course);

    void deleteGroup(Group course);
}
