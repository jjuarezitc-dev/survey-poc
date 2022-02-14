package com.example.application.views.list;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.example.application.data.entity.Beverage;
import com.example.application.data.entity.Department;
import com.example.application.data.repository.BeverageRepository;
import com.example.application.data.repository.DepartmentRepository;
import com.example.application.data.repository.UserRepository;
import com.example.application.data.service.EmployeeService;
import com.example.application.security.SecurityService;
import com.example.application.views.form.SurveyForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

@PWA(name = "Flow CRM Tutorial", shortName = "Flow CRM Tutorial", enableInstallPrompt = false)
@Theme(themeFolder = "flowcrmtutorial")
@PageTitle("Encuesta")
@Route(value = "")
public class ListView extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	private DepartmentRepository departmentRepository;
	private EmployeeService employeeService;
	private BeverageRepository beverageRepository;
	private UserRepository userRepository;
	
	SurveyForm surveyForm;

	public ListView(DepartmentRepository departmentRepository, EmployeeService employeeService, BeverageRepository beverageRepository, SecurityService securityService, UserRepository userRepository) {
		this.departmentRepository = departmentRepository;
		this.employeeService = employeeService;
		this.beverageRepository = beverageRepository;
		this.userRepository = userRepository;
		
		String employeeId = SecurityContextHolder.getContext().getAuthentication().getName();
		Button logoutButton = new Button("Log out", e -> securityService.logout());
		
        setSpacing(false);

        Image img = new Image("images/office-depot-logo.png", "Office Depot logo");
        img.setWidth("250px");

        add(new H2("Queremos conocerte mejor " + employeeId));
        add(new Paragraph("Por favor contesta la siguiente encuesta."));
        
        configureForm();
        add(logoutButton);
        
        if(! this.userRepository.isSurveyCompletedByUsername(employeeId)) {
        	add(surveyForm);
        } else {
        	add (new H2("Ya completaste la encuesta. Gracias"));
        }
        

        setSizeFull();
    }
	
	private void configureForm() {
		List<Department> departmentList = departmentRepository.findAll();
		List<Beverage> beverageList = beverageRepository.findAll();
		
		surveyForm = new SurveyForm(departmentList, beverageList, this.employeeService, this.userRepository); 
		surveyForm.setWidth("25em");
    }

}
