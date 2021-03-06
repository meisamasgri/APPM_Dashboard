package ir.mci.appm_dashboard.webcontroller;

import ir.mci.appm_dashboard.entity.AppmApiKey;
import ir.mci.appm_dashboard.repository.AppmApiKeyRepository;
import ir.mci.appm_dashboard.repository.AppmMonitorListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class LandingPageController {

    @Autowired
    AppmApiKeyRepository appmApiKeyRepository;

    @Autowired
    AppmMonitorListRepository appmMonitorListRepository;

    @GetMapping({"/"})
    public ModelAndView returnLandingPage(Model model) {

        //add-appm modal
        Date date = new Date();
        AppmApiKey appmApiKey = new AppmApiKey("appm_","","","9090","","");
        model.addAttribute("newAppmModel",appmApiKey);

        //view to display
        ModelAndView appmApiKeyMv = new ModelAndView("home");
        appmApiKeyMv.addObject("appminfo", appmApiKeyRepository.findAll());
        appmApiKeyMv.addObject("monitorlist", appmMonitorListRepository.findAll());
        appmApiKeyMv.setViewName("home");

        return appmApiKeyMv;

    }

}
