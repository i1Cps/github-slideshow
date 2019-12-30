import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

/**
 * Reads and contains in memory the map of the game.
 *
 */

public class Map {

    /* checks if gold1 exists */
    private int gold1;
    /* checks if gold2 exists */
    private int gold2;
    /* checks if exit1 exists */
    private int exit1;
    /* checks if exit2 exists */
    private int exit2;
    /* 5x5 map representation */
    private char[][] fiveByFive;
    /* Representation of the map */
    private char[][] map;
    /* exit1 positionX */
    private int exit1PositionX;
    /* exit1 positionY */
    private int exit1PositionY;
    /* exit2 positionX */
    private int exit2PositionX;
    /* exit2 positionY */
    private int exit2PositionY;
    /* gold1 positionX */
    private int gold1PositionX;
    /* gold1 positionY */
    private int gold1PositionY;
    /* gold2 positionX */
    private int gold2PositionX;
    /* gold2 positionY */
    private int gold2PositionY;
    /* Player X position */
    private int playerPositionX;
    /* Player Y position */
    private int playerPositionY;
    /* Bot X position */
    private int botPositionX;
    /* Bot Y position */
    private int botPositionY;
    /* Map name */
    private String mapName;
    /* Gold required for the human player to win */
    private int goldRequired;


    /**
     * Default constructor, creates the default map "Very small Labyrinth of doom".
     */
    public Map() {

        mapName = "Very small Labyrinth of Doom";
        goldRequired = 2;
        /* if gold1 or gold2 = 1 it means it exists, if gold1 or gold2 = 0 it means it does not exist */
        gold1 = 1;
        gold2 = 1;
        /* if exit1 or exit2 = 1 it means it exists, if exit1 or exit2 = 0 it means it does not exist */
        exit1 = 1;
        exit2 = 1;

        this.playerPositionX = 0;
        this.playerPositionY = 0;
        this.botPositionX = 0;
        this.botPositionY = 0;
        fiveByFive = new char[][]{
                {'.','.','.','.','.'},
                {'.','.','.','.','.'},
                {'.','.','.','.','.'},
                {'.','.','.','.','.'},
                {'.','.','.','.','.'},

        };



        map = new char[][]{
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', 'G', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'E', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '.', 'E', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'G', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };

        gold1PositionX = 7;
        gold1PositionY = 2;
        gold2PositionX = 12;
        gold2PositionY = 5;
        exit1PositionX = 3;
        exit1PositionY = 4;
        exit2PositionX = 17;
        exit2PositionY = 2;
    }

    /**
     * Constructor that accepts a map to read in from. (Not completed)
     *
     * @param fileName: The filename of the map file.
     */
    public Map(String fileName) {
        readMap(fileName);
    }

    /**
     * When player uses look they only see a 5x5 grid relative to where they are
     * @return fiveByFive map
     */
    protected char[][] mapTo5x5Grid() {
        // loops for fiveByFive[][] adding in the elements taking from map[][].
        for(int i = 0; i<5; i++) {
            try {
                fiveByFive[0][i] = map[playerPositionY - 2][playerPositionX + (i - 2)];
            } catch (Exception ex) {
                fiveByFive[0][i] = '#';
            }
         }
        for(int i = 0; i<5; i++) {
            try {
                fiveByFive[1][i] = map[playerPositionY - 1][playerPositionX + (i - 2)];
            } catch (Exception ex) {
                fiveByFive[1][i] = '#';
            }
        }
        for(int i = 0; i<5; i++) {
            try {
                fiveByFive[2][i] = map[playerPositionY][playerPositionX + (i - 2)];
            } catch (Exception ex) {
                fiveByFive[2][i] = '#';
            }
        }
        for(int i = 0; i<5; i++) {
            try {
                fiveByFive[3][i] = map[playerPositionY + 1][playerPositionX + (i - 2)];
            } catch (Exception ex) {
                fiveByFive[3][i] = '#';
            }
        }
        for(int i = 0; i<5; i++) {
            try {
                fiveByFive[4][i] = map[playerPositionY + 2][playerPositionX + (i - 2)];
            } catch (Exception ex) {
                fiveByFive[4][i] = '#';
            }
        }
        fiveByFive[2][2] = 'P';
        return fiveByFive;
    }

    /**
     * if a player move onto a gold tile but does not pick it up and proceeds to move off tile. this method replaces
     * gold 1 tile on map.
     */
    protected void resetGold1Tile() {
        int y = getGold1PositionY();
        int x = getGold1PositionX();
        //replaces gold1 tile
        map[y][x] = 'G';
    }

    /**
     * if a player move onto a gold tile but does not pick it up and proceeds to move off tile. this method replaces
     * gold 2 tile on map.
     */
    protected void resetGold2Tile() {
        int y = getGold2PositionY();
        int x = getGold2PositionX();
        //replaces gold2 tile
        map[y][x] = 'G';
    }

    /**
     * if a player move onto a exit tile but does not pick it up and proceeds to move off tile. this method replaces
     * exit 1 tile on map.
     */
    protected void resetExit1Tile() {
        int y = getExit1PositionY();
        int x = getExit1PositionX();
        //replaces exit1 tile
        map[y][x] = 'E';
    }

    /**
     * if a player move onto a exit tile but does not pick it up and proceeds to move off tile. this method replaces
     * exit 2 tile on map.
     */
    protected void resetExit2Tile() {
        int y = getExit2PositionY();
        int x = getExit2PositionX();
        //replaces exit2 tile
        map[y][x] = 'E';
    }

    /**
     * setter for attribute which shows if gold 1 is present or not .
     *
     * @param x if x = 1 gold 1 is present else if x = 0 gold 1 is not present ( x should only ever equal 1 or 0 ).
     */
    public void setGold1(int x) {
        this.gold1 = x;
    }

    /**
     * setter for attribute which shows if gold 2 is present or not .
     *
     * @param x if x = 1 gold 2 is present else if x = 0 gold 2 is not present ( x should only ever equal 1 or 0 ).
     */
    protected void setGold2(int x) {
        this.gold2 = x;
    }

    /**
     * getter for attribute which shows if gold 1 is present or not.
     *
     * @return attribute which shows if gold 1 if present or not.
     */
    public int getGold1() {
        return gold1;
    }

    /**
     * getter for attribute which shows if gold 2 is present or not.
     *
     * @return attribute which shows if gold 2 if present or not.
     */
    public int getGold2() {
        return gold2;
    }

    /**
     * getter for attribute which shows if exit 1 is present or not.
     *
     * @return attribute which shows if exit 1 if present or not.
     */
    public int getExit1() {
        return exit1;
    }

    /**
     * getter for attribute which shows if exit 1 is present or not.
     *
     * @return attribute which shows if exit 1 if present or not.
     */
    public int getExit2() {
        return exit2;
    }

    /**
     * Getter for x coordinate of exit 1.
     *
     * @return the x coordinate of exit 1.
     */
    public int getExit1PositionX() {
        return this.exit1PositionX;
    }

    /**
     * Getter for y coordinate of exit 1.
     *
     * @return the y coordinate of exit 1.
     */
    public int getExit1PositionY() {
        return this.exit1PositionY;
    }

    /**
     * Getter for x coordinate of exit 2.
     *
     * @return the x coordinate of exit 2.
     */
    public int getExit2PositionX() {
        return this.exit2PositionX;
    }

    /**
     * Getter for y coordinate of exit 2.
     *
     * @return the y coordinate of exit 2.
     */
    public int getExit2PositionY() {
        return this.exit2PositionY;
    }

    /**
     * Getter for y coordinate of gold 1.
     *
     * @return the y coordinate of gold 1.
     */
    public int getGold1PositionY() {
        return this.gold1PositionY;
    }

    /**
     * Getter for x coordinate of gold 1.
     *
     * @return the x coordinate of gold 1.
     */
    public int getGold1PositionX() {
        return this.gold1PositionX;
    }

    /**
     * Getter for y coordinate of gold 2.
     *
     * @return the y coordinate of gold 2.
     */
    public int getGold2PositionY() {
        return this.gold2PositionY;
    }

    /**
     * Getter for x coordinate of gold2.
     *
     * @return the x coordinate of gold2.
     */
    public int getGold2PositionX() {
        return this.gold2PositionX;
    }


    /**
     * Getter for y coordinate of bot.
     *
     * @return the y coordinate of bot.
     */
    public int getBotPositionY() {
        return this.botPositionY;
    }

    /**
     * Getter for x coordinate of bot.
     *
     * @return the x coordinate of bot.
     */
    public int getBotPositionX() {
        return this.botPositionX;
    }

    /**
     * Setter for y coordinate of bot.
     *
     * @param y coordinate.
     */
    public void setBotPositionY(int y) {
        this.botPositionY = y;
    }

    /**
     * Setter for x coordinate of bot.
     *
     * @param x coordinate.
     */
    public void setBotPositionX(int x) {
        this.botPositionX = x;
    }

    /**
     * Getter for y coordinate of player.
     *
     * @return the y coordinate of player.
     */
    public int getPlayerPositionY() {
        return this.playerPositionY;
    }

    /**
     * Getter for x coordinate of player.
     *
     * @return the x coordinate of player.
     */
    public int getPlayerPositionX() {
        return this.playerPositionX;
    }

    /**
     * Setter for y coordinate of player.
     *
     * @param y coordinate.
     */
    public void setPlayerPositionY(int y) {
        this.playerPositionY = y;
    }

    /**
     * Setter for x coordinate of player.
     *
     * @param x coordinate.
     */
    public void setPlayerPositionX(int x) {
        this.playerPositionX = x;
    }

    /**
     * gets random number which represents a direction 0 = North, 1 = East, 2 = South, 3 = West.
     */
    protected void randomBotMove() {
        Random rand = new Random();
        //generates random number between 0 and 3.
        int x = rand.nextInt(4);
        
        if (x == 0) {
            // This makes sure bot does not move into a wall
            if(map[botPositionY - 1][botPositionX] == '#') {
                randomBotMove();
            }
            // This makes sure the bot does not move into a gold tile
            else if (map[botPositionY - 1][botPositionX] == 'G') {
                randomBotMove();
            }
            // This makes sure the bot does not move into a exit tile
            else if (map[botPositionY - 1][botPositionX] == 'E') {
                randomBotMove();
            }
            else {
                adjustBotN(botPositionY - 1,botPositionX);
                botPositionY = botPositionY -1;

            }
        }
        else if (x == 1) {
            // This makes sure bot does not move into a wall
            if(map[botPositionY][botPositionX + 1] == '#') {
                randomBotMove();
            }
            // This makes sure the bot does not move into a gold tile
            else if (map[botPositionY][botPositionX + 1] == 'G') {
                randomBotMove();
            }
            // This makes sure the bot does not move into a exit tile
            else if (map[botPositionY][botPositionX + 1] == 'E') {
                randomBotMove();
            }
            else {
                adjustBotE(botPositionY,botPositionX + 1);
                botPositionX = botPositionX + 1;
            }
        }
        else if (x == 2) {
            // This makes sure bot does not move into a wall
            if(map[botPositionY + 1][botPositionX] == '#') {
                randomBotMove();
            }
            // This makes sure the bot does not move into a gold tile
            else if (map[botPositionY + 1][botPositionX] == 'G') {
                randomBotMove();
            }
            // This makes sure the bot does not move into a exit tile
            else if (map[botPositionY + 1][botPositionX] == 'E') {
                randomBotMove();
            }
            else {
                adjustBotS(botPositionY + 1,botPositionX);
                botPositionY = botPositionY + 1;
            }
        }
        else if (x == 3) {
            // This makes sure bot does not move into a wall
            if(map[botPositionY][botPositionX - 1] == '#') {
                randomBotMove();
            }
            // This makes sure the bot does not move into a gold tile
            else if (map[botPositionY][botPositionX - 1] == 'G') {
                randomBotMove();
            }
            // This makes sure the bot does not move into a exit tile
            else if (map[botPositionY][botPositionX - 1] == 'E') {
                randomBotMove();
            }
            else {
                adjustBotW(botPositionY,botPositionX - 1);
                botPositionX = botPositionX - 1;
            }
        }
    }

    /**
     * move bot tile 1 space in the east direction.
     *
     * @param y current bot y coordinate.
     * @param x current bot x coordinate.
     */
    protected void adjustBotE(int y, int x) {
        map[y][x] = 'B';
        map[y][x - 1] = '.';
    }

    /**
     * move bot tile 1 space in the south direction.
     *
     * @param y current bot y coordinate.
     * @param x current bot x coordinate.
     */
    protected void adjustBotS(int y, int x) {
        map[y][x] = 'B';
        map[y - 1][x] = '.';
    }

    /**
     * move bot tile 1 space in the North direction
     *
     * @param y current bot y coordinate
     * @param x current bot x coordinate
     */
    protected void adjustBotN(int y, int x) {
        map[y][x] = 'B';
        map[y + 1][x] = '.';
    }

    /**
     * move bot tile 1 space in the west direction
     *
     * @param y current bot y coordinate
     * @param x current bot x coordinate
     */
    protected void adjustBotW(int y, int x) {
        map[y][x] = 'B';
        map[y][x + 1] = '.';
    }

    /**
     * move player tile 1 space in the east direction.
     *
     * @param y current player y coordinate.
     * @param x current player x coordinate.
     */
    protected void adjustPlayerE(int y, int x) {
        map[y][x] = 'P';
        map[y][x - 1] = '.';
    }


    /**
     * move player tile 1 space in the south direction.
     *
     * @param y current player y coordinate.
     * @param x current player x coordinate.
     */
    protected void adjustPlayerS(int y, int x) {
        map[y][x] = 'P';
        map[y - 1][x] = '.';
    }

    /**
     * move player tile 1 space in the North direction
     *
     * @param y current player y coordinate
     * @param x current player x coordinate
     */
    protected void adjustPlayerN(int y, int x) {
        map[y][x] = 'P';
        map[y + 1][x] = '.';
    }

    /**
     * move player tile 1 space in the west direction
     *
     * @param y current player y coordinate
     * @param x current player x coordinate
     */
    protected void adjustPlayerW(int y, int x) {
        map[y][x] = 'P';
        map[y][x + 1] = '.';
    }

    /**
     * @return : Gold required to exit the current map.
     */
    protected int getGoldRequired() {
        return goldRequired;
    }

    /**
     * Random number generated between 0 and vertical length of map.
     *
     * @return : number.
     */
    protected int getRandomPositionY() {
        int y = mapSizeY();
        Random rand = new Random();
        //returns random number between 0 and vertical map size - 1 (too ensure it works for index intake)
        return rand.nextInt(y - 1);
    }

    /**
     * Random number generated between 0 and horizontal length of map.
     *
     * @return : number.
     */
    protected int getRandomPositionX() {
        int x = mapSizeX();
        Random rand = new Random();
        //returns random number between 0 and horizontal map size - 1 (too ensure it works for index intake)
        return rand.nextInt(x - 1);
    }

    /**
     * get random position for bot from method "getRandomPositionX" and "getRandomPositionY" and check if that
     * position meets. Conditions for a player to start there. Those condition are that it cannot be a Gold tile,
     * a Wall or a player.
     */
    protected void botStartPosition() {

        //gets random positions
        int y = getRandomPositionY();
        int x = getRandomPositionX();
        //this is so bot does not spawn on gold
        if (map[y][x] == 'G') {
            botStartPosition();
        }
        //this is so bot does not spawn on player
        else if (map[y][x] == 'P') {
            botStartPosition();
        }
        //this is so bot does not spawn on wall
        else if (map[y][x] == '#') {
            botStartPosition();
        }
        //this is so bot does not spawn on exit
        else if (map[y][x] == 'E') {
            botStartPosition();
        }
        else {
            map[y][x] = 'B';
            setBotPositionY(y);
            setBotPositionX(x);
        }
    }

    /**
     * get random position for player from method "getRandomPositionX" and "getRandomPositionY" and check if that
     * position meets. Conditions for a player to start there. Those condition are that it cannot be a Gold tile,
     * a Wall or a bot.
     */
    protected void playerStartPosition() {

        //gets random positions
        int y = getRandomPositionY();
        int x = getRandomPositionX();
        if (map[y][x] == 'G') {
            playerStartPosition();
        }
        else if (map[y][x] == '#') {
            playerStartPosition();
        }
        else if (map[y][x] == 'B') {
            playerStartPosition();
        }
        else {
            map[y][x] = 'P';
            setPlayerPositionY(y);
            setPlayerPositionX(x);
        }
    }

    /**
     * Method which returns vertical length of map.
     *
     * @return vertical length of map.
     */
    protected int mapSizeY() {
        return map.length;
    }

    /**
     * Method which returns horizontal length of map.
     *
     * @return horizontal length of map.
     */
    protected int mapSizeX() {
        return map[0].length;
    }

    /**
     * @return : The map as stored in memory.
     */
    protected char[][] getMap() {
        return map;
    }

    /**
     * @return : The name of the current map.
     */
    protected String getMapName() {
        return mapName;
    }

    /**
     * Reads the map from file. (Not complete)
     *
     * @param fileName: file name of map
     */
    protected void readMap(String fileName) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            mapName = reader.readLine();

               System.out.println(mapName);
            //}
        } catch (IOException e) {
        e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

