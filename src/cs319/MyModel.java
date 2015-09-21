package cs319;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel{

	private String[] columnNames = {"First Name", "Last Name", "Age", "Gender", "Vegetarian"};;
	private Object[][] dataValues = {
				{"Kathy", "Smith", 25, 'F', false},
				{"John", "Doe", 43, 'M', false},
				{"Sue", "Black", 61, 'F', true},
				{"Jane", "White", 17, 'F', true},
				{"Joe", "Brown", 32, 'M', false},
				{"Abby", "Dawn", 41, 'F', false},
				{"Mila", "Manson", 26, 'F', true},
				{"Mila", "Manson", 26, 'F', true},
				{"Jack", "Schmitt", 32, 'M', true}
				//add more values here to make the scrollbar work
			};
	private String dataFileName;
	
	//default value constructor
    MyModel() {
	
    }
    
	//constructor with header values
    MyModel(String dataFileName, String[] header) {
    	this.columnNames = header;
    	this.dataFileName = dataFileName;
    	
    	try {
			readData();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    
    

	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return dataValues.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return dataValues[row][column];
	}
	
	@Override
    public String getColumnName(int col) {
    	return columnNames[col];
    }

	
	private void readData() throws FileNotFoundException{
		Scanner data = new Scanner(new File(dataFileName));
		
		ArrayList dataList = new ArrayList();
		data.useDelimiter(",");
		while(data.hasNext()){
			dataList.add(data.next());
		}
		System.out.print(dataList);
	}

}
