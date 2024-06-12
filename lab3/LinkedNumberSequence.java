package lab3;
public class LinkedNumberSequence implements NumberSequence
{
    private class Node
    {
        public double number;
        public Node next;
        public Node (double number)
        {
            this.number = number;
            next = null;
        }
    }

    // the first node in the node-sequence
    private Node first;

    // create the sequence
    public LinkedNumberSequence(double[] numbers)
    {
        if (numbers.length < 2)
            throw new IllegalArgumentException("not a sequence");
        first = new Node(numbers[0]);
        Node n = first;
        for (int i = 1; i < numbers.length; i++)
        {
            n.next = new Node(numbers[i]);
            n = n.next;
        }
    }

    // toString returns the character string representing this sequence
    public String toString() {
        String s = "";
        Node n = first;
        while (n.next != null) {
            s = s + n.number + ", ";
            n = n.next;
        }
        s = s + n.number;
        return s;
    }

    // add code here
    @Override
    public int length() {
        int numberOfElements = 0;
        Node n = first;
        // Traverse the linked list and count the number of elements
        while (n != null) {
            numberOfElements++;
            n = n.next;
        }
        return numberOfElements;
    }

    @Override
    public double upperBound() {
        Node max = first;
        Node n = first;
        while (n.next != null){
            if(max.number < n.number){
                max = n;
            }
            n=n.next;
        }
        return max.number;
    }

    @Override
    public double lowerBound() {
        Node min = first;
        Node n = first;
        while (n.next != null){
            if(min.number > n.number){
                min = n;
            }
            n=n.next;
        }
        return min.number;
    }

    /*@Override
    public double numberAt(int position) throws IndexOutOfBoundsException {
        int pos=0;
        // Handle the case where the linked list is empty
        if (first == null)
            return -1;
        else {
            Node n = first;
            // Traverse the linked list and return the element at the specified position
            while (n.next != null){
                if(pos==position){
                    return n.number;
                }
                n = n.next;
                pos++;
            }
        }
        return -1;
    }*/

    @Override
    public double numberAt(int position) throws IndexOutOfBoundsException {
        int pos=0;
        if(this.length() <= position ||0 > position) {
            throw new IndexOutOfBoundsException("Position not available ");
        }
        // Handle the case where the linked list is empty
        if (first == null)
            return -1;
        else {
            Node n = first;
            // Traverse the linked list and return the element at the specified position
            while (n.next != null){
                if(pos==position){
                    return n.number;
                }
                n = n.next;
                pos++;
            }
        }
        return -1;
    }

    @Override
    public int positionOf(double number) {
        int pos=0;
        // Handle the case where the linked list is empty
        if (first == null)
            return -1;
        else {
            Node n = first;
            while (n.next != null){
                if(number==n.number){
                    return pos;
                }
                n = n.next;
                pos++;
            }
        }
        return -1;
    }

    @Override
    public boolean isIncreasing() {
        Node n = first;
        Node pre = null;
        // Traverse the linked list and check if it is in increasing order
        while(n.next!=null){
            pre = n;
            n=n.next;
            if(n.number<=pre.number){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isDecreasing() {
        Node n = first;
        Node pre = null;
        while(n.next!=null){
            pre = n;
            n=n.next;
            if(n.number>=pre.number){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(double number) {
        Node n = first;
        while (n.next != null){
            if(n.number==number){
                return true;
            }
            n = n.next;
        }
        return false;
    }

    @Override
    public void add(double number) {
        Node node = new Node(number);
        // Handle the case where the linked list is empty
        if (first == null)
            first = node;
        else {
            Node n = first;
            while (n.next != null)
                n = n.next;
            n.next = node;
        }
    }

    @Override
    public void insert(int position, double number) throws IndexOutOfBoundsException {
        if (position > this.length()){
            throw new IndexOutOfBoundsException("Position not available ");
        }
        Node node = new Node(number);
        // Handle the case where the linked list is empty
        if (position == 0) {
            node.next = first;
            first = node;
        }
        else {	
        Node n = first;
        for (int i = 0; i < position - 1; i++) {
                n = n.next;
            }
        node.next = n.next;
        n.next = node;
        }
    }

    @Override
    public void removeAt(int position) throws IndexOutOfBoundsException, IllegalStateException {
        if (position > this.length()){
            throw new IndexOutOfBoundsException("Position not available ");
        }
        
        if (this.length() < 3) {
            throw new IllegalStateException("List has too few elements ");
        }
        Node n = first;
        if (position == 0) {
            first = n.next;
        }
        else {
        for (int i = 0; i < position - 1; i++) {
                n = n.next;
            }
        n.next = n.next.next;
        }
    }

    
    @Override
    public double[] asArray() {
        double[] copy = new double[this.length()];
        int copyIndex = 0;
        Node n = first;
        while(n!=null){
            copy[copyIndex]=n.number;
            copyIndex++;
            n=n.next;
        }
        return copy;
    }
}
