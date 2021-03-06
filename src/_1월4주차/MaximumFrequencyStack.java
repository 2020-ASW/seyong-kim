package _1월4주차;

import java.util.*;

public class MaximumFrequencyStack {

    Map<Integer, Integer> feqMap;
    Map<Integer, Stack<Integer>> stackMap;
    int maxFreq;

    public MaximumFrequencyStack() {
        maxFreq = 0;
        feqMap = new HashMap<>();
        stackMap = new HashMap<>();
    }

    public void push(int x) {
        if (!feqMap.containsKey(x)) {
            feqMap.put(x, 1);
        } else {
            feqMap.put(x, feqMap.get(x) + 1);
        }

        int frequency = feqMap.get(x);
        maxFreq = Math.max(maxFreq, frequency);

        if (!stackMap.containsKey(frequency)) {
            stackMap.put(frequency, new Stack<>());
        }
        stackMap.get(frequency).add(x);
    }

    public int pop() {
        int element = stackMap.get(maxFreq).pop();
        if (stackMap.get(maxFreq).size() == 0) maxFreq--;
        feqMap.put(element, feqMap.get(element) - 1);
        return element;
    }

    public static void main(String[] args) {
        MaximumFrequencyStack M = new MaximumFrequencyStack();
        M.push(5);
        M.push(1);
        M.push(2);
        M.push(5);
        M.push(5);
        M.push(5);
        M.push(1);
        M.push(6);
        M.push(1);
        M.push(5);
        System.out.print(M.pop()+" ");
        System.out.print(M.pop()+" ");
        System.out.print(M.pop()+" ");
        System.out.print(M.pop()+" ");
        System.out.print(M.pop()+" ");
        System.out.print(M.pop()+" ");
        System.out.print(M.pop()+" ");
        System.out.print(M.pop()+" ");
        System.out.print(M.pop()+" ");
        System.out.print(M.pop()+" ");

        // Output >> 5 5 1 5 1 5 6 2 1 5
    }
}
