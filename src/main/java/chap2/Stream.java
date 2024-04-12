package chap2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Stream {

    public static void main(String[] args) {

        List<Integer> x = Arrays.asList(1,2,3,4,5,6);

        int sum = x.stream().reduce((a, b) -> a+b).get();
        System.out.println("sum: " + sum);

        List<String> t = Arrays.asList("P", "a", "b", "l", "o");

        String s = t.stream().reduce((a,b) -> a+b).get();
        System.out.println("Join: " + s);

        x.stream().forEach((a) -> System.out.println(a));

        List<Integer> subLst = x.stream().filter(
                a -> a%2==0).collect(
                        Collectors.toList());

        System.out.println("Sublista: " + subLst);

    }

}
