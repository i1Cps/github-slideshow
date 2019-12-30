import java.util.Arrays;
import java.util.StringJoiner;

/**
     * Contains the main logic part of the game, as it processes.
     *
     */
    public class GameLogic {

    private Map map;
    private HumanPlayer humanPlayer;
    private boolean gameRunning;
    private int goldAmount;

    /**
     * Default constructor
     */
    public GameLogic() {

        goldAmount = 0;
        map = new Map();
        humanPlayer = new HumanPlayer();
        gameRunning = true;
    }

    /**
     * Checks if the game is running
     *
     * @return if the game is running.
     */
    protected boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * Returns the gold required to win.
     *
     * @return : Gold required to win.
     */
    protected String hello() {
        String getGoldRequired = "" + map.getGoldRequired();
        return getGoldRequired;
    }

    /**
     * Returns the gold currently owned by the player.
     */
    protected void gold() {
        System.out.println(goldAmount);

    }

    /**
     * Checks if movement is legal and updates player's location on the map
     * and prints if successful or not.
     *
     * @param direction : The direction of the movement.
     *
     */
    protected void move(char direction) {

        //player x coordinate
        int playerX = map.getPlayerPositionX();
        //player y coordinate
        int playerY = map.getPlayerPositionY();
        //map
        char[][] charMap = map.getMap();
        //gold1 x coordinate
        int gold1X = map.getGold1PositionX();
        //gold1 y coordinate
        int gold1Y = map.getGold1PositionY();
        //gold2 x coordinate
        int gold2X = map.getGold2PositionX();
        //gold2 y coordinate
        int gold2Y = map.getGold2PositionY();
        //object which shows if gold1 tile is present on the map or not
        int gold1 = map.getGold1();
        //object which shows if gold2 tile is present on the map or not
        int gold2 = map.getGold2();
        //object which shows if exit1 tile is present on the map or not
        int exit1 = map.getExit1();
        //object which shows if exit2 tile is present on the map or not
        int exit2 = map.getExit2();
        //exit1 x coordinate
        int exit1X = map.getExit1PositionX();
        //exit1 y coordinate
        int exit1Y = map.getExit1PositionY();
        //exit2 x coordinate
        int exit2X = map.getExit2PositionX();
        //exit2 y coordinate
        int exit2Y = map.getExit2PositionY();

        //checks if player wants to move west direction.
        if (direction == 'w') {
            //checks if this move will place player in a wall.
            if (charMap[playerY][playerX - 1] == '#') {
                System.out.println("FAIL");
            }
            //checks if player is currently on a exit1 tile. Ensures that if player moves off the exit1 tile it is
            //still visible on the map.
            else if (playerX == exit1X && playerY == exit1Y) {
                map.adjustPlayerW(playerY, playerX - 1);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX - 1);
                map.setPlayerPositionY(playerY);
                if (exit1 == 1) {
                    map.resetExit1Tile();
                }
            }
            //checks if player is currently on a exit2 tile. Ensures that if player moves off the exit2 tile it is
            //still visible on the map.
            else if (playerX == exit2X && playerY == exit2Y) {
                map.adjustPlayerW(playerY, playerX - 1);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX - 1);
                map.setPlayerPositionY(playerY);
                if (exit2 == 1) {
                    map.resetExit2Tile();
                }
            }
            //checks if player is currently on a gold1 tile. Ensures that if player moves off the gold1 tile it is
            //still visible on the map assuming player hasn't used "pickup".
            else if (playerX == gold1X && playerY == gold1Y) {
                map.adjustPlayerW(playerY, playerX - 1);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX - 1);
                map.setPlayerPositionY(playerY);
                if (gold1 == 1) {
                    map.resetGold1Tile();
                }
            }
            //checks if player is currently on a gold2 tile. Ensures that if player moves off the gold2 tile it is
            //still visible on the map assuming player hasn't used "pickup".
            else if (playerY == gold2X && playerY == gold2Y) {
                map.adjustPlayerW(playerY, playerX - 1);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX - 1);
                map.setPlayerPositionY(playerY);
                if (gold2 == 1) {
                    map.resetGold2Tile();
                }
            } else {
                map.adjustPlayerW(playerY, playerX - 1);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX - 1);
                map.setPlayerPositionY(playerY);
            }

        }

        //checks if player wants to move north direction.
        else if (direction == 'n') {
            //checks if it will place player on wall
            if (charMap[playerY - 1][playerX] == '#') {
                System.out.println("FAIL");
            }
            //checks if player is currently on a exit1 tile. Ensures that if player moves off the exit1 tile it is
            //still visible on the map.
            else if (playerX == exit1X && playerY == exit1Y) {
                map.adjustPlayerN(playerY - 1, playerX);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX);
                map.setPlayerPositionY(playerY - 1);
                if (exit1 == 1) {
                    map.resetExit1Tile();
                }
            }
            //checks if player is currently on a exit2 tile. Ensures that if player moves off the exit2 tile it is
            //still visible on the map.
            else if (playerX == exit2X && playerY == exit2Y) {
                map.adjustPlayerN(playerY - 1, playerX);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX);
                map.setPlayerPositionY(playerY - 1);
                if (exit2 == 1) {
                    map.resetExit2Tile();
                }
            }
            //checks if player is currently on a gold1 tile. Ensures that if player moves off the gold1 tile it is
            //still visible on the map assuming player hasn't used "pickup".
            else if (playerX == gold1X && playerY == gold1Y) {
                map.adjustPlayerN(playerY - 1, playerX);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX);
                map.setPlayerPositionY(playerY - 1);
                if (gold1 == 1) {
                    map.resetGold1Tile();
                }
            }
            //checks if player is currently on a gold2 tile. Ensures that if player moves off the gold2 tile it is
            //still visible on the map assuming player hasn't used "pickup".
            else if (playerX == gold2X && playerY == gold2Y) {
                map.adjustPlayerN(playerY - 1, playerX);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX);
                map.setPlayerPositionY(playerY - 1);
                if (gold2 == 1) {
                    map.resetGold2Tile();
                }
            } else {
                map.adjustPlayerN(playerY - 1, playerX);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX);
                map.setPlayerPositionY(playerY - 1);
            }
        } else if (direction == 'e') {
            if (charMap[playerY][playerX + 1] == '#') {
                System.out.println("FAIL");
            }
            //checks if player is currently on a exit1 tile. Ensures that if player moves off the exit1 tile it is
            //still visible on the map.
            else if (playerX == exit1X && playerY == exit1Y) {
                map.adjustPlayerE(playerY, playerX + 1);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX + 1);
                map.setPlayerPositionY(playerY);
                if (exit1 == 1) {
                    map.resetExit1Tile();
                }
            }
            //checks if player is currently on a exit2 tile. Ensures that if player moves off the exit2 tile it is
            //still visible on the map.
            else if (playerX == exit2X && playerY == exit2Y) {
                map.adjustPlayerE(playerY, playerX + 1);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX + 1);
                map.setPlayerPositionY(playerY);
                if (exit2 == 1) {
                    map.resetExit2Tile();
                }
            }
            //checks if player is currently on a gold1 tile. Ensures that if player moves off the gold1 tile it is
            //still visible on the map assuming player hasn't used "pickup".
            else if (playerX == gold1X && playerY == gold1Y) {
                map.adjustPlayerE(playerY, playerX + 1);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX + 1);
                map.setPlayerPositionY(playerY);
                if (gold1 == 1) {
                    map.resetGold1Tile();
                }
            }
            //checks if player is currently on a gold2 tile. Ensures that if player moves off the gold2 tile it is
            //still visible on the map assuming player hasn't used "pickup".
            else if (playerX == gold2X && playerY == gold2Y) {
                map.adjustPlayerE(playerY, playerX + 1);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX + 1);
                map.setPlayerPositionY(playerY);
                if (gold2 == 1) {
                    map.resetGold2Tile();
                }
            } else {
                map.adjustPlayerE(playerY, playerX + 1);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX + 1);
                map.setPlayerPositionY(playerY);
            }

        } else if (direction == 's') {
            if (charMap[playerY + 1][playerX] == '#') {
                System.out.println("FAIL");
            }
            //checks if player is currently on a exit1 tile. Ensures that if player moves off the exit1 tile it is
            //still visible on the map.
            else if (playerX == exit1X && playerY == exit1Y) {
                map.adjustPlayerS(playerY + 1, playerX);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX);
                map.setPlayerPositionY(playerY + 1);
                if (exit1 == 1) {
                    map.resetExit1Tile();
                }
            }
            //checks if player is currently on a exit2 tile. Ensures that if player moves off the exit2 tile it is
            //still visible on the map.
            else if (playerX == exit2X && playerY == gold2Y) {
                map.adjustPlayerS(playerY + 1, playerX);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX);
                map.setPlayerPositionY(playerY + 1);
                if (exit2 == 1) {
                    map.resetExit2Tile();
                }
            }
            //checks if player is currently on a gold1 tile. Ensures that if player moves off the gold1 tile it is
            //still visible on the map assuming player hasn't used "pickup".
            else if (playerX == gold1X && playerY == gold1Y) {
                map.adjustPlayerS(playerY + 1, playerX);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX);
                map.setPlayerPositionY(playerY + 1);
                if (gold1 == 1) {
                    map.resetGold1Tile();
                }
            }
            //checks if player is currently on a gold2 tile. Ensures that if player moves off the gold2 tile it is
            //still visible on the map assuming player hasn't used "pickup".
            else if (playerX == gold2X && playerY == gold2Y) {
                map.adjustPlayerS(playerY + 1, playerX);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX);
                map.setPlayerPositionY(playerY + 1);
                if (gold2 == 1) {
                    map.resetGold2Tile();
                }
            } else {
                map.adjustPlayerS(playerY + 1, playerX);
                System.out.println("SUCCESS");
                map.setPlayerPositionX(playerX);
                map.setPlayerPositionY(playerY + 1);
            }
        }
    }

    /**
     * Converts the map from a 2D char array to a single string.
     */
    protected void look() {
        //gets 5x5 map
        char[][] charMap = map.mapTo5x5Grid();
        //gets player position
        int playerX = map.getPlayerPositionX();
        int playerY = map.getPlayerPositionY();


        StringJoiner sj = new StringJoiner((System.lineSeparator()));
        //converts map to string
        for (char[] row : charMap) {
            sj.add(Arrays.toString(row));
        }
        String result = sj.toString();
        //prints string version
        System.out.println(result);
    }


    /**
     * Processes the player's pickup command, updating the map and the player's gold amount.
     * prints out if the player successfully picked-up gold or not and how much gold they now have.
     */
    protected void pickup() {
        //gets current player positions
        int x = map.getPlayerPositionX();
        ;
        int y = map.getPlayerPositionY();

        //checks if player is on gold1 tile
        if (x == map.getGold1PositionX() && y == map.getGold1PositionY()) {
            //increases gold amount by 1
            goldAmount += 1;
            System.out.println("SUCCESS. Gold owned: " + goldAmount);
            //lets system know that gold1 shouldn't be represented on the map anymore
            map.setGold1(0);
        }
        //checks if player is on gold2 tile
        else if (x == map.getGold2PositionX() && y == map.getGold2PositionY()) {
            //increases gold amount by 1
            goldAmount += 1;
            System.out.println("SUCCESS. Gold owned: " + goldAmount);
            //lets system know that gold2 shouldn't be represented on the map anymore
            map.setGold2(0);
        }
        //if none of the condition are meet code returns "FAIL" and amount of gold player has.
        else {
            System.out.println("FAIL. Gold owned: " + goldAmount);
        }
    }

    /**
     * Quits the game, shutting down the application. to successfully quit player must have required gold and
     * be on either exit tile 1 or exit tile 2. If condition is not meet, code shut downs and player progress is
     * deleted.
     */
    protected void quitGame() {
        int playerX = map.getPlayerPositionX();
        int playerY = map.getPlayerPositionY();
        int exit1X = map.getExit1PositionX();
        int exit1Y = map.getExit1PositionY();
        int exit2X = map.getExit2PositionX();
        int exit2Y = map.getExit2PositionY();

        //checks if player has the right amount of gold to win
        if (goldAmount == map.getGoldRequired()) {
            //checks if player is on exit1 tile
            if (playerX == exit1X && playerY == exit1Y) {
                System.out.println("WIN. NAISU!!!!!!! you won the game, EZ claps");
            }
            //checks if player is on exit2 tile
            else if (playerX == exit2X && playerY == exit2Y) {
                System.out.println("WIN. NAISU!!!!!!! you beat " + map.getMapName() + " EZ claps");
            }
            //prints "LOSE"
            else {
                System.out.println("LOSE");
            }
        }
        //prints "LOSE"
        else {
            System.out.println("LOSE");
        }
        //sets game running to false, which stop code running in the main method.
        gameRunning = false;
    }

    /**
     * Starts the game and is the only method directly called from the main.
     */
    protected void startGame() {
        map.playerStartPosition();
        map.botStartPosition();

        while (isGameRunning()) {
            //checks if bot has caught player.
            if(map.getBotPositionX() == map.getPlayerPositionX() && map.getBotPositionY() == map.getPlayerPositionY()) {
                System.out.println("The Bot caught you, unlucky :(");
                //sets game running to false, which stop code running in the main method.
                gameRunning = false;
            }
            //checks user input to see which command it is.
            String in = humanPlayer.getInputFromConsole();
            if (in.equals("Invalid")) {
                System.out.println("Invalid");
            } else if (in.equals("hello")) {
                System.out.println(hello());
            } else if (in.equals("look")) {
                look();
            } else if (in.equals("gold")) {
                gold();
            } else if (in.equals("pickup")) {
                pickup();
            } else if (in.equals("move n")) {
                move('n');
            } else if (in.equals("move s")) {
                move('s');
            } else if (in.equals("move e")) {
                move('e');
            } else if (in.equals("move w")) {
                move('w');
            } else if (in.equals("quit")) {
                quitGame();
            }
            map.randomBotMove();
        }
    }

    public static void main(String[] args) {
        GameLogic defaultMap = new GameLogic();

        defaultMap.startGame();
    }
}

