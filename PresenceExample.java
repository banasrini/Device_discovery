import org.json.JSONArray;

import com.pubnub.api.*;
import java.util.Arrays;
import java.util.Arrays;

public class PresenceExample {
	String uuid1 = "";
	public void publish(){
		Pubnub pubnub = new Pubnub("demo","demo");
		pubnub.setUUID("JAVAbava");
		JSONArray jsa = new JSONArray();
		jsa.put("a");
		jsa.put("b");
		pubnub.publish("iotchannel", jsa, new Callback(){
			
			@Override
			public void successCallback(String arg0, Object arg1) {
				System.out.println(arg1);
			}
		});
	}
		
	public void subscribe(){
	try {
		Pubnub pubnub = new Pubnub("demo", "demo");
		pubnub.setUUID("JAVAbava");
		String uuid = pubnub.uuid();
		System.out.print(uuid);
		pubnub.subscribe("userz", new Callback() {

		      @Override
		      public void connectCallback(String channel, Object message) {
		          System.out.println("SUBSCRIBE : CONNECT on channel:" + channel
		                     + " : " + message.getClass() + " : "
		                     + message.toString());
		      }

		      @Override
		      public void disconnectCallback(String channel, Object message) {
		          System.out.println("SUBSCRIBE : DISCONNECT on channel:" + channel
		                     + " : " + message.getClass() + " : "
		                     + message.toString());
		      }

		      public void reconnectCallback(String channel, Object message) {
		          System.out.println("SUBSCRIBE : RECONNECT on channel:" + channel
		                     + " : " + message.getClass() + " : "
		                     + message.toString());
		      }

		      @Override
		      public void successCallback(String channel, Object message) {
		          System.out.println("SUBSCRIBE : " + channel + " : "
		                     + message.getClass() + " : " + message.toString());
		      }

		      @Override
		      public void errorCallback(String channel, PubnubError error) {
		          System.out.println("SUBSCRIBE : ERROR on channel " + channel
		                     + " : " + error.toString());
		      }
		    }
		  );
		} catch (PubnubException e) {
		  System.out.println(e.toString());
		}
	}
	
	Callback subscribe_callback = new Callback() {

	    @Override
	    public void connectCallback(String channel, Object message) {
	      System.out.println("CONNECT on channel(presence):" + channel
	                 + " : " + message.getClass() + " : "
	                 + message.toString());
	    }

	    @Override
	    public void disconnectCallback(String channel, Object message) {
	      System.out.println("DISCONNECT on channel:" + channel
	                 + " : " + message.getClass() + " : "
	                 + message.toString());
	    }

	    public void reconnectCallback(String channel, Object message) {
	      System.out.println("RECONNECT on channel:" + channel
	                 + " : " + message.getClass() + " : "
	                 + message.toString());
	    }

	    @Override
	    public void successCallback(String channel, Object message) {
	      System.out.println(channel + " : "
	                 + message.getClass() + " : " + message.toString());
	    }

	    @Override
	    public void errorCallback(String channel, PubnubError error) {
	      System.out.println("ERROR on channel " + channel
	                 + " : " + error.toString());
	    }
	};
	
	public void presence(){
		Pubnub pubnub = new Pubnub("demo", "demo");
		pubnub.setUUID("JAVAbava");
		try {
			pubnub.presence("iotchannel", subscribe_callback);
		} catch (PubnubException e) {
			System.out.println(e.toString());
		}
	}
	
	Callback callback = new Callback() {
		  public void successCallback(String channel, Object response) {
		    System.out.println(response.toString());
		    String temp = response.toString();
		    int start = temp.indexOf('[');
		    int end = temp.indexOf(']');
		    for(int index = start+1;index<end;index++){
		    	
		    	if(temp.charAt(index)!=','){
		    		
		    		uuid1 = uuid1 + temp.charAt(index);
		    	}
		    	else{
		    		System.out.println();
		    	}
		    }

    		
    		String replaced = uuid1.replace("\""," ");
    		
   
    		String[] uuidlist = replaced.split("\\s+");
    		for (String ss : uuidlist){
    			System.out.println(ss);
    		}
    		
		  }
		  public void errorCallback(String channel, PubnubError error) {
			System.out.println(error.toString());
		  }
		};
	
	public void herenow(){
		Pubnub pubnub = new Pubnub("demo", "demo");
		pubnub.setUUID("JAVAbava");
		pubnub.hereNow("iotchannel", callback);
	}

	public static void main(String[] args) {
		
		new PresenceExample().publish();
		new PresenceExample().subscribe();
		new PresenceExample().presence();
		new PresenceExample().herenow();

	}

}
