package com.recruiting.controller;

import com.recruiting.domain.IndividualTimeOff;
import com.recruiting.domain.User;
import com.recruiting.service.employee.EmployeeDetailService;
import com.recruiting.service.employee.dto.model.EmployeeDetailsModel;
import com.recruiting.service.entity.TimeOffService;
import com.recruiting.validation.EmployeeDetailsModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marta Ginosyan
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
    private EmployeeDetailsModelValidator employeeDetailsModelValidator;


    @RequestMapping(value = "")
    public String candidate() {
        return "redirect:/employee/home/";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String account(Model model) {
        model.addAttribute("employee", employeeDetailService.getEmployeeDetailsModel(getAuthorizedUser().getId()));
        model.addAttribute("timeOffTypes", timeOffService.getValidTimeOffTypes());

        return "employee-home";
    }

    @RequestMapping(value = "/request-time-off", method = RequestMethod.POST)
    public String requestTimeOff(@Validated @ModelAttribute(value = "employee") EmployeeDetailsModel employeeDetailsModel,
            BindingResult bindingResult,
            Model model) {
        IndividualTimeOff individualTimeOff = employeeDetailsModel.getNewIndividualTimeOff();
        individualTimeOff.setUser(getAuthorizedUser());
        timeOffService.saveIndividualTimeOff(employeeDetailsModel.getNewIndividualTimeOff());
        return "redirect:/employee/home";
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String details(Model model) {
        model.addAttribute("pageWrapper", employeeDetailService.getTimeOffsByEmployee(getAuthorizedUser().getId(), new PageRequest(0, 3)));
        model.addAttribute("user", getAuthorizedUser());
        return "employee-timeoffs-detail";
    }

    @RequestMapping(value = "/details/next-page", method = RequestMethod.POST)
    public String detailsNextPage(Model model, Pageable pageable) {
        model.addAttribute("pageWrapper", employeeDetailService.getTimeOffsByEmployee(getAuthorizedUser().getId(), pageable));
        model.addAttribute("user", getAuthorizedUser());
        return "employee-timeoffs-detail";
    }

    @RequestMapping(value = "/non-approved-requests", method = RequestMethod.GET)
    public String nonApprovedRequests(Model model) {
        model.addAttribute("pageWrapper", timeOffService.getNotApprovedTimeOffRequests(getAuthorizedUser(), new PageRequest(0, 3)));
        model.addAttribute("user", getAuthorizedUser());
        return "employee-non-approved-requests";
    }

    @RequestMapping(value = "/non-approved-requests/next-page", method = RequestMethod.POST)
    public String nonApprovedRequestsNextPage(Model model, Pageable pageable) {
        model.addAttribute("pageWrapper", timeOffService.getNotApprovedTimeOffRequests(getAuthorizedUser(), pageable));
        model.addAttribute("user", getAuthorizedUser());
        return "employee-non-approved-requests";
    }

    @RequestMapping(value = "/time-off-summary", method = RequestMethod.GET)
    public String employeeTimeOffSummary(Model model) {
        User user = getAuthorizedUser();
        model.addAttribute("pageWrapper", employeeDetailService.getTimeOffSummaryForEmployee(user.getId(), new PageRequest(0, 3)));
        model.addAttribute("user", user);
        return "employee-timeoff-summary";
    }

    @RequestMapping(value = "/time-off-summary/next-page", method = RequestMethod.POST)
    public String employeeTimeOffSummary(Model model, Pageable pageable) {
        User user = getAuthorizedUser();
        model.addAttribute("pageWrapper", employeeDetailService.getTimeOffSummaryForEmployee(getAuthorizedUser().getId(), pageable));
        model.addAttribute("user", user);
        return "employee-timeoff-summary";
    }

    @RequestMapping(value = "/time-off-request/delete/{id}", method = RequestMethod.GET)
    public String deleteTimeOff(@PathVariable("id") Long id) {
        timeOffService.deleteTimeOff(id);
        return "redirect:/employee";
    }

    @RequestMapping(value = "/under-construction")
    public String underConstruction() {
        return "under-construction-home";
    }

    @InitBinder("employee")
    protected void initBinder(final WebDataBinder binder) {
        binder.addValidators(employeeDetailsModelValidator);
    }

}
