package ExcelDriven;

import java.io.IOException;
import java.util.ArrayList;

public class Sample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		dataDriven d = new dataDriven();
		ArrayList<?> data = d.getData("purchase");

		System.out.println(data);
	}

}
