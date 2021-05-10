package it.polimi.ingsw.connection;

import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Player.BasicPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler implements Runnable {

        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private ArrayList<Game> games;

        public ClientHandler(Socket socket, ArrayList<Game> games) throws IOException {
            this.games = games;
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        }

        @Override
        public void run() {
            try
            {
                while (true)
                {
                    String line = in.readLine() ;
                    if(line.equals("new game"))
                    {
                        String num = in.readLine();
                        System.out.println(num);
                        int playerNum = Integer.parseInt(num);
                        String nickName = in.readLine();
                        ArrayList<Player> tmp = new ArrayList<Player>();
                        Player first = new BasicPlayer(nickName);
                        tmp.add(first);

                        //Deciding the new game id
                        int newId;
                        if(games.size() == 0)
                            newId = 0;
                        else
                            newId = games.get(games.size()-1).getGameId();
                        Game game = new Game(tmp, DefaultCreator.produceDevCard(),newId);
                        System.out.println("new game created with ID: " + newId);
                    }

                        //System.out.println("Ricevuto un messaggio");
                    if(line.equals("quit")){
                        break;
                    } else {
                        out.println(line);
                        out.flush();
                    }
                }
                //close connections
                in.close();
                out.close();
                socket.close();
            } catch (IOException e){
                System.err.println(e.getMessage());
            }
        }

}



