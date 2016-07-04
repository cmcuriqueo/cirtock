/*
 * Copyright 2016 Cesar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.edu.udc.cirtock.view.intranet.negocio;

import ar.edu.udc.cirtock.db.CirtockConnection;
import ar.edu.udc.cirtock.exception.CirtockException;
import ar.edu.udc.cirtock.model.Insumo;
import ar.edu.udc.cirtock.view.intranet.html.InsumoPage;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.wicket.feedback.ExactLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

/**
 *
 * @author Cesar
 */
public class FormularioInsumo extends WebPage {
    	private static final long serialVersionUID = 1L;
	private Form formulario;
	private RequiredTextField<String> descripcion;
	private RequiredTextField<String> nombre;
	private NumberTextField<Integer> cantidad;
        
	
	public FormularioInsumo(final PageParameters parameters) {
		
		super(parameters);
		 add(new FeedbackPanel("feedbackErrors", new ExactLevelFeedbackMessageFilter(FeedbackMessage.ERROR)));
		formulario = new Form("formulario_insumo");
		
                nombre = new RequiredTextField<String>("nombre", new Model());
                
                nombre.add(new IValidator<String>(){
                    @Override
                    public void validate(IValidatable<String> validatable) {
                        String nombre = validatable.getValue().trim().toUpperCase();
                        if(!nombre.matches("^\\w{3,20}$")){
                            ValidationError error = new ValidationError();
                            error.setMessage("El campo 'nombre' no es valido");
                            validatable.error(error);
                        }
                    }
                    
                });
		formulario.add(nombre);
                
                descripcion = new RequiredTextField<String>("descripcion", new Model());
		              
                descripcion.add(new IValidator<String>(){
                    @Override
                    public void validate(IValidatable<String> validatable) {
                        String descripcion = validatable.getValue().trim().toUpperCase();
                        if(!descripcion.matches("^\\w{3,50}$")){
                            ValidationError error = new ValidationError();
                            error.setMessage("El campo 'descripcion' no es valido");
                            validatable.error(error);
                        }
                    }
            
                });
                formulario.add(descripcion);
                
                cantidad = new NumberTextField<Integer>("cantidad", new Model());
                cantidad.setType(Integer.class);
                cantidad.add(new IValidator<Integer>(){
                    @Override
                    public void validate(IValidatable<Integer> validatable) {
                         Integer cantidad = validatable.getValue();
                         if(cantidad < 0){
                            ValidationError error = new ValidationError();
                            error.setMessage("El campo 'cantidad' no es valido");
                            validatable.error(error);
                         }
                    }                    
                });
                
                formulario.add(cantidad);

		
		formulario.add(new Button("enviar"){
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void onSubmit() {
				String desc = (String)descripcion.getModelObject();
				String nomb = (String)nombre.getModelObject();
				Integer cant = cantidad.getModelObject();
				Connection conn = null;
				try {

					conn = CirtockConnection.getConection("cirtock", "cirtock", "cirtock");
					Insumo ins = new Insumo();
					ins.setDescripcion(desc);
					ins.setNombre(nomb);
					ins.setCantidad(cant);
					ins.insert("", conn);

				} catch (CirtockException e) {
					System.out.println("Error al acceder a la base de datos");						
				} finally {
					try {
						conn.close();
					} catch (SQLException e) { ; }						
				}
				setResponsePage(InsumoPage.class);
			};
		});
		
		add(formulario);
        };

}
