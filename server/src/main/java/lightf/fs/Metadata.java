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
		String[] pathDirNames;
		if(path.startsWith("/")){
			pathDirNames = path.replaceFirst("/", "").split("/");
		}else{
			pathDirNames = path.split("/");
		}

		List<FileSystemElement> target = MetadataHolder.metadata.root.childlen;
		for(int i = 0 ; i < pathDirNames.length; i++){
			String pathDirName = pathDirNames[i];
			for(FileSystemElement child : target){
				if(child.name.equals(pathDirName)){
					target = child.childlen;
					if(i == pathDirNames.length - 1){
						return target;
					}
				}
			}
		}

		throw new RuntimeException("can not find " + path);
	}
	
	public static abstract class FileSystemElement{
		public String name;
		public Dir parent;
		public List<FileSystemElement> childlen;

		public FileSystemElement(String name) {
			this.name = name;
			this.parent = null;
			this.childlen = new ArrayList<Metadata.FileSystemElement>();
		}

	}
	
	public static class Dir extends FileSystemElement{

		public Dir(String name) {
			super(name);
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
