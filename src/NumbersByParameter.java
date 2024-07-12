import com.sun.source.tree.CaseTree;

import java.util.ArrayList;
import java.util.Arrays;

public class NumbersByParameter extends DualNumbers{
    protected final static String[] properties =
            {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "EVEN", "ODD", "SUNNY", "SQUARE", "JUMPING", "HAPPY", "SAD"};
    private static final String[][] impossibleProperties = {
            {"EVEN", "ODD"},
            {"DUCK", "SPY"},
            {"HAPPY", "SAD"},
            {"SUNNY", "SQUARE"},
            {"-EVEN", "-ODD"},
            {"-DUCK", "-SPY"},
            {"-HAPPY", "-SAD"},
            {"-SUNNY", "-SQUARE"}};
    protected ArrayList<String> parameters;

    public NumbersByParameter(long firstNumber, long secondNumber, ArrayList<String> parameters) {
        super(firstNumber, secondNumber);
        this.parameters = parameters;
    }

    @Override
    public void process() {
        if (!isNatural(secondNumber))
            secondNumberErrorMessage();
        else if (isValid()){
            multipleNumbersByParameter();
        }
    }

    boolean isValid() {
        boolean valid = true;
        ArrayList<String> wrongProp = new ArrayList<>();

        for (String param : parameters) {
            if (Arrays.stream(properties).noneMatch(param.replaceFirst("-", "").toUpperCase()::equals)) {
                valid = false;
                wrongProp.add(param);
            } else if (parameters.contains("-" + param)) {
                mutuallyExclusivePropertiesError(new String[]{param, "-" + param});
                valid = false;
            }
        }

        if (!wrongProp.isEmpty()){
            parameterError(wrongProp);
        }

        if (parameters.contains("EVEN") && parameters.contains("ODD")) {
            mutuallyExclusivePropertiesError(impossibleProperties[0]);
            valid = false;
        } else if (parameters.contains("DUCK") && parameters.contains("SPY")) {
            mutuallyExclusivePropertiesError(impossibleProperties[1]);
            valid = false;
        } else if (parameters.contains("SUNNY") && parameters.contains("SQUARE")) {
            mutuallyExclusivePropertiesError(impossibleProperties[3]);
            valid = false;
        } else if (parameters.contains("HAPPY") && parameters.contains("SAD")) {
            mutuallyExclusivePropertiesError(impossibleProperties[2]);
            valid = false;
        } else if (parameters.contains("-EVEN") && parameters.contains("-ODD")) {
            mutuallyExclusivePropertiesError(impossibleProperties[4]);
            valid = false;
        } else if (parameters.contains("-DUCK") && parameters.contains("-SPY")) {
            mutuallyExclusivePropertiesError(impossibleProperties[5]);
            valid = false;
        } else if (parameters.contains("-SUNNY") && parameters.contains("-SQUARE")) {
            mutuallyExclusivePropertiesError(impossibleProperties[7]);
            valid = false;
        } else if (parameters.contains("-HAPPY") && parameters.contains("-SAD")) {
            mutuallyExclusivePropertiesError(impossibleProperties[6]);
            valid = false;
        }


        return valid;
    }

    void multipleNumbersByParameter() {
        int count = 0;
        long increaseNumber = usersNumber;

        while (count < secondNumber) {
            if (parametersMatch(increaseNumber, parameters)) {
                System.out.println(numberInfo(increaseNumber));
                count++;
            }
            increaseNumber++;
        }
    }

    boolean parametersMatch(long number, ArrayList<String> parameters) {
        boolean result = true;

        for (String param : parameters) {
            if (!param.startsWith("-")){
                if (!numberInfo(number).contains(param.toLowerCase())) {
                    result = false;
                }
            } else {
                if (numberInfo(number).contains(param.replaceFirst("-", "").toLowerCase())) {
                    result = false;
                }
            }
        }

        return result;
    }

    void parameterError(ArrayList<String> parameters) {
        String errMessage;

        switch (parameters.size()) {
            case 1 -> errMessage = String.format("The property [%s] is wrong.", parameters.get(0));
            default -> errMessage = String.format("The properties [%s, %s] are wrong.", parameters.get(0), parameters.get(1));
        }

        System.out.printf("\n"+ errMessage +"\n" +
                "Available properties:\n" +
                Arrays.toString(properties) + "\n",
                parameters.get(0).toUpperCase());
    }

    void mutuallyExclusivePropertiesError(String[] params) {
        System.out.printf("\nThe request contains mutually exclusive properties: [%s, %s]\n" +
                        "There are no numbers with these properties. \n", params[0], params[1]);
    }
}
