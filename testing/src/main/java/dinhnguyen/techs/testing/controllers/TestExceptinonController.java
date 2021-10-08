package dinhnguyen.techs.testing.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dinhnguyen.techs.testing.exceptions.BadArgumentsException;
import dinhnguyen.techs.testing.exceptions.InternalException;
import dinhnguyen.techs.testing.exceptions.ResourceNotFoundException;

@RestController
public class TestExceptinonController {

	@GetMapping("/exception/{exception_id}")
	public void getSpecificException(@PathVariable("exception_id") String pException) {
		if ("not_found".equals(pException)) {
			throw new ResourceNotFoundException("resource not found");
		} else if ("bad_arguments".equals(pException)) {
			throw new BadArgumentsException("bad arguments");
		} else {
			throw new InternalException("internal error");
		}
	}

}
