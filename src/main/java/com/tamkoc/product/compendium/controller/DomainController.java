package com.tamkoc.product.compendium.controller;

import com.tamkoc.product.compendium.editorsupport.DomainStatusEditorSupport;
import com.tamkoc.product.compendium.exception.DomainNotFoundException;
import com.tamkoc.product.compendium.model.Domain;
import com.tamkoc.product.compendium.model.DomainStatus;
import com.tamkoc.product.compendium.service.IDomainService;
import com.tamkoc.product.compendium.service.IDomainStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author Gokhan Tamkoc on 08.10.2016.
 */
@Controller
@RequestMapping(value = "/domain")
public class DomainController {

    @Autowired
    private IDomainService domainService;

    @Autowired
    private IDomainStatusService domainStatusService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(DomainStatus.class, new DomainStatusEditorSupport(domainStatusService));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listDomain() {
        ModelAndView modelAndView = new ModelAndView("domain-list");
        java.util.List<Domain> domainList = domainService.findAll();
        modelAndView.addObject("domainList", domainList);
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createDomain() {
        ModelAndView modelAndView = new ModelAndView("domain-new", "domain", new Domain());
        modelAndView.addObject("domainStatusList", domainStatusService.findAll());
        return modelAndView;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView createNewDomain(@ModelAttribute("domain") @Valid Domain domain, BindingResult result, final RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
        {
            ModelAndView modelAndView = new ModelAndView("domain-new", "domain", domain);
            modelAndView.addObject("domainStatusList", domainStatusService.findAll());
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView();

        domainService.create(domain);

        String message = "New domain " + domain.getName() + " was successfully created.";
        modelAndView.setViewName("redirect:/domain/list");
        redirectAttributes.addFlashAttribute("successMessage", message);

        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editDomain(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("domain-edit");
        Domain domain = domainService.findById(id);
        modelAndView.addObject("domainStatusList", domainStatusService.findAll());
        modelAndView.addObject("domain", domain);
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editExistingDomain(@ModelAttribute @Valid Domain domain, BindingResult result, @PathVariable Long id, final RedirectAttributes redirectAttributes) throws DomainNotFoundException {

        if (result.hasErrors())
        {
            ModelAndView modelAndView = new ModelAndView("domain-edit", "domain", domain);
            modelAndView.addObject("domainStatusList", domainStatusService.findAll());
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return modelAndView;
        }

        domainService.update(domain);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/domain/list");
        String message = "\"" + domain.getName() + "\" named domain was successfully updated.";
        redirectAttributes.addFlashAttribute("successMessage", message);

        return modelAndView;
    }
}
