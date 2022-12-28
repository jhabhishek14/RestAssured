import io.restassured.RestAssured;
import static io.restassured.RestAssured.*; //by default given will give an error and
//it will not resolve by hovering over it, because it comes from a static package which you need to be manually import.
//.* will pull out all the packages under RestAssured in this case
import static org.hamcrest.Matchers.*;//This is need to use EqualTo method in below then section

import PayloadOrBody.PayloadOrbody;


public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//validate if Add place APi working as expected.
		//Rest assured works on 3 principles Given(All the input details, when (Submit the API (resource and method)) 
		//, then(Validate the response)
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		given().log().all().queryParam("Key", "qaclick123").header("Content-Type","application/json")
		//We have pasted below json body and eclipse convert it in this format which java accept 
		.body(PayloadOrbody.AddPlace())
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"));
		
		//Add place->update place with new add -->and get an verify
		
		
	}

}
