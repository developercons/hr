package com.recruiting.controller;

import com.recruiting.domain.IndividualTimeOff;
import com.recruiting.service.employee.EmployeeDetailService;
import com.recruiting.service.employee.dto.model.EmployeeModel;
import com.recruiting.service.entity.TimeOffService;
import com.recruiting.utils.ConstantLabels;
import com.recruiting.validation.EmployeeModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Martha on 4/28/2017.
 */
@Controller
@RequestMapping(value = "/employee")
@PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEE')")
public class EmployeeController extends AbstractController {

    @Autowired
    private EmployeeDetailService employeeDetailService;

    @Autowired
    private TimeOffService timeOffService;

    @Autowired
    private EmployeeModelValidator employeeModelValidator;


    @RequestMapping(value = "")
    public String candidate() {
        return "redirect:/employee/home/";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String account(Model model) {
        model.addAttribute("employee", employeeDetailService.getEmployeeModel(getAuthorizedUser().getId()));
        model.addAttribute("timeOffTypes", timeOffService.getTimeOffTypes());

        return "employee-home";
    }

    @RequestMapping(value = "/request-time-off", method = RequestMethod.POST)
    public String requestTimeOff(@Validated @ModelAttribute(value = "employee") EmployeeModel employeeModel,
            BindingResult bindingResult,
            Model model) {
        IndividualTimeOff individualTimeOff = employeeModel.getNewIndividualTimeOff();
        individualTimeOff.setUser(getAuthorizedUser());
        timeOffService.saveIndividualTimeOff(employeeModel.getNewIndividualTimeOff());
        return "redirect:/employee/home";
    }

    @RequestMapping(value = "/edit-account", method = RequestMethod.GET)
    public String editAccount(ModelMap model) {
        model.put("candidateUsername", getAuthorizedUser().getUsername());
        model.put("timePeriods", ConstantLabels.TIME_PERIODS_LIST);
        return "redirect:/edit-account/candidate";
    }

    @RequestMapping(value = "/under-construction")
    public String underConstruction() {
        return "under-construction-candidate";
    }

    @InitBinder("employee")
    protected void initBinder(final WebDataBinder binder) {
        binder.addValidators(employeeModelValidator);
    }

}
