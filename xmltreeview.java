import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Event.*;
import java.io.*;
import javax.swing.tree.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.apache.xerces.parsers.*;

public class xmltreeview
 {

       private SAXTreeBuilder saxTree = null;
       private static String file = "";
       
       public static void main(String args[]){
              JFrame frame = new JFrame("XMLTreeView: [ VIPSResult.xml ]");
              frame.setSize(400,400);

              frame.addWindowListener(new WindowAdapter(){
                   public void windowClosing(WindowEvent ev){
                       System.exit(0);
                   }
              });
              file = "VIPSResult.xml";
              new xmltreeview(frame);
       }
       
       public xmltreeview(JFrame frame){ 
              frame.getContentPane().setLayout(new BorderLayout());  
              DefaultMutableTreeNode top = new DefaultMutableTreeNode(file);
//              DefaultMutableTreeNode top = new DefaultMutableTreeNode("XML Document"); 
              
              saxTree = new SAXTreeBuilder(top); 
              
              try {             
              SAXParser saxParser = new SAXParser();
              saxParser.setContentHandler(saxTree);
              saxParser.parse(new InputSource(new FileInputStream(file)));
              }catch(Exception ex){
                 top.add(new DefaultMutableTreeNode(ex.getMessage()));
              }
              JTree tree = new JTree(saxTree.getTree()); 
              JScrollPane scrollPane = new JScrollPane(tree);
              
              frame.getContentPane().add("Center",scrollPane);                                           
              frame.setVisible(false);       
              
        } 
        
     
}
class SAXTreeBuilder extends DefaultHandler{
       
       private DefaultMutableTreeNode currentNode = null;
       private DefaultMutableTreeNode previousNode = null;
       private DefaultMutableTreeNode rootNode = null;

       public SAXTreeBuilder(DefaultMutableTreeNode root){
              rootNode = root;
       }
       public void startDocument(){
              currentNode = rootNode;
       }
       public void endDocument(){
       }
       public void characters(char[] data,int start,int end){
              String str = new String(data,start,end);              
		
              if (!str.equals("") && Character.isLetter(str.charAt(0)))
		{
                  currentNode.add(new DefaultMutableTreeNode(str));           
			

		}
       }
       public void startElement(String uri,String qName,String lName,Attributes atts){
              previousNode = currentNode;
              currentNode = new DefaultMutableTreeNode(lName);
              // Add attributes as child nodes //
              attachAttributeList(currentNode,atts);
              previousNode.add(currentNode);              
       }
       public void endElement(String uri,String qName,String lName){
              if (currentNode.getUserObject().equals(lName))
                  currentNode = (DefaultMutableTreeNode)currentNode.getParent();              
       }
       public DefaultMutableTreeNode getTree(){
              return rootNode;
       }
       
       private void attachAttributeList(DefaultMutableTreeNode node,Attributes atts)
	{
		try
		{
		byte []b=new byte[2];
		b[0]=13;b[1]=10;
		int val,val1;
		String toWrite="";
		char ch;
               FileOutputStream fos=new FileOutputStream(new File("req_data.txt"),true);
		//fos.write("-------------------------------------------".getBytes());
		//fos.write(b);		
		for (int i=0;i<atts.getLength();i++){
                    String name = atts.getLocalName(i);
                    String value = atts.getValue(name);
		
		if(("ID".equals(name)))
		{
		fos.write((name + " = " + value).getBytes());
		fos.write(b);
		}		

		if(("SRC".equals(name)||"src".equals(name)))
		{
			toWrite="";
			String []str = value.split("<");
			for(String a : str) {
				if(((a.indexOf("A "))==0)||((a.indexOf("A "))==0)){
					
					if(a.contains("title=")){
						val=a.indexOf("title=");
						val1 = "title=".length();
						if('\"'==a.charAt(val+val1)) {
							int val2 = a.indexOf("\"",val+val1+1);
							toWrite = (String)a.subSequence(val+val1+1,val2);											
							System.out.println(toWrite);
							fos.write(toWrite.getBytes());
							fos.write(b);				
						}
						else {
							int val2 = a.indexOf(" ",val+val1);
							toWrite = (String)a.subSequence(val+val1,val2);											
							System.out.println(toWrite);
							fos.write(toWrite.getBytes());
							fos.write(b);				
						}
					}
				}
			}
			//fos.write((value).getBytes());
			//fos.write(b);
		}	
		       node.add(new DefaultMutableTreeNode(name + " = " + value));
		//System.out.println("req data..."+name + " = " + value+",");
               }
		System.out.println("");
		}
		catch(Exception e){}
       }
       
}
