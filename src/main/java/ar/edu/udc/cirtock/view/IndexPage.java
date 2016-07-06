package ar.edu.udc.cirtock.view;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.wicket.feedback.ExactLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import ar.edu.udc.cirtock.db.CirtockConnection;
import ar.edu.udc.cirtock.exception.CirtockException;
import ar.edu.udc.cirtock.model.Usuario;
import ar.edu.udc.cirtock.view.intranet.html.ProductoPage;

public class IndexPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Form formulario;
	private RequiredTextField<String> usuario;
	private RequiredTextField<String> password;
	
	public IndexPage(final PageParameters parameters) {
		
		super(parameters);
		add(new FeedbackPanel("feedbackErrors", new ExactLevelFeedbackMessageFilter(FeedbackMessage.ERROR)));
		formulario = new Form("f_usuario");
		
		usuario = new RequiredTextField<String>("usuario", new Model());
                
        usuario.add(new IValidator<String>(){
	        @Override
	        public void validate(IValidatable<String> validatable) {
		        String nombre = validatable.getValue().trim().toUpperCase();
		        if(!nombre.matches("ADMIN")){
			        ValidationError error = new ValidationError();
			        error.setMessage("El 'usuario' no es valido");
			        validatable.error(error);
		        }
	        }
        });
        formulario.add(usuario);
                
        password = new RequiredTextField<String>("contrasenia", new Model());
		              
        password.add(new IValidator<String>(){
	        @Override
	        public void validate(IValidatable<String> validatable) {
		        String descripcion = validatable.getValue().trim().toUpperCase();
		        if(!descripcion.matches("1234")){
			        ValidationError error = new ValidationError();
			        error.setMessage("La 'Contraseña' no es valido");
			        validatable.error(error);
		        }
	        }
        });
        formulario.add(password);
      
            formulario.add(new Button("Ingresar"){
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void onSubmit() {
				String usu = (String)usuario.getModelObject();
				String pass = (String)password.getModelObject();
				Connection conn = null;
				try {

					conn = CirtockConnection.getConection("cirtock", "cirtock", "cirtock");
					Usuario usuario = new Usuario();
					usuario.setUsuario(usu);
					usuario.setPassword(pass);;
					usuario.insert("", conn);

				} catch (CirtockException e) {
					System.out.println("Error al acceder a la base de datos");						
				} finally {
					try {
						conn.close();
					} catch (SQLException e) { ; }						
				}
				setResponsePage(ProductoPage.class);
			};
		});
		
		add(formulario);
	};
}
