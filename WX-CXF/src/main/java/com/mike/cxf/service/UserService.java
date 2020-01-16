package com.mike.cxf.service;

import com.mike.cxf.domain.User;
import com.mike.cxf.result.Response;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
import java.util.List;

/*
  Author:Mike
  创建时间：2019/11/5
  描述：
*/
@WebService
@Path(value="/users/")
public interface UserService {

    @GET
    @Path("/")//http:ip:port/users
    @Produces({MediaType.APPLICATION_JSON})
    List<User> getUsers();

    @DELETE
    @Path("{id}")  //http://ip:port/users/1
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML}) //请求accept
    Response delete(@PathParam("id") int id);

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    User getUser(@PathParam("id") int id);

    @POST
    @Path("/add")
    @Produces({MediaType.APPLICATION_JSON})
    Response add(User user);

    @PUT
    @Path("/update")
    @Produces({MediaType.APPLICATION_JSON})
    Response update(User user);
}

