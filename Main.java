public class Main {

    static final int SIZE = 10;
    static final String ENTER_SHIP_COORDINATE_PROMPT = "Geben Sie die %skoordinaten für eine Schiff der Länge %d ein:";

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
            return index++;
        }
    }

    static int getMaxSurroundingRow(final Coordinate start, final Coordinate end) {
        int index = Math.max(start.row(), end.row());

        if (index == 9) {
            return index;
        } else {
            return index++;
        }
    }

    static int getMinSurroundingColumn(final Coordinate start, final Coordinate end) {
        int index = Math.min(start.column(), end.column());

        if (index == 1) {
            return index;
        } else {
            return index--;
        }
    }

    static int getMinSurroundingRow(final Coordinate start, final Coordinate end) {
        int index = Math.min(start.row(), end.row());

        if (index == 1) {
            return index;
        } else {
            return index--;
        }
    }

    static Coordinate toCoordinate(final String input) {

        char columnChar = Character.toUpperCase(input.charAt(0));
        int column = columnChar - 'A';
        int row = input.charAt(1) - '1';

        return new Coordinate(row, column);
    }

    static boolean isValidCoordinate(final String input) {
        if (input.length() != 2) {
            return false;
        }

        if (input.toUpperCase().matches("[A-J](10|[1-9)]")) {
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

    public static void main(String[] args) {
        test();
    }


    static void test() {
        //Coordinate a = new Coordinate (SIZE, SIZE);
        //System.out.println( getMaxSurroundingColumn(a, a) );
        System.out.println(getMaxSurroundingColumn(new Coordinate(1, 2), new Coordinate(2, 3)));
        System.out.println(getMaxSurroundingColumn(new Coordinate(9, 2), new Coordinate(2, 3)));
        System.out.println(getMinSurroundingColumn(new Coordinate(1, 2), new Coordinate(2, 3)));
        System.out.println(getMinSurroundingColumn(new Coordinate(0, 2), new Coordinate(2, 3)));
        System.out.println(getMaxSurroundingRow(new Coordinate(1, 2), new Coordinate(2, 3)));
        System.out.println(getMaxSurroundingRow(new Coordinate(1, 9), new Coordinate(2, 3)));
        System.out.println(getMinSurroundingRow(new Coordinate(1, 2), new Coordinate(2, 3)));
        System.out.println(getMinSurroundingRow(new Coordinate(1, 0), new Coordinate(2, 3)));
        System.out.println(toCoordinate("c5"));
    }

    static boolean istZahlGeradeUngerade(int input) {
        if (input % 2 != 0) {
            return false;
        } else {
            return true;
        }
    }

        static void welcherWochentagIstHeute(int input){
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
    }