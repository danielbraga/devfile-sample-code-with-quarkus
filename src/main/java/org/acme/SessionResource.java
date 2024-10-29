package org.acme;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Path("/session")
public class SessionResource {

    //    @Inject
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
