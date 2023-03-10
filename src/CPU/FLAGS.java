package CPU;

public enum FLAGS {
    C(1), // Carry Bit
    Z(2), // Zero
    I(4), // Disable Interrupts
    D(8), // Decimal Mode (unused in this implementation)
    B(16), // Break
    U(32), // Unused
    V(64), // Overflow
    N(128); // Negative
    
    private final int value;
    
    private FLAGS(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}