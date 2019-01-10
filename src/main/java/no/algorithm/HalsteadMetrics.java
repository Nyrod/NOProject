package no.algorithm;

import java.io.IOException;
import java.util.ArrayList;

public class HalsteadMetrics {

    private int uniqueOperators;
    private int uniqueOperands;
    private int allOperators;
    private int allOperands;

    public HalsteadMetrics() {
    }

    public void calculateHalsteadMetrics(String filePath) throws IOException {
       ArrayList<String> result = RunCCSM.run(filePath);
        result.forEach(this::processString);
    }

    private void processString(String result) {
        if(result.contains("operator")) {
            if(uniqueOperators == 0 || allOperators == 0) {
                int index = result.indexOf(":") + 1;
                int value = Integer.parseInt(result.substring(index, result.length()).trim());
                if (result.contains("different")) {
                    uniqueOperators = value;
                } else {
                    allOperators = value;
                }
            }
        } else if(result.contains("operand")) {
            int index = result.indexOf(":") + 1;
            int value = Integer.parseInt(result.substring(index, result.length()).trim());
            if(result.contains("different")) {
                uniqueOperands += value;
            } else {
                allOperands += value;
            }
        }
    }

    private int getVocabulary() {
        return uniqueOperands + uniqueOperators;
    }

    private int getProgramLength() {
        return allOperators + allOperands;
    }

    private double getVolume() {
        return (getProgramLength()* (Math.log(getVocabulary()/Math.log(2))));
    }

    private double getDifficult() {
        if(uniqueOperands == 0)
            uniqueOperands = 1;
        return (uniqueOperators * allOperands)/(2 * uniqueOperands);
    }

    private double getWorkRequire() {
        return  getDifficult() * getVolume();
    }

    private double getEstimateErrors() {
        return getVolume()/3000;
    }

    @Override
    public String toString() {
        return "Miary Halsteada" +
                "\nLiczba różnych operatorów w programie = " + uniqueOperators +
                "\nLiczba różnych operandów w programie = " + uniqueOperands +
                "\nŁączna liczba operatorów w programie = " + allOperators +
                "\nŁączna liczba operandów w programie = " + allOperands +
                "\nSłownik programu = " + getVocabulary() +
                "\nDługość programu = " + getProgramLength() +
                "\nObjętość programu = " + getVolume() +
                "\nTrudność programu = " + getDifficult() +
                "\nWymagany nakład pracy = " + getWorkRequire() +
                "\nPrzewidywana liczba błędów = " + getEstimateErrors();
    }
}
