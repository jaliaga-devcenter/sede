package terealco.sedeelectronica.utils;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import teralco.sedeelectronica.utils.GeneradorUtils;

public class GeneradorUtilsTest {
	@Test
	public void testGenerar() {
		String out = GeneradorUtils.generarToken();
		assertNotNull(out);
	}

}
