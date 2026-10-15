public class Complex {
    private double real;
    private double img;

    public Complex () {
        real = 0;
        img = 0;
    }
    public Complex (double real, double img){
        this.real = real;
        this.img = img;
    }
    public double getReal () {
        return real;
    }
    public double getImg () {
        return img;
    }
    public void setReal () {
        this.real = real;
    }
    public void setImg () {
        this.img = img;
    }

    public Complex add (Complex other) {
        return new Complex(
            real + other.real,
            img + other.img
        );
    }
    
    //умноженин
    public Complex multiply (Complex other) {
        double newReal = real * other.real - img * other.img;
        double newImg = real * other.img + img * other.real;
        
        return new Complex (newReal, newImg);
    }
    
    //деление
    public Complex divide (Complex other) {
        double znamenatel = other.real * other.real + other.img * other.img;
        if (znamenatel == 0) {
            throw new ArithmeticException("делить на 0 нельзя");
        }
        double newReal = (real * other.real + img * other.img)/znamenatel;
        double newImg = (img * other.real - real * other.img)/znamenatel;
        return new Complex(newReal, newImg);
    }
 
    //вычитание
    public Complex subtract (Complex other) {
        return new Complex(
            real - other.real,
            img - other.img
        );
    }

    //противоположное
    public Complex negative() {
        return new Complex(-real, -img);
    }

    public double abs () {
        return Math.sqrt(real * real + img * img);
    }

    private static double round (double value) {
        return Math.round((value * 10000))/10000.0 + 0.0;
    }
    
    //вывод числа
    @Override 
    public String toString () {
        double r = round(real);
        double i = round(img);

        if (i>=0) {
            return r + "+" + i + "i";
        } else {
            return r+ "-" + (-i) + "i";
        }
    }
}
