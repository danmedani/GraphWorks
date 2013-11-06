import java.util.ArrayList;
import java.io.*;
import javax.swing.*;

public class TreeNode {
    
    ArrayList<TreeNode> children = new ArrayList<TreeNode>();
    int value;
    
    /* File Output Variables */
       
    public TreeNode(int value){
	    this.value = value;
    }
    
    public void addChild(TreeNode child){
	children.add(child);
    }
    
    public void buildNextLayer(int remainingLayers, int maxDegree){
	
	if (remainingLayers >= 1){
	    for (int i=value; i <= maxDegree; i++){
		children.add(new TreeNode(i));
		children.get(i-value).buildNextLayer(remainingLayers-1, 
						     maxDegree);
	    }
	}
    }
    
    public int getValue(){
	return value;
    }
    
    public int printSequence(String prevPath, JTextArea textArea,
                              PrintStream outFile){
	int counter = 0;
	if (children.size() == 0) {
	    try{
                if (textArea.getLineCount() < 15)
                    textArea.append(prevPath + value + "\n");
                outFile.println(prevPath + value);
                counter = 1;
	    }
	    catch (Exception e) {
		System.err.println("Error writing to the file!");
	    }	    
	}	
	else{
	    String newSeq = prevPath + "" + value;
	    for (int i=0; i < children.size(); i++){
		counter += children.get(i).printSequence(newSeq, textArea,
							 outFile);
	    }
	}
        return counter;
    }

    public int printSequence(String prevPath, PrintStream outFile){

        int counter = 0;

	if (children.size() == 0) {
	    try{
                outFile.println(prevPath + value);
                counter = 1;
	    }
	    catch (Exception e) {
		System.err.println("Error writing to the file!");
	    }
	}else{
	    String newSeq = prevPath + "" + value;
	    for (int i=0; i < children.size(); i++){
		counter += children.get(i).printSequence(newSeq, outFile);
	    }
	}

        return counter;
    }
    
}