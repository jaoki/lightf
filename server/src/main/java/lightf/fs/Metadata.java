package lightf.fs;

import java.util.ArrayList;
import java.util.List;

import lightf.api.FileContent;

public class Metadata {
	public Dir root;
	
	public Metadata(){
		root = new Dir(null);
	}
	
	public List<FileSystemElement> findChildren(String path){
		List<FileSystemElement> children = new ArrayList<FileSystemElement>();
		for(Dir child : MetadataHolder.metadata.root.childlen){
			children.add(child);
			
		}
		return children;

	}
	
	public static abstract class FileSystemElement{
		public String name;
		public Dir parent;

		public FileSystemElement(String name) {
			this.name = name;
			this.parent = null;
		}

	}
	
	public static class Dir extends FileSystemElement{
		public List<Dir> childlen;

		public Dir(String name) {
			super(name);
			this.childlen = null;
		}

	}
	
	public static class File extends FileSystemElement{
		public Dir parent;
		public FileContent content;
		public File(String name) {
			super(name);
		}
	}

}
