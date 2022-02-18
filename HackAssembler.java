import java.io.File;
import java.io.FileWriter;

/**
 * This is the main program that drives the entire assembly process, using the services of the Parser and Code modules.
 * The program gets the name of the input source file, say, <i>Prog</i>, from the command-line argument. It constructs
 * a Parser for parsing the input file <i>Prog</i><code>.asm</code> and creates an output file,
 * <i>Prog</i><code>.hack</code>, into which it will write the translated binary instructions.
 *
 * @author Maarten Derks
 */
class HackAssembler {

    static File file;

    static Parser parser;
    static SymbolTable symbolTable;

    public static void main(String[] args) throws Exception {
        if (args.length == 1) {
            if (args[0].endsWith(".asm")) {
                file = new File(args[0]);
                initialize();
                firstPass();
                secondPass();
            } else {
                System.out.println("Invalid file extension");
            }
        } else {
            System.out.println("Invalid number of arguments");
        }
    }

    /**
     * Opens the input file (Prog.asm), and gets ready to process it.
     * Constructs a symbol table, and adds to it all the predefined symbols.
     *
     * @throws Exception
     */
    static void initialize() throws Exception {
        parser = new Parser(file);
        symbolTable = new SymbolTable();
        symbolTable.addEntry("R0", 0);
        symbolTable.addEntry("R1", 1);
        symbolTable.addEntry("R2", 2);
        symbolTable.addEntry("R3", 3);
        symbolTable.addEntry("R4", 4);
        symbolTable.addEntry("R5", 5);
        symbolTable.addEntry("R6", 6);
        symbolTable.addEntry("R7", 7);
        symbolTable.addEntry("R8", 8);
        symbolTable.addEntry("R9", 9);
        symbolTable.addEntry("R10", 10);
        symbolTable.addEntry("R11", 11);
        symbolTable.addEntry("R12", 12);
        symbolTable.addEntry("R13", 13);
        symbolTable.addEntry("R14", 14);
        symbolTable.addEntry("R15", 15);
        symbolTable.addEntry("SCREEN", 16384);
        symbolTable.addEntry("KBD", 24576);
        symbolTable.addEntry("SP", 0);
        symbolTable.addEntry("LCL", 1);
        symbolTable.addEntry("ARG", 2);
        symbolTable.addEntry("THIS", 3);
        symbolTable.addEntry("THAT", 4);
    }

    /**
     * Reads the program lines, one by one, focusing only on <code>(label)</code> declarations.
     * Adds the found labels to the symbol table.
     */
    static void firstPass() {
        int lineCounter = 0;
        while (parser.hasMoreLines()) {
            parser.advance();
            if (parser.instructionType() == InstructionType.L_INSTRUCTION) {
                symbolTable.addEntry(parser.symbol(), lineCounter);
            } else {
                lineCounter++;
            }
        }
    }

    /**
     * While there are more lines to process:
     *  Gets the next instruction, and parses it
     *  If the instruction is <code>@symbol</code>
     *      Translate the <code>symbol</code> to its binary value
     *  If the instruction is <code>dest=comp;jump</code>
     *      Translates each of the three fields into its binary value
     *  Assembles the binary values into a string of sixteen <code>0</code>'s and <code>1</code>'s
     *  Writes the string to the output file.
     *
     * @throws Exception
     */
    static void secondPass() throws Exception {
        FileWriter fileWriter = new FileWriter(file.getPath().substring(0, file.getPath().indexOf('.')) + ".hack");
        parser = new Parser(file);
        while (parser.hasMoreLines()) {
            parser.advance();
            switch (parser.instructionType()) {
                case A_INSTRUCTION: fileWriter.write(String.format("%16s", Integer.toBinaryString(Integer.parseInt(parser.symbol()))).replaceAll(" ", "0") + "\r\n");
                                    break;
                case C_INSTRUCTION: fileWriter.write("111" + Code.comp(parser.comp()) + Code.dest(parser.dest()) + Code.jump(parser.jump()) + "\r\n");
                                    break;
                default:            fileWriter.write(parser.inst + "\r\n");
                                    break;
            }
        }
        fileWriter.close();
    }
}
