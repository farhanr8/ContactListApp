package contactlist;

import java.awt.EventQueue;

import contactlist.gui.SearchWindow;

public class AppLauncher {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchWindow window = new SearchWindow();
					window.frmContactSearch.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/* Old Main */
//	public static void main(String[] args) throws Exception {
//	
//	try {
//		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CONTACTS?serverTimezone=UTC", "root", "M0dhubazar");
//		
//		Statement myStmt = myConn.createStatement();
//		ResultSet myRs = myStmt.executeQuery("select * from CONTACT"); 
//		
//		while(myRs.next()) {
//			System.out.println(myRs.getString("Lname"));
//		}
//	}
//	catch(Exception e) {
//		e.printStackTrace();
//	}
//	
//	ContactDAO dao = new ContactDAO();
//	System.out.println(dao.searchContacts("thom"));
//	 System.out.println(dao.getAllContacts());
//}

}
