package lightf.api.master;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import lightf.fs.Metadata.File;
import lightf.fs.Metadata.FileSystemElement;
import lightf.fs.MetadataHolder;

@Path("/cat")
public class CatCommand {
	
	@XmlRootElement
	public static class CatResult {
		private String content;

		public String getContent() { return content; }
		public void setContent(String content) { this.content = content; }
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public CatResult get(@QueryParam("path") String path){
		FileSystemElement file = MetadataHolder.metadata.getFileSystemElement(path);
		File f = (File)file;
		CatResult c = new CatResult();
		c.setContent(f.fileContentHandle.getContent());
		return c;
	}

}
