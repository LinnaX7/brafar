import java.util.ArrayList;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        boolean result = false;
        if (str.length() % 2 == 0) {
            if (str.length() > 0) {
                result = true;
            }
        }
        for (int i = 0; i < str.length(); i++) {
            char x = str.charAt(i);
            if (!(x == '(' || x == ')' || x == '[' || x == ']' || x == '{' || x == '}')) {
                result = false;
            }
            break;
        }
        ArrayList<String> tempString = new ArrayList<String>();
        for (int i = 0; i < str.length(); i++) {
            char strs = str.charAt(i);
            if (strs == '(' || strs == '[' || strs == '{') {
                tempString.add(Character.toString(strs));
            }
            int size = tempString.size();
            if (strs == ')' || strs == ']' || strs == '}') {
                if (size == 0) {
                    result = false;
                }
            }
            int j = tempString.size() - 1;
            if (strs == ')') {
                if (tempString.get(j).equals(String.valueOf('('))) {
                    tempString.remove(j);
                } else {
                    result = false;
                    break;
                }
            } else {
                if (strs == ']') {
                    if (tempString.get(j).equals(String.valueOf('['))) {
                        tempString.remove(j);
                    } else {
                        result = false;
                        break;
                    }
                } else {
                    if (strs == '}') {
                        if (tempString.get(j).equals(String.valueOf('{'))) {
                            tempString.remove(j);
                        } else {
                            result = false;
                            break;
                        }
                    }
                }
            }
        }
        if (tempString.size() != 0) {
            result = false;
        }
        return result;
    }
}
