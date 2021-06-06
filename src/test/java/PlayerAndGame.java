import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Player.Player;

public class PlayerAndGame {
    public Game game;
    public Player player;

    public PlayerAndGame(Game game, Player player)
    {
        this.game = game;
        this.player = player;
    }
}
