package Model;

import lombok.*;


@Getter
@Setter
public class Map {

    static public int size;
    static public String[][] Layout;

    public Map(int level){
        this.size = CalculateMapSize(level);
        this.Layout = InitializeMap(this.size);
    }

    public int getSize(){
        return (this.size);
    }

    public String[][] getLayout(){
        return (this.Layout);
    }

    public static int CalculateMapSize(int level){

        int result =  (level-1)*5+10-(level%2);
        return result;
    }

    public String[][] InitializeMap(int size){

        String[][] Layout = new String[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                Layout[i][j] = ".";
            }
        }
        return( Layout);
    }

    public void PrintMap(){
        int length = Layout[0].length;
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                System.out.print(this.Layout[i][j]);
            }
            System.out.println("");
        }
    }
}
