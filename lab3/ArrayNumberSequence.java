package lab3;
public class ArrayNumberSequence implements NumberSequence
{
    // numbers in the sequence
    private double[] numbers;

    // create the sequence
    public ArrayNumberSequence(double[] numbers) {
        if (numbers.length < 2)
            throw new IllegalArgumentException("not a sequence");
        this.numbers = new double[numbers.length];
        for (int i = 0; i < numbers.length; i++)
            this.numbers[i] = numbers[i];
    }

    // toString returns the character string representing this sequence
    public String toString() {
        String s = "";
        for (int i = 0; i < numbers.length - 1; i++)
            s = s + numbers[i] + ", ";
        s = s + numbers[numbers.length - 1];
        return s;
    }

    // add code here
    @Override
    public int length() {
        return numbers.length;
    }

    @Override
    public double upperBound() {
        double max = numbers[0];
        // Find the maximum value in the sequence
        for(double b : numbers){
            if(max<b){
                max=b;
            }
        }
        return max;
    }

    @Override
    public double lowerBound() {
        double min = numbers[0];
        // Find the minimum value in the sequence
        for(double b : numbers){
            if(min>b){
                min=b;
            }
        }
        return min;
    }

    

    @Override
    public double numberAt(int position) throws IndexOutOfBoundsException {
        if(position<numbers.length){
         return numbers[position];
        }
        return -1;
    }

    @Override
    public int positionOf(double number) {
        for(int i=0; i<numbers.length; i++){
            if(numbers[i]==number){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isIncreasing() {
        for(int i=1; i<numbers.length;i++){
            if(numbers[i]<numbers[i-1])
                return false;
        }
        return true;
    }

    @Override
    public boolean isDecreasing() {
        for(int i=1; i<numbers.length;i++){
            if(numbers[i]>numbers[i-1])
                return false;
        }
        return true;
    }

    @Override
    public boolean contains(double number) {
        for(double b: numbers) {
            if(b==number){
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(double number) {
        double[] a = new double[numbers.length + 1];
        for (int i = 0; i < numbers.length; i++)
            a[i] = numbers[i];
        a[a.length - 1] = number;
        numbers = a;
    }

    @Override
    public void insert(int position, double number) throws IndexOutOfBoundsException {
        double[] a = new double[numbers.length + 1];
        for (int i = 0; i < position; i++)
            a[i] = numbers[i];
        a[position] = number;
        for (int i = position; i < numbers.length; i++)
            a[i+1] = numbers[i];
        numbers = a;
    }

    @Override
    public void removeAt(int position) throws IndexOutOfBoundsException, IllegalStateException {
        double[] a = new double[numbers.length - 1];
        for (int i = 0; i < position; i++)
            a[i] = numbers[i];
        for (int i = position; i < numbers.length - 1; i++)
            a[i] = numbers[i + 1];
        numbers = a;
    }

    @Override
    public double[] asArray() {
        double[] copy = new double[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            copy[i] = numbers[i];
        }
        return copy;
    }
}
