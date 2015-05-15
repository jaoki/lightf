package lightf.api.slave;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

@Path("/data")
public class DataApi {
	
	@XmlRootElement
	public static class FileContentResult {
		public String content; // TODO this should be off heap.
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public FileContentResult get(@QueryParam("blockId") String blockId){
		return new FileContentResult();
	}

}
