package com.khandal.demorest;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("aliens")
public class AlienResource {
	
	AlienRepository repo= new AlienRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Alien> getAliens() {
		System.out.println("we called1...");
		
		
	
		return repo.getAliens();
	}
    
	@GET
	@Path("alien/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien getAlien(@PathParam("id") int id) {
		
		return repo.getAlien(id);
	}
	
	
	@Path("alien")
	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien createAlien(Alien a) {
		
		System.out.println(a);
		
		repo.create(a);
		
		return a;
	}
	
	
	@Path("alien")
	@PUT
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien updateAlien(Alien a) {
		
		System.out.println(a);
		
		if(repo.getAlien(a.getId()).getId()==0) {
			createAlien(a);
		}
		else {
			repo.update(a);
		}
		
		
		
		return a;
	}
	
	
	@DELETE
	@Path("alien/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien killAlien(@PathParam("id") int id) {
		
		Alien a= repo.getAlien(id);
		if(a.getId()!=0) {
			repo.delete(id);
		}
		
		return a;
	}
	
	
}
