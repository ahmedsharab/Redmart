package co.nz.kastana.android.redmart.assignment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedString;

public class TestClient implements Client {

    private List<Request> requests = new ArrayList<Request>();
    private List<Response> cannedResponses = new ArrayList<Response>();

    @Override
    public Response execute(Request request) throws IOException {
        this.requests.add(request);
        if(cannedResponses.size() > 0) {
            return cannedResponses.remove(0);
        }

        return new Response("", 200, "", new ArrayList<Header>(), new TypedString(""));
    }

    public void addResponse(Response response) {
        this.cannedResponses.add(response);
    }

    public Request getRequest(int index) {
        return requests.get(index);
    }

    public static Response response(int status, String body) {
        return new Response("", status, "", new ArrayList<Header>(), new TypedString(body));
    }

    public static Response responseWithNullBody(int status) {
        return new Response("", status, "", new ArrayList<Header>(), null);
    }
}