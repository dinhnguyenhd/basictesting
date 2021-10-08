package dinhnguyen.techs.testing.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import dinhnguyen.techs.testing.exceptions.BadArgumentsException;
import dinhnguyen.techs.testing.exceptions.InternalException;
import dinhnguyen.techs.testing.exceptions.ResourceNotFoundException;

class TestExceptinonControllerTest {

	@Autowired
	private MockMvc mvc;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	public void givenNotFound_whenGetSpecificException_thenNotFoundCode() throws Exception {
		String exceptionParam = "not_found";
		mvc.perform(get("/exception/{exception_id}", exceptionParam).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
				.andExpect(result -> assertEquals("resource not found", result.getResolvedException().getMessage()));
	}

	@Test
	public void givenBadArguments_whenGetSpecificException_thenBadRequest() throws Exception {
		String exceptionParam = "bad_arguments";

		mvc.perform(get("/exception/{exception_id}", exceptionParam).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof BadArgumentsException))
				.andExpect(result -> assertEquals("bad arguments", result.getResolvedException().getMessage()));
	}

	@Test
	public void givenOther_whenGetSpecificException_thenInternalServerError() throws Exception {
		String exceptionParam = "dummy";

		mvc.perform(get("/exception/{exception_id}", exceptionParam).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InternalException))
				.andExpect(result -> assertEquals("internal error", result.getResolvedException().getMessage()));
	}

}
