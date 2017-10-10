package com.dvdrental.controler;

import com.dvdrental.dao.IActorDAO;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvdrental.entity.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

@Controller
@RequestMapping("user")
public class DVDrentalController {
	@Autowired
	private IActorDAO actorDAO;    
    
    // Sakila database
    @GetMapping("actors")
	public ResponseEntity<JSONArray> getAllActors() {
		List<Actor> listActors = actorDAO.getAllActors();
        
        JSONObject responseJsons = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        int i = 0;
        for( Actor actor: listActors ) {
            JSONObject ja = new JSONObject();
            ja.put("actorId", actor.getActorId());
            ja.put("firstName", actor.getFirstName());
            ja.put("lastName", actor.getLastName());
            // ja.put("lastUpdate", actor.getLastUpdate());
            // ja.put("filmActors", actor.getFilmActors());
            jsonArray.add(ja);
            if (i++ >= 10)
                break;
        }
        
        System.out.println(jsonArray);
        
        responseJsons.put("Actors", jsonArray);
        
        System.out.println(responseJsons);
        
		return new ResponseEntity<>(jsonArray, HttpStatus.OK);
	}
    
	@GetMapping("actor/{id}")
	public ResponseEntity<JSONObject> getActorById(@PathVariable("id") Short id) throws ParseException {
		List<Actor> actors = actorDAO.getActorById(id);
        Actor actor = actors.get(0);
        
        JSONObject res = new JSONObject();
        res.put("actorId", actor.getActorId());
        res.put("firstName", actor.getFirstName());
        res.put("lastName", actor.getLastName());
        res.put("lastUpdate", actor.getLastUpdate());
        //res.put("filmActors", actor.getFilmActors());
 
        /* Broke at gson.toJson(actor)
        Gson gson = new Gson();
        JSONParser parser = new JSONParser();
        
        String jsonString = gson.toJson(actor);
        
        JSONObject res = (JSONObject)parser.parse(jsonString);
        */
        System.out.println(res);
        
		return new ResponseEntity<>(res, HttpStatus.OK);
	} 
      
} 