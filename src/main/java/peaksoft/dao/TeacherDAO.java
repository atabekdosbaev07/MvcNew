package peaksoft.dao;


import peaksoft.entity.Teacher;

import java.util.List;

public interface TeacherDAO {

    List<Teacher> getAllTeacher();

    void addTeacher(Teacher teacher,Long groupId);

    Teacher getTeacherById(Long id);

    void updateTeacher(Teacher teacher);

    void deleteTeacher(Teacher teacher);
}
