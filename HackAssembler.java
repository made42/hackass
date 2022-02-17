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

    public static void main(String[] args) throws Exception {
        if (args.length == 1) {
            if (args[0].endsWith(".asm")) {
                FileWriter fileWriter = new FileWriter(args[0].substring(0, args[0].indexOf('.')) + ".hack");
                Parser parser = new Parser(new File(args[0]));
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
            } else {
                System.out.println("Invalid file extension");
            }
        } else {
            System.out.println("Invalid number of arguments");
        }
    }
}
