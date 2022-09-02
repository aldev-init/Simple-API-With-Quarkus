package Controller;

import DTO.CourseAddRequest;
import DTO.CourseUpdateRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseService {

    private List<Map<String,Object>> courseList;

    public CourseService(){
        courseList = new ArrayList<>();
    }

    @GET
    @Path("/course")
    public Response getCourse(){
        return Response.ok(courseList).build();
    }

    @GET
    @Path("/course/{id}")
    public Response getByIdCourse(
            @PathParam("id") int id
    ){
        for(Map<String,Object> course : courseList){
            if(course.get("Id").equals(id)){
                return Response.ok(course).build();
            }
        }
        return Response.ok("Data Tidak Ditemukan").build();
    }

    @POST
    @Path("/course")
    public Response addCourse(CourseAddRequest request){
        //cara untuk request body by course tapi pake json object
        Map<String,Object> data = new HashMap<>();
        String name = request.Name;
        int id = request.Id;
        String difficult = request.Difficult;
        long price = request.Price;

        data.put("Id",id);
        data.put("Nama",name);
        data.put("Difficult",difficult);
        data.put("Price",price);

        courseList.add(data);//add to list
        return Response.ok().entity(data).build();
    }

    @PUT
    @Path("course/{id}")
    public Response updateCourse(
            @PathParam("id") int id,
            CourseUpdateRequest request
    ){
        for(Map<String,Object> course : courseList){
            if(course.get("Id").equals(id)){
                course.put("Nama",request.Name);
                course.put("Difficult",request.Difficult);
                course.put("Price",request.Price);
                return Response.ok(courseList).build();
            }
        }

        return Response.ok("Data Tidak Ditemukan").build();
    }

    @DELETE
    @Path("course/{id}")
    public Response deleteCourse(
            @PathParam("id") int id
    ){
        for(Map<String,Object> course : courseList){
            if(course.get("Id").equals(id)){
                courseList.remove(course);
                return Response.ok("Data Berhasil Dihapus").build();
            }
        }

        return Response.ok("Data Tidak Ditemukan").build();
    }
}