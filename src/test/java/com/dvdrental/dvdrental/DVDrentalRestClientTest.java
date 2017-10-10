package com.dvdrental.dvdrental;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dvdrental.entity.Actor;

public class DVDrentalRestClientTest {    
    public void getActorByIdDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/actor/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Actor> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Actor.class, 1);
        Actor actor = responseEntity.getBody();
        System.out.println("Id:"+actor.getActorId()+", FirstName:"+actor.getFirstName()
                 +", Category:"+actor.getLastName());      
    }
	public void getAllActorsDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/actors";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Actor[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Actor[].class);
        Actor[] actors = responseEntity.getBody();
        for(Actor actor : actors) {
              System.out.println("Id:"+actor.getActorId()+", FirstName:"+actor.getFirstName()
                      +", Category: "+actor.getLastName());
        }
    }
    public void addActorDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/actor";
	    Actor objActor = new Actor();
	    objActor.setFirstName("Spring REST Security using Hibernate");
	    objActor.setLastName("Spring");
        HttpEntity<Actor> requestEntity = new HttpEntity<Actor>(objActor, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());    	
    }
    public void updateActorDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/actor";
	    Actor objActor = new Actor();
	    objActor.setActorId((short)1);
	    objActor.setFirstName("Update:Java Concurrency");
	    objActor.setLastName("Java");
        HttpEntity<Actor> requestEntity = new HttpEntity<Actor>(objActor, headers);
        restTemplate.put(url, requestEntity);
    }
    public void deleteActorDemoById() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/actor/{id}";
        HttpEntity<Actor> requestEntity = new HttpEntity<Actor>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);        
    }
    
    /**
    * Main
    */
    public static void main(String args[]) {
    	DVDrentalRestClientTest util = new DVDrentalRestClientTest();
        
        util.getActorByIdDemo();
    	util.getAllActorsDemo();
    	util.addActorDemo();
    	util.updateActorDemo();
    	util.deleteActorDemoById();
    }    
}
