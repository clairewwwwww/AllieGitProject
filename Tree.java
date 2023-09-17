import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Tree {
    private ArrayList<String> treeList;
    private HashMap <String, String> blobMap;
    //private PrintWriter pw;

    public Tree() throws IOException
    {
        initialize();
        treeList = new ArrayList<String>();
        blobMap = new HashMap <String, String> ();
        //pw = new PrintWriter(new FileWriter("tree", true));
    }

    public void initialize ()
    {
        File file = new File ("tree");
        if (!file.exists())
        {
            File newFile = new File("tree");
        }
        //new File("objects").mkdirs();
    }
    /*Add another entry into the tree:
Entries are added as new lines of Strings
    tree.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b")
    tree.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt")
Entries are a newline String in format  typeOfFile : shaOfFile : optionalFileName
    typeOfFile: Can be either the String 'blob' or 'tree' 
    shaOfFile: Is a valid sha1 of a file in the objects folder
    optionalFileName: if it's a blob, please include the filename
    the two are separated by a " : "
Do NOT allow for duplicate 'trees' or duplicate 'filenames' in the file */

    public void addTree (String entry) throws Throwable
    {
        String typeOfFile = entry.substring(0, 4); 
        String shaOfFile = entry.substring(7, 47);
        if(treeList.contains(shaOfFile) || blobMap.containsKey(shaOfFile))
        {
            return;
        }
        if(typeOfFile.equals("tree"))
        {
            treeList.add(shaOfFile);
        }
        else
        {
            String optionalFileName = entry.substring(50);
            blobMap.put(shaOfFile, optionalFileName);
        }
        printToFile();
    }
/*Remove an entry from a tree by...
    Remove a BLOB entry from the tree based on a filename
        tree.remove("file1.txt")
    Remove a TREE entry based on SHA1
        tree.remove("bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b")
 */
    public void removeTree (String entry) throws IOException
    {
        if(treeList.contains(entry))
        {
            treeList.remove(entry);
        }
        else if (blobMap.containsValue(entry))
        {
            Set<String> blobSet = blobMap.keySet();
            String target = "";
            for(String key: blobSet)
		    {
                if(blobMap.get(key).equals(entry))
                {
                    target = key;
                }
		    }
            blobMap.remove(target);
        }
        printToFile();
    }

    private void printToFile() throws IOException
    {
        PrintWriter pw = new PrintWriter(new FileWriter("tree", false));
        Set<String> blobSet = blobMap.keySet();
        for(String key: blobSet)
		{
            String fileName = blobMap.get(key);
            pw.append("blob : " + key + " : " + fileName);
            pw.append("\n");
		}

        if(treeList.size() > 0)
        {
            int last = treeList.size()-1;
            for (int i = 0; i < last; i++) 
            {
                pw.append("tree : " + treeList.get(i));
                pw.append("\n");
            }
            pw.append("tree : " + treeList.get(last));
        }
        pw.close();
    }
}
