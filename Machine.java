import java.util.Timer;

public class Machine {

    // konstante za dostop do registrov
    private static final int A = 0;
    private static final int X = 1;
    private static final int L = 2;
    private static final int B = 3;
    private static final int S = 4;
    private static final int T = 5;

    // spremenljivke za registre
    private int regA;
    private int regX;
    private int regL;
    private int regB;
    private int regS;
    private int regT;
    private double regF;
    private int regPC;
    private int regSW;

    // pomnilnik
    private static final int MAX_MEMORY = 1e8;
    private static final int MAX_ADDRESS = MAX_MEMORY-1;
    private byte[] memory;


    // VI naprave
    private static final int MAX_DEVICE = 256;
    private Device[] naprave;

    // procesorska ura
    private static int speed;
    private Timer timer;



    // konstruktor
    public Machine()    {
        memory = new byte[MAX_MEMORY];
        naprave = new Device[MAX_DEVICE];

        for (int i = 0; i < MAX_DEVICE; i++) {
            switch (i) {
                case 0:
                    naprave[i] =  new InputDevice(System.in);
                    break;
                case 1:
                    naprave[i] =  new OutputDevice(System.out);
                    break;
                case 2:
                    naprave[i] =  new OutputDevice(System.err);
                    break;
                default:
                    // TODO name
                    naprave[i] = new FileDevice(Integer.toHexString(i));
            }
        }
    }

    // getters and setters for registers
    /**
     * @return the regA
     */
    public int getRegA() {
        return regA;
    }
    /**
     * @param regA the regA to set
     */
    public void setRegA(int regA) {
        this.regA = regA;
    }

    /**
     * @return the regB
     */
    public int getRegB() {
        return regB;
    }
    /**
     * @param regB the regB to set
     */
    public void setRegB(int regB) {
        this.regB = regB;
    }

    /**
     * @return the regF
     */
    public double getRegF() {
        return regF;
    }
    /**
     * @param regF the regF to set
     */
    public void setRegF(double regF) {
        this.regF = regF;
    }

    /**
     * @return the regL
     */
    public int getRegL() {
        return regL;
    }
    /**
     * @param regL the regL to set
     */
    public void setRegL(int regL) {
        this.regL = regL;
    }

    /**
     * @return the regS
     */
    public int getRegS() {
        return regS;
    }
    /**
     * @param regS the regS to set
     */
    public void setRegS(int regS) {
        this.regS = regS;
    }

    /**
     * @return the regT
     */
    public int getRegT() {
        return regT;
    }/**
     * @param regT the regT to set
     */
    public void setRegT(int regT) {
        this.regT = regT;
    }
    
    /**
     * @return the regX
     */
    public int getRegX() {
        return regX;
    }
    /**
     * @param regX the regX to set
     */
    public void setRegX(int regX) {
        this.regX = regX;
    }

    /**
     * @return the regPC
     */
    public int getRegPC() {
        return regPC;
    }
    /**
     * @param regPC the regPC to set
     */
    public void setRegPC(int regPC) {
        this.regPC = regPC;
    }
    
    /**
     * @return the regSW
     */
    public int getRegSW() {
        switch(regSW)   {
            case -1: return 0x00;
            case  0: return 0x40;
            case  1: return 0x80;
            default: error("getRegSW");
        }
        return 0;
    }
    /**
     * @param regSW the regSW to set
     */
    public void setRegSW(int regSW) {
        this.regSW = regSW;
    }

    //splošna getter and setter
    public int getReg(int regno)    {
        switch(regno)   {
            case A: return getRegA();
            case X: return getRegX();
            case L: return getRegL();
            case B: return getRegB();
            case S: return getRegS();
            case T: return getRegT();
            default: error("getReg");
        }
    }
    public void setReg(int regno, int val)  {
        switch(regno)   {
            case A: setRegA(val);
            case X: setRegX(val);
            case L: setRegL(val);
            case B: setRegB(val);
            case S: setRegS(val);
            case T: setRegT(val);
            default: error("setReg");
        }
    }

    // getter in setter za dostop do pomnilnika
    public int getByte(int addr) {
        
    }
    public void setByte(int addr, int val)  {

    }

    public int getWord(int addr) {
        
    }
    public void setWord(int addr, int val)  {

    }

    public double getFloat(int addr)  {
        // Koristno uporabiti Double.doubleToLongBits() ...

    }
    public void setFloat(int addr, double value) {
        
    }

    // VI naprave - metode
    public Device getDevice(int num)    {
        return naprave[num];
    }
    public void setDevice(int num, Device dev) {
        naprave[num] = dev;
    }

    // metoda za obravnavo težav
    public void notImplemented(String mnemonic) {
        System.err.println(mnemonic);
        System.err.println("Ni ravno težava stroja, bolj težava programerja :)");
    }
    public void invalidOpcode(int opcode)   {
        System.err.println(opcode);
        System.err.println("Izvajalnik je naletel na operacijsko kodo ukaza, ki ni veljavna.");
    }
    public void invalidAddressing() {
        System.err.println("Neveljavno naslavljanje.");
    }


    // nalaganje iz PC registra
    public int fetch()  {
        int currentByte = getByte(PC);
        PC++;
        if(PC > MAX_ADDRESS)    {
            System.err.println("PC register overflow");
            PC = 0;
        }
        return currentByte;
    }

    // izvajalnik
    public void execute()   {
        // TODO
    }

    // izvajlniki formatov
    public boolean execF1(int opcode)   {
        switch(opcode)  {
            case Opcode.FLOAT: return true;
            case Opcode.FIX: return true;
            case Opcode.NORM: return true;
            case Opcode.SIO: return true;
            case Opcode.HIO: return true;
            case Opcode.TIO: return true;
            default: return false;
        }
    }
    public boolean execF2(int opcode, int operand)   {

        switch(opcode)  {
            case Opcode.ADDR: return true;
            case Opcode.SUBR: return true;
            case Opcode.MULR: return true;
            case Opcode.DIVR: return true;
            case Opcode.COMPR: return true;
            case Opcode.SHIFTL: return true;
            case Opcode.SHIFTR: return true;
            case Opcode.RMO: return true;
            case Opcode.CLEAR: return true;
            case Opcode.TIXR: return true;
            case Opcode.SVC: return true;
            default: return false;
        }
    }
    public boolean execSICF3F4(int opcode, int ni, int operand)   {
        switch(opcode)  {
            case Opcode.STA: return true;
            case Opcode.STX: return true;
            case Opcode.STL: return true;
            case Opcode.STCH: return true;
            case Opcode.STB: return true;
            case Opcode.STS: return true;
            case Opcode.STF: return true;
            case Opcode.STT: return true;
            case Opcode.STSW: return true;
            case Opcode.JEQ: return true;
            case Opcode.JGT: return true;
            case Opcode.JLT: return true;
            case Opcode.J: return true;
            case Opcode.RSUB: return true;
            case Opcode.JSUB: return true;
            case Opcode.LDA: return true;
            case Opcode.LDX: return true;
            case Opcode.LDL: return true;
            case Opcode.LDCH: return true;
            case Opcode.LDB: return true;
            case Opcode.LDS: return true;
            case Opcode.LDF: return true;
            case Opcode.LDT: return true;
            case Opcode.ADD: return true;
            case Opcode.SUB: return true;
            case Opcode.MUL: return true;
            case Opcode.DIV: return true;
            case Opcode.AND: return true;
            case Opcode.OR: return true;
            case Opcode.COMP: return true;
            case Opcode.TIX: return true;
            case Opcode.RD: return true;
            case Opcode.WD: return true;
            case Opcode.TD: return true;
            case Opcode.ADDF: return true;
            case Opcode.SUBF: return true;
            case Opcode.MULF: return true;
            case Opcode.DIVF: return true;
            case Opcode.COMPF: return true;
            case Opcode.LPS: return true;
            case Opcode.STI: return true;
            case Opcode.SSK: return true;
            default: return false;
        }
    }

    // Procesorske ura
    public void start() {
        if(timer == null)   {
            timer = new Timer();
            // TODO
            timer.scheduleAtFixedRate(task, delay, period);
        }
    }
    public void stop()  {
        if(timer != null)   {
            timer.cancel();
            timer = null;
        }
    }
    public boolean isRunning() {
        return timer != null;
    }
    // nastavljanje hitrosti
    public int getSpeed()   {
        return speed;
    }
    public void setSpeed(int kHz)  {
        speed = kHz;
    }
}