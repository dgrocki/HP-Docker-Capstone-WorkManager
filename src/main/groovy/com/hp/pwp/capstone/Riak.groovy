package com.hp.pwp.capstone
import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.cap.Quorum;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

class Riak{
	RiakClient client = RiakClient.newClient(8087, "172.17.0.2");
	
	Location location = new Location(new Namespace("Test Bucker"), "TestKey" );
	String myData = "this is my data";
	StoreValue sv = new StoreValue.Builder(myData).withLocation(location).build();
	StoreValue.Response svResponse = client.execute(sv);

	
	FetchValue fv = new FetchValue.Builder(location).build();
	FetchValue.Response response = client.execute(fv);

	String value = response.getValue(String.class);
	public String myData(){
		return value;
	}
}
