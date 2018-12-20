package com.hadoop.hdfs;

import java.security.PrivilegedExceptionAction;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;

public class HdfsTest {
	 public static void main(String args[]) {

	        try {
	            UserGroupInformation ugi
	                = UserGroupInformation.createRemoteUser("bdi");

	            ugi.doAs(new PrivilegedExceptionAction<Void>() {

	                public Void run() throws Exception {

	                    Configuration conf = new Configuration();
	                    conf.set("fs.defaultFS", "hdfs://192.168.0.170:9000/home/bdi/hdata/dfs/name");
	                    conf.set("hadoop.job.ugi", "bdi");

	                    FileSystem fs = FileSystem.get(conf);

	                    fs.createNewFile(new Path("test"));

	                    FileStatus[] status = fs.listStatus(new Path("/"));
	                    for(int i=0;i<status.length;i++){
	                        System.out.println(status[i].getPath());
	                    }
	                    return null;
	                }
	            });
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
