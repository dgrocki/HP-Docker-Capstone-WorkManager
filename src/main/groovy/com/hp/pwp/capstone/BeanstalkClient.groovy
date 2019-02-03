package com.hp.pwp.capstone

import com.surftools.BeanstalkClientImpl.ClientImpl
import com.surftools.BeanstalkClientImpl.JobImpl
import com.surftools.BeanstalkClient.BeanstalkException


class BeanstalkClient{
	private ClientImpl connection = new ClientImpl("0.0.0.0", 11300);
	private JobImpl currentJob;	//can we only be working on one job at a time?
	public List<String> listTubes(){
		connection.useTube("riak")
			return connection.listTubes();
	}

	public void sendWork(String json){
		long priority = 0;
		int delaySeconds = 0;
		int timeToRun = 10;
		byte[] data = json.getBytes();

		connection.put(priority, delaySeconds, timeToRun, data);
	}	
	//functionhere for testing purposes
	public void useTube(String s){
		connection.useTube(s);
	}
	//pull a new job off the new_work queue
	public String recieve_new_work(){
		connection.watchTube("new_work");
			
		JobImpl job = beanstalk.recieveWork();
		
		String s = new String(job.data);
		println s;
		
		connection.delete(job.jobId);
			
		return s;
	}
	//put a new job on the to_workerB queue
	public void send_to_workerB(String json){
		connection.usetTube("to_worker_b");
		sendWork(json);
		
		
	}
	//pull a job off of the riak queue
	//returns a string of json data and the job is deleted
	public String recieve_riak_work(){
		connection.watchTube("riak");
		JobImpl job = beanstalk.recieveWork();
		
		String s = new String(job.data);
		println s;
		
		connection.delete(job.jobId);
					
		return s;

	}
	//put a job on the status queue
	public void send_status(String json){
		connection.usetTube("status");
		sendWork(json);
	}



}
