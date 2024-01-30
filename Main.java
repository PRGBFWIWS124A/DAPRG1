public class Main {

    static final int SIZE = 10;
    static final String ENTER_SHIP_COORDINATE_PROMPT = "Geben Sie die %s für eine Schiff der Länge %d ein:";

    static int distance(final Coordinate start, final Coordinate end) {
        return Math.abs(start.column() - end.column()) + Math.abs(start.row() - end.row());
    }

    static Coordinate getRandomCoordinate() {
        return new Coordinate(Utility.getRandomInt(SIZE), Utility.getRandomInt(SIZE));
    }

    static boolean onOneLine(final Coordinate start, final Coordinate end){
        return (start.row() == end.row() || start.column() == end.column());
    }

    static void showSeparatorLine() {
        System.out.println("   +-+-+-+-+-+-+-+-+-+-+      +-+-+-+-+-+-+-+-+-+-+");
    }

    static int getMaxSurroundingColumn(final Coordinate start, final Coordinate end){
        int index = Math.max(start.column(), end.column());
        if(index > SIZE){
            return -1;
        }
        else if(index == 9){
            return index;
        }
        else {
            return index++;
        }
    }

    static int getMaxSurroundingRow(final Coordinate start, final Coordinate end) {
        int index = Math.max(start.row(), end.row());
        if(index > SIZE){
            return -1;
        }
        else if(index == 9){
            return index;
        }
        else {
            return index++;
        }
    }

    static int getMinSurroundingColumn(final Coordinate start, final Coordinate end) {
        int index = Math.min(start.column(), end.column());
        if(index > SIZE){
            return -1;
        } else if (index == 0) {
            return index;
        } else {
            return index--;
        }
    }

    static int getMinSurroundingRow(final Coordinate start, final Coordinate end) {
        int index = Math.min(start.row(), end.row());
        if(index > SIZE){
            return -1;
        }
        else if (index == 0) {
            return index;
        }
        else {
            return index--;
        }
    }

    static Coordinate toCoordinate(final String input) {
        if(input.length()!=2){
            return null;
        }

        char columnChar = Character.toUpperCase(input.charAt(0));
        int column = columnChar - 'A';
        int row = input.charAt(1) - '1';

        return new Coordinate(row, column);
    }

    static boolean isValidCoordinate(final String input) {
        if (input.length() != 2) {
            return false;
        }

        /* TODO */

    }

    static String getStartCoordinatePrompt(final int length) {
        return String.format(ENTER_SHIP_COORDINATE_PROMPT, "Startkoordinaten", length);
    }

    static String getEndCoordinatePrompt(final int length) {
        return String.format(ENTER_SHIP_COORDINATE_PROMPT, "Endkoordinaten", length);
    }

    public static void main(String[] args) {
        //Coordinate a = new Coordinate (SIZE, SIZE);
        //System.out.println( getMaxSurroundingColumn(a, a) );
        System.out.println(getMaxSurroundingColumn(new Coordinate(1,2), new Coordinate(2,3)));
        System.out.println(getMaxSurroundingColumn(new Coordinate(9,2), new Coordinate(2,3)));
        System.out.println(getMinSurroundingColumn(new Coordinate(1,2), new Coordinate(2,3)));
        System.out.println(getMinSurroundingColumn(new Coordinate(0,2), new Coordinate (2,3)));
        System.out.println(getMaxSurroundingRow(new Coordinate(1,2),new Coordinate (2,3)));
        System.out.println(getMaxSurroundingRow(new Coordinate(1,9), new Coordinate (2,3)));
        System.out.println(getMinSurroundingRow(new Coordinate(1,2),new Coordinate (2,3)));
        System.out.println(getMinSurroundingRow(new Coordinate(1,0), new Coordinate (2,3)));
        System.out.println(toCoordinate("c5"));
    }
}