import java.util.HashMap;

/**
 * Since Hack instructions can contain symbolic references, the assembly process must resolve them into actual
 * addresses. The assembler deals with this task using a <i>symbol table</i>, designed to create and maintain the
 * correspondence between symbols and their meaning (in Hack's case, RAM and ROM addresses).
 *
 * @author Maarten Derks
 */
class SymbolTable {

    private final HashMap<String, Integer> st;

    /**
     * Creates a new empty symbol table.
     */
    SymbolTable() {
        st = new HashMap<>();
    }

    /**
     * Adds <code>&lt;symbol,address&gt;</code> to the table.
     *
     * @param symbol    the symbol to be added
     * @param address   the address of the symbol
     */
    void addEntry(String symbol, int address) {
        st.put(symbol, address);
    }

    /**
     * Does the symbol table contain the given <code>symbol</code>?
     *
     * @param symbol
     * @return
     */
    boolean contains(String symbol) {
        return st.get(symbol) != null;
    }

    /**
     * Returns the address associated with the <code>symbol</code>.
     *
     * @param symbol
     * @return          the address (int) associated with symbol
     */
    int getAddress(String symbol) {
        return st.get(symbol);
    }
}
