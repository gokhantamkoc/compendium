package com.tamkoc.product.compendium.controller;

import com.tamkoc.product.compendium.editorsupport.CourseStatusEditorSupport;
import com.tamkoc.product.compendium.editorsupport.PathEditorSupport;
import com.tamkoc.product.compendium.exception.CourseNotFoundException;
import com.tamkoc.product.compendium.model.Course;
import com.tamkoc.product.compendium.model.CourseStatus;
import com.tamkoc.product.compendium.model.Path;
import com.tamkoc.product.compendium.service.ICourseService;
import com.tamkoc.product.compendium.service.ICourseStatusService;
import com.tamkoc.product.compendium.service.IPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author Gokhan Tamkoc on 17.10.2016.
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    ICourseService courseService;

    @Autowired
    ICourseStatusService courseStatusService;

    @Autowired
    IPathService pathService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(CourseStatus.class, new CourseStatusEditorSupport(courseStatusService));
        binder.registerCustomEditor(Path.class, new PathEditorSupport(pathService));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("course-list");
        modelAndView.addObject("courseList", courseService.findAll());
        modelAndView.addObject("pathList", pathService.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("course-new", "course", new Course());
        modelAndView.addObject("courseStatusList", courseStatusService.findAll());
        modelAndView.addObject("pathList", pathService.findAll());
        return modelAndView;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("course") @Valid Course course, BindingResult result, final RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
        {
            ModelAndView modelAndView = new ModelAndView("course-new", "course", course);
            modelAndView.addObject("courseStatusList", courseStatusService.findAll());
            modelAndView.addObject("pathList", pathService.findAll());
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView();

        courseService.create(course);

        String message = "New course " + course.getName() + " was successfully created.";
        modelAndView.setViewName("redirect:/course/list");
        redirectAttributes.addFlashAttribute("successMessage", message);

        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("course-edit");
        Course course = courseService.findById(id);
        modelAndView.addObject("courseStatusList", courseStatusService.findAll());
        modelAndView.addObject("pathList", pathService.findAll());
        modelAndView.addObject("course", course);
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute @Valid Course course, BindingResult result, @PathVariable Long id, final RedirectAttributes redirectAttributes) throws CourseNotFoundException {

        if (result.hasErrors())
        {
            ModelAndView modelAndView = new ModelAndView("course-edit", "course", course);
            modelAndView.addObject("courseStatusList", courseStatusService.findAll());
            modelAndView.addObject("pathList", pathService.findAll());
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return modelAndView;
        }

        courseService.update(course);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/course/list");
        String message = "\"" + course.getName() + "\" named course was successfully updated.";
        redirectAttributes.addFlashAttribute("successMessage", message);

        return modelAndView;
    }
}
