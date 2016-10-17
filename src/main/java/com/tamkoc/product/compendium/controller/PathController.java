package com.tamkoc.product.compendium.controller;

import com.tamkoc.product.compendium.editorsupport.DomainEditorSupport;
import com.tamkoc.product.compendium.editorsupport.PathStatusEditorSupport;
import com.tamkoc.product.compendium.exception.PathNotFoundException;
import com.tamkoc.product.compendium.model.Domain;
import com.tamkoc.product.compendium.model.Path;
import com.tamkoc.product.compendium.model.PathStatus;
import com.tamkoc.product.compendium.service.IDomainService;
import com.tamkoc.product.compendium.service.IPathService;
import com.tamkoc.product.compendium.service.IPathStatusService;
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
 * @author Gokhan Tamkoc on 15.10.2016.
 */
@Controller
@RequestMapping(value = "/path")
public class PathController {
    @Autowired
    private IPathService pathService;

    @Autowired
    private IPathStatusService pathStatusService;

    @Autowired
    private IDomainService domainService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(PathStatus.class, new PathStatusEditorSupport(pathStatusService));
        binder.registerCustomEditor(Domain.class, new DomainEditorSupport(domainService));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("path-list");
        List<Path> pathList = pathService.findAll();
        modelAndView.addObject("pathList", pathList);
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("path-new", "path", new Path());
        modelAndView.addObject("pathStatusList", pathStatusService.findAll());
        modelAndView.addObject("domainList", domainService.findAll());
        return modelAndView;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("path") @Valid Path path, BindingResult result, final RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
        {
            ModelAndView modelAndView = new ModelAndView("path-new", "path", path);
            modelAndView.addObject("pathStatusList", pathStatusService.findAll());
            modelAndView.addObject("domainList", domainService.findAll());
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView();

        pathService.create(path);

        String message = "New path " + path.getName() + " was successfully created.";
        modelAndView.setViewName("redirect:/path/list");
        redirectAttributes.addFlashAttribute("successMessage", message);

        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("path-edit");
        Path path = pathService.findById(id);
        modelAndView.addObject("pathStatusList", pathStatusService.findAll());
        modelAndView.addObject("domainList", domainService.findAll());
        modelAndView.addObject("path", path);
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute @Valid Path path, BindingResult result, @PathVariable Long id, final RedirectAttributes redirectAttributes) throws PathNotFoundException {

        if (result.hasErrors())
        {
            ModelAndView modelAndView = new ModelAndView("path-edit", "path", path);
            modelAndView.addObject("pathStatusList", pathStatusService.findAll());
            modelAndView.addObject("domainList", domainService.findAll());
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return modelAndView;
        }

        pathService.update(path);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/path/list");
        String message = "\"" + path.getName() + "\" named path was successfully updated.";
        redirectAttributes.addFlashAttribute("successMessage", message);

        return modelAndView;
    }
}
