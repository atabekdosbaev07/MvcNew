package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Group;
import peaksoft.entity.Student;
import peaksoft.service.GroupService;
import peaksoft.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final GroupService groupsService;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupsService) {
        this.studentService = studentService;
        this.groupsService = groupsService;
    }
    @ModelAttribute("groupList")
    public List<Group> getAllGroups(){
        return groupsService.getAllGroup();
    }

    @GetMapping
    public String getAllStudent(Model model){
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students",students);
        return "student/students";
    }
    @GetMapping("/addStudent")
    public String addStudent(Model model){
        model.addAttribute("student",new Student());
        return "student/addStudent";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.addStudent(student,student.getGroupsId());
        return "redirect:/students";
    }

    @GetMapping("/updateStudent")
    public String updateStudent(@RequestParam("studentId")Long id, Model model){
        Student student = studentService.getStudentById(id);
        model.addAttribute("student",student);
        return "student/updateStudent";
    }

    @PostMapping("/saveUpdateStudent")
    public String saveUpdateStudent(@ModelAttribute("student")Student student){
        studentService.updateStudent(student);
        return "redirect:/students";
    }

    @DeleteMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("studentId") Long id){
        studentService.deleteStudent(studentService.getStudentById(id));
        return "redirect:/students";
    }
}