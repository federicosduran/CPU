package CPU;

import CPU.Instrucciones.AND;
import CPU.Instrucciones.Instruccion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Micro6502 {

    // Variables de registro del CPU, expuestas públicamente para facilitar el acceso desde externo.
    // Esto es todo_ lo que tiene el 6502.
    public byte a = 0x00;           // Registro acumulador
    public byte x = 0x00;           // Registro X
    public byte y = 0x00;           // Registro Y
    public byte stkp = 0x00;        // Puntero de la pila (apunta a la ubicación en el bus)
    public short pc = 0x0000;       // Contador de programa
    public byte status = 0x00;      // Registro de estado

    // Funciones de eventos externos. En hardware, estos representan pines que se activan para producir un cambio de estado.
    public void reset() {}          // Interrupción de reinicio: fuerza al CPU a un estado conocido.
    public void irq() {}            // Solicitud de interrupción: ejecuta una instrucción en una ubicación específica.
    public void nmi() {}            // Interrupción no mascarable: similar a IRQ, pero no se puede ignorar.
    public void clock() {}          // El CPU se ejecuta a una frecuencia particular, y esta función se llama una vez por ciclo de reloj.
    public void connectBus(Bus bus) {}

    // Variables
    byte fetched = 0x00;    // Representa el valor de entrada de trabajo para la ALU
    short temp = 0x0000;    // Variable auxiliar utilizada en todas partes
    short addr_abs = 0x0000;// Todas las direcciones de memoria utilizadas terminan aquí
    short addr_rel = 0x00;  // Representa la dirección absoluta siguiente después de un salto condicional
    byte opcode = 0x00;     // Es el byte de instrucción
    byte cycles = 0;        // Cuenta cuántos ciclos de reloj quedan para la instrucción
    int clock_count = 0;    // Una acumulación global del número de ciclos de reloj
    Bus bus;
    // Tabla de búsqueda utilizada para implementar el conjunto de instrucciones del 6502.
    private List<Instruccion> lookup;

    // Constructor de la CPU, inicializa la tabla de búsqueda.
    public Micro6502() {
        lookup =new ArrayList<>();
        buildLookup();
    }
    public void conectarBus(Bus bus)
    {
        this.bus=bus;
    }
    // Construye la tabla de búsqueda.

    private void buildLookup() {
        lookup.add(new AND()) ;        // Añadir instrucciones (sin modo de direccionamiento)


    }

    // Ejecuta una instrucción.
    public int execute() {
        // Lee la instrucción en la posición del contador de programa actual
        // y la ejecuta llamando a la función correspondiente en la tabla de búsqueda (lookup table).
        int opcode = bus.read(pc++);
        Instruccion instruccion = lookup.get(opcode);
        if (instruccion != null) {
            return instruccion.ejecutar();
        }
        else return 0;
    }

    ///////////FLAGS //////////////////////////
    public int GetFlag(FLAGS f) {
        if ((status & f.getValue()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
    void setFlag(FLAGS flag, boolean valor) {
        if (valor) {
            status |= flag.getValue();
        } else {
            status &= ~flag.getValue();
        }
    }

    void procesarModo(AddressMode mode)
    {
        short mask;
        switch (mode) {
            case IMMEDIATE:
                fetched=fetch(mode);
                break;
            case ZERO_PAGE:
                mask = 0x00FF;
                addr_abs = (short) (mask & bus.read(pc++));
                fetch(mode);
                break;
            case ZERO_PAGE_X:
                mask = 0x00FF;
                addr_abs = (short) ((mask & bus.read(pc++))+x);
                fetch(mode);
                break;
            case ZERO_PAGE_Y:
                mask = 0x00FF;
                addr_abs = (short) ((mask & bus.read(pc++))+y);
                fetch(mode);
                break;
            default:
                throw new UnsupportedOperationException("Modo de direccionamiento no soportado: " + mode);
        }
    }

    public byte fetch(AddressMode mode)
    {
        if (!(mode== AddressMode.IMPLIED))
            byte fetched = bus.read(addr_abs);
        return fetched;
    }
}