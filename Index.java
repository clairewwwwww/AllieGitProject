import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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

    public void addBlobs (String fileName)
    {
        Blob blob = new Blob (fileName);
        hm.add (blob.originalName, Blob.encryptPassword(fileName));


        PrintWriter pw = new PrintWriter ("index");
        for (HashMap.Entry <String, String> entry : index.entrySet ())
        {
            pw.println (entry.getKey () + " : " + entry.getValue ());

        }
        pw.close;



    }


    public void removeBlob (String fileName)
    {
        hm.remove (fileName);

         PrintWriter pw = new PrintWriter ("index");
        for (HashMap.Entry <String, String> entry : index.entrySet ())
        {
            pw.println (entry.getKey () + " : " + entry.getValue ());

        }
        pw.close;
    }




 }


