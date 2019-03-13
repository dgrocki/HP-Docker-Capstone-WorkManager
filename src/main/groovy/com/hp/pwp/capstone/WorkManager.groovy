package com.hp.pwp.capstone
import com.surftools.BeanstalkClientImpl.ClientImpl
import com.surftools.BeanstalkClientImpl.JobImpl
import com.surftools.BeanstalkClient.BeanstalkException
import static groovyx.gpars.actor.Actors.actor

class WorkManager{

	public static void main(String [] args) {

	
		def riak = actor{
				println System.getenv("BEANSTALK");

			BeanstalkClient beanstalk = new BeanstalkClient();		
			Riak riak_client = new Riak();
			
			while(1){
				String new_work = beanstalk.recieve_riak_work();
				println "Storing in riak... ";
				riak_client.store(new_work);
				println "Fetching from riak... ";
				println riak_client.fetch();
			}

		}

		def await_new_work = actor {
				println System.getenv("BEANSTALK");
			BeanstalkClient beanstalk = new BeanstalkClient();		
			while(1){

				String new_work = beanstalk.recieve_new_work();
				
				println "Recieved new work: \n" + new_work;
				
				

				beanstalk.send_to_workerB(new_work);
			}

		}



		[riak, await_new_work]*.join()


			return;
	}

}
