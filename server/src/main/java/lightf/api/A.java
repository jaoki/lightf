package lightf.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;

@Path("/ls")
public class A {
	
	@XmlRootElement
	public static class AResult {

	}

	@GET
	public AResult get(){
		return new AResult();
		
	}

}
