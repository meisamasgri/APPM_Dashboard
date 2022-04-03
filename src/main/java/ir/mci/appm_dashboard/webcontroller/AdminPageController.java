package ir.mci.appm_dashboard.webcontroller;

import ir.mci.appm_dashboard.entity.AppmApiKey;
import ir.mci.appm_dashboard.repository.AppmApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class AdminPageController {

    @Autowired
    AppmApiKeyRepository appmApiKeyRepository;


    @GetMapping({"/admin"})
    public ModelAndView returnAdminPage(Model model) {

        //add-appm modal
        Date date = new Date();
        AppmApiKey appmApiKey = new AppmApiKey(null,"appm_","","9090","");
        model.addAttribute("appmApiKeyModel",appmApiKey);

        //view to display
        ModelAndView appmApiKeyMv = new ModelAndView("admin");
        appmApiKeyMv.addObject("appmApiKeyMv", appmApiKeyRepository.findAll());
        appmApiKeyMv.setViewName("admin");

        return appmApiKeyMv;

    }

}
