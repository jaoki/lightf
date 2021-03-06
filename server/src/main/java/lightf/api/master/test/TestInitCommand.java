package lightf.api.master.test;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;

import lightf.fs.Metadata;
import lightf.fs.Metadata.Dir;
import lightf.fs.Metadata.File;
import lightf.fs.Metadata.FileSystemElement;
import lightf.fs.MetadataHolder;

@Path("/test/init")
public class TestInitCommand {
	
	@XmlRootElement
	public static class AResult {

	}

	@GET
	public AResult get(){
		MetadataHolder.metadata = new Metadata();
		List<FileSystemElement> children1 = new ArrayList<Metadata.FileSystemElement>();
		Dir d1 = new Dir("d1");
		Dir d11 = new Dir("d11");
		File f12 = new File("f12");
		d1.childlen.add(d11);
		d1.childlen.add(f12);
		Dir d2 = new Dir("d2");
		children1.add(d1);
		children1.add(d2);
		MetadataHolder.metadata.root.childlen = children1;
		return new AResult();
		
	}

}
