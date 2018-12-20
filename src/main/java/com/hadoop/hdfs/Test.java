package com.hadoop.hdfs;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
public class Test {
	private static final String NAME_NODE = "hdfs://192.168.0.170:9000/user/bid";// nameNomeHost = localhost if you use hadoop in
																		// local mode

	public static void main(String[] args) throws URISyntaxException, IOException {
		Configuration conf = new Configuration();
		//conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
		conf.set("fs.defaultFS",NAME_NODE);
		System.setProperty("HADOOP_USER_NAME", "bdi");
		conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
		conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
		FileSystem fs = FileSystem.get(conf);
		Path filenamePath = new Path("test.txt");  
		try {
		    if (fs.exists(filenamePath)) {
		        fs.delete(filenamePath, true);
		    }

		    FSDataOutputStream fin = fs.create(filenamePath);
		    fin.writeUTF("hello");
		    fin.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

		FileStatus[] fsStatus = fs.listStatus(new Path("/"));
		for(int i = 0; i < fsStatus.length; i++){
		   System.out.println(fsStatus[i].getPath().toString());
		}
	}
}
