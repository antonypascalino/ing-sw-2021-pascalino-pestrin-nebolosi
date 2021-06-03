import it.polimi.ingsw.view.MainMenu;
import it.polimi.ingsw.view.data.BasicData;
import it.polimi.ingsw.view.data.PlayerData;
import org.junit.Test;

import java.util.Scanner;

//Testing the client menu and request sender
public class ClientTest {

    //Test the creation of a new player and the print of a menu
    @Test
    public void testNewPlayer()
    {
        Scanner scanner = new Scanner(System.in);
        PlayerData data = new BasicData("Rango");
        //MainMenu menu = new MainMenu(data);
        //menu.menuMaker();

    }
}
