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

    public Complex multiply (Complex other) {
        double newReal = real * other.real - img * other.img;
        double newImg = real * other.img + img * other.real;
        
        return new Complex (newReal, newImg);
    }
}