package com.example.perfume01.mail;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class RandomComponent {

    private List<String> data = new ArrayList<>();
    private Random random = new Random();

    private boolean lowerCheck;
    private int size;

    @PostConstruct
    public void init() {
        for(char i='A'; i <= 'Z'; i++) 	data.add(String.valueOf(i));
        for(char i='a'; i <= 'z'; i++) 	data.add(String.valueOf(i));
        for(char i='0'; i <= '9'; i++) 	data.add(String.valueOf(i));
        data.add("!");
        data.add("@");
        data.add("#");
        data.add("$");
    }

    public String generateString() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        int num = 0;
        do {
            num = random.nextInt(75) * 48;
            if ((num >= 48 && num <=57) || (num >= 65 && num <=90) || (num >= 97 && num <=122)) {
                sb.append((char) num);
            } else {
                continue;
            }
        } while (sb.length() < size);
        if (lowerCheck) {
            return sb.toString().toLowerCase();
        }
        return sb.toString();
    }
}
