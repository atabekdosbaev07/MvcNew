package peaksoft.service;

import peaksoft.entity.Group;

import java.util.List;

public interface GroupService {

    List<Group> getAllGroup();
    void addGroup(Group group,Long courseId);
    Group getGroupById(Long id);
    void updateGroup(Group course);
    void deleteGroup(Group course);
}
