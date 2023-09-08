import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Index {
    
    private HashMap <String, String> hm;
    public Index()
    {

        hm = new HashMap <String, String> ();
    }

    public void initialize ()
    {
        File file = new File ("objects");
        if (!file.exists() || !file.isDirectory())
        {
            String dirName = "objects";
            File directory = new File (dirName);
            directory.mkdirs();
        }
        File index = new File ("objects");

        if (!index.exists ())
        {
            index = new File ("objects", "index");
        }
    }

    public void addBlobs (String fileName) throws Throwable
    {
        Blob blob = new Blob (fileName);
        String s = Blob.encryptPassword(fileName);
        hm.put (blob.originalName(), s);
        


        PrintWriter pw = new PrintWriter ("index");
        for (HashMap.Entry <String, String> entry : hm.entrySet ())
        {
            pw.println (entry.getKey () + " : " + entry.getValue ());

        }
        pw.close();



    }


    public void removeBlob (String fileName) throws FileNotFoundException
    {
        hm.remove (fileName);

         PrintWriter pw = new PrintWriter ("index");
        for (HashMap.Entry <String, String> entry : hm.entrySet ())
        {
            pw.println (entry.getKey () + " : " + entry.getValue ());

        }
        pw.close();
    }




 }


