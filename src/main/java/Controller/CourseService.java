package Controller;

import DTO.Course.CourseAddRequest;
import DTO.Course.CourseUpdateRequest;
import Models.CourseModel;
import Models.MentorModel;
import io.vertx.core.json.JsonObject;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

/*
all method for manipulate data like insert,update,delete using @Transactional anotations
 */


@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseService {

    @GET
    @Path("/course")
    public Response getCourse(){
        List<CourseModel> courseList = CourseModel.listAll();
        return Response.ok(courseList).build();
    }

    @GET
    @Path("/course/{id}")
    public Response getByIdCourse(
            @PathParam("id") long id
    ){
        return CourseModel.findByIdOptional(id)
                .map(course -> Response.ok(course).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/course/filter")
    public Response getByDifficultCourse(
            @QueryParam("diff") String difficult
    ){
        /*
        query to sql,but maybe different using hibernate orm this for filter data
        note:dont forget to mapping your entity,use @Entity(name="") before use this
         */
        List<CourseModel> courseFilter = CourseModel.list("SELECT m FROM course m WHERE difficult = ?1 ORDER BY id_course DESC",difficult);
        return Response.ok(courseFilter).build();
    }

    @POST
    @Transactional
    @Path("/course")
    public Response addcourse(CourseAddRequest request){
        CourseModel courseInsert = new CourseModel();
        courseInsert.name = request.name;
        courseInsert.price = request.price;
        courseInsert.difficult = request.difficult;
        courseInsert.mentor = MentorModel.findById(request.mentor);
        //save
        CourseModel.persist(courseInsert);

        JsonObject result = new JsonObject();
        result.put("Status",true);
        result.put("Data",request);
        return Response.ok(result).build();
        //error post data
    }

    @PUT
    @Transactional
    @Path("course/{id}")
    public Response updateCourse(
            @PathParam("id") long id,
            CourseUpdateRequest request
    ){
        CourseModel course = CourseModel.findById(id);

        course.name = request.name;
        course.difficult = request.difficult;
        course.price = request.price;
        course.mentor = MentorModel.findById(request.mentor);

        CourseModel.persist(course);

        JsonObject result = new JsonObject();
        result.put("Status",true);
        result.put("Data",course);

        return Response.ok(result).build();
    }

    @DELETE
    @Transactional
    @Path("course/{id}")
    public Response deleteCourse(
            @PathParam("id") long id
    ){
        boolean delete = CourseModel.deleteById(id);
        JsonObject result = new JsonObject();
        result.put("Message","Data Berhasil Dihapus");
        if(delete){
            return Response.ok(result).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}