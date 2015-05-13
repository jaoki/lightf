package lightf.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;

@Path("/filecontent")
public class FileContent {
	
	@XmlRootElement
	public static class FileContentResult {
		public String content; // TODO this should be off heap.
	}

	@GET
	public FileContentResult get(){
		return new FileContentResult();
	}

}
