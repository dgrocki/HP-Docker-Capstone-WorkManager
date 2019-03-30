package com.hp.pwp.capstone
import com.surftools.BeanstalkClientImpl.ClientImpl
import com.surftools.BeanstalkClientImpl.JobImpl
import com.surftools.BeanstalkClient.BeanstalkException
import static groovyx.gpars.actor.Actors.actor
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import org.eclipse.jetty.server.Server
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

class WorkManager{

	public static void main(String [] args) {



		def riak = actor{

			BeanstalkClient beanstalk = new BeanstalkClient();		
			Riak riak_client = new Riak();
			while(1){
				String new_work = beanstalk.recieve_riak_work();
				JsontoJava json = new Gson().fromJson(new_work,JsontoJava.class);
				String s = json.outputPath;
				File file = new File(s);
				byte[] fileArray;
				fileArray = Files.readAllBytes(file.toPath());
				
				String store_value = new String(fileArray);
				println "Storing in riak... ";
				riak_client.store(store_value);



				println "Fetching from riak... ";
				byte[]fetch =  riak_client.fetch();
				println fetch;
				s = "/mnt/Out.pdf";
				File file2 = new File(s);
				Files.write(file2.toPath(), fetch);
			}

		}

		def await_new_work = actor {
			println System.getenv("BEANSTALK");
			BeanstalkClient beanstalk = new BeanstalkClient();		
			beanstalk.useTube("new_work");
			while(1){

				final Jetty jetty = new Jetty(8080, beanstalk);
				jetty.start();
				Thread.sleep(500);
				if (false == jetty.isStarted()) {
					throw new Exception("Cannot start jetty server");
				}
			}

		}


		//setup all the threads
		[riak, await_new_work]*.join()

		return;
	}

}
