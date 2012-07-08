import org.w3c.tidy.Tidy;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.w3c.dom.Document;

public class htmltoxml
{
   public static void main(String[] args)
{
      try{
         FileInputStream FIS=new FileInputStream("D:\\xmltograpviz\\htmltoxml\\0.html");
         FileOutputStream FOS=new FileOutputStream("D:\\xmltograpviz\\htmltoxml\\out2.xml");   
         Tidy T=new Tidy();
         Document D=T.parseDOM(FIS,FOS);
         }

      catch (java.io.FileNotFoundException e)
         {
		System.out.println(e.getMessage());
	}   


}
}
