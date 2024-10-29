package org.acme;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Path("/session")
public class SessionResource {

    @Context
    HttpServletRequest request;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String createSession() throws UnknownHostException {
        HttpSession session = request.getSession(true);
        Integer count = (Integer) session.getAttribute("count");
        if (count == null) {
            count = 0;
        }
        count++;
        session.setAttribute("count", count);

        String hostname = InetAddress.getLocalHost().getHostName();

        return "{ \"jsessionid\": \"" + session.getId() + "\", " +
                "\"count\": " + count + ", " +
                "\"hostname\": \"" + hostname + "\" }";
    }
}
