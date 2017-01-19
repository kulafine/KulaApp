package appr.kulaf.com.kulaapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Prince on 1/19/2017.
 */

public class Server {

    private static Server server_instance;
    private RequestQueue server_request;
    private static Context context;


    public Server(Context ctx) {
        context = ctx;
        server_request = getRequest();
    }

    public RequestQueue getRequest(){
        if(server_request == null){
            server_request = Volley.newRequestQueue(context);
        }

        return server_request;
    }

    public static synchronized Server getServer_instance(Context context){
        if(server_instance == null){
            server_instance = new Server(context);
        }

        return server_instance;
    }

    public<T> void addRequest(Request<T> req){
        server_request.add(req);
    }


}
