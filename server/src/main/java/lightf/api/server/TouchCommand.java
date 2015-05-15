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
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public TouchResult get(@QueryParam("path") String path, @QueryParam("content") String content){
		FileSystemElement element = MetadataHolder.metadata.getFileSystemElement(path);
		File f = (File)element;
		f.fileContentHandle.setContent(content);
		return new TouchResult();
	}

}
