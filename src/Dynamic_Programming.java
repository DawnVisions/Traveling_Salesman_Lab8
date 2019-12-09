import java.util.ArrayList;

public class Dynamic_Programming {


    static int setToInteger(ArrayList<Integer> set)
    {
        int number = 0;
        for(Integer node:set)
        {
            number += Math.pow(2, node-1);
        }

        return number;
    }

    static ArrayList<Integer> binaryIntegerToSet(int number)
    {
        ArrayList<Integer> set = new ArrayList<>();
        for(int i = 1; i<32; i++)
        {
            if(number % Math.pow(2,i-1) == 0)
                set.add(i);
        }
        return set;
    }
}
