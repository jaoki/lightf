package lightf.api.server;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import lightf.fs.Metadata.File;
import lightf.fs.Metadata.FileSystemElement;
import lightf.fs.MetadataHolder;

@Path("/touch")
public class TouchCommand {
	
	@XmlRootElement
	public static class TouchResult {
		public TouchResult() {}
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public TouchResult get(@QueryParam("path") String path, @QueryParam("content") String content){
		List<FileSystemElement> children = MetadataHolder.metadata.findChildren(path);
		File f = (File)children.get(0);
		f.fileContentHandler.content = content;
		return new TouchResult();
	}

}
