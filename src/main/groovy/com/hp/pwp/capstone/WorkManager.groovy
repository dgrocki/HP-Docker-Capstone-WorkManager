package com.hp.pwp.capstone
import com.surftools.BeanstalkClientImpl.ClientImpl
import com.surftools.BeanstalkClientImpl.JobImpl
import com.surftools.BeanstalkClient.BeanstalkException


class WorkManager{
	private ClientImpl connection = new ClientImpl("0.0.0.0", 11300);
	private JobImpl currentJob;	//can we only be working on one job at a time?

	public void sendWork(String json){
		long priority = 0;
		int delaySeconds = 0;
		int timeToRun = 10;
		byte[] data = json.getBytes();
		
		connection.put(priority, delaySeconds, timeToRun, data);
		
	}	
	public JobImpl recieveWork(){
		currentJob = connection.reserve();
		return currentJob;
	}
	public void deleteWork(long id){	//when should we delete the job off of the queue?
		connection.delete(id);
		
	}
	public static void main(String [] args) {
		WorkManager wm = new WorkManager();		

		String input = "Hello World";	
		wm.sendWork(input);
		JobImpl job = wm.recieveWork();
		
		println "Byte array comming back:\n"+ job.data;
		String s = new String(job.data);
		println "Byte array converted back to string:\n" + s;
		
		wm.deleteWork(job.jobId);

		return;
	}

}
