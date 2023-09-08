import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class Blob
{

    public Blob (String fileName) throws Throwable
    {
        String beforeShah = fileToString (fileName);
        String password = encryptPassword (beforeShah);
        stringToFile (password, fileName);
    }   



    public static String fileToString (String fileName) throws Throwable
        {
            String endResult = "";
            //File file = new File ("output.txt");
            File file = new File (fileName);
            char ch;
 
             // check if File exists or not
            FileReader fr;
            try
            {
                fr = new FileReader(fileName);
                while(fr.ready())
                {
                    ch = (char) fr.read();
                    endResult += ch;
                }
 
                fr.close();
            } catch (Error | IOException e)
            {
                throw e;
            }
            return endResult;
        }

    private static String encryptPassword(String password)
    {
        String sha1 = "";
        try
        { 
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static void stringToFile (String string, String fileName) throws IOException
        {
     


            // attach a file to FileWriter
            File file = new File ("objects/" + string);
            file.createNewFile();
            FileWriter fw= new FileWriter(file);
            FileReader fr = new FileReader (fileName);

            char ch;
            String endResult = "";
            while(fr.ready())
                {
                    ch = (char) fr.read();
                    endResult += ch;
                }



                fw.write(endResult);
     
            //close the file
            fw.close();
        }


}