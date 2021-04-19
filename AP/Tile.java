public class Tile {
    private int styleNumber;
    private String color;
    private double width;
    private double height;
    private String material;
    private double price;

    Tile(int style, String col) {
        styleNumber = style;
        color = col; 
        
    }

    Tile(int style, String col, double w, double h, String mat, double price) {
        this.styleNumber = style;
        this.color = col;
        this.material = mat;
        this.width = w;
        this.height = h;
        this.price = price;

    }


    Tile(int style, String col, String mat, double price) {
        this.styleNumber = style;
        this.color = col;
        this.material = mat;
        this.price = price;

    }

    public String toString() {
        return "barhsdfg";
    }

    public static void main(String[] args) {
        Tile t = new Tile(101, "blue");


        System.out.println(t);
    }
}
