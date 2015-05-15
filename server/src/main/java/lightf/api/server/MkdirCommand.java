package lightf.api.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;

@Path("/mkdir")
public class MkdirCommand {
	
	@XmlRootElement
	public static class MkdirResult {

	}

	@GET
	public MkdirResult get(){
		return new MkdirResult();
	}

}
