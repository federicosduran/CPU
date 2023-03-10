package CPU;

import java.util.ArrayList;

public class Bus {
    private Micro6502 cpu;
    private ArrayList<Integer> ram = new ArrayList<>(65536);

    public Bus(Micro6502 cpu) {
        // Conecta CPU al bus
        this.cpu = cpu;
        // Inicializa todos los bytes de  la memoria RAM a 0x00
        for (int i = 0; i < 65536; i++) {
            ram.add(0x00);
        }
    }

    public void write(int addr, byte data) {
        if (addr >= 0x0000 && addr <= 0xFFFF) {
            ram.set(addr, data & 0xFF);
        }
    }

    public byte read(int addr) {
        if (addr >= 0x0000 && addr <= 0xFFFF) {
            return (byte) (ram.get(addr) & 0xFF);
        }

        return 0x00;
    }
}