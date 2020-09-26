package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class FileFortuneService implements FortuneService {

    private final List<String> fortunes = new ArrayList<>();
    private final Random random = new Random();

    public FileFortuneService() {
        System.out.println(">> FileFortuneService: Inside Default-Constructor.");
    }

    //@PostConstruct
    private List<String> loadFortunesFile() {
        System.out.println(">> FileFortuneService: inside method 'loadFortunesFile'.");

        try {
            String fileName = "src/com/luv2code/springdemo/fortune_data.txt";
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = null;
            while ( (line = reader.readLine()) != null) {
                fortunes.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred during file-stream: ");
            e.printStackTrace();
        }
        return fortunes;

    }



    @Override
    public String getFortune() {
        List<String>  fortuneList = this.loadFortunesFile();
        int rnd = random.nextInt(fortuneList.size());
        return fortuneList.get(rnd);
    }
}

