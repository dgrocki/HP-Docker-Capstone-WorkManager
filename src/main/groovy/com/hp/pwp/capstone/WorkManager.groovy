package com.hp.pwp.capstone
import com.surftools.BeanstalkClientImpl.ClientImpl
import com.surftools.BeanstalkClientImpl.JobImpl
import com.surftools.BeanstalkClient.BeanstalkException


class WorkManager{
	public static void main(String [] args) {

		long priority = 0;
		int delaySeconds = 0;
		int timeToRun = 10;
		byte[] data = "hey";
		ClientImpl connection = new ClientImpl("localhost", 11300);
		connection.put(priority, delaySeconds, timeToRun, data);

		JobImpl job = connection.reserve();
		println job.data;

		return;
	}

}
