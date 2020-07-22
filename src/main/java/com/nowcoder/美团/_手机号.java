package com.nowcoder.美团;

import java.util.*;

public class _手机号 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        scanner.nextLine();
        List<String> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            list.add(scanner.nextLine());
        }
        for (String number : list) {
            if (number.length() != 11) System.out.println("-1");
            String start = number.substring(0, 3);
            switch (start) {
                case "133":
                case "153":
                case "180":
                case "181":
                case "189":
                    System.out.println("China Telecom");
                    break;
                case "130":
                case "131":
                case "155":
                case "185":
                case "186":
                    System.out.println("China Unicom");
                    break;
                case "135":
                case "136":
                case "150":
                case "182":
                case "188":
                    System.out.println("China Mobile Communications");
                    break;
                default:
                    System.out.println("-1");
            }
        }
    }
}