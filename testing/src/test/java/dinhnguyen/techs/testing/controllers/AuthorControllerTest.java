package dinhnguyen.techs.testing.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dinhnguyen.techs.testing.entity.Author;
import dinhnguyen.techs.testing.forms.AuthorForm;
import dinhnguyen.techs.testing.services.AuthorService;

@WebMvcTest(AuthorController.class)
@RunWith(SpringRunner.class)
class AuthorControllerTest {

	@MockBean
	private AuthorService authorService;

	// @Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	// @Test
	void testGetAuthorByName() {
		Author author = new Author();
		author.setName("Jack Ma");
		author.setAddress(" Hue ");
		when(this.authorService.getAuthorByName("Jack Ma")).thenReturn(author);
		try {
			mockMvc.perform(
					MockMvcRequestBuilders.get("/author/by/{name}", "Jack Ma").accept(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jack Ma"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	void testListAuthor() {
		List<Author> authors = new ArrayList<Author>();
		Author author1 = new Author("Dinh Nguyen", "Hue");
		Author author2 = new Author("Thuy Duong", "Ho Chi Minh");
		Author author3 = new Author("Anh Nguyen ", "Da Nang");
		authors.add(author1);
		authors.add(author2);
		authors.add(author3);
		when(this.authorService.list()).thenReturn(authors);
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/author/list").accept(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.[*].name").isNotEmpty())
					.andExpect(MockMvcResultMatchers.jsonPath("$.[*].address").isNotEmpty());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	void testSave() {
		Author author = new Author();
		author.setName("Dinh Nguyen");
		author.setAddress("Ho Chi Minh ");
		this.modelMapper = new ModelMapper();
		AuthorForm form = this.modelMapper.map(author, AuthorForm.class);
		when(this.authorService.insert(form)).thenReturn(author);

		try {
			this.mockMvc
					.perform(MockMvcRequestBuilders.post("/author/save").accept(MediaType.APPLICATION_JSON)
							.content(this.objectMapper.writeValueAsString(form)).contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isCreated())
					.andExpect(MockMvcResultMatchers.jsonPath("$.author.name").exists());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Test
	void testUpdate() {
		Author author = new Author();
		author.setName("Dinh Nguyen");
		author.setAddress("Ho Chi Minh ");
		this.modelMapper = new ModelMapper();
		AuthorForm form = this.modelMapper.map(author, AuthorForm.class);
		when(this.authorService.update(form)).thenReturn(author);

		try {
			this.mockMvc
					.perform(MockMvcRequestBuilders.put("/author/save").accept(MediaType.APPLICATION_JSON)
							.content(this.objectMapper.writeValueAsString(form)).contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isCreated())
					.andExpect(MockMvcResultMatchers.jsonPath("$.author.name").exists());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void testDeleteById() {
		try {
			when(this.authorService.deleteById(3l)).thenReturn("success");
			mockMvc.perform(MockMvcRequestBuilders.delete("/author/delete/3").accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.result").value("success"));

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// Test Validation: + Throw exception
	@Test
	void testValidation() {
		try {
			mockMvc.perform(
					MockMvcRequestBuilders.post("/users").content("abd").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isBadRequest())
					.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Name is mandatory"))
					.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
