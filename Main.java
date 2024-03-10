import java.io.IOException;
import java.util.Scanner;

public class Main {
    static final int SIZE = 10;
    static final String ENTER_SHIP_COORDINATE_PROMPT = "Geben Sie die %skoordinaten für eine Schiff der Länge %d ein:";
    static final int ALL_HIT = 14;


    static int distance(final Coordinate start, final Coordinate end) {
        return Math.abs(start.column() - end.column()) + Math.abs(start.row() - end.row());
    }

    static Coordinate getRandomCoordinate() {
        return new Coordinate(Utility.getRandomInt(SIZE), Utility.getRandomInt(SIZE));
    }

    static boolean onOneLine(final Coordinate start, final Coordinate end) {
        return (start.row() == end.row() || start.column() == end.column());
    }

    static void showSeparatorLine() {
        System.out.println("   +-+-+-+-+-+-+-+-+-+-+      +-+-+-+-+-+-+-+-+-+-+");
    }

    static int getMaxSurroundingColumn(final Coordinate start, final Coordinate end) {
        int index = Math.max(start.column(), end.column());

        if (index == 9) {
            return index;
        } else {
            index++;
            return index;
        }
    }

    static int getMaxSurroundingRow(final Coordinate start, final Coordinate end) {
        int index = Math.max(start.row(), end.row());

        if (index == 9) {
            return index;
        } else {
            index++;
            return index;
        }
    }

    static int getMinSurroundingColumn(final Coordinate start, final Coordinate end) {
        int index = Math.min(start.column(), end.column());

        if (index == 1) {
            return index;
        } else {
            index--;
            return index;
        }
    }

    static int getMinSurroundingRow(final Coordinate start, final Coordinate end) {
        int index = Math.min(start.row(), end.row());

        if (index == 1) {
            return index;
        } else {
            index--;
            return index;
        }
    }

    static Coordinate toCoordinate(final String input) {
        int column = Character.toUpperCase(input.charAt(0)) - 'A';
        int row = input.charAt(1) - '1';

        return new Coordinate(row, column);
    }

    static boolean isValidCoordinate(final String input) {
        if (input.toUpperCase().matches("[A-J](10|[1-9)])")) {
            return true;
        }
        return false;
    }

    static String getStartCoordinatePrompt(final int length) {
        return String.format(ENTER_SHIP_COORDINATE_PROMPT, "Start", length);
    }

    static String getEndCoordinatePrompt(final int length) {
        return String.format(ENTER_SHIP_COORDINATE_PROMPT, "End", length);
    }

    static void showRowNumber(final int row) {
        if (row + 1 < 10) {
            System.out.print(" ");
        }
        System.out.print(row + 1);
    }

    static String grade(final int points) {
        String result = "";
        if (points > 100 || points < 0) {
            result = "Ungültige Punktzahl";
        } else if (points <= 100 && points >= 97) {
            result = "1,0";
        } else if (points <= 96 && points >= 92) {
            result = "1,3";
        } else if (points <= 91 && points >= 89) {
            result = "1,7";
        } else if (points <= 88 && points >= 85) {
            result = "2,0";
        } else if (points <= 84 && points >= 81) {
            result = "2,3";
        } else if (points <= 80 && points >= 77) {
            result = "2,7";
        } else if (points <= 76 && points >= 72) {
            result = "3,0";
        } else if (points <= 71 && points >= 67) {
            result = "3,3";
        } else if (points <= 66 && points >= 59) {
            result = "3,7";
        } else if (points <= 58 && points >= 50) {
            result = "4,0";
        } else if (points <= 49 && points >= 0) {
            result = "5,0";
        }

        return result;
    }


    static Coordinate getRandomEndCoordinate(final Coordinate start, final int distance) {
        int choices = 0;
        if (start.row() - distance >= 0) {
            choices++;
        }
        if (start.row() + distance < SIZE) {
            choices++;
        }
        if (start.column() - distance >= 0) {
            choices++;
        }
        if (start.column() + distance < SIZE) {
            choices++;
        }
        int choice = Utility.getRandomInt(choices);

        if (start.row() - distance >= 0) {
            choice--;
            if (choice < 0) {
                return new Coordinate(start.column(), start.row() - distance);
            }
        }
        if (start.row() + distance < SIZE) {
            choice--;
            if (choice < 0) {
                return new Coordinate(start.column(), start.row() + distance);
            }
        }
        if (start.column() - distance >= 0) {
            choice--;
            if (choice < 0) {
                return new Coordinate(start.column() - distance, start.row());
            }
        }
        return new Coordinate(start.column() + distance, start.row());
    }

    static void showField(final Field field, final boolean showShips) {
        if (showShips == true) {
            System.out.print("O");
        } else {
            System.out.print(" ");
        }

        switch (field) {
            case Field.FREE:
                System.out.print(" ");
                break;
            case Field.WATER_HIT:
                System.out.print("X");
                break;
            case Field.SHIP_HIT:
                System.out.print("*");
                break;
        }
    }

    static void placeShip(final Coordinate start, final Coordinate end, final Field[][] field) {
        if (start.column() == end.column()) {
            for (int i = Math.min(start.column(), end.column()); i < Math.max(start.column(), end.column()); i++) {
                field[start.row()][i] = Field.SHIP;
            }
        } else {
            for (int i = Math.min(start.row(), end.row()); i < Math.max(start.row(), end.row()); i++) {
                field[i][start.column()] = Field.SHIP;
            }
        }
    }


    static void showRow(final int row, final Field[][] ownField, final Field[][] otherField) {
        showRowNumber(row);
        System.out.print(" |");
        for (int i = 0; i < SIZE; i++) {
            showField(ownField[row][i], true);
            System.out.print("|");
        }
        System.out.print("   ");
        showRowNumber(row);
        System.out.print(" |");

        for (int j = 0; j < SIZE; j++) {
            showField(otherField[row][j], false);
            System.out.print("|");
        }

        System.out.println();
    }

    static void showFields(final Field[][] ownField, final Field[][] otherField) {
        System.out.println("    A B C D E F G H I J        A B C D E F G H I J");
        for (int row = 0; row < SIZE; row++) {
            showSeparatorLine();
            showRow(row, ownField, otherField);
        }
        showSeparatorLine();
        System.out.println();
    }


    static boolean shipSunk(final Coordinate shot, final Field[][] field) {
        int row = shot.row();
        int column = shot.column();
        while (row > 0 && Field.SHIP_HIT == field[row][column]) {
            row--;
        }
        if (Field.SHIP == field[row][column]) {
            return false;
        }
        row = shot.row();
        column = shot.column();
        while (row < SIZE - 1 && Field.SHIP_HIT == field[row][column]) {
            row++;
        }
        if (Field.SHIP == field[row][column]) {
            return false;
        }
        row = shot.row();
        column = shot.column();
        while (column < SIZE - 1 && Field.SHIP_HIT == field[row][column]) {
            column++;
        }
        if (Field.SHIP == field[row][column]) {
            return false;
        }
        row = shot.row();
        column = shot.column();
        while (column > 0 && Field.SHIP_HIT == field[row][column]) {
            column--;
        }
        if (Field.SHIP == field[row][column]) {
            return false;
        }
        return true;
    }

    static void setAllFree(final Field[][] field) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = Field.FREE;
            }
        }
    }

    static int countHits(final Field[][] field) {
        int counter = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == Field.SHIP_HIT) {
                    counter++;
                }
            }
        }
        return counter;
    }

    static void fillWaterHits(final Coordinate shot, final Field[][] field) {
        int row = shot.row();
        int column = shot.column();
        while (row > 0 && Field.SHIP_HIT == field[row][column]) {
            row--;
        }
        int minRow = row;
        row = shot.row();
        column = shot.column();
        while (row < SIZE - 1 && Field.SHIP_HIT == field[row][column]) {
            row++;
        }
        int maxRow = row;
        row = shot.row();
        column = shot.column();
        while (column < SIZE - 1 && Field.SHIP_HIT == field[row][column]) {
            column++;
        }
        int maxColumn = column;
        row = shot.row();
        column = shot.column();
        while (column > 0 && Field.SHIP_HIT == field[row][column]) {
            column--;
        }
        int minColumn = column;
        for (row = minRow; row <= maxRow; row++) {
            for (column = minColumn; column <= maxColumn; column++) {
                if (field[row][column] == Field.FREE) {
                    field[row][column] = Field.WATER_HIT;
                }
            }
        }
    }


    static boolean noConflict(final Coordinate start, final Coordinate end, final Field[][] field) {
        int minRow = Math.min(start.row(), end.row());
        int maxRow = Math.max(start.row(), end.row());
        int minColumn = Math.min(start.column(), end.column());
        int maxColumn = Math.max(start.column(), end.column());

        if (start.row() == end.row()) {
            for (int i = minRow; i <= maxRow; i++) {
                if (field[i][start.column()] != Field.FREE) {
                    return false;
                }
            }
        } else {
            for (int i = minColumn; i <= maxColumn; i++) {
                if (field[start.row()][i] != Field.FREE) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    static Coordinate readCoordinate(final String prompt) throws IOException {

        String eingabe = "";
        while (!isValidCoordinate(eingabe)) {
            System.out.println(prompt);
            try {
                eingabe = Utility.readStringFromConsole();
            } catch (IOException e) {
                eingabe = "";
            }
        }
        return toCoordinate(eingabe);
    }

    static Coordinate getRandomUnshotCoordinate(final Field[][] field) {
        int chances = 0;
        for (int row = 0; row < field.length; row++) {
            for (int column = 0; column < field[0].length; column++) {
                if (field[row][column] == Field.FREE || field[row][column] == Field.SHIP) {
                    chances++;
                }
            }
        }

        int rand = Utility.getRandomInt(chances);
        for (int row = 0; row < field.length; row++) {
            for (int column = 0; column < field[0].length; column++) {
                if (field[row][column] == Field.FREE || field[row][column] == Field.SHIP) {
                    rand--;
                    if (rand < 0) return new Coordinate(column, row);
                }
            }
        }
        throw new IllegalStateException();
    }

    static Coordinate readEndCoordinate(final int length) {
        Scanner scanner = new Scanner(System.in);
        getEndCoordinatePrompt(length);
        String input = scanner.next();
        input += length;
        return toCoordinate(input);
    }


    static Coordinate readStartCoordinate(final int length) {
        Scanner scanner = new Scanner(System.in);
        getStartCoordinatePrompt(length);
        String input = scanner.next();
        input += length;
        return toCoordinate(input);
    }

    static boolean allHit(final Field[][] field) {
        return countHits(field) == ALL_HIT;
    }

    static boolean endCondition(final Field[][] ownField, final Field[][] otherField) {
        if (allHit(ownField) || allHit(otherField)) {
            return true;
        }
        return false;
    }

    static boolean validPosition(final Coordinate start, final Coordinate end, final int length, final Field[][] field) {
        if (noConflict(start, end, field) && onOneLine(start, end) && distance(start, end) == length - 1) {
            return true;
        }
        return false;
    }

    static void shot(final Coordinate shot, final Field[][] field) {
        switch (field[shot.row()][shot.column()]) {
            case Field.FREE:
                field[shot.row()][shot.column()] = Field.WATER_HIT;
                break;
            case Field.SHIP:
                field[shot.row()][shot.column()] = Field.SHIP_HIT;
                if (shipSunk(shot, field)) {
                    fillWaterHits(shot, field);
                }
                break;
        }

    }


    static void turn(final Field[][] ownField, final Field[][] otherField) throws IOException {
        showFields(ownField, otherField);
        shot(readCoordinate("Geben Sie die Koordinaten Ihres nächsten Schusses ein: "), otherField);
        shot(getRandomUnshotCoordinate(ownField), ownField);
    }


    static void outputWinner(final Field[][] ownField, final Field[][] otherField) {
        showFields(ownField, otherField);
        if (allHit(ownField)) {
            System.out.println("Du hast gewonnen");
        }
        if (allHit(otherField)) {
            System.out.println("Computer hat gewonnen");
        }
    }



    public static void main(String[] args) {
        test();
    }


    //------------------------------------------------------------------------------------//


    static void test() {
//        Coordinate a = new Coordinate (SIZE, SIZE);
//        System.out.println( getMaxSurroundingColumn(a, a) );
//        System.out.println(getMaxSurroundingColumn(new Coordinate(1, 2), new Coordinate(2, 3)));
//        System.out.println(getMaxSurroundingColumn(new Coordinate(9, 2), new Coordinate(2, 3)));
//        System.out.println(getMinSurroundingColumn(new Coordinate(1, 2), new Coordinate(2, 3)));
//        System.out.println(getMinSurroundingColumn(new Coordinate(0, 2), new Coordinate(2, 3)));
//        System.out.println(getMaxSurroundingRow(new Coordinate(1, 2), new Coordinate(2, 3)));
//        System.out.println(getMaxSurroundingRow(new Coordinate(1, 9), new Coordinate(2, 3)));
//        System.out.println(getMinSurroundingRow(new Coordinate(1, 2), new Coordinate(2, 3)));
//        System.out.println(getMinSurroundingRow(new Coordinate(1, 0), new Coordinate(2, 3)));
//        System.out.println(toCoordinate("c5"));
    }

    static boolean istZahlGeradeUngerade(int input) {
        if (input % 2 != 0) {
            return false;
        } else {
            return true;
        }
    }

    static void welcherWochentagIstHeute(int input) {
        if (input == 1) {
            System.out.println("Montag");
        } else if (input == 2) {
            System.out.println("Dienstag");
        } else if (input == 3) {
            System.out.println("Mittwoch");
        } else if (input == 4) {
            System.out.println("Donnerstag");
        } else if (input == 5) {
            System.out.println("Freitag");
        } else if (input == 6) {
            System.out.println("Samstag");
        } else if (input == 7) {
            System.out.println("Sonntag");
        }
    }

    static void welcherMonat(int input) {
        switch (input) {
            case 1:
                System.out.println("Januar");
                break;

            case 2:
                System.out.println("Februar");
                break;

            case 3:
                System.out.println("März");
                break;

            case 4:
                System.out.println("April");
                break;

            case 5:
                System.out.println("Mai");
                break;

            case 6:
                System.out.println("Juni");
                break;

            case 7:
                System.out.println("Juli");
                break;

            case 8:
                System.out.println("August");
                break;

            case 9:
                System.out.println("September");
                break;

            case 10:
                System.out.println("Oktober");
                break;

            case 11:
                System.out.println("November");
                break;

            case 12:
                System.out.println("Dezember");
                break;

        }
    }

    static int max(final int[] array) {
        try {
            if (array.length == 0) ;
        } catch (Exception e) {
            throw new IllegalArgumentException("Array ungültig");
        }

        int maximum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maximum) {
                maximum = array[i];
            }
        }
        return maximum;
    }
}