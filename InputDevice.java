import java.io.IOException;
import java.io.InputStream;

public class InputDevice extends Device {

    private InputStream is;

    public InputDevice(InputStream is)  {
        this.is = is;
    }

    @Override
    public byte read()  	{
        try {
            return is.read();
        } catch (IOException e) {
            return -1;
        }
    }
}