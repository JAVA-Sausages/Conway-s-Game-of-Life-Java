package com.example.conwaysgameoflifejava.gameCore;

import com.example.conwaysgameoflifejava.cell.Cell;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RleReader {
    private final GameState gameState;
    private String pattern;
    private int birthRule;
    private int saveRule;
    private int setupWidth;
    private int setupHeight;
    private final ArrayList<Cell> cellsFromFile = new ArrayList<>();

    public RleReader(GameState gameState){
        this.gameState = gameState;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void readFile(String path) throws FileNotFoundException {
        File patternFile = new File(path);
        Scanner sc = new Scanner(patternFile);
        StringBuilder buffer = new StringBuilder();

        while(sc.hasNextLine()){
            buffer.append(sc.nextLine()).append("\n");
        }

        setPattern(buffer.toString());
        readSetup();
    }

    private void readSetup(){
        /// Dividing pattern file by lines and skipping comments
        String[] lines = this.pattern.split("\\n");
        int positionToStart = 0;
        for(int i = 0; i< lines.length; i++)
            if(lines[i].charAt(0) == 'x'){
                positionToStart = i;
                break;
            }

        ///Getting setup Width and Height from the pattern-file;
        String[] tempArray = lines[positionToStart].split(" ");
        try {
            setupWidth = Integer.parseInt(tempArray[2].replace(",",""));
            setupHeight = Integer.parseInt(tempArray[5].replace(",",""));
        } catch(Exception ignored){}

        ///Getting pattern rules if there is a presence
        if(tempArray.length > 5){
            String[] rules = tempArray[8].split("/");
            StringBuilder buffer = new StringBuilder();
            for(int i = 0; i < rules[0].length(); i++){
                if(Character.isDigit(rules[0].charAt(i)))
                    buffer.append(rules[0].charAt(i));
            }
            try {
                birthRule = Integer.parseInt(buffer.toString());
            } catch(Exception ignored) {}
            buffer = new StringBuilder();
            for(int i = 0; i < rules[1].length(); i++){
                if(Character.isDigit(rules[1].charAt(i)))
                    buffer.append(rules[1].charAt(i));
            }
            try{
                saveRule = Integer.parseInt(buffer.toString());
            } catch(Exception ignored) {}
        }

        ///Invoke of function which is reading Cells
        if(positionToStart+ 1 < lines.length-1)
            for(int i = 0; i< (lines.length-1-positionToStart+-1);i++)
            {
                lines[positionToStart +1] += lines[positionToStart+2+i];
            }
        readCells(lines[positionToStart + 1]);
        for(Cell k : cellsFromFile)
            System.out.println(k.getArrayPosY() + " " + k.isAlive());
        gameState.setCells(cellsFromFile, setupWidth, setupHeight, birthRule, saveRule);
    }

    private void readCells(String cellsString) {
        int rowIndex = 0;
        int colIndex = 0;
        boolean flagForPart = false;
        ArrayList<String> pattern = new ArrayList<>();
        int tempInt = -1;
        String[] parts = cellsString.split("[$]");
        int iterator = 0;
        String empty = "";

        String temp = parts[parts.length-1];
        temp = temp.substring(0, temp.length()-1);
        parts[parts.length-1] = temp;

        for(String part : parts){
            String tempPart = part + "#";
            while(true){
                try {
                    tempInt = Integer.parseInt(String.valueOf(tempPart.charAt(iterator)));
                } catch(Exception ignored){}
                if(tempInt != -1){
                    tempInt = -1;
                    flagForPart = true;
                } else {
                    if(flagForPart) {
                        int amountOfCells = 1;
                        ///Getting number of pattern repeats
                        try {
                            amountOfCells = Integer.parseInt(tempPart.substring(0, iterator));
                        }catch (Exception e) {e.printStackTrace();}
                        ///Checking state of the cell for the current pattern
                        boolean aliveness = tempPart.charAt(iterator) != 'b';
                        /// Creating cells from current row
                        ///NOT SURE ABOUT indexation
                        for(int i = 0; i < amountOfCells; i++){
                            Cell toBeAdded = new Cell(colIndex,rowIndex);
                            toBeAdded.setAlive(aliveness);
                            cellsFromFile.add(toBeAdded);
                        }
                        tempPart = tempPart.substring(iterator+1);
                        iterator = -1;
                        flagForPart = false;
                    } else {
                        ///Checking for the end of the row
                        if(tempPart.charAt(iterator) == '#'){
                            colIndex = 0;
                            iterator = 0;
                            break;
                        }
                        ///Adding cell without number of repeats (With one repeat)
                        boolean aliveness = tempPart.charAt(iterator) != 'b';
                        Cell toBeAdded = new Cell(colIndex,rowIndex);
                        toBeAdded.setAlive(aliveness);
                        cellsFromFile.add(toBeAdded);
                        tempPart = tempPart.substring(1);
                        iterator--;
                        colIndex++;
                    }
                }
                iterator++;
            }
            rowIndex++;
        }
    }
}
