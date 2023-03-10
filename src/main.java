import CPU.Bus;
import CPU.Micro6502;

public class main {
    public static void main(String[] args) {
        Micro6502 cpu = new Micro6502();
        Bus bus = new Bus(cpu);
        System.out.println("");


    }
}
