package CPU;

public enum AddressMode {
    IMPLIED("IMP"),            // Dirección implícita
    RELATIVE("REL"),            // Relativo
    IMMEDIATE("IMM"),           // Dirección inmediata
    ABSOLUTE("ABS"),            // Dirección absoluta
    ABSOLUTE_X(",ABX"),         // Dirección absoluta indexada en X
    ABSOLUTE_Y(",ABY"),         // Dirección absoluta indexada en Y
    ZERO_PAGE("ZP0"),           // Dirección en página cero
    ZERO_PAGE_X(",ZPX"),        // Dirección en página cero indexada en X
    ZERO_PAGE_Y(",ZPY"),        // Dirección en página cero indexada en Y
    INDIRECT("IND"),            // Indirección
    INDEXED_INDIRECT(",IZX"),   // Indirección indexada en X
    INDIRECT_INDEXED(",IZY");   // Indirección indexada en Y

    private String mnemonic;

    AddressMode(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getMnemonic() {
        return mnemonic;
    }
}




