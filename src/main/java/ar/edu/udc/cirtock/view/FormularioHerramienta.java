package ar.edu.udc.cirtock.view;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import ar.edu.udc.cirtock.db.CirtockConnection;
import ar.edu.udc.cirtock.exception.CirtockException;
import ar.edu.udc.cirtock.model.Herramienta;

public class FormularioHerramienta extends WebPage{
	private static final long serialVersionUID = 1L;
	private Form formulario;
	private RequiredTextField<String> descripcion;
	private RequiredTextField<String> nombre;
	private NumberTextField<Integer> cantidad;
        
	
	public FormularioHerramienta(final PageParameters parameters) {
		
		super(parameters);
				
		formulario = new Form("formulario_herramienta");
		
		formulario.add(descripcion = new RequiredTextField<String>("descripcion", new Model()));
                
                descripcion.add(new IValidator<String>(){
                    @Override
                    public void validate(IValidatable<String> validatable) {
                        String descripcion = validatable.getValue().trim().toUpperCase();
                        if(descripcion.matches("^\\w\\s")){
                            ValidationError error = new ValidationError();
                            error.setMessage("descripcion no valida");
                            validatable.error(error);
                        }
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
            
                });
                
		formulario.add(nombre = new RequiredTextField<String>("nombre", new Model()));
                
                cantidad = new NumberTextField<Integer>("cantidad", new Model());
                cantidad.setType(Integer.class);
                
                formulario.add(cantidad);

		
		formulario.add(new Button("enviar"){
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void onSubmit() {
				try {
					String desc = (String)descripcion.getModelObject();
					String nomb = (String)nombre.getModelObject();
					Integer cant = cantidad.getModelObject();

					Connection conn;
					try {
						conn = CirtockConnection.getConexion("cirtock", "cirtock", "cirtock");
						Herramienta hc = new Herramienta();
						hc.setDescripcion(desc);
						hc.setNombre(nomb);
						hc.setCantidad(cant);
						hc.insert("", conn);

					} catch (SQLException e) {
						System.out.println("Error al acceder a la base de datos");
					} catch (CirtockException e) {
						
					}
					
				} catch(NumberFormatException e){
					System.err.println("Error");
				}
				setResponsePage(HerramientaPage.class);
			};
		});
		
		add(formulario);
        };
        
}
