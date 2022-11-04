package peaksoft.service;


import peaksoft.entity.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeacher();

    void addTeacher(Teacher teacher,Long courseId);

    Teacher getTeacherById(Long id);

    void updateTeacher(Teacher teacher);

    void deleteTeacher(Teacher teacher);
}
