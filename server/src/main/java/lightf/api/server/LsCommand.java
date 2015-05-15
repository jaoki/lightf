package lightf.api.server;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import lightf.fs.Metadata.FileSystemElement;
import lightf.fs.MetadataHolder;

@Path("/ls")
public class LsCommand {
	
	@XmlRootElement
	public static class LsResult {
		public List<FileSystemElement> children;

		public LsResult() {
			children = null;
		}

		public LsResult(List<FileSystemElement> children) {
			this.children = children;
		}

	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public LsResult get(@QueryParam("path") String path){
		List<FileSystemElement> children = MetadataHolder.metadata.findChildren(path);
		return new LsResult(children);
	}

}
