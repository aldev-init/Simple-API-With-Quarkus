package Controller;

import DTO.Mentor.AddMentorRequest;
import DTO.Mentor.UpdateMentorRequest;
import Models.CourseModel;
import Models.MentorModel;
import io.vertx.core.json.JsonObject;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MentorService {

    @GET
    @Path("/mentor")
    public Response getAllMentor(){
        List<MentorModel> mentor = MentorModel.listAll();
        return Response.ok(mentor).build();
    }

    @GET
    @Path("/mentor/{id}")
    public Response getMentorById(
            @PathParam("id") long id
    ){
         return MentorModel.findByIdOptional(id)
                 .map(mentor -> Response.ok(mentor).build())
                 .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/mentor/filter")
    public Response getMentorByStatus(
            @QueryParam("status") int status
    ){
        List<MentorModel> mentor = MentorModel.list("SELECT m FROM mentor m WHERE status = ?1 ORDER BY id desc",status);
        return Response.ok(mentor).build();
    }

    @POST
    @Path("/mentor")
    @Transactional
    public Response addMentor(AddMentorRequest request){
        MentorModel mentor = new MentorModel();
        mentor.name = request.name;
        mentor.gelar = request.gelar;
        mentor.status = request.status;
        MentorModel.persist(mentor);

        JsonObject result = new JsonObject();
        result.put("Status",true);
        result.put("Message","Berhasil Menambahkan Data Mentor");
        result.put("Data",mentor);

        return Response.ok(result).build();
    }

    @PUT
    @Path("/mentor/{id}")
    @Transactional
    public Response updateMentor(
            @PathParam("id") long id,
            UpdateMentorRequest request
    ){
        MentorModel mentor = MentorModel.findById(id);
        mentor.name = request.name;
        mentor.gelar = request.gelar;
        mentor.status = request.status;

        MentorModel.persist(mentor);

        JsonObject result = new JsonObject();
        result.put("Status",true);
        result.put("Message","Berhasil Mengubah Data Mentor");
        result.put("Data",mentor);

        return Response.ok(result).build();
    }

    @DELETE
    @Path("/mentor/{id}")
    @Transactional
    public Response deleteMentor(
            @PathParam("id") long id
    ){
        boolean delete = MentorModel.deleteById(id);
        JsonObject result = new JsonObject();
        result.put("Message","Data Berhasil Dihapus");
        if(delete){
            return Response.ok(result).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
