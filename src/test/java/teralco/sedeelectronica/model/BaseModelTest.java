package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class BaseModelTest {
	@Test
	public void testBase() {
		BaseModel bm = new BaseModel();
		bm.setId((long) 168);
		Long id = (long) 168;
		assertEquals(id, bm.getId());
		assertFalse(bm.isNew());
	}
}
