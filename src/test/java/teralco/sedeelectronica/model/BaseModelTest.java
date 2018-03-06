package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import teralco.sedeelectronica.model.BaseModel;

public class BaseModelTest {
	@Test
	public void testAdjudicacion() {
		BaseModel bm = new BaseModel();
		bm.setId((long) 168);
		Long id = (long) 168;
		assertEquals(id, bm.getId());
	}
}
