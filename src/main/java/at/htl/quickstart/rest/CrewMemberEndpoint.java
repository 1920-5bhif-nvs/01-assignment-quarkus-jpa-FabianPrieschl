package at.htl.quickstart.rest;

import at.htl.quickstart.model.CrewMember;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("crewMember")
public class CrewMemberEndpoint {

    @Inject
    EntityManager em;

    @GET
    @Path("findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<CrewMember> list = em.createNamedQuery("CrewMember.findAll", CrewMember.class).getResultList();
        GenericEntity entity = new GenericEntity<List<CrewMember>>(list){};
        if(list != null && !list.isEmpty()) {
            return Response.ok(entity).build();
        } else {
            return  Response.noContent().build();
        }
    }

    @GET
    @Path("find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCrewMember(@PathParam("id") long id) {
        CrewMember cm = em.find(CrewMember.class, id);
        if(cm != null) {
            return Response.ok().entity(cm).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
