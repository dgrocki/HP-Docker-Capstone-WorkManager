package com.hp.pwp.capstone
import com.surftools.BeanstalkClientImpl.ClientImpl
import com.surftools.BeanstalkClientImpl.JobImpl
import com.surftools.BeanstalkClient.BeanstalkException
import org.eclipse.jetty.server.Server
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

class WorkManager{

	public static void main(String [] args) {

		final Jetty jetty = new Jetty(8080);
		jetty.start();
		Thread.sleep(500);
		if (false == jetty.isStarted()) {
			throw new Exception("Cannot start jetty server");
		}
		//		Server server = new Server(8080);
		//		server.start();
		//		server.join();


		/*	BeanstalkClient beanstalk = new BeanstalkClient();		


			beanstalk.useTube("new_work");
			String input = "New work from the outside world";	
			beanstalk.sendWork(input);

			sleep(2000);

			String new_work = beanstalk.recieve_new_work();
			println "Recieved new work: \n" + new_work;
			beanstalk.send_to_workerB(new_work);

			new_work += " + worker B info";		

			beanstalk.useTube("to_worker_b");	
			beanstalk.sendWork(new_work);
		 */
		return;
	}

}
