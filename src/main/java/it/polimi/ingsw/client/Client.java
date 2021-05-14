package it.polimi.ingsw.client;

import com.google.gson.Gson;
import it.polimi.ingsw.Request.NewGameRequest;
import it.polimi.ingsw.Request.Request;

import java.io.IOException;

public class Client
{
        public static void main(String[] args) throws IOException {
           /*LineClient client = new LineClient("127.0.0.1", 8080);
            try{
                client.startClient();
            }catch (IOException e){
                System.err.println(e.getMessage());
            }
           * */
            LineClient client = new LineClient("127.0.0.1", 8080);
            try {
                client.startClient();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String serverResponse = "test";

            Request request = new NewGameRequest("SickNebo", 3);
            System.out.println(request.toString());
            try {
                Gson gson = new Gson();
                String jsonReq = gson.toJson(request);
                serverResponse = client.sendMessage(jsonReq);
                System.out.println(serverResponse);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while(true){

            }
        }
}


