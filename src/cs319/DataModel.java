package cs319;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.AbstractListModel;

public class DataModel extends AbstractListModel {
	private ArrayList<String> values;
	
	public DataModel() throws FileNotFoundException{
		Scanner inFile = new Scanner(new File("companies.txt"));
		values = new ArrayList<String>();
		
		while (inFile.hasNextLine()) {
			values.add(inFile.next());
		}
		inFile.close();
		
	}
	
	public void removeComp(String company){
		values.remove(company);
		
		try {
			PrintWriter out = new PrintWriter(new FileWriter("companies.txt"));
			out.append(values.get(0));
			for(int i = 1; i < values.size(); i++){
				out.println();
				out.append(values.get(i));
			}
			out.close();
		} 
		
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public String getElementAt(int index) {
		return values.get(index);
	}

	@Override
	public int getSize() {
		return values.size();
	}
}
