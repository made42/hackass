/**
 * This module provides services for translating symbolic Hack mnemonics into their binary codes. Specifically, it
 * translates symbolic Hack mnemonics into their binary codes according to the language specifications.
 *
 * @author Maarten Derks
 */
class Code {

    /**
     * Returns the binary code of the dest mnemonic.
     *
     * @param field dest mnemonic
     * @return      3 bits, as a string
     */
    static String dest(String field) {
        if (field != null) {
            switch (field) {
                case "M":   return "001";
                case "D":   return "010";
                case "MD":  return "011";
                case "A":   return "100";
                case "AM":  return "101";
                case "AD":  return "110";
                case "ADM": return "111";
                default:    return null;
            }
        } else {
            return "000";
        }
    }

    /**
     * Returns the binary code of the comp mnemonic.
     *
     * @param field comp mnemonic
     * @return      7 bits, as a string
     */
    static String comp(String field) {
        if (field != null) {
            switch (field) {
                case "0":   return "0101010";
                case "1":   return "0111111";
                case "-1":  return "0111010";
                case "D":   return "0001100";
                case "A":   return "0110000";
                case "!D":  return "0001101";
                case "!A":  return "0110001";
                case "D+1": return "0011111";
                case "A+1": return "0110111";
                case "D-1": return "0001110";
                case "A-1": return "0110010";
                case "D+A": return "0000010";
                case "D-A": return "0010011";
                case "A-D": return "0000111";
                case "D&A": return "0000000";
                case "D|A": return "0010101";
                case "M":   return "1110000";
                case "!M":  return "1110001";
                case "M+1": return "1110111";
                case "M-1": return "1110010";
                case "D+M": return "1000010";
                case "D-M": return "1010011";
                case "M-D": return "1000111";
                case "D&M": return "1000000";
                case "D|M": return "1010101";
                default:    return null;
            }
        } else {
            return "000";
        }
    }

    /**
     * Returns the binary code of the jump mnemonic.
     *
     * @param field jump mnemonic
     * @return      3 bits, as a string
     */
    static String jump(String field) {
        if (field != null) {
            switch (field) {
                case "JGT": return "001";
                case "JEQ": return "010";
                case "JGE": return "011";
                case "JLT": return "100";
                case "JNE": return "101";
                case "JLE": return "110";
                case "JMP": return "111";
                default:    return "000";
            }
        } else {
            return "000";
        }
    }
}
