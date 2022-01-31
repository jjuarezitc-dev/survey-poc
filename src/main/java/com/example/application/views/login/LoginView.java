package com.example.application.views.login;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Login | Vaadin CRM")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {
	private static final long serialVersionUID = 1L;
	
	private final LoginForm login = new LoginForm();
	
	public LoginView() {
		addClassName("login-view");
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);
		
		login.setAction("login");
		
		LoginI18n i18n = LoginI18n.createDefault();

		LoginI18n.Form i18nForm = i18n.getForm();
		i18nForm.setTitle("Ingresa tus credenciales");
		i18nForm.setUsername("Número de empleado");
		i18nForm.setPassword("Password");
		i18nForm.setSubmit("Entrar");
		i18nForm.setForgotPassword("¿Problemas para entrar?");
		i18n.setForm(i18nForm);

		LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
		i18nErrorMessage.setTitle("Error");
		i18nErrorMessage.setMessage("Credenciales incorrectas");
		i18n.setErrorMessage(i18nErrorMessage);
		
		login.setI18n(i18n);
		
		Image img = new Image("images/office-depot-logo.png", "Office Depot logo");
        img.setWidth("250px");
		
		add(new H1("Encuesta de clima laboral"), img, login);
		
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		if(beforeEnterEvent.getLocation()
				.getQueryParameters()
				.getParameters()
				.containsKey("error")
				) {
			login.setError(true);
		}
	}

}
