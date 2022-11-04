package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.GroupDAO;
import peaksoft.dao.TeacherDAO;
import peaksoft.entity.Group;
import peaksoft.entity.Teacher;
import peaksoft.service.TeacherService;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherDAO teacherDAO;

    @Autowired
    public TeacherServiceImpl(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherDAO.getAllTeacher();
    }

    @Override
    public void addTeacher(Teacher teacher,Long courseId) {
        teacherDAO.addTeacher(teacher,courseId);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherDAO.getTeacherById(id);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherDAO.updateTeacher(teacher);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        teacherDAO.deleteTeacher(teacher);
    }
}
