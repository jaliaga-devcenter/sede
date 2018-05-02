package terealco.sedeelectronica.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import teralco.sedeelectronica.utils.EncryptUtils;

public class EncryptUtilsTest {

	@Autowired
	private EncryptUtils encrypt;

	@Test
	public void testEncrypt() {
		this.encrypt = new EncryptUtils();
		this.encrypt.setFileStrKey("Bar12345Bar12345");
		this.encrypt.init();
		String clear = "testo to raro";
		String in = this.encrypt.encrypt(clear);
		String out = this.encrypt.decrypt(in);

		assertEquals(out, clear);
	}
}
