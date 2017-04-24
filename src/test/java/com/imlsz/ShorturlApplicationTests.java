package com.imlsz;

import com.imlsz.web.IndexController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShorturlApplicationTests {
	private MockMvc mvc;
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
	}
	@Test
	public void testIndex() throws Exception {
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/"));
		actions.andExpect(status().isOk());
	}


}
