package com.tamkoc.product.compendium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Gokhan Tamkoc on 09.10.2016.
 */

@Controller
public class NavigationController {

    @RequestMapping(value={"/", "index"}, method=RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index");
    }

}
