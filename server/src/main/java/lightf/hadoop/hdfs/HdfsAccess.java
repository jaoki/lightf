package lightf.hadoop.hdfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.hdfs.DistributedFileSystem;

public class HdfsAccess {
	public static void accessWithDistributedFileSystem() throws IOException, FileNotFoundException {
		DistributedFileSystem dfs = new DistributedFileSystem();
//		URI uri;
//		Configuration conf;
//		dfs.initialize(uri, conf);
		org.apache.hadoop.fs.Path f = new org.apache.hadoop.fs.Path("/");
		RemoteIterator<LocatedFileStatus> listFiles = dfs.listFiles(f, false);
		dfs.close();
	}

	public static void accessWithFileSystem() throws IOException, FileNotFoundException {
		URI uri = null;
		try {
			uri = new URI("/");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Configuration conf = new Configuration();
		conf.setQuietMode(false);
		conf.addResource("aaa.xml");
		FileSystem dfs = FileSystem.get(uri, conf);
		org.apache.hadoop.fs.Path f = new org.apache.hadoop.fs.Path("/");
		RemoteIterator<LocatedFileStatus> listFiles = dfs.listFiles(f, false);
		dfs.close();
	}
	

}
