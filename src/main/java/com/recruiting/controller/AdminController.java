package com.recruiting.controller;

import com.recruiting.domain.IndividualTimeOff;
import com.recruiting.model.modelUtils.PageWrapper;
import com.recruiting.service.admin.AdminService;
import com.recruiting.service.employee.EmployeeDetailService;
import com.recruiting.service.employee.dto.model.EmployeeDetailsModel;
import com.recruiting.service.employee.dto.model.EmployeeModel;
import com.recruiting.service.entity.TimeOffService;
import com.recruiting.service.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Marta Ginosyan
 */

@Controller
@RequestMapping(value = "/admin")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class AdminController extends AbstractController {

    @Autowired
    UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private TimeOffService timeOffService;

    @Autowired
    private EmployeeDetailService employeeDetailService;

    @RequestMapping(value = "")
    public String candidate() {
        return "redirect:/admin/home/";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String account(
            Model model) {
        PageWrapper<EmployeeModel> pageWrapper = adminService.getEmployee(new PageRequest(0, 3));
        model.addAttribute("pageWrapper", pageWrapper);
        return "admin-home";
    }

    @RequestMapping(value = "/home/vacations-disposed-from-outdated", method = RequestMethod.GET)
    public String vacationsFromOutdated(
            Model model) {
        PageWrapper<EmployeeDetailsModel> pageWrapper = adminService.getEmployeeDetails(new PageRequest(0, 3));
        model.addAttribute("pageWrapper", pageWrapper);
        return "admin-home-outdated";
    }

    @RequestMapping(value = "/home/vacations-disposed-balanced", method = RequestMethod.GET)
    public String vacationsBalanced(
            Model model) {
        PageWrapper<EmployeeDetailsModel> pageWrapper = adminService.getEmployeeDetails(new PageRequest(0, 3));
        model.addAttribute("pageWrapper", pageWrapper);
        return "admin-home-balanced";
    }

    @RequestMapping(value = "/home/employees", method = RequestMethod.POST)
    public String account(
            Model model,
            Pageable pageable) {
        PageWrapper<EmployeeModel> pageWrapper = adminService.getEmployee(pageable);
        model.addAttribute("pageWrapper", pageWrapper);
        return "admin-home";
    }

    @RequestMapping(value = "/employees/time-off-requests", method = RequestMethod.GET)
    public String timeOffsNotApproved(
            Model model) {
        PageWrapper<IndividualTimeOff> pageWrapper = timeOffService.getNotApprovedTimeOffRequests(new PageRequest(0, 3));
        model.addAttribute("pageWrapper", pageWrapper);
        return "admin-non-approved";
    }

    @RequestMapping(value = "/employees/time-off-requests/next-page", method = RequestMethod.POST)
    public String timeOffsNotApprovedNextPage(
            Model model,
            Pageable pageable) {
        PageWrapper<IndividualTimeOff> pageWrapper = timeOffService.getNotApprovedTimeOffRequests(pageable);
        model.addAttribute("pageWrapper", pageWrapper);
        return "admin-non-approved";
    }

    @RequestMapping(value = "/employee/time-off-details/{id}", method = RequestMethod.GET)
    public String employeeTimeOffDetails(@PathVariable("id") String id, Model model) {
        model.addAttribute("pageWrapper", employeeDetailService.getTimeOffsByEmployee(id, new PageRequest(0, 3)));
        model.addAttribute("employee", userService.findById(id));
        return "admin-employee-timeoffs-detail";
    }

    @RequestMapping(value = "/employee/time-off-details/next-page/{id}", method = RequestMethod.POST)
    public String employeeTimeOffDetailsNextPage(@PathVariable("id") String id, Model model, Pageable pageable) {
        model.addAttribute("pageWrapper", employeeDetailService.getTimeOffsByEmployee(id, pageable));
        model.addAttribute("employee", userService.findById(id));
        return "admin-employee-timeoffs-detail";
    }

    @RequestMapping(value = "/employee/preview/{id}", method = RequestMethod.GET)
    public String previewEmployeeTimeOffs(@PathVariable("id") String id, Model model) {
        model.addAttribute("employeeMap", employeeDetailService.getTimeOffSummaryForEmployee(id));
        return "redirect:/admin/employees/time-off-requests/";
    }

    @RequestMapping(value = "/employees/time-off-request/approve/{id}", method = RequestMethod.GET)
    public String approveTimeOff(@PathVariable("id") String id) {
        timeOffService.approveTimeOff(id);
        return "redirect:/admin/employees/time-off-requests/";
    }

    @RequestMapping(value = "/employees/time-off/delete/{id}", method = RequestMethod.GET)
    public String deleteTimeOff(@PathVariable("id") String id) {
        timeOffService.deleteTimeOff(id);
        return "redirect:/admin/employees/time-off-requests/";
    }

    @RequestMapping(value = "/employees/time-off/dispose/{id}", method = RequestMethod.GET)
    public String disposeTimeOff(@PathVariable("id") String id) {
        timeOffService.disposeTimeOff(id);
        return "redirect:/admin/employees/time-off-requests/";
    }

    @RequestMapping(value = "/company-configurations", method = RequestMethod.GET)
    public String editAccount() {
        return "redirect:/edit-account/company";
    }

    @RequestMapping(value = "/under-construction")
    public String underConstruction() {
        return "under-construction-home";
    }

}
