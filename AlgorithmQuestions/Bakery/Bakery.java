package third;

import java.util.*;
/*
1
1 1


2
2 1 2
1 3

4
2 1 2
3 3 5 5
3 8 7 9
2 10 8

 */
public class Bakery {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        // Input
        int n = scanner.nextInt();

        List<Customer> customers = new ArrayList<Customer>();
        int ind=0;

        for(int i=0; i<n; i++){
            int m = scanner.nextInt();
            for(int j=0;j<m;j++){
                int val = scanner.nextInt();
                Customer customer = new Customer();
                customer.setX(i);
                customer.setY(j);
                customer.setP(val);
                customers.add(customer);
                System.out.println("Added customer " + customer.toString());
            }
        }

        // Sort Customer List
        Collections.sort(customers, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if((o1.getP() < o2.getP()) || (o1.getP()==o2.getP() && o1.getY() <= o2.getY()))
                    return -1;
                return 1;
            }
        });

        // Print Sorted Customer List
        int m = customers.size();
        for(int i=0; i<m; i++){
            System.out.println(customers.get(i).toString());
        }

        // Answer
        int timeLapsed = 0;

        // Index Array to get current element being sorted in a queue
        List<Integer> index = new ArrayList<Integer>(Collections.nCopies(n, -1));

        for(int i=0; i<m; i++){

            // Check if the current customer has been processed
            Customer currentCustomer = customers.get(i);
            int patience = currentCustomer.getP();
            int queue = currentCustomer.getX();
            int position = currentCustomer.getY();

            // To avoid reprocessing Already processed when processing smaller values in queue
            if(position >= index.get(queue)){

                // Check if the current customer can be processed
                System.out.println("Processing " + currentCustomer.toString());

                //if we can process this person within patience-timeLapsed
                if ((patience - timeLapsed) >= position - index.get(queue)){
                    timeLapsed += (position-index.get(queue));
                    index.set(queue, position);
                }
                else{
                    timeLapsed = patience;
                    // Move to the next element
                    break;
                }
            }
        }

        System.out.println("Maximum customers that can be served " + timeLapsed);
    }
}
