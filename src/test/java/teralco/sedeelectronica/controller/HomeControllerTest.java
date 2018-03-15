package teralco.sedeelectronica.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import teralco.sedeelectronica.app.TestApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
@WebMvcTest(DownloadController.class)
public class HomeControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void test() {

	}
}
