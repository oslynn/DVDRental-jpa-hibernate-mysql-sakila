package com.dvdrental.dao;
import java.util.List;
import com.dvdrental.entity.Actor;
public interface IActorDAO {
    List<Actor> getAllActors();
    List<Actor> getActorById(Short actorId);
    void addActor(Actor actor);
    void updateActor(Actor actor);
    void deleteActor(Short actorId);
    boolean actorExists(String title, String category);
}
 