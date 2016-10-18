package com.tamkoc.product.compendium.controller;

import com.tamkoc.product.compendium.editorsupport.CourseEditorSupport;
import com.tamkoc.product.compendium.editorsupport.SessionStatusEditorSupport;
import com.tamkoc.product.compendium.exception.SessionNotFoundException;
import com.tamkoc.product.compendium.model.Course;
import com.tamkoc.product.compendium.model.Session;
import com.tamkoc.product.compendium.model.SessionStatus;
import com.tamkoc.product.compendium.service.ICourseService;
import com.tamkoc.product.compendium.service.ISessionService;
import com.tamkoc.product.compendium.service.ISessionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Gokhan Tamkoc on 18.10.2016.
 */
@Controller
@RequestMapping(value = "/session")
public class SessionController {
    @Autowired
    private ISessionService sessionService;

    @Autowired
    private ISessionStatusService sessionStatusService;

    @Autowired
    private ICourseService courseService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(SessionStatus.class, new SessionStatusEditorSupport(sessionStatusService));
        binder.registerCustomEditor(Course.class, new CourseEditorSupport(courseService));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("session-list");
        List<Session> sessionList = sessionService.findAll();
        modelAndView.addObject("sessionList", sessionList);
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("session-new", "session", new Session());
        modelAndView.addObject("sessionStatusList", sessionStatusService.findAll());
        modelAndView.addObject("courseList", courseService.findAll());
        return modelAndView;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("session") @Valid Session session, BindingResult result, final RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
        {
            ModelAndView modelAndView = new ModelAndView("session-new", "session", session);
            modelAndView.addObject("sessionStatusList", sessionStatusService.findAll());
            modelAndView.addObject("courseList", courseService.findAll());
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView();

        sessionService.create(session);

        String message = "New session " + session.getName() + " was successfully created.";
        modelAndView.setViewName("redirect:/session/list");
        redirectAttributes.addFlashAttribute("successMessage", message);

        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("session-edit");
        Session session = sessionService.findById(id);
        modelAndView.addObject("sessionStatusList", sessionStatusService.findAll());
        modelAndView.addObject("courseList", courseService.findAll());
        modelAndView.addObject("session", session);
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute @Valid Session session, BindingResult result, @PathVariable Long id, final RedirectAttributes redirectAttributes) throws SessionNotFoundException {

        if (result.hasErrors())
        {
            ModelAndView modelAndView = new ModelAndView("session-edit", "session", session);
            modelAndView.addObject("sessionStatusList", sessionStatusService.findAll());
            modelAndView.addObject("courseList", courseService.findAll());
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return modelAndView;
        }

        sessionService.update(session);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/session/list");
        String message = "\"" + session.getName() + "\" named session was successfully updated.";
        redirectAttributes.addFlashAttribute("successMessage", message);

        return modelAndView;
    }
}
