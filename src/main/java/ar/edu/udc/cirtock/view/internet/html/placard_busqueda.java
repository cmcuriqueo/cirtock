package ar.edu.udc.cirtock.view.internet.html;

import org.apache.wicket.markup.html.WebPage;


import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;

import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import ar.edu.udc.cirtock.view.intranet.html.HerramientaPage;



public class placard_busqueda extends WebPage {
	private static final long serialVersionUID = 1L;
	private Form formulario;
	private RequiredTextField<String> Usuario;
	private RequiredTextField<String> Contraseña;
	
	public placard_busqueda(final PageParameters parameters) {
		super(parameters);
		formulario = new Form("f_usuario");
		
		formulario.add(Usuario = new RequiredTextField<String>("usuario", new Model()));
			Usuario.add(new IValidator<String>(){

				@Override
				public void validate(IValidatable<String> validatable) {
					 String usuario = validatable.getValue().trim().toUpperCase();
                     if(usuario.matches("ADMIN")){
                         ValidationError error = new ValidationError();
                         error.setMessage("usuario no valido");
                         validatable.error(error);
                     }
                     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }
			});
		formulario.add(Contraseña = new RequiredTextField<String>("contrasenia", new Model()));
			Contraseña.add(new IValidator<String>(){

				@Override
				public void validate(IValidatable<String> validatable) {
					String contraseña = validatable.getValue().trim().toUpperCase();
                    if(contraseña.matches("1234")){
                        ValidationError error = new ValidationError();
                        error.setMessage("contraseña no valida");
                        validatable.error(error);
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
			});
			formulario.add(new Button("Ingresar"){
				private static final long serialVersionUID = 1L;
				public void onSubmit(){
					/*
					RequiredTextField<String> usuario = new RequiredTextField<String>("usuario", new Model());
					Usuario = usuario;
					RequiredTextField<String> contraseña = new RequiredTextField<String>("contrasenia", new Model());
					Contraseña = contraseña;
					*/
					System.out.println("hola");
					setResponsePage(HerramientaPage.class);
				};
			});
	  add(formulario);
	};
}
