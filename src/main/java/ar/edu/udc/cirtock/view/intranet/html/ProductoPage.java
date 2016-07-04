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
import ar.edu.udc.cirtock.model.Producto;
import ar.edu.udc.cirtock.view.intranet.negocio.FormularioHerramienta;
import ar.edu.udc.cirtock.view.intranet.negocio.FormularioProducto;

public class ProductoPage extends WebPage {
	private static final long serialVersionUID = 1L;
	public LinkedList<Producto> productos;
	
	public ProductoPage() {
		
	    add(new Link<InsumoPage>("insumo") {
	          /**
			 * 
			 */
		private static final long serialVersionUID = 1L;

			@Override
		    public void onClick() {
				setResponsePage(InsumoPage.class);
		    }
	    });
	    add(new Link<HerramientaPage>("herramienta") {
	          /**
			 * 
			 */
		private static final long serialVersionUID = 1L;

			@Override
		    public void onClick() {
				setResponsePage(HerramientaPage.class);
		    }
	    });
	    
		Connection conn;
		try {
			conn = CirtockConnection.getConection("cirtock", "cirtock", "cirtock");
			String patronDescripcion="";
			Integer patronCantidad=null;
			String patronNombre= "";
			productos = Consultas.obtenerProductos(conn, patronDescripcion, patronNombre, patronCantidad);
		} catch (CirtockException e) {
			System.out.println(e.getMessage());
		}

		add(new ListView("lista", productos) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem item) {
				Producto producto = (Producto) item.getModelObject();
		        item.add(new Label("nombre", producto.getNombre()));
		        item.add(new Label("descripcion", producto.getDescripcion()));
			}
		});
		
	    add(new Link<FormularioProducto>("nuevo") {
	    /**
	     * 
	     */
			private static final long serialVersionUID = 1L;
	
				@Override
			    public void onClick() {
					setResponsePage(FormularioProducto.class);
			    }
	    });
	}
}
