package ceq.maven.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class MyRestController {

	@RequestMapping(value="/{user}", method=RequestMethod.GET)
    public String getUser(@PathVariable String user) {
        return ">" + user;
    }

    @RequestMapping(value="/{user}/customers", method=RequestMethod.GET)
    List<String> getUserCustomers(@PathVariable String user) {
        return Arrays.asList("Hello " + user, "You " + user);
    }

	@RequestMapping("/X")
	public String hola() {
		return "Hola";
	}

}
