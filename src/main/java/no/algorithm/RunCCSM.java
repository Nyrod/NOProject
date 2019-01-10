package no.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RunCCSM {

    public static ArrayList<String> run(String filePath) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("ccsm --exclude-file=.h$$ " +
                "--output-metrics=OP_CNT,OP_TYPES_CNT,HALSTEAD_OPERAND_UNIQUE_CNT,HALSTEAD_OPERAND_CNT " +
                filePath);

        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        ArrayList result = new ArrayList<String>();
        input.lines().forEach(result::add);
        return result;
    }
}
