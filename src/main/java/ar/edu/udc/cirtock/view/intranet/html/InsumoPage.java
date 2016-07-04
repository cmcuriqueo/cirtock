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
package ar.edu.udc.cirtock.view.intranet.html;

import java.sql.Connection;
import java.util.LinkedList;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import ar.edu.udc.cirtock.db.CirtockConnection;
import ar.edu.udc.cirtock.db.Consultas;
import ar.edu.udc.cirtock.exception.CirtockException;
import ar.edu.udc.cirtock.model.Insumo;
import ar.edu.udc.cirtock.view.intranet.negocio.FormularioInsumo;
/**
 *
 * @author Cesar
 */
public class InsumoPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LinkedList<Insumo> insumos;
	@SuppressWarnings("unchecked")
	public InsumoPage() {
		
		Connection conn;
		try {
			conn = CirtockConnection.getConection("cirtock", "cirtock", "cirtock");
			String patronDescripcion="";
			Integer patronCantidad=null;
			String patronNombre= "";
			insumos = Consultas.obtenerInsumos(conn, patronDescripcion, patronNombre, patronCantidad);
		} catch (CirtockException e) {
			
			System.out.println(e.getMessage());
		}
		
		
		add(new ListView("lista", insumos) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem item) {
		        Insumo insumo = (Insumo) item.getModelObject();
		        item.add(new Label("nombre", insumo.getNombre()));
		        item.add(new Label("descripcion", insumo.getDescripcion()));
		        item.add(new Label("cantidad", insumo.getCantidad()));
			}
		});
		
	      add(new Link<FormularioInsumo>("nuevo") {
	          /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
	          public void onClick() {
	              setResponsePage(FormularioInsumo.class);
	          }
	      });

	}
}
