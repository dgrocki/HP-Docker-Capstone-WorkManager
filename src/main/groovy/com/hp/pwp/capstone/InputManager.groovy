package com.hp.pwp.capstone
import com.surftools.BeanstalkClientImpl.ClientImpl
import com.surftools.BeanstalkClientImpl.JobImpl
import com.surftools.BeanstalkClient.BeanstalkException

class InputManager{

	public static void main(String [] args) {
		BeanstalkClient beanstalk = new BeanstalkClient();		

		beanstalk.useTube("new_work");
		while(true){
			print "Enter a string for system input: ";
			String input = System.in.newReader().readLine();

			beanstalk.sendWork(input);

			sleep(1000);
		}

		return;
	}

}

