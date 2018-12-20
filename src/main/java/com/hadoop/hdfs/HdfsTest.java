package com.hadoop.hdfs;

import java.io.PrintWriter;
import java.security.PrivilegedExceptionAction;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
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
	                    conf.set("fs.defaultFS", "hdfs://192.168.0.170:9000/user/bdi");
	                    conf.set("hadoop.job.ugi", "bdi");
	                    conf.setBoolean("dfs.support.append", true);
	                    FileSystem fs = FileSystem.get(conf);

	            		Path filenamePath = new Path("test2.txt");
	            		Path reportFile = new Path("log1");
	            		if(fs.exists(filenamePath)) {
	        		        fs.delete(filenamePath, true);
	        		        fs.delete(reportFile, true);
	            		}
            		    FSDataOutputStream fin = fs.create(filenamePath);
            		    fin.writeUTF("DataBasic activities are suitable for classes and workshops for participants from middle school through higher education. No prior data experience necessary");
            		    fin.close();
	                    
	        		    
	                    FileStatus[] status = fs.listStatus(new Path("/user/bdi"));
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
