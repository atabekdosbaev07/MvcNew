package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;
    private final CourseService courseService;

    @Autowired
    public GroupController(GroupService groupService,CourseService courseService) {
        this.groupService = groupService;
        this.courseService = courseService;
    }

    @ModelAttribute("courseList")
    public List<Course>getAllCourse(){
        return courseService.getAllCourse();
    }

    @GetMapping()
    public String getAllGroup(Model model){
        model.addAttribute("groups",groupService.getAllGroup());
        return "group/groups";
    }
    @GetMapping("/addGroup")
    public String addGroup(Model model){
        model.addAttribute("group",new Group());
        return "group/addGroup";
    }
    @PostMapping("/saveGroup")
    public String saveGroup(@ModelAttribute("group") Group groups) {
        groupService.addGroup(groups, groups.getCourseId());
        return "redirect:/groups";
    }

    @GetMapping("/updateGroup")
    public String updateGroup(@RequestParam("groupId")Long id, Model model){
        Group group=groupService.getGroupById(id);
        model.addAttribute("group",group);
        return "group/updateGroup";
    }
    @PostMapping("/saveUpdateGroup")
    public String saveUpdateGroup(@RequestParam("courseId") Long id,@ModelAttribute("group") Group groups){
        groups.setCourse(Collections.singletonList(courseService.getCourseById(id)));
        groupService.updateGroup(groups);
        return "redirect:/groups";
    }
    @DeleteMapping("/deleteGroup")
    public String deleteGroup(@RequestParam("groupId")Long id){
        groupService.deleteGroup(groupService.getGroupById(id));
        return "redirect:/groups";
    }
}
