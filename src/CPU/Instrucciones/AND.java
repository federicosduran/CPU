package CPU.Instrucciones;
import CPU.AddressMode;
import CPU.Micro6502;

public class AND extends Instruccion {
    public AND(AddressMode addressMode, Micro6502 cpu) {

        super("AND", (byte) 0x29, addressMode, (byte) 2,cpu);
    }
    public AND(){super("AND", (byte) 0x29, null, (byte) 2,new Micro6502());};


    @Override
    public int ejecutar() {
        return 0;
    }


}






