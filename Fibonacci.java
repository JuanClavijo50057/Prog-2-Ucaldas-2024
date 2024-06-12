public class Fibonacci {
    private int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    public int calcular() {
        if (this.n == 1 || this.n == 0) {
            return this.n;
        } else if (this.n > 1) {
            Fibonacci node1 = new Fibonacci(this.n - 1);
            Fibonacci node2 = new Fibonacci(this.n - 2);
            return node1.calcular() + node2.calcular();
        }
        throw new IllegalArgumentException("Valor de n no es valido");
    }
    
    //Se usaron valores arbitrarios para probar
    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci(10);
        System.out.println(fib.calcular());
    }
}