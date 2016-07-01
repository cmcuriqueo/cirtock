package ar.edu.udc.cirtock;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import ar.edu.udc.cirtock.view.intranet.html.*;
import ar.edu.udc.cirtock.view.intranet.negocio.FormularioHerramienta;
import ar.edu.udc.cirtock.view.WicketApplication;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(FormularioHerramienta.class);

		//assert rendered page class
		tester.assertRenderedPage(FormularioHerramienta.class);
	}
}
