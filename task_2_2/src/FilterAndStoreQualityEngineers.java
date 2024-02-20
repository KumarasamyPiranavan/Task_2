import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class FilterAndStoreQualityEngineers {
	public static void main(String[] args) throws Exception {
		
		String url = "jdbc:mysql://127.0.0.1:3333/task_2_2";
		String userName ="root";
		String passWord ="";
		
		 
		
		// Establishing the connection
		Connection con = DriverManager.getConnection(url,userName,passWord);
		
		
		

		
		
		File inputFile = new File("src\\employee_data.xml");
		
		// Create a DocumentBuilder
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			// Parse the XML file to create a Document object
			Document document = dBuilder.parse(inputFile);
			
			// Normalize the document
            document.getDocumentElement().normalize();
            
            // Get the root element
            Element rootElement = document.getDocumentElement();
            
            // Get all employee nodes
            NodeList employeeList = rootElement.getElementsByTagName("employee");
            
            // Iterate through each eployee node
            for (int i = 0; i < employeeList.getLength(); i++) {
            	Node employeeNode = employeeList.item(i);
            	
            	if (employeeNode.getNodeType() == Node.ELEMENT_NODE) {
            		Element employeeElement = (Element) employeeNode;
            		
            		// Retrieve employee details
                    int id = Integer.parseInt(employeeElement.getElementsByTagName("id").item(0).getTextContent());
                    String name  = employeeElement.getElementsByTagName("name").item(0).getTextContent();
                    String position = employeeElement.getElementsByTagName("position").item(0).getTextContent();
                    String department = employeeElement.getElementsByTagName("department").item(0).getTextContent();
                    
                    SaveToDatabase(con,id,name,position,department);
                    
//                    // Display employee details
//                    System.out.println(" " + title);
//                    System.out.println("A: " + author);
//                    System.out.println("Genre: " + genre);
//                    System.out.println("Price: " + price);
//                    System.out.println("Publication Date: " + publication_date);
//                    System.out.println("ISBN: " + isbn);
//                    System.out.println();
                    
            		
            	}
            	
            	
            }


            
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
	}





    private static void SaveToDatabase(Connection con, int id, String name, String position, String department) throws SQLException {
		// SQL query to insert data
				String sql = "INSERT INTO employees(id, name, position, department) VALUE(?, ?, ?, ?)";
				
		// Creating a PreparedStatement with the SQL query
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        
        // Setting values for the placeholder in the SQL query
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, position);
        preparedStatement.setString(4, department);

        // Executing the SQL query to insert data
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Data inserted successfully!");
        } else {
            System.out.println("Failed to insert data.");
        }
	}

}
