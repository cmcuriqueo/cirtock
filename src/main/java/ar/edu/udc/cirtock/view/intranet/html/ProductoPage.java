package ar.edu.udc.cirtock.view.intranet.html;

import java.sql.Connection;
import java.util.LinkedList;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;

import ar.edu.udc.cirtock.db.CirtockConnection;
import ar.edu.udc.cirtock.db.Consultas;
import ar.edu.udc.cirtock.exception.CirtockException;
import ar.edu.udc.cirtock.model.Insumo;
import ar.edu.udc.cirtock.model.Producto;
import ar.edu.udc.cirtock.view.IndexPage;
import ar.edu.udc.cirtock.view.intranet.negocio.FormularioHerramienta;
import ar.edu.udc.cirtock.view.intranet.negocio.FormularioProducto;

public class ProductoPage extends WebPage {

    private static final long serialVersionUID = 1L;
    public LinkedList<Producto> productos;
    public TextField<String> busquedaInput;
    public Form formBusqueda;
    public Link<IndexPage> cerrar;
    public Button busqueda;
    Link<InsumoPage> insumo;
    Link<HerramientaPage> herramienta;
    ListView lista;
    
    public ProductoPage() {
    {
        Connection conn;
        try {
            conn = CirtockConnection.getConection("cirtock", "cirtock", "cirtock");
            productos = Consultas.obtenerProductos(conn, "", "", null);
        } catch (CirtockException e) {
            System.out.println(e.getMessage());
        }
        lista = new ListView("lista", productos) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem item) {
                Insumo insumo = (Insumo) item.getModelObject();
                item.add(new Label("nombre", insumo.getNombre()));
                item.add(new Label("descripcion", insumo.getDescripcion()));
                item.add(new Label("cantidad", insumo.getCantidad()));
            }
        };
    }
        
        cerrar = new Link<IndexPage>("cerrar") {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setResponsePage(IndexPage.class);
                getSession().invalidate();
            }
        };
        busqueda = new Button("busquedaBoton") {

            @Override
            public void onSubmit() {
                System.out.println("ENTROD");
                String busqueda = busquedaInput.getModelObject();
                Connection conn;
                try {
                    conn = CirtockConnection.getConection("cirtock", "cirtock", "cirtock");
                    productos = Consultas.obtenerProductos(conn, null, busqueda, null);
                } catch (CirtockException e) {
                    System.out.println(e.getMessage());
                }
            }
        };
        insumo = new Link<InsumoPage>("insumo") {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setResponsePage(InsumoPage.class);
            }
        };
        herramienta = new Link<HerramientaPage>("herramienta") {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setResponsePage(HerramientaPage.class);
            }
        };
        
        formBusqueda = new Form("form_busqueda");

        busquedaInput = new TextField<>("busquedaInput", new Model<String>());
        formBusqueda.add(busquedaInput);
                formBusqueda.add(new Button("busquedaBoton") {

            @Override
            public void onSubmit() {
                String busqueda = busquedaInput.getModelObject();
                Connection conn;
                try {
                    conn = CirtockConnection.getConection("cirtock", "cirtock", "cirtock");
                    productos = Consultas.obtenerProductos(conn, null, busqueda, null);
                    lista.setList(productos);
                } catch (CirtockException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        
        formBusqueda.add(busqueda);
        add(cerrar);
        add(formBusqueda);

        add(insumo);
        add(herramienta);
        add(lista);

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
