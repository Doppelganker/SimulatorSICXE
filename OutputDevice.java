import java.io.OutputStream;

public class OutputDevice extends Device    {

    private OutputStream os;

    public OutputDevice(OutputStream os)    {
        this.os = os;
    }

    @Override
    public void write(byte value) {
        try {
            // TODO byte -> int conversion
            os.write(value);
            os.flush();
        } catch (IOException e) {
            
        }
    }
}