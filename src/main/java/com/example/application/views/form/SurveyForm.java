package com.example.application.views.form;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.example.application.data.entity.Beverage;
import com.example.application.data.entity.Department;
import com.example.application.data.repository.UserRepository;
import com.example.application.data.service.EmployeeService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;


public class SurveyForm extends FormLayout {
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService;
	private UserRepository userRepository;
	
	TextField firstName = new TextField("Nombre");
	TextField lastName = new TextField("Apellido");
	EmailField email = new EmailField("Email");
	ComboBox<Department> department = new ComboBox<>("Área");
	RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
	CheckboxGroup<Beverage> checkboxGroup = new CheckboxGroup<>();
	Notification notification = null;
	
	DatePicker datePicker = new DatePicker("Fecha de nacimiento");

	Button save = new Button("Guardar");
	
	String username = "";

	public SurveyForm(List<Department> departmentList, List<Beverage> beverageList, EmployeeService employeeService, UserRepository userRepository) {
		String employeeId = SecurityContextHolder.getContext().getAuthentication().getName();
		this.username = employeeId;
		this.userRepository = userRepository;
		this.employeeService = employeeService;
		addClassName("contact-form");

		department.setItems(departmentList);
		department.setItemLabelGenerator(Department::getName);
		
		radioGroup.setLabel("Mascota favorita");
		radioGroup.setItems("Perros", "Gatos", "Peces");
		radioGroup.setValue("Perros");
		
		checkboxGroup.setLabel("¿Qué bebidas te gustan?");
		checkboxGroup.setItems(beverageList);
		checkboxGroup.setItemLabelGenerator(Beverage::getName);

		add(new HorizontalLayout(firstName, lastName), new HorizontalLayout(email, datePicker), department, radioGroup, checkboxGroup, createButtonsLayout());
	}

	private HorizontalLayout createButtonsLayout() {
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		save.addClickShortcut(Key.ENTER);
		
		save.addClickListener(clickEvent -> {
			if(isFormValid()) {
				try {
					this.employeeService.save(firstName.getValue(),
							lastName.getValue(), email.getValue(), datePicker.getValue(), department.getValue(), checkboxGroup.getValue(), radioGroup.getValue());
					notification = Notification.show("Se guardó tu respuesta");
					notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
					
					this.clearForm();
					return;
				} catch (Exception e) {
					e.printStackTrace();
					notification = Notification.show("Ocurrió un error");
					notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
					return;
				}
				
			} else {
				notification = Notification.show("Por favor completa todos los campos.");
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			}
			
		});

		return new HorizontalLayout(save);
	}
	
	private void clearForm() {
		this.firstName.clear();
		this.lastName.clear();
		this.email.clear();
		this.datePicker.clear();
		this.checkboxGroup.clear();
		this.radioGroup.clear();
		this.department.clear();
		this.email.setInvalid(false);
		
		System.out.println("just cleaned");
		System.out.println("***** find all " + this.userRepository.findAll());
		System.out.println("***** USERNAME IS ->" + this.username);
		this.userRepository.setAsCompleted(this.username);
	}
	
	private boolean isFormValid() {
		return !(firstName.isEmpty() || lastName.isEmpty() || datePicker.isEmpty() || department.isEmpty());
	}
}
