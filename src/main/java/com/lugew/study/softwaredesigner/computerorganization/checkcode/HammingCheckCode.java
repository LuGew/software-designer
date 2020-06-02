package com.lugew.study.softwaredesigner.computerorganization.checkcode;

import java.util.*;

/**
 * n有效位数，k校验位数
 * 需满足2^k-1>=n+k
 * 有
 * k    |   n
 * 2    |   1
 * 3    |   2~4
 * 4    |   5~11
 * 5    |   12~26
 * 6    |   27~57
 * 7    |   58~120
 *
 * @author LuGew
 * @since 2020/6/2
 */
public class HammingCheckCode extends ParityCheckCode {
    private int informationBits = 1;
    private int checkBits = 2;
    private int[] checkPositions = new int[]{1, 2};
    private Map<Integer, List<Integer>> checkPositionInformationPositionMap;

    public HammingCheckCode() {
    }

    public HammingCheckCode(int informationBits) {
        initialize(informationBits);
    }

    public void initialize(int informationBits) {
        this.informationBits = informationBits;
        this.checkBits = calculateCheckBits(this.informationBits);
        generateCheckPositions(this.checkBits);
        linkCheckPosition();
    }

    private void linkCheckPosition() {
        int checkPositionIndex = 0;
        int checkPositionLength = getCheckBits();
        int temp;
        for (int i = 0; i < getLength(); i++) {
            if (checkPositionIndex < checkPositionLength) {
                temp = checkPositions[checkPositionIndex];
                if (i + 1 == temp) {
                    checkPositionIndex++;
                    continue;
                }
            }
            linkCheckPosition(i, checkPositionIndex);
        }
    }

    private void linkCheckPosition(int index, int checkPositionIndex) {
        int position = index + 1;
        if (checkPositionIndex > getCheckBits() - 1) {
            checkPositionIndex = getCheckBits() - 1;
        }
        int temp;
        while (position > 0) {
            temp = checkPositions[checkPositionIndex];
            if (temp <= position) {
                checkPositionInformationPositionMap.get(temp).add(index);
                position -= temp;
            }
            checkPositionIndex--;
        }
    }

    private void generateCheckPositions(int checkBits) {
        checkPositions = new int[checkBits];
        checkPositionInformationPositionMap = new LinkedHashMap<>();
        int index;
        for (int i = checkBits - 1; i >= 0; i--) {
            index = 1 << i;
            checkPositionInformationPositionMap.put(index, new ArrayList<>());
            checkPositions[i] = index;
        }
    }

    private int calculateCheckBits(int informationBits) {
        int checkBits = 1;
        while ((1 << checkBits) - checkBits < informationBits + 1) {
            checkBits++;
        }
        return checkBits;
    }

    @Override
    public char[] generate(char[] binary) {
        ensureLegal(binary);
        initialize(binary.length);
        char[] result = new char[getLength()];
        copyInformation(binary, result);
        doGenerate(result);
        return result;
    }

    private void copyInformation(char[] binary, char[] result) {
        int checkPositionIndex = 0;
        int informationIndex = 0;
        int checkPositionLength = getCheckBits();

        for (int i = 0; i < result.length; i++) {
            if (checkPositionIndex < checkPositionLength) {
                int temp = checkPositions[checkPositionIndex];
                if (i + 1 == temp) {
                    result[i] = 'H';
                    checkPositionIndex++;
                    continue;
                }
            }
            result[i] = binary[informationIndex++];
        }
    }

    private void doGenerate(char[] result) {
        int index;
        for (int checkPosition : checkPositions) {
            index = checkPosition;
            List<Integer> informationPositions = checkPositionInformationPositionMap.get(index);
            result[index - 1] = calculateParityCheckCode(informationPositions, result);
        }
    }


    public int getLength() {
        return checkBits + informationBits;
    }

    public int getInformationBits() {
        return informationBits;
    }

    public int getCheckBits() {
        return checkBits;
    }

    protected char calculateParityCheckCode(List<Integer> informationPositions, char[] result) {
        int oneCount = 0;
        char code;
        char information;
        for (Integer position : informationPositions) {
            information = result[position];
            if (information == ONE) {
                oneCount++;
            } else if (information == ZERO) {

            } else {
                throw new RuntimeException("input not binary");
            }
        }
        code = oneCount % 2 == 0 ? ZERO : ONE;
        if (this.getMethod().equals(Method.ODD)) {
            code = code == ZERO ? ONE : ZERO;
        }
        return code;
    }

    public void enableOddMode() {
        setMethod(Method.ODD);
    }

    public void enableEvenMode() {
        setMethod(Method.EVEN);
    }

    public void setInformationBits(int informationBits) {
        initialize(informationBits);
    }

    @Override
    public boolean check(char[] checkCode) {
        ensureLegal(checkCode);
        char[] original = checkCode.clone();
        char[] corrected = correct(checkCode);
        return Arrays.equals(corrected, checkCode);
    }

    @Override
    public char[] correct(char[] binary) {
        ensureLegal(binary);
        char[] checkedResult = new char[checkPositionInformationPositionMap.size()];
        int index = -1;
        for (Map.Entry<Integer, List<Integer>> entry : checkPositionInformationPositionMap.entrySet()) {
            index++;
            int oneCount = 0;
            for (Integer informationIndex : entry.getValue()) {
                if (binary[informationIndex] == ONE) {
                    oneCount++;
                }
            }
            if (binary[entry.getKey() - 1] == ONE) {
                oneCount++;
            }
            if (isChecked(oneCount)) {
                checkedResult[index] = ZERO;
            } else {
                checkedResult[index] = ONE;
            }
        }
        doCorrect(binary, checkedResult);
        return binary;
    }

    private void doCorrect(char[] binary, char[] checkedResult) {
        int wrongIndex = -1;
        int index = checkedResult.length - 1;
        for (char c : checkedResult) {
            wrongIndex += (c - '0') << index--;
        }
        switchValue(binary, wrongIndex);
    }

    private void switchValue(char[] binary, int index) {
        if (index >= 0 && index < binary.length) {
            binary[index] = (binary[index] == ZERO ? ONE : ZERO);
        }
    }

}
