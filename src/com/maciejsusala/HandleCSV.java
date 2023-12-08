package com.maciejsusala;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HandleCSV {

    public static List<List<String>> putCSVtoList(String filePath) {

        List<List<String>> csvData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] cells = line.split("\t");

                List<String> row = new ArrayList<>(List.of(cells));

                csvData.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvData;
    }
    public static void printList(List<List<String>> list) {
        for (List<String> row : list) {
            for (String cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }
}
