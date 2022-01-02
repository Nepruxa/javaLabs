package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @Autowired
    WebService service;

    @GetMapping("/data")
    public String data(Model model) {
        model.addAttribute("schools", service.findAll());
        return "data";
    }
}
