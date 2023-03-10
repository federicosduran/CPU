package CPU.Instrucciones;
import CPU.AddressMode;
import CPU.Micro6502;

public abstract class Instruccion {
    protected String name;            // Nombre de la instrucción
    protected int opcode;             // Opcode de la instrucción
    protected AddressMode mode;    // Modo de direccionamiento de la instrucción
    protected int cycles;             // Cantidad de ciclos que tarda en ejecutarse la instrucción
    protected Micro6502 cpu;
    // Constructor


    public Instruccion(String name, int opcode, AddressMode mode, int cycles, Micro6502 cpu) {
        this.name = name;
        this.opcode = opcode;
        this.mode = mode;
        this.cycles = cycles;
        this.cpu = cpu;
    }

    public abstract int ejecutar();


}
