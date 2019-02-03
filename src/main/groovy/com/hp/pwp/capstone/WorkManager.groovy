package com.hp.pwp.capstone
import com.surftools.BeanstalkClientImpl.ClientImpl
import com.surftools.BeanstalkClientImpl.JobImpl
import com.surftools.BeanstalkClient.BeanstalkException

class WorkManager{
	private BeanstalkClient beanstalk = new BeanstalkClient();		
	
	public static void main(String [] args) {
		WorkManager beanstalk = new WorkManager();

		BeanstalkClient beanstalk = new BeanstalkClient();		


			
		beanstalk.useTube("new_work");
		String input = "New work from the outside world";	
		beanstalk.sendWork(input);
		beanstalk.useTube("riak");
		input = "More stuff for Riak";	
		beanstalk.sendWork(input);
		beanstalk.useTube("new_work");
		input = "New work number 2 from the outside world";	
		beanstalk.sendWork(input);
		beanstalk.useTube("riak");
		input = "Time to put something in Riak from Worker B";	
		beanstalk.sendWork(input);
		
		
		beanstalk.recieve_new_work();
		beanstalk.send_to_riak();
		beanstalk.recieve_new_work();
		beanstalk.send_to_riak();
		return;
	}

}
