package lightf.fs;

import java.util.ArrayList;
import java.util.List;

import lightf.fs.Metadata.Dir;
import lightf.fs.Metadata.File;
import lightf.fs.Metadata.FileSystemElement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestMetadata {
	@Test
	public void test1(){
		Metadata metadata = new Metadata();
		List<FileSystemElement> children1 = new ArrayList<Metadata.FileSystemElement>();
		Dir d1 = new Dir("d1");
		Dir d11 = new Dir("d11");
		d1.childlen.add(d11);
		File f12 = new File("f12");
		d1.childlen.add(f12);

		Dir d2 = new Dir("d2");

		children1.add(d1);
		children1.add(d2);

		metadata.root.childlen = children1;
		
		List<FileSystemElement> children = metadata.findChildren("/d1");
		Assert.assertEquals(children.size(), 2);
		Assert.assertEquals(children.get(0).name, "d11");
		Assert.assertEquals(children.get(1).name, "f12");
		
		FileSystemElement file = metadata.getFileSystemElement("/d1/f12");
		Assert.assertEquals(file.name, "f12");
//		Assert.assertNull(file.content); TODO fix this

	}

}
