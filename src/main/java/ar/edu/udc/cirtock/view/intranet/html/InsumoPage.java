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
import ar.edu.udc.cirtock.view.IndexPage;
import ar.edu.udc.cirtock.view.intranet.negocio.FormularioInsumo;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

/**
 *
 * @author Cesar
 */
public class InsumoPage extends WebPage {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public LinkedList<Insumo> insumos = null;
    public Link<IndexPage> cerrar;
    public Link<ProductoPage> producto;
    public Link<HerramientaPage> herramienta;
    public ListView lista;
    public TextField<String> busquedaInput;
    public Form formBusqueda;

    {
        Connection conn;
        try {
            conn = CirtockConnection.getConection("cirtock", "cirtock", "cirtock");
            Integer patronCantidad = null;
            insumos = Consultas.obtenerInsumos(conn, "", "", patronCantidad);
        } catch (CirtockException e) {
            System.out.println(e.getMessage());
        }
        lista = new ListView("lista", insumos) {

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

    @SuppressWarnings("unchecked")
    public InsumoPage() {
        cerrar = new Link<IndexPage>("cerrar") {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setResponsePage(IndexPage.class);
            }
        };
        producto = new Link<ProductoPage>("producto") {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setResponsePage(ProductoPage.class);
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

        busquedaInput = new TextField<String>("busquedaInput", new Model<String>());
        formBusqueda.add(busquedaInput);

        formBusqueda.add(new Button("busquedaBoton") {

            @Override
            public void onSubmit() {
                String busqueda = busquedaInput.getModelObject();
                Connection conn;
                try {
                    conn = CirtockConnection.getConection("cirtock", "cirtock", "cirtock");
                    insumos = Consultas.obtenerInsumos(conn, null, busqueda, null);
                    lista.setList(insumos);
                } catch (CirtockException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        
        add(formBusqueda);

        add(cerrar);
        add(producto);
        add(herramienta);

        add(lista);

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
