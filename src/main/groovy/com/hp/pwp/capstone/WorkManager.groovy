package com.hp.pwp.capstone
import com.surftools.BeanstalkClientImpl.ClientImpl
import com.surftools.BeanstalkClientImpl.JobImpl
import com.surftools.BeanstalkClient.BeanstalkException
import static groovyx.gpars.actor.Actors.actor

class WorkManager{

	public static void main(String [] args) {

		def riak = actor{

			BeanstalkClient beanstalk = new BeanstalkClient();		
			Riak riak_client = new Riak();

			while(1){
				String new_work = beanstalk.recieve_riak_work();
				riak_client.store(new_work);

				println riak_client.fetch();
			}

		}

		def await_new_work = actor {
			BeanstalkClient beanstalk = new BeanstalkClient();		
			while(1){
				beanstalk.useTube("new_work");
				String input = "New work from the outside world";	
				beanstalk.sendWork(input);

				sleep(2000);

				String new_work = beanstalk.recieve_new_work();
				println "Recieved new work: \n" + new_work;
				new_work += " + worker B info";		
				beanstalk.send_to_workerB(new_work);
			}

		}



		[riak, await_new_work]*.join()


			return;
	}

}
