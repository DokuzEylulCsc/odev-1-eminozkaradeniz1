import java.io.*;

public class Program {
    public static void main(String[] args) throws IOException {
        Meydan meydan = new Meydan();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        meydan.start();
        System.out.flush();

        System.setOut(old);
        System.out.println(baos.toString());

        BufferedWriter writer = new BufferedWriter(new FileWriter("Meydan.txt"));
        writer.write(baos.toString());
        writer.close();
    }
}
