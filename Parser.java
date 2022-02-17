import java.io.File;
import java.util.Scanner;

/**
 * The Parser encapsulates access to the input assembly code. In particular, it provides a convenient means for
 * advancing through the source code, skipping comments and white space, and breaking each symbolic instruction into its
 * underlying components.
 *
 * @author Maarten Derks
 */
class Parser {

    private final Scanner sc;
    String inst;

    /**
     * Opens the input file/stream and gets ready to parse it.
     *
     * @param file  Input file or stream
     * @throws Exception
     */
    Parser(File file) throws Exception {
        sc = new Scanner(file);
    }

    /**
     * Are there more lines in the input?
     *
     * @return  <code>true</code> if there are more lines in the input;
     *          <code>false</code> otherwise.
     */
    boolean hasMoreLines() {
        return sc.hasNextLine();
    }

    /**
     * Skips over whitespace and comments, if necessary.
     * Reads the next instruction from the input, and makes it the current instruction.
     * This method should be called only if {@link #hasMoreLines() hasMoreLines} is true.
     * Initially there is no current instruction.
     */
    void advance() {
        do {
            inst = sc.nextLine().replaceFirst("//.+", "").trim();
        } while (inst.equals("") || inst.startsWith("//"));
    }

    /**
     * Returns the type of the current instruction:
     *
     * @return  {@link InstructionType#A_INSTRUCTION A_INSTRUCTION} for <code>@xxx</code>, where <code>xxx</code> is
     *          either a decimal number or a symbol.
     *          {@link InstructionType#C_INSTRUCTION C_INSTRUCTION} for <code>dest=comp;jump</code>
     */
    InstructionType instructionType() {
        return inst.startsWith("@") ? InstructionType.A_INSTRUCTION : InstructionType.C_INSTRUCTION;
    }

    /**
     * If the current instruction is <code>@xxx</code>, returns decimal <code>xxx</code> (as a string).
     * Should be called only if {@link #instructionType() instructionType} is {@link InstructionType#A_INSTRUCTION A_INSTRUCTION}.
     *
     * @return  the instruction's symbol (string)
     */
    String symbol() {
        return inst.substring(1);
    }

    /**
     * Returns the symbolic <code>dest</code> part of the current C-instruction (8 possibilities).
     * Should be called only if {@link #instructionType() instructionType} is {@link InstructionType#C_INSTRUCTION C_INSTRUCTION}.
     *
     * @return  the instruction's <code>dest</code> field
     */
    String dest() {
        return inst.contains("=") ? inst.substring(0, inst.indexOf("=")) : null;
    }

    /**
     * Returns the symbolic <code>jump</code> part of the current C-instruction (28 possibilities).
     * Should be called only if {@link #instructionType() instructionType} is {@link InstructionType#C_INSTRUCTION C_INSTRUCTION}.
     *
     * @return  the instruction's <code>comp</code> field
     */
    String comp() {
        if (dest() == null && jump() == null) return inst;
        if (dest() == null && jump() != null) return inst.substring(0, inst.indexOf(";"));
        if (dest() != null && jump() == null) return inst.substring(inst.indexOf("=") + 1);
        if (dest() != null && jump() != null) return inst.substring(inst.indexOf("="), inst.indexOf(";"));
        return null;
    }

    /**
     * Returns the symbolic <code>jump</code> part of the current C-instruction (8 possibilities).
     * Should be called only if {@link #instructionType() instructionType} is {@link InstructionType#C_INSTRUCTION C_INSTRUCTION}.
     *
     * @return  the instruction's <code>jump</code> field
     */
    String jump() {
        return inst.contains(";") ? inst.substring(inst.indexOf(";") + 1) : null;
    }
}
