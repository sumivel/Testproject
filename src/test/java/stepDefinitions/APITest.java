package stepDefinitions;


import java.util.List;


import java.io.IOException;

import org.junit.Assert;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class APITest {
		

	public static Response response;
	public static String jsonresponse;
	public  boolean flag, flag2; 
		
		//private String ENDPOINT_MUSIC = "https://testapi.io/api/ottplatform/media";

		@Given("I  send GET HTTP request {string}")
		public void i_send_get_http_request(String string)throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
			System.out.print(1);
			
			String ENDPOINT_MUSIC = string;
			response = RestAssured.get(ENDPOINT_MUSIC); 
			
			System.out.println(response.getHeader("Date"));
			System.out.println("welcome1 " );
			System.out.print(1);
			//throw new io.cucumber.java.PendingException();
			
		}
		@Then("I recieve valid HTTP response code {int}")
		public void i_recieve_valid_http_response_code(Integer int1)throws Throwable {
			System.out.println(response.getStatusCode());
			Assert.assertEquals(int1.intValue(), response.getStatusCode());
			
			
		}

		@Then("verify response time below {int} ms")
		public void verify_response_time_below_ms(Integer int1) {
			long millis=response.getTime();
			boolean less_res = false;
			System.out.println(millis);
			System.out.println(int1.longValue());
			if (millis < int1.longValue()){  less_res = true;} else { less_res = false;}
		        	  Assert.assertEquals( less_res, true);
			
		}
		
		@Then("verify id field is not null or empty")
		public void verify_id_field_is_not_null_or_empty() throws IOException {
			jsonresponse = response.asString();	
			String str = "id";
			 boolean is_id_null = true;
			 String key = new String("data.");
			 key.concat(str);
			 System.out.println(key);
			 		  System.out.println("test3");
			  List<String> id = JsonPath.from(jsonresponse).get(key);
	    	  for(int i=0;i<id.size();i++) {
	    		String idn=  String.valueOf(id.get(i));  
	    	  if(idn.equals("null")||idn.equals("")) {
	    		  System.out.println(idn); 
	    		  is_id_null=false;
	    	  }
	    	  else {System.out.println(idn);}
	    	  }
	    	  Assert.assertEquals( is_id_null, true);
		    
		}
		
		@Then("segment_type for all track is {string}")
		public void segment_type_for_all_track_is(String string) {
			boolean is_segment_all_music = true; 
			//jsonresponse = response.asString();
			List<String> segmenttype = JsonPath.from(jsonresponse).get("data.segment_type");
	    	  for(int i=0;i<segmenttype.size();i++) {
	      		String idn=segmenttype.get(i);    	
	      	  if(!idn.equals(string)) {
	      		   		   is_segment_all_music=false;
	      	  }
	      	  }
	    	  Assert.assertEquals( is_segment_all_music, true);
	    		
		}
		@Then("verify {string} field in the title is not null or empty")
		public void verify_primary_field_in_the_title_is_not_null_or_empty(String string) {
			boolean is_field_title_null = true;
			jsonresponse = response.asString();
			List<String> primaryfield = JsonPath.from(jsonresponse).get("data.title_list.primary");
	    	  for(int i=0;i<primaryfield.size();i++) {
	    		  String idn=  String.valueOf(primaryfield.get(i)); 
	      		 if(idn.equals(null)||idn.equals("")) {
	      			 		is_field_title_null=false;
	       	  }
	      		 }
	    	  Assert.assertEquals( is_field_title_null, true);
	      
		  
		}

		@Then("verify only one track list has {string} field in {string} as true")
		public void verify_only_one_track_list_has_field_in_as_true(String string, String string2) throws IOException {
			
			int count=0;
			jsonresponse = response.asString();
		     List<String> nowplaying = JsonPath.from(jsonresponse).get("data.offset.now_playing");
			for(int i=0;i<nowplaying.size();i++) {
				String idn=  String.valueOf(nowplaying.get(i));  
		  	  if(idn.equals("true")) {
		  		  count++;
		  	  }}
			Assert.assertEquals( count, 1);
			 
		  
		}
		
		
	}
