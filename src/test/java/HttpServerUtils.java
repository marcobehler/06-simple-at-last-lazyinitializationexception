import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Thanks for watching this episode! Send any feedback to info@marcobehler.com!
 */
public class HttpServerUtils {


    public static String wget(String website) {
        StringBuilder b = new StringBuilder();
        try {
            URL url = new URL(website);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    b.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b.toString();
    }


    public static Server startWebServer(int port, Class<? extends Servlet> servletClass) {
      return startWebServer(port, servletClass, null);
    }

    public static Server startWebServer(int port, Class<? extends Servlet> servletClass, Class<? extends Filter> filter) {
        Server server = new Server(port);

        try {
            ServletHandler handler = new ServletHandler();
            server.setHandler(handler);
            if (filter != null) {
                handler.addFilterWithMapping(filter, "/", 0);
            }
            handler.addServletWithMapping(servletClass, "/*");
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // server.join();

        return server;
    }
}
