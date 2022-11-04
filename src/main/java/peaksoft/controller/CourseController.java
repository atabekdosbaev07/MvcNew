package peaksoft.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;


import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CompanyService companyService;
    private final CourseService coursesService;

    @Autowired
    public CourseController(CompanyService companyService, CourseService coursesService) {
        this.companyService = companyService;
        this.coursesService = coursesService;
    }

    @ModelAttribute("companyList")
    public List<Company>getAllCompany(){
        return companyService.getAllCompanies();
    }

    @GetMapping()
    public String getAllCourses( Model model){
        List<Course> courses = coursesService.getAllCourse();
        model.addAttribute("courses",courses);
        return "course/courses";
    }
    @GetMapping("/addCourse")
    public String addCourse(Model model){
        model.addAttribute("course",new Course());
        return "course/addCourse";
    }
    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course){
        coursesService.addCourse(course,course.getCompanyId());
        return "redirect:/courses";
    }

    @GetMapping("/updateCourse")
    public String updateCourse(@RequestParam("courseId") Long id, Model model){
        Course course =  coursesService.getCourseById(id);
        model.addAttribute("course", course);
        return "course/updateCourse";
    }

    @PostMapping("/saveUpdateCourse")
    public String saveUpdateCourse(@RequestParam("companyId") Long id,@ModelAttribute("course") Course course){
        course.setCompany(companyService.getCompanyById(id));
        coursesService.updateCourse(course);
        return "redirect:/courses";
    }

    @DeleteMapping("/deleteCourse")
    public String deleteCourse(@RequestParam("courseId") Long id ){
        coursesService.deleteCourse(coursesService.getCourseById(id));
        return "redirect:/courses";
    }
}
