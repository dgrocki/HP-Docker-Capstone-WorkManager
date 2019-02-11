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
	private RiakClient client = RiakClient.newClient(8087, "172.17.0.2");
	
	private Location location = new Location(new Namespace("Test Bucker"), "TestKey" );
	
	public String fetch(){
		FetchValue fv = new FetchValue.Builder(location).build();
		FetchValue.Response response = client.execute(fv);

		return response.getValue(String.class);

	}

	public Boolean store(String myData){
		StoreValue sv = new StoreValue.Builder(myData).withLocation(location).build();
		StoreValue.Response svResponse = client.execute(sv);
		return svResponse;

	}















}
