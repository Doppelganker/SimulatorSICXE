import java.io.RandomAccessFile;

public class FileDevice extends Device  {

    private RandomAccessFile raf;
    private String name;

    public FileDevice(String name)   {
        this.name = name;
    }

    private openFile()  {
        try {
            raf = new RandomAccessFile(name, "rw");
        } catch (FileNotFoundException e) {
        }
    }

    // TODO byte <--> int conversion
    @Override
    public byte read() {
        if(raf == null) openFile();
        try {
            return raf.read();
        } catch (IOException e) {
        }
    }

    @Override
    public void write(byte value)   {
        if(raf == null) openFile();
        try {
            raf.write(value);
        } catch (IOException e) {
        }
    }
}