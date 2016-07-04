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
import ar.edu.udc.cirtock.model.Herramienta;
import ar.edu.udc.cirtock.view.intranet.negocio.FormularioHerramienta;

public class HerramientaPage extends WebPage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LinkedList<Herramienta> herramientas;
	@SuppressWarnings("unchecked")
	public HerramientaPage() {
		
		Connection conn;
		try {
			conn = CirtockConnection.getConection("cirtock", "cirtock", "cirtock");
			String patronDescripcion="";
			Integer patronCantidad=null;
			String patronNombre= "";
			herramientas = Consultas.obtenerHerramientas(conn, patronDescripcion, patronNombre, patronCantidad);
		} catch (CirtockException e) {
			
			System.out.println(e.getMessage());
		}
		
		
		add(new ListView("lista", herramientas) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem item) {
		        Herramienta herramienta = (Herramienta) item.getModelObject();
		        item.add(new Label("nombre", herramienta.getNombre()));
		        item.add(new Label("descripcion", herramienta.getDescripcion()));
		        item.add(new Label("cantidad", herramienta.getCantidad()));
			}
		});
		
	      add(new Link<FormularioHerramienta>("nuevo") {
	          /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
	          public void onClick() {
	              setResponsePage(FormularioHerramienta.class);
	          }
	      });

	}
}