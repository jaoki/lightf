package lightf.fs;

import java.util.ArrayList;
import java.util.List;

import lightf.api.slave.FileContent;

public class Metadata {
	public Dir root;
	
	public Metadata(){
		root = new Dir(null);
	}
	
	public List<FileSystemElement> findChildren(String path){
		String[] pathDirNames;
		if(path.startsWith("/")){
			pathDirNames = path.replaceFirst("/", "").split("/");
		}else{ // TODO it should be error
			pathDirNames = path.split("/");
		}

		List<FileSystemElement> targets = root.childlen;
		for(int i = 0 ; i < pathDirNames.length; i++){
			String pathDirName = pathDirNames[i];
			for(FileSystemElement child : targets){
				if(child.name.equals(pathDirName)){
					if(!(child instanceof Dir))
						throw new RuntimeException("can not find " + path);
					targets = ((Dir)child).childlen;
					if(i == pathDirNames.length - 1){
						return targets;
					}
				}
			}
		}

		throw new RuntimeException("can not find " + path);
	}
	
	public FileSystemElement getFileSystemElement(String path){
		String[] pathDirNames;
		if(!path.startsWith("/")){
			throw new RuntimeException("should be a full path " + path);
		}

		pathDirNames = path.replaceFirst("/", "").split("/");

		List<FileSystemElement> targets = root.childlen;
		// FIXME ugly nested loops
		for(int i = 0 ; i < pathDirNames.length; i++){
			String pathDirName = pathDirNames[i];
			for(FileSystemElement child : targets){
				if(child.name.equals(pathDirName)){
					if(!(child instanceof Dir))
						throw new RuntimeException("can not find " + path);
					targets = ((Dir)child).childlen;
					if(i == pathDirNames.length - 2){
						for (FileSystemElement f : targets) {
							if(f.name.equals(pathDirNames[i+1]))
								return f;
						}
					}
				}
			}
		}
		
		

//
//		FileSystemElement target = this.root;
//		for(int i = 0 ; i < pathDirNames.length; i++){
//			String pathDirName = pathDirNames[i];
//			if(pathDirName.equals(target.name)){
//				if(i == pathDirNames.length - 1){
//					return target;
//				}
////				target = target.childlen;
//			}
//			for(FileSystemElement child : target.childlen){
//			}
//		}

		throw new RuntimeException("can not find " + path);
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
		public List<FileSystemElement> childlen;

		public Dir(String name) {
			super(name);
			this.childlen = new ArrayList<Metadata.FileSystemElement>();
		}

	}
	
	public static class File extends FileSystemElement{
		public FileContent fileContentHandler;

		public File(String name) {
			super(name);
		}
	}

}
