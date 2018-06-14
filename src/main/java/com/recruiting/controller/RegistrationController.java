package com.recruiting.controller;

import com.recruiting.entity.User;
import com.recruiting.entity.VerificationToken;
import com.recruiting.model.modelUtils.StringItemModel;
import com.recruiting.service.entity.UserService;
import com.recruiting.utils.ConstantLabels;
import com.recruiting.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

/**
 * @author Marta Ginosyan
 */

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    UserService userDetailsService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String register(Model model) {
        String error = (String) model.asMap()
                .get("error");
        model.addAttribute("error", StringUtils.isNullOrEmpty(error) ? null : error);
        model.addAttribute("registrationtypes", ConstantLabels.REGISTRATION_TYPES_LIST);
        model.addAttribute("choosenType", new StringItemModel());
        return "registration";
    }

    @RequestMapping(value = "/redirect")
    public String resolveRegistrationType(@RequestParam("type") String type) {
        return "redirect:/registration/" + type.toLowerCase();
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String confirmRegistration(RedirectAttributes redirectAttributes, @RequestParam("token") String token) {


        VerificationToken verificationToken = userDetailsService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = "Invalid token.";
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/registration";
        }

        if (verificationToken.getExpiryDate()
                .isBefore(LocalDateTime.now())) {
            String messageValue = "Token expired.";
            redirectAttributes.addFlashAttribute("error", messageValue);
            return "redirect:/registration";
        }

        User user = verificationToken.getUser();
        user.setEnabled(true);
//        genericCrudService.save(user);
        return "redirect:/login";
    }

    @RequestMapping(value = "/under-construction")
    public String underConstruction() {
        return "under-construction-home";
    }

}
