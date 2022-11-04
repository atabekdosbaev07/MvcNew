package peaksoft.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;
import peaksoft.entity.Teacher;
import peaksoft.service.CourseService;
import peaksoft.service.TeacherService;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final CourseService courseService;

    @Autowired
    public TeacherController(TeacherService groupService,CourseService courseService) {
        this.teacherService = groupService;
        this.courseService = courseService;
    }

    @ModelAttribute("courseList")
    public List<Course> getAllCourse(){
        return courseService.getAllCourse();
    }

    @GetMapping()
    public String getAllGroup(Model model){
        model.addAttribute("teachers", teacherService.getAllTeacher());
        return "teacher/teachers";
    }

    @GetMapping("/addTeacher")
    public String addGroup(Model model){
        model.addAttribute("teacher",new Teacher());
        return "teacher/addTeacher";
    }

    @PostMapping("/saveTeacher")
    public String saveGroup(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.addTeacher(teacher, teacher.getCourseId());
        return "redirect:/teachers";
    }

    @GetMapping("/updateTeacher")
    public String updateGroup(@RequestParam("teacherId")Long id, Model model){
        Teacher teacher = teacherService.getTeacherById(id);
        model.addAttribute("teacher",teacher);
        return "teacher/updateTeacher";
    }

    @PostMapping("/saveUpdateTeacher")
    public String saveUpdateGroup(@RequestParam("courseId") Long id,@ModelAttribute("teacher") Teacher teacher){
        teacher.setCourse(courseService.getCourseById(id));
        teacherService.updateTeacher(teacher);
        return "redirect:/teachers";
    }

    @DeleteMapping("/deleteTeacher")
    public String deleteGroup(@RequestParam("teacherId")Long id){
        teacherService.deleteTeacher(teacherService.getTeacherById(id));
        return "redirect:/teachers";
    }
}
