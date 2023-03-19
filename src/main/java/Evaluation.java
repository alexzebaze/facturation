import io.swagger.models.auth.In;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Evaluation {

    public static void main(String[] args)
    {
        String orders = "up up right down left";
        String[] ordersArr = orders.split(" ");

        int i = 0;
        String result = "";
        while (i<ordersArr.length){
            String currentStr = ordersArr[i];
            int count = 0;
            for (int j = i; j < ordersArr.length ; j++) {
                if(ordersArr[j].equals(currentStr)) {
                    count++;
                }
                else
                    break;
            }
            result += currentStr+String.valueOf(count);

            i += count;
        }
        result = result.replace("up", "^");
        result = result.replace("right", ">");
        result = result.replace("left", "<");
        result = result.replace("down", "v");

        System.out.println(result);
    }
}
