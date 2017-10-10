package com.dvdrental.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dvdrental.entity.Actor;
import java.util.ArrayList;
import org.hibernate.Session;

@Transactional
@Repository
public class ActorDAO implements IActorDAO {
	@PersistenceContext	
	private EntityManager entityManager;
    
    Session session = null;

    public ActorDAO() {
//        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
	@Override
	public List<Actor> getActorById(Short actorId) {
        List<Actor> actor = new ArrayList<Actor>();
        Actor act = entityManager.find(Actor.class, actorId);
        actor.add(act);
        
		return actor;
	}
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Actor> getAllActors() {
        List<Actor> listActor = null;

        String hql = "SELECT * FROM Actor; ";        
        
        listActor = entityManager.createNativeQuery(hql, Actor.class)
                .getResultList();
            
		return listActor;
	}	
	@Override
	public void addActor(Actor actor) {
		entityManager.persist(actor);
	}
	@Override
	public void updateActor(Actor actor) {
		Actor artcl = (Actor)getActorById(actor.getActorId());
		artcl.setFirstName(actor.getFirstName());
		artcl.setLastName(actor.getLastName());
		entityManager.flush();
	}
	@Override
	public void deleteActor(Short actorId) {
		entityManager.remove(getActorById(actorId));
	}
	@Override
	public boolean actorExists(String title, String category) {
		String hql = "FROM Actor as atcl WHERE atcl.title = ? and atcl.category = ?";
		int count = entityManager.createQuery(hql).setParameter(1, title)
		              .setParameter(2, category).getResultList().size();
		return count > 0 ? true : false;
	}
}
