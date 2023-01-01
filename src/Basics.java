import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

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
		
		//adding a new addres <post>
		String response = given().log().all().queryParam("Key", "qaclick123").header("Content-Type","application/json")
		.body(PayloadOrbody.AddPlace())	
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)").extract().asString();
		
		//System.out.println(response);
		JsonPath js =new JsonPath(response); //here we are converting string response to json by using Jsonpath
		String Placeid_response = js.getString("place_id");//here we are getting the placeid from json response (look at json response)
		
		//updating the address <put>
		given().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "  \"place_id\": \""+Placeid_response+"\",\r\n"
				+ "  \"address\": \"70 summer walk, usa\",\r\n"
				+ "  \"key\": \"qaclick123\"\r\n"
				+ "}")		
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		//verifying the updated address<Get>
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body("Placeid_response"))
		.when().get("maps/api/place/update/json")
		.then().assertThat().log().all().body("address", equalTo("70 summer walk, usa"));
		
		
		//Add place->update place with new add -->and get an verify
		
		
	}

}
