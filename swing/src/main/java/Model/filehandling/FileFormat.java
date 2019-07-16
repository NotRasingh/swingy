package Model.filehandling;


public class FileFormat {
    public static void fileCheck(String[] file) {

        if (!file[0].matches("\\d+?")) {
            System.out.println("Error on line 1: Value has to be a positive number");
            System.exit(1);
        }
        for (int n = 1; n < file.length; n++) {
            String[] check = file[n].split(" ");

            if ((check[1].charAt(0) != check[0].charAt(0)) || check[1].length() < 2  ){
                System.out.println("Error on line " + (n+1) + ": Invalid aircraft name");
                System.exit(1);
            }
            if (check.length != 5){
                System.out.println("Error on line " + (n+1) + ": Insufficient values");
                System.exit(1);
            }
            if (!check[2].matches("-?\\d+?")) {
                System.out.println("Error on line " + (n+1) + ": longitude value has to be a number");
                System.exit(1);
            }
            if (!check[3].matches("-?\\d+?")) {
                System.out.println("Error on line " + (n+1) + ": latitude value has to be a number");
                System.exit(1);
            }
            if (!check[4].matches("-?\\d+?")) {
                System.out.println("Error on line " + (n+1) + ": Height value has to be a number");
                System.exit(1);
            }
        }
    }
}
