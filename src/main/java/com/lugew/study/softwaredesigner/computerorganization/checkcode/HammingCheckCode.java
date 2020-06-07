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
            result[index - 1] = generateParityCheckCode(informationPositions, result);
        }
    }


    public int getLength() {
        return checkBits + informationBits;
    }

    public int getInformationBits() {
        return informationBits;
    }

    public void setInformationBits(int informationBits) {
        initialize(informationBits);
    }

    public int getCheckBits() {
        return checkBits;
    }

    public void enableOddMode() {
        setCheckMethod(CheckMethod.ODD);
    }

    public void enableEvenMode() {
        setCheckMethod(CheckMethod.EVEN);
    }

    @Override
    public boolean check(char[] checkCode) {
        ensureLegal(checkCode);
        char[] original = checkCode.clone();
        char[] corrected = correct(checkCode);
        return Arrays.equals(original, checkCode);
    }

    @Override
    public char[] correct(char[] binary) {
        ensureLegal(binary);
        char[] checkedResult = new char[checkPositionInformationPositionMap.size()];
        int index = -1;
        for (Map.Entry<Integer, List<Integer>> entry : checkPositionInformationPositionMap.entrySet()) {
            index++;
            if (isParityPass(xor(xor(entry.getValue(), binary), map.get(binary[entry.getKey() - 1])))) {
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
            wrongIndex += map.get(c) << index--;
        }
        not(binary, wrongIndex);
    }

    private byte xor(List<Integer> indexes, char[] binary) {
        byte result = 0;
        for (Integer index : indexes) {
            result ^= binary[index];
        }
        return result;
    }

    private byte xor(byte c1, byte c2) {
        return (byte) (c1 ^ c2);
    }

    private char generateParityCheckCode(List<Integer> indexes, char[] binary) {
        byte XORResult = xor(indexes, binary);
        if (isOddCheck()) {
            XORResult ^= 1;
        }
        return map.inverse().get(XORResult);
    }

    protected char not(char input) {
        return map.inverse().get((byte) (map.get(input) ^ 0b1));
    }

    protected void not(char[] binary, int index) {
        binary[index] = not(binary[index]);
    }

}
