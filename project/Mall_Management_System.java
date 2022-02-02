package project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Mall_Management_System {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	Statement st;

	String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
	String RESET = "\033[0m"; // Text Reset
	String CYAN_UNDERLINED = "\033[4;36m"; // CYAN
	String CYAN_BACKGROUND_BRIGHT = "\033[0;106m"; // CYAN
	String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
	String BLACK_BOLD = "\033[1;30m"; // BLACK

	public Mall_Management_System() throws FileNotFoundException, IOException, SQLException {
		con = MyConnection.getMyConnection();
		// System.out.println("connection get open");
	}

	public void user_orderData() throws SQLException {

		String s1;
		int table_no;
		char re;
		st = con.createStatement();
		rs = st.executeQuery("Select * from users ");

		System.out
				.println("Name" + "\t" + "Name_Of_variety" + "\t\t" + "Table_no" + "\t\t" + "Price" + "\t\t" + "Total");
		while (rs.next()) {
			System.out.print(rs.getString(1) + "\t\t   ");
			System.out.print(rs.getString(2) + "\t \t  ");
			System.out.print(rs.getInt(3) + "\t \t  ");
			System.out.print(rs.getFloat(4) + "\t \t  ");
			System.out.print(rs.getFloat(5) + "\t\t   ");
			System.out.println();
		}
		System.out.println(
				"..............................................................................................................................................");

		do {
			s1 = "select * from users where No_of_table = ?";
			pstmt = con.prepareStatement(s1);
			Scanner use = new Scanner(System.in);
			System.out.println(CYAN_UNDERLINED + "Enter a Table_no" + RESET);
			int no_of_table = use.nextInt();
			pstmt.setInt(1, no_of_table);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("Name" + "\t " + "Name_of_Variety" + "\t" + "Price" + "\t" + "Total");
				System.out.print(rs.getString(1) + "\t\t");
				System.out.print(rs.getString(2) + "\t\t");
				System.out.print(rs.getFloat(4) + "\t\t");
				System.out.println(rs.getFloat(5));
				System.out.println();
				System.out.println(
						".................................................................................................................");
			}

			System.out.println("do you want continue for get some details(y/n)");
			re = use.next().charAt(0);
		} while (re == 'y' || re == 'Y');

	}

	public void insertData_Nonveg() throws SQLException {

		Scanner in = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Name_of_variety" + RESET);
		String Name_of_variety = in.next();
		System.out.println(CYAN_UNDERLINED + "enter the price" + RESET);
		float price = in.nextFloat();

		pstmt = con.prepareStatement("insert into Non_veg_food(Name_of_variety,price)values(?,?)");
		pstmt.setString(1, Name_of_variety);
		pstmt.setFloat(2, price);

		int n = pstmt.executeUpdate();
		System.out.println(n + " Record is Inserted");
	}

	public void updateData_Nonveg() throws SQLException {
		Scanner up = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter Id where we want update" + RESET);
		int id = up.nextInt();
		System.out.println(CYAN_UNDERLINED + "Enter the Name_of_variety" + RESET);
		String Name_of_variety = up.next();
		System.out.println(CYAN_UNDERLINED + "Enter the Price" + RESET);
		float price = up.nextFloat();

		String s = "update Non_veg_food  Set Name_of_variety = ? , price = ? where id = ?";
		pstmt = con.prepareStatement(s);

		pstmt.setString(1, Name_of_variety);
		pstmt.setFloat(2, price);
		pstmt.setInt(3, id);

		int i = pstmt.executeUpdate();
		System.out.println(i + "Record Updated");
	}

	public void deleteRecord_Nonveg() throws SQLException {
		Scanner del = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter id where we want delete" + RESET);
		int id = del.nextInt();
		String s = "Delete from Non_veg_food where id = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, id);

		int r = pstmt.executeUpdate();
		System.out.println(r + "Deleted Successfully");

	}

	public void readRecord_Nonveg() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("Select * from Non_veg_food ");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getFloat(3));
			System.out.println();
		}
	}

	public void orderData_Nonveg() throws SQLException {
		float price = 0, sum = 0;
		char de;
		String name_variety = null;
		String s1;

		Scanner ord = new Scanner(System.in);
		do {
			System.out.println(CYAN_UNDERLINED + "Enter Your Name" + RESET);
			String name = ord.next();
			System.out.println(CYAN_UNDERLINED + "Enter Your Table_no" + RESET);
			int table = ord.nextInt();

			do {
				System.out.println(CYAN_UNDERLINED + "enter the Name_of_variety" + RESET);
				String Name_of_variety = ord.next();
				String s = "select * from Non_veg_food price  where Name_of_variety = ?";
				pstmt = con.prepareStatement(s);
				pstmt.setString(1, Name_of_variety);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println("Name " + "\t" + "Name_of_Variety" + "\t" + "Price");
					System.out.print(rs.getInt(1) + "\t   ");
					System.out.print(rs.getString(2) + "\t   ");
					System.out.print(rs.getFloat(3));
					System.out.println();
					System.out.println(
							"......................................................................................");

					price = rs.getFloat(3);
					sum += price;

					name_variety = rs.getString(2);
				}
				System.out.println("do you want continue to order(y/n)");
				de = ord.next().charAt(0);
			} while (de == 'y' || de == 'Y');

			pstmt = con.prepareStatement("insert into users values(?,?,?,?,?)");
			pstmt.setString(1, name);
			pstmt.setString(2, name_variety);
			pstmt.setInt(3, table);
			pstmt.setFloat(4, price);
			pstmt.setFloat(5, sum);
			pstmt.executeUpdate();

			s1 = "select * from users where name = ?";
			pstmt = con.prepareStatement(s1);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("Name" + "\t" + "Name_of_Variety" + "\t" + "Price" + "\t" + "Total");
				System.out.print(rs.getString(1) + "\t   ");
				System.out.print(rs.getString(2) + "\t   ");
				System.out.print(rs.getFloat(4) + "\t   ");
				System.out.println(rs.getFloat(5));
				System.out.println();
				System.out.println(
						"........................................................................................................");
			}

			System.out.println(CYAN_BACKGROUND_BRIGHT + "Total Amount : " + sum + RESET);

			System.out.println("do you want continue to Non_veg_foods(y/n)");
			de = ord.next().charAt(0);
		} while (de == 'y' || de == 'Y');

	}

	public void selectData_Nonveg() throws SQLException {
		Scanner sel = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Name_of_variety" + RESET);
		String Name_of_variety = sel.next();
		String s = "select * from Non_veg_food  where Name_of_Variety = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, Name_of_variety);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getInt(3));
			System.out.println();
		}

	}

	public void insertData_veg() throws SQLException {

		Scanner in = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Name_of_variety" + RESET);
		String Name_of_variety = in.next();
		System.out.println(CYAN_UNDERLINED + "Enter the Price" + RESET);
		float price = in.nextFloat();

		pstmt = con.prepareStatement("insert into veg_food(Name_of_variety,price) values(?,?)");
		pstmt.setString(1, Name_of_variety);
		pstmt.setFloat(2, price);

		int n = pstmt.executeUpdate();
		System.out.println(n + " Record is Inserted");
	}

	public void updateData_veg() throws SQLException {
		Scanner up = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter id where we want update" + RESET);
		int id = up.nextInt();
		System.out.println(CYAN_UNDERLINED + "Enter the Name_of_variety" + RESET);
		String Name_of_variety = up.next();
		System.out.println(CYAN_UNDERLINED + "Enter the Price" + RESET);
		float price = up.nextFloat();

		String s = "update veg_food  Set Name_of_variety = ? , price = ? where id = ?";
		pstmt = con.prepareStatement(s);

		pstmt.setString(1, Name_of_variety);
		pstmt.setFloat(2, price);
		pstmt.setInt(3, id);

		int i = pstmt.executeUpdate();
		System.out.println(i + "Record Updated");
	}

	public void readRecord_veg() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("Select * from veg_food ");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getFloat(3));
			System.out.println();
		}
	}

	public void deleteRecord_veg() throws SQLException {
		Scanner del = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter id where we want delete" + RESET);
		int id = del.nextInt();
		String s = "Delete from veg_food where id = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, id);

		int r = pstmt.executeUpdate();
		System.out.println(r + "Deleted Successfully");

	}

	public void orderData_veg() throws SQLException {
		float price = 0, sum = 0;
		char de;
		String name_variety = null;
		String s1;

		Scanner ord = new Scanner(System.in);
		do {
			System.out.println(CYAN_UNDERLINED + "Enter Your Name" + RESET);
			String name = ord.next();
			System.out.println(CYAN_UNDERLINED + "Enter Your Table_no" + RESET);
			int table = ord.nextInt();

			do {
				System.out.println(CYAN_UNDERLINED + "enter the Name_of_variety" + RESET);
				String Name_of_variety = ord.next();
				String s = "select * from veg_food price  where Name_of_variety = ?";
				pstmt = con.prepareStatement(s);
				pstmt.setString(1, Name_of_variety);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println("Name" + "\t" + "Name_of_Variety" + "\t" + "Price");
					System.out.print(rs.getInt(1) + "\t   ");
					System.out.print(rs.getString(2) + "\t   ");
					System.out.print(rs.getFloat(3));
					System.out.println();
					System.out.println(
							"....................................................................................");

					price = rs.getFloat(3);
					sum += price;

					name_variety = rs.getString(2);
				}
				System.out.println("do you want continue to order(y/n)");
				de = ord.next().charAt(0);
			} while (de == 'y' || de == 'Y');

			pstmt = con.prepareStatement("insert into users values(?,?,?,?,?)");
			pstmt.setString(1, name);
			pstmt.setString(2, name_variety);
			pstmt.setInt(3, table);
			pstmt.setFloat(4, price);
			pstmt.setFloat(5, sum);
			pstmt.executeUpdate();

			s1 = "select * from users where name = ?";
			pstmt = con.prepareStatement(s1);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("Name" + "\t" + "Name_of_Variety" + "\t" + "Price" + "\t" + "Total");
				System.out.print(rs.getString(1) + "\t   ");
				System.out.print(rs.getString(2) + "\t   ");
				System.out.print(rs.getFloat(4) + "\t   ");
				System.out.println(rs.getFloat(5));
				System.out.println();
				System.out.println(
						"...........................................................................................................");
			}

			System.out.println(CYAN_BACKGROUND_BRIGHT + "Total Amount : " + sum + RESET);

			System.out.println("do you want continue with  VEG_FOODS(y/n)");
			de = ord.next().charAt(0);
		} while (de == 'y' || de == 'Y');

	}

	public void selectData_veg() throws SQLException {
		Scanner sel = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Name_of_variety" + RESET);
		String Name_of_variety = sel.next();
		String s = "select * from Non_veg_food  where Name_of_Variety = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, Name_of_variety);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getInt(3));
			System.out.println();
		}

	}

	public void insertData_iceBaker() throws SQLException {

		Scanner in = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Name_of_variety" + RESET);
		String Name_of_variety = in.next();
		System.out.println(CYAN_UNDERLINED + "Enter the Price" + RESET);
		float price = in.nextFloat();

		pstmt = con.prepareStatement("insert into Ice_Baker(Name_of_variety,price)values(?,?)");
		pstmt.setString(1, Name_of_variety);
		pstmt.setFloat(2, price);

		int n = pstmt.executeUpdate();
		System.out.println(n + " Record is Inserted");
	}

	public void updateData_iceBaker() throws SQLException {
		Scanner up = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter id where we want update" + RESET);
		int id = up.nextInt();
		System.out.println(CYAN_UNDERLINED + "enter the Name_of_variety" + RESET);
		String Name_of_variety = up.next();
		System.out.println(CYAN_UNDERLINED + "enter the price" + RESET);
		float price = up.nextFloat();

		String s = "update Ice_Baker  Set Name_of_variety = ? , price = ? where id = ?";
		pstmt = con.prepareStatement(s);

		pstmt.setString(1, Name_of_variety);
		pstmt.setFloat(2, price);
		pstmt.setInt(3, id);

		int i = pstmt.executeUpdate();
		System.out.println(i + "Record Updated");
	}

	public void deleteRecord_iceBaker() throws SQLException {
		Scanner del = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter id where we want delete" + RESET);
		int id = del.nextInt();
		String s = "Delete from Ice_Baker where id = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, id);

		int r = pstmt.executeUpdate();
		System.out.println(r + "Deleted Successfully");

	}

	public void readRecord_icebaker() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("Select * from Ice_Baker ");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getFloat(3));
			System.out.println();
		}
	}

	public void orderData_iceBaker() throws SQLException {
		float price = 0, sum = 0;
		char de;
		String name_variety = null;
		String s1;

		Scanner ord = new Scanner(System.in);
		do {
			System.out.println(CYAN_UNDERLINED + "Enter your name" + RESET);
			String name = ord.next();
			System.out.println(CYAN_UNDERLINED + "Enter table no" + RESET);
			int table = ord.nextInt();

			do {
				System.out.println(CYAN_UNDERLINED + "Enter the Name_of_variety" + RESET);
				String Name_of_variety = ord.next();
				String s = "select * from Ice_baker price  where Name_of_variety = ?";
				pstmt = con.prepareStatement(s);
				pstmt.setString(1, Name_of_variety);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println("Name" + "\t" + "Name_of_Varirty" + "\t" + "Price");
					System.out.print(rs.getInt(1) + "\t");
					System.out.print(rs.getString(2) + "\t");
					System.out.print(rs.getFloat(3));
					System.out.println();
					System.out.println(
							"...................................................................................");

					price = rs.getFloat(3);
					sum += price;

					name_variety = rs.getString(2);
				}

				System.out.println("do you want continue to order(y/n)");
				de = ord.next().charAt(0);
			} while (de == 'y' || de == 'Y');

			pstmt = con.prepareStatement("insert into users values(?,?,?,?,?)");
			pstmt.setString(1, name);
			pstmt.setString(2, name_variety);
			pstmt.setInt(3, table);
			pstmt.setFloat(4, price);
			pstmt.setFloat(5, sum);
			pstmt.executeUpdate();

			s1 = "select * from users where name = ?";
			pstmt = con.prepareStatement(s1);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("Name" + "\t" + "Name_of_Variety" + "\t" + "Price" + "\t" + "Total");
				System.out.print(rs.getString(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.print(rs.getFloat(4) + "\t");
				System.out.println(rs.getFloat(5));
				System.out.println();
				System.out.println(
						".........................................................................................................");
			}

			System.out.println(CYAN_BACKGROUND_BRIGHT + "Total Amount : " + sum + RESET);

			System.out.println("do you want continue with ICE_BAKER(y/n)");
			de = ord.next().charAt(0);
		} while (de == 'y' || de == 'Y');

	}

	public void selectData_iceBaker() throws SQLException {
		Scanner sel = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Name_of_variety" + RESET);
		String Name_of_variety = sel.next();
		String s = "select * from Ice_Baker  where Name_of_Variety = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, Name_of_variety);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getInt(3));
			System.out.println();
		}

	}

	public void insertData_sweetsBaker() throws SQLException {

		Scanner in = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Name_of_variety" + RESET);
		String Name_of_variety = in.next();
		System.out.println(CYAN_UNDERLINED + "Enter the price" + RESET);
		float price = in.nextFloat();

		pstmt = con.prepareStatement("insert into Sweets_Baker(Name_of_variety, price) values(?,?)");
		pstmt.setString(1, Name_of_variety);
		pstmt.setFloat(2, price);

		int n = pstmt.executeUpdate();
		System.out.println(n + " Record is Inserted");
	}

	public void deleteRecord_sweetsBaker() throws SQLException {
		Scanner del = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter id where we want delete" + RESET);
		int id = del.nextInt();
		String s = "Delete from Sweets_Baker where id = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, id);

		int r = pstmt.executeUpdate();
		System.out.println(r + "Deleted Successfully");

	}

	public void readRecord_sweetsBaker() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("Select * from Sweets_Baker ");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getFloat(3));
			System.out.println();
		}
	}

	public void orderData_sweetsBaker() throws SQLException {
		float price = 0, sum = 0;
		char de;
		String name_variety = null;
		String s1;

		Scanner ord = new Scanner(System.in);
		do {
			System.out.println(CYAN_UNDERLINED + "Enter your name" + RESET);
			String name = ord.next();
			System.out.println(CYAN_UNDERLINED + "Enter table no" + RESET);
			int table = ord.nextInt();

			do {
				System.out.println(CYAN_UNDERLINED + "Enter the Name_of_variety" + RESET);
				String Name_of_variety = ord.next();
				String s = "select * from Sweets_Baker price  where Name_of_variety = ?";
				pstmt = con.prepareStatement(s);
				pstmt.setString(1, Name_of_variety);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println("Name" + "\t" + "Name_of_Variety" + "\t" + "Price");
					System.out.print(rs.getInt(1) + "\t");
					System.out.print(rs.getString(2) + "\t");
					System.out.print(rs.getFloat(3));
					System.out.println();
					System.out.println(
							".........................................................................................");

					price = rs.getFloat(3);
					sum += price;

					name_variety = rs.getString(2);

				}

				System.out.println("do you want continue to order(y/n)");
				de = ord.next().charAt(0);
			} while (de == 'y' || de == 'Y');

			pstmt = con.prepareStatement("insert into users values(?,?,?,?,?)");
			pstmt.setString(1, name);
			pstmt.setString(2, name_variety);
			pstmt.setInt(3, table);
			pstmt.setFloat(4, price);
			pstmt.setFloat(5, sum);
			pstmt.executeUpdate();

			s1 = "select * from users where name = ?";
			pstmt = con.prepareStatement(s1);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("Name" + "\t" + "Name_of_Variety" + "\t" + "Price" + "\t" + "Total");
				System.out.print(rs.getString(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.print(rs.getFloat(4) + "\t");
				System.out.println(rs.getFloat(5));
				System.out.println();
				System.out.println(
						".........................................................................................................");
			}

			System.out.println(CYAN_BACKGROUND_BRIGHT + "Total Amount : " + sum + RESET);

			System.out.println("do you want continue with SWEETS_BAKER(y/n)");
			de = ord.next().charAt(0);
		} while (de == 'y' || de == 'Y');
	}

	public void selectData_sweetsBaker() throws SQLException {
		Scanner sel = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Name_of_variety" + RESET);
		String Name_of_variety = sel.next();
		String s = "select * from Sweets_Baker  where Name_of_Variety = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, Name_of_variety);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getInt(3));
			System.out.println();
		}

	}

	public void updateData_sweetsBaker() throws SQLException {
		Scanner up = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter id where we want update" + RESET);
		int id = up.nextInt();
		System.out.println(CYAN_UNDERLINED + "Enter the Name_of_variety" + RESET);
		String Name_of_variety = up.next();
		System.out.println(CYAN_UNDERLINED + "Enter the price" + RESET);
		float price = up.nextFloat();

		String s = "update Sweets_Baker  Set Name_of_variety = ? , price = ? where id = ?";
		pstmt = con.prepareStatement(s);

		pstmt.setString(1, Name_of_variety);
		pstmt.setFloat(2, price);
		pstmt.setInt(3, id);

		int i = pstmt.executeUpdate();
		System.out.println(i + "Record Updated");
	}

	public void insertData_max() throws SQLException {

		Scanner in = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Dress_Code" + RESET);
		int Dress_code = in.nextInt();
		System.out.println(CYAN_UNDERLINED + "Enter the Dress_name" + RESET);
		String dressname = in.next();
		System.out.println(CYAN_UNDERLINED + "Enter the Price" + RESET);
		float price = in.nextFloat();
		System.out.println(CYAN_UNDERLINED + "Enter the discount" + RESET);
		float discount = in.nextFloat();

		float sum = (discount / 100);
		sum = +price * sum;
		float total = price - sum;

		pstmt = con.prepareStatement("insert into MAX values(?,?,?,?,?)");
		pstmt.setInt(1, Dress_code);
		pstmt.setString(2, dressname);
		pstmt.setFloat(3, price);
		pstmt.setFloat(4, discount);
		pstmt.setFloat(5, total);

		int n = pstmt.executeUpdate();
		System.out.println(n + " Record is Inserted");
	}

	public void updateData_max() throws SQLException {
		Scanner up = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter Dress_code where we want update" + RESET);
		int code_no = up.nextInt();
		System.out.println(CYAN_UNDERLINED + "Enter the Name_of_dress" + RESET);
		String Name_of_dresses = up.next();
		System.out.println(CYAN_UNDERLINED + "Enter the Price" + RESET);
		float price = up.nextFloat();
		System.out.println(CYAN_UNDERLINED + "Enter the Discount Amount" + RESET);
		float discount = up.nextFloat();
		float sum = (discount / 100);
		sum = +price * sum;
		float Total = price - sum;

		String s = "update MAX  Set Name_of_dresses = ? , price = ?,discount =?,Total=? where code_no = ?";
		pstmt = con.prepareStatement(s);

		pstmt.setString(1, Name_of_dresses);
		pstmt.setFloat(2, price);
		pstmt.setFloat(3, discount);
		pstmt.setFloat(4, Total);
		pstmt.setFloat(5, code_no);

		int i = pstmt.executeUpdate();
		System.out.println(i + "Record Updated");
	}

	public void readRecord_max() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("Select * from MAX ");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getFloat(3) + "\t");
			System.out.print(rs.getFloat(4) + "\t");
			System.out.print(rs.getFloat(5));
			System.out.println();
		}
	}

	public void deleteRecord_max() throws SQLException {
		Scanner del = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter dress_code where we want delete" + RESET);
		int code_no = del.nextInt();
		String s = "Delete from MAX where code_no = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, code_no);

		int r = pstmt.executeUpdate();
		System.out.println(r + "Deleted Successfully");

	}

	public void orderData_max() throws SQLException {
		float price = 0, sum = 0;
		char de;
		String name_dresses = null;
		String s1;
		float pay = 0;
		int code_no;

		Scanner ord = new Scanner(System.in);
		do {

			System.out.println(CYAN_UNDERLINED + "Enter Your Coined_no" + RESET);
			int coined_no = ord.nextInt();

			do {
				System.out.println(CYAN_UNDERLINED + "Enter the dress_code" + RESET);
				code_no = ord.nextInt();
				String s = "select * from max  where code_no = ?";
				pstmt = con.prepareStatement(s);
				pstmt.setInt(1, code_no);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println(
							"...............................................................................................................................");
					System.out.println(
							"Code_no" + "\t" + "Name_Dress" + "\t" + "Price" + "\t" + "Discount" + "\t" + "Total");
					System.out.print(rs.getInt(1) + "\t");
					System.out.print(rs.getString(2) + "\t");
					System.out.print(rs.getFloat(3) + "\t");
					System.out.print(rs.getFloat(4) + "\t");
					System.out.print(rs.getFloat(5));
					System.out.println();

					price = rs.getFloat(5);
					sum += price;

					name_dresses = rs.getString(2);

				}

				System.out.println("do you want continue to order(y/n)");
				de = ord.next().charAt(0);
			} while (de == 'y' || de == 'Y');
			System.out.println(CYAN_BACKGROUND_BRIGHT + "Total amount : " + sum + RESET);

			System.out.println(PURPLE_BOLD_BRIGHT + "*********Payment*********" + RESET);

			System.out.println("Enter payment amount");
			pay = ord.nextFloat();
			float balance = 0;

			if (pay > sum) {
				balance = sum - pay;
				System.out.println("Thank you shopping\n balance" + balance);
			}
			do {
				int bal = 0;
				if (pay < sum) {
					balance = sum - pay;
					System.out.println("oh your payment is lesser than total \n you have to pay : " + balance);
					System.out.println("Enter your Balance amount : ");
					bal = ord.nextInt();
					if (bal == balance) {
						System.out.println("Thank You Shopping \n Over The Max");
					}
				}
				pay = pay + bal;
			} while (pay != sum);

			if (pay == sum) {
				System.out.println("Thank you shopping\n HAVE A NICE DAY");
			}

			pstmt = con.prepareStatement("insert into custtable_max values(?,?,?,?,?,?)");
			pstmt.setInt(1, coined_no);
			pstmt.setInt(2, code_no);
			pstmt.setString(3, name_dresses);
			pstmt.setFloat(4, price);
			pstmt.setFloat(5, sum);
			pstmt.setFloat(6, pay);
			pstmt.executeUpdate();

			s1 = "select * from custtable_max where coined_no = ?";
			pstmt = con.prepareStatement(s1);
			pstmt.setInt(1, coined_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						".............................................................................................................................");
				System.out.println("Coined_no" + "\t" + "code_no" + "\t" + "Price" + "\t" + "Total" + "\t" + "Payment");
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getInt(2) + "\t");
				System.out.print(rs.getFloat(4) + "\t");
				System.out.print(rs.getFloat(5) + "\t");
				System.out.print(rs.getFloat(6));

				System.out.println();
			}

			System.out.println(CYAN_BACKGROUND_BRIGHT + "Total paid  amount : " + sum + RESET);

			System.out.println("do you want continue with MAX(y/n)");
			de = ord.next().charAt(0);
		} while (de == 'y' || de == 'Y');

	}

	public void selectData_max() throws SQLException {
		Scanner sel = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "enter the Dress_code" + RESET);
		int code_no = sel.nextInt();
		String s = "select * from Max  where code_no = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, code_no);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getFloat(3) + "\t");
			System.out.print(rs.getFloat(4) + "\t");
			System.out.print(rs.getFloat(5));
			System.out.println();
		}

	}

	public void custtable_max() throws SQLException {

		String s1;
		char re;
		st = con.createStatement();
		rs = st.executeQuery("Select * from custtable_max ");

		System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Name_od_Dress" + "\t" + "Price" + "\t" + "Total"
				+ "\t" + "Payment");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t   ");
			System.out.print(rs.getInt(2) + "\t   ");
			System.out.print(rs.getString(3) + "\t   ");
			System.out.print(rs.getFloat(4) + "\t   ");
			System.out.print(rs.getFloat(5) + "\t   ");
			System.out.print(rs.getFloat(6));
			System.out.println();

		}
		System.out.println(
				".......................................................................................................................................................................");

		do {

			Scanner use = new Scanner(System.in);
			System.out.println(CYAN_UNDERLINED + "Enter a coined_no" + RESET);
			int coined_no = use.nextInt();
			s1 = "select * from custtable_max where coined_no = ?";
			pstmt = con.prepareStatement(s1);
			pstmt.setInt(1, coined_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						"..............................................................................................................................");
				System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Price" + "\t" + "Total" + "\t" + "Payment");
				System.out.print(rs.getInt(1) + "\t   ");
				System.out.print(rs.getInt(2) + "\t   ");
				System.out.print(rs.getFloat(4) + "\t   ");
				System.out.print(rs.getFloat(5) + "\t   ");
				System.out.print(rs.getFloat(6));
				System.out.println();

			}

			System.out.println("do you want continue to order(y/n)");
			re = use.next().charAt(0);
		} while (re == 'y' || re == 'Y');

	}

	public void insertData_Trends() throws SQLException {

		Scanner in = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Dress_Code" + RESET);
		int Dress_code = in.nextInt();
		System.out.println(CYAN_UNDERLINED + "Enter the Dress_name" + RESET);
		String dressname = in.next();
		System.out.println(CYAN_UNDERLINED + "Enter the Price" + RESET);
		float price = in.nextFloat();
		System.out.println(CYAN_UNDERLINED + "Enter the Discount" + RESET);
		float discount = in.nextFloat();

		float sum = (discount / 100);
		sum = +price * sum;
		float total = price - sum;

		pstmt = con.prepareStatement("insert into Trends values(?,?,?,?,?)");
		pstmt.setInt(1, Dress_code);
		pstmt.setString(2, dressname);
		pstmt.setFloat(3, price);
		pstmt.setFloat(4, discount);
		pstmt.setFloat(5, total);

		int n = pstmt.executeUpdate();
		System.out.println(n + " Eecord is Inserted");
	}

	public void updateData_Trends() throws SQLException {
		Scanner up = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter Dress_code where we want update" + RESET);
		int code_no = up.nextInt();
		System.out.println(CYAN_UNDERLINED + "Enter the Name_of_dress" + RESET);
		String Name_of_dresses = up.next();
		System.out.println(CYAN_UNDERLINED + "Enter the Price" + RESET);
		float price = up.nextFloat();
		System.out.println(CYAN_UNDERLINED + "Enter the Discount Amount" + RESET);
		float discount = up.nextFloat();
		float sum = (discount / 100);
		sum = +price * sum;
		float Total = price - sum;

		String s = "update Trends  Set Name_of_dresses = ? , price = ?,discount =?,Total=? where code_no = ?";
		pstmt = con.prepareStatement(s);

		pstmt.setString(1, Name_of_dresses);
		pstmt.setFloat(2, price);
		pstmt.setFloat(3, discount);
		pstmt.setFloat(4, Total);
		pstmt.setFloat(5, code_no);

		int i = pstmt.executeUpdate();
		System.out.println(i + "Record Updated");
	}

	public void readRecord_Trends() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("Select * from Trends ");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getFloat(3) + "\t");
			System.out.print(rs.getFloat(4) + "\t");
			System.out.print(rs.getFloat(5));
			System.out.println();
		}
	}

	public void deleteRecord_Trends() throws SQLException {
		Scanner del = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter dress_code where we want delete" + RESET);
		int code_no = del.nextInt();
		String s = "Delete from Trends where code_no = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, code_no);

		int r = pstmt.executeUpdate();
		System.out.println(r + "Deleted Successfully");

	}

	public void orderData_Trends() throws SQLException {
		float price = 0, sum = 0;
		char de;
		String name_dresses = null;
		String s1;
		float pay = 0;
		int code_no = 0;
		Scanner ord = new Scanner(System.in);
		do {
			System.out.println(CYAN_UNDERLINED + "Enter your Coined_no" + RESET);
			int coined_no = ord.nextInt();

			do {
				System.out.println(CYAN_UNDERLINED + "Enter the Dress_code" + RESET);
				code_no = ord.nextInt();
				String s = "select * from Trends  where code_no = ?";
				pstmt = con.prepareStatement(s);
				pstmt.setInt(1, code_no);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println(
							"..................................................................................................................................");
					System.out.println(
							"Code_no" + "\t" + "Name_of_dress" + "\t" + "Price" + "\t" + "Discount" + "t" + "Total");
					System.out.print(rs.getInt(1) + "\t");
					System.out.print(rs.getString(2) + "\t");
					System.out.print(rs.getFloat(3) + "\t");
					System.out.print(rs.getFloat(4) + "\t");
					System.out.print(rs.getFloat(5));
					System.out.println();

					price = rs.getFloat(5);
					sum += price;

					name_dresses = rs.getString(2);

				}
				System.out.println("do you want continue to order(y/n)");
				de = ord.next().charAt(0);
			} while (de == 'y' || de == 'Y');

			System.out.println(CYAN_BACKGROUND_BRIGHT + "Total amount : " + sum + RESET);

			System.out.println(PURPLE_BOLD_BRIGHT + "*********Payment*********" + RESET);

			System.out.println("Enter payment amount");
			pay = ord.nextFloat();
			float balance = 0;

			if (pay > sum) {
				balance = sum - pay;
				System.out.println("Thank you shopping\n balance" + balance);
			}
			do {
				int bal = 0;
				if (pay < sum) {
					balance = sum - pay;
					System.out.println("oh your payment is lesser than total \n you have pay : " + balance);
					System.out.println("Enter your Balance amount : ");
					bal = ord.nextInt();
					if (bal == balance) {
						System.out.println("Thank You Shopping \n Over The Trends");
					}

					pay = pay + bal;
				}
			} while (pay != sum);

			if (pay == sum) {
				System.out.println("Thank you shopping\n HAVE A NICE DAY");
			}

			pstmt = con.prepareStatement("insert into custtable_Trends values(?,?,?,?,?,?)");
			pstmt.setInt(1, coined_no);
			pstmt.setInt(2, code_no);
			pstmt.setString(3, name_dresses);
			pstmt.setFloat(4, price);
			pstmt.setFloat(5, sum);
			pstmt.setFloat(6, pay);
			pstmt.executeUpdate();

			s1 = "select * from custtable_Trends where coined_no = ?";
			pstmt = con.prepareStatement(s1);
			pstmt.setInt(1, coined_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						"..............................................................................................................................");
				System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Price" + "\t" + "Total" + "\t" + "Payment");
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getInt(2) + "\t");
				System.out.print(rs.getFloat(4) + "\t");
				System.out.print(rs.getFloat(5) + "\t");
				System.out.print(rs.getFloat(6));

				System.out.println();
			}
			System.out.println(CYAN_BACKGROUND_BRIGHT + "Total Paid Amount : " + sum + RESET);

			System.out.println("do you want continue to TRENDS(y/n)");
			de = ord.next().charAt(0);
		} while (de == 'y' || de == 'Y');

	}

	public void selectData_Trends() throws SQLException {
		Scanner sel = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Dress_code" + RESET);
		int code_no = sel.nextInt();
		String s = "select * from Trends  where code_no = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, code_no);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getFloat(3) + "\t");
			System.out.print(rs.getFloat(4) + "\t");
			System.out.print(rs.getFloat(5));
			System.out.println();
		}

	}

	public void custtable_Trends() throws SQLException {

		String s1;
		char re;
		st = con.createStatement();
		rs = st.executeQuery("Select * from custtable_Trends ");

		System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Name_of_dress" + "\t" + "Price" + "\t" + "Total"
				+ "\t" + "Payment");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getInt(2) + "\t");
			System.out.print(rs.getString(3) + "\t");
			System.out.print(rs.getFloat(4) + "\t");
			System.out.print(rs.getFloat(5) + "\t");
			System.out.print(rs.getFloat(6));
			System.out.println();
		}
		System.out.println(
				"....................................................................................................................................................................");

		do {

			Scanner use = new Scanner(System.in);
			System.out.println(CYAN_UNDERLINED + "Enter a coined_no" + RESET);
			int coined_no = use.nextInt();
			s1 = "select * from custtable_Trends where coined_no = ?";
			pstmt = con.prepareStatement(s1);
			pstmt.setInt(1, coined_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						"...............................................................................................................................................");
				System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Price" + "\t" + "Total" + "\t" + "Payment");
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getInt(2) + "\t");
				System.out.print(rs.getFloat(4) + "\t");
				System.out.print(rs.getFloat(5) + "\t");
				System.out.print(rs.getFloat(6));
				System.out.println();
			}

			System.out.println("do you want continue to order(y/n)");
			re = use.next().charAt(0);
		} while (re == 'y' || re == 'Y');

	}

	public void insertData_Lifestyle() throws SQLException {

		Scanner in = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Dress_Code" + RESET);
		int Dress_code = in.nextInt();
		System.out.println(CYAN_UNDERLINED + "Enter the Dress_name" + RESET);
		String dressname = in.next();
		System.out.println(CYAN_UNDERLINED + "Enter the Price" + RESET);
		float price = in.nextFloat();
		System.out.println(CYAN_UNDERLINED + "Enter the Discount" + RESET);
		float discount = in.nextFloat();

		float sum = (discount / 100);
		sum = +price * sum;
		float total = price - sum;

		pstmt = con.prepareStatement("insert into Lifestyle values(?,?,?,?,?)");
		pstmt.setInt(1, Dress_code);
		pstmt.setString(2, dressname);
		pstmt.setFloat(3, price);
		pstmt.setFloat(4, discount);
		pstmt.setFloat(5, total);

		int n = pstmt.executeUpdate();
		System.out.println(n + " Record is Inserted");
	}

	public void updateData_Lifestyle() throws SQLException {
		Scanner up = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter Dress_code where we want update" + RESET);
		int code_no = up.nextInt();
		System.out.println(CYAN_UNDERLINED + "Enter the Name_of_dress" + RESET);
		String Name_of_dresses = up.next();
		System.out.println(CYAN_UNDERLINED + "Enter the Price" + RESET);
		float price = up.nextFloat();
		System.out.println(CYAN_UNDERLINED + "Enter the Discount Amount" + RESET);
		float discount = up.nextFloat();
		float sum = (discount / 100);
		sum = +price * sum;
		float Total = price - sum;

		String s = "update Lifestyle   Set Name_of_dresses = ? , price = ?,discount =?,Total=? where code_no = ?";
		pstmt = con.prepareStatement(s);

		pstmt.setString(1, Name_of_dresses);
		pstmt.setFloat(2, price);
		pstmt.setFloat(3, discount);
		pstmt.setFloat(4, Total);
		pstmt.setFloat(5, code_no);

		int i = pstmt.executeUpdate();
		System.out.println(i + "Record Updated");
	}

	public void readRecord_Lifestyle() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("Select * from Lifestyle ");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getFloat(3) + "\t");
			System.out.print(rs.getFloat(4) + "\t");
			System.out.print(rs.getFloat(5));
			System.out.println();
		}
	}

	public void deleteRecord_LifeStyle() throws SQLException {
		Scanner del = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter dress_code where we want delete" + RESET);
		int code_no = del.nextInt();
		String s = "Delete from Lifestyle where code_no = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, code_no);

		int r = pstmt.executeUpdate();
		System.out.println(r + "Deleted Successfully");

	}

	public void orderData_Lifestyle() throws SQLException {
		float price = 0, sum = 0;
		char de;
		String name_dresses = null;
		String s1;
		float pay;
		int code_no = 0;
		Scanner ord = new Scanner(System.in);
		do {
			System.out.println(CYAN_UNDERLINED + "Enter your Coined_no" + RESET);
			int coined_no = ord.nextInt();

			do {
				System.out.println(CYAN_UNDERLINED + "Enter the Dress_code" + RESET);
				code_no = ord.nextInt();
				String s = "select * from Lifestyle  where code_no = ?";
				pstmt = con.prepareStatement(s);
				pstmt.setInt(1, code_no);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println(
							"..................................................................................................................................");
					System.out.println(
							"Code_no" + "\t" + "Name_of_dress" + "\t" + "Price" + "\t" + "Discount" + "t" + "Total");
					System.out.print(rs.getInt(1) + "\t");
					System.out.print(rs.getString(2) + "\t");
					System.out.print(rs.getFloat(3) + "\t");
					System.out.print(rs.getFloat(4) + "\t");
					System.out.print(rs.getFloat(5));
					System.out.println();

					price = rs.getFloat(5);
					sum += price;

					name_dresses = rs.getString(2);
				}
				System.out.println("do you want continue to order(y/n)");
				de = ord.next().charAt(0);
			} while (de == 'y' || de == 'Y');

			System.out.println(CYAN_BACKGROUND_BRIGHT + "Total amount : " + sum + RESET);

			System.out.println(PURPLE_BOLD_BRIGHT + "*********Payment*********" + RESET);

			System.out.println("Enter payment amount");
			pay = ord.nextInt();
			float balance = 0;

			if (pay > sum) {
				balance = sum - pay;
				System.out.println("Thank you shopping\n balance" + balance);
			}
			do {
				int bal = 0;
				if (pay < sum) {
					balance = sum - pay;
					System.out.println("oh your payment is lesser than total \n you have pay : " + balance);
					System.out.println("Enter your Balance amount : ");
					bal = ord.nextInt();
					if (bal == balance) {
						System.out.println("Thank You Shopping \n Over The LifeStyle");
					}

					pay = pay + bal;
				}
			} while (pay != sum);

			if (pay == sum) {
				System.out.println("Thank you shopping\n HAVE A NICE DAY");
			}

			pstmt = con.prepareStatement("insert into custtable_Lifestyle values(?,?,?,?,?,?)");
			pstmt.setInt(1, coined_no);
			pstmt.setInt(2, code_no);
			pstmt.setString(3, name_dresses);
			pstmt.setFloat(4, price);
			pstmt.setFloat(5, sum);
			pstmt.setFloat(6, pay);
			pstmt.executeUpdate();

			s1 = "select * from custtable_Lifestyle where coined_no = ?";
			pstmt = con.prepareStatement(s1);
			pstmt.setInt(1, coined_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						"..............................................................................................................................");
				System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Price" + "\t" + "Total" + "\t" + "Payment");
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getInt(2) + "\t");
				System.out.print(rs.getFloat(4) + "\t");
				System.out.print(rs.getFloat(5) + "\t");
				System.out.print(rs.getFloat(6));
				System.out.println();

			}
			System.out.println(CYAN_BACKGROUND_BRIGHT + "Total Paid Amount : " + sum + RESET);

			System.out.println("do you want continue to LIFESTYLE(y/n)");
			de = ord.next().charAt(0);
		} while (de == 'y' || de == 'Y');

	}

	public void selectData_Lifestyle() throws SQLException {
		Scanner sel = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Dress_code" + RESET);
		int code_no = sel.nextInt();
		String s = "select * from Lifestyle  where code_no = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, code_no);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getFloat(3) + "\t");
			System.out.print(rs.getFloat(4) + "\t");
			System.out.print(rs.getFloat(5));
			System.out.println();
		}

	}

	public void custtable_LifeStyle() throws SQLException {

		String s1;
		char re;
		st = con.createStatement();
		rs = st.executeQuery("Select * from custtable_Lifestyle ");

		System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Name_of_dress" + "\t" + "Price" + "\t" + "Total"
				+ "\t" + "Payment");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getInt(2) + "\t");
			System.out.print(rs.getString(3) + "\t");
			System.out.print(rs.getFloat(4) + "\t");
			System.out.print(rs.getFloat(5) + "\t");
			System.out.println();
		}
		System.out.println(
				"....................................................................................................................................................................");

		do {

			Scanner use = new Scanner(System.in);
			System.out.println(CYAN_UNDERLINED + "Enter a coined_no" + RESET);
			int coined_no = use.nextInt();
			s1 = "select * from custtable_Lifestyle where coined_no = ?";
			pstmt = con.prepareStatement(s1);
			pstmt.setInt(1, coined_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						"...............................................................................................................................................");
				System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Price" + "\t" + "Total" + "\t" + "Payment");
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getInt(2) + "\t");
				System.out.print(rs.getFloat(4) + "\t");
				System.out.println(rs.getFloat(5));
				System.out.println();
			}

			System.out.println("do you want continue to order(y/n)");
			re = use.next().charAt(0);
		} while (re == 'y' || re == 'Y');

	}

	public void custtable_Lifestyle() throws SQLException {

		String s1;
		char re;
		st = con.createStatement();
		rs = st.executeQuery("Select * from custtable_lifestyle ");
		while (rs.next()) {
			System.out.println(
					"....................................................................................................................................................................");
			System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Name_of_dress" + "\t" + "Price" + "\t" + "Total"
					+ "\t" + "Payment");
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getInt(2) + "\t");
			System.out.print(rs.getString(3) + "\t");
			System.out.print(rs.getFloat(4) + "\t");
			System.out.print(rs.getFloat(5) + "\t");
			System.out.print(rs.getFloat(6));
			System.out.println();
		}
		System.out.println(
				"....................................................................................................................................................................");

		do {

			Scanner use = new Scanner(System.in);
			System.out.println(CYAN_UNDERLINED + "Enter a coined_no" + RESET);
			int coined_no = use.nextInt();
			s1 = "select * from custtable_lifestyle where coined_no = ?";
			pstmt = con.prepareStatement(s1);
			pstmt.setInt(1, coined_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						"...............................................................................................................................................");
				System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Price" + "\t" + "Total" + "\t" + "Payment");
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getInt(2) + "\t");
				System.out.print(rs.getFloat(4) + "\t");
				System.out.print(rs.getFloat(5) + "\t");
				System.out.print(rs.getFloat(6));
				System.out.println();
			}

			System.out.println("do you want continue to order(y/n)");
			re = use.next().charAt(0);
		} while (re == 'y' || re == 'Y');

	}

	public void insertData_Metro() throws SQLException {

		Scanner in = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Footwear_Code" + RESET);
		int code_no = in.nextInt();
		System.out.println(CYAN_UNDERLINED + "Enter the Footwear_name" + RESET);
		String footwear = in.next();
		System.out.println(CYAN_UNDERLINED + "Enter the Price" + RESET);
		float price = in.nextFloat();
		System.out.println(CYAN_UNDERLINED + "Enter the discount" + RESET);
		float discount = in.nextFloat();

		float sum = (discount / 100);
		sum = +price * sum;
		float total = price - sum;

		pstmt = con.prepareStatement("insert into Metro values(?,?,?,?,?)");
		pstmt.setInt(1, code_no);
		pstmt.setString(2, footwear);
		pstmt.setFloat(3, price);
		pstmt.setFloat(4, discount);
		pstmt.setFloat(5, total);

		int n = pstmt.executeUpdate();
		System.out.println(n + " Record is Inserted");
	}

	public void updateData_Metro() throws SQLException {
		Scanner up = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter Code_no where we want update" + RESET);
		int code_no = up.nextInt();
		System.out.println(CYAN_UNDERLINED + "Enter the Name_of_Footwear" + RESET);
		String Name_of_footwear = up.next();
		System.out.println(CYAN_UNDERLINED + "Enter the Price" + RESET);
		float price = up.nextFloat();
		System.out.println(CYAN_UNDERLINED + "enter the discount amount" + RESET);
		float discount = up.nextFloat();
		float sum = (discount / 100);
		sum = +price * sum;
		float Total = price - sum;

		String s = "update Trends  Set Name_of_footwear = ? , price = ?,discount =?,Total=? where code_no = ?";
		pstmt = con.prepareStatement(s);

		pstmt.setString(1, Name_of_footwear);
		pstmt.setFloat(2, price);
		pstmt.setFloat(3, discount);
		pstmt.setFloat(4, Total);
		pstmt.setFloat(5, code_no);

		int i = pstmt.executeUpdate();
		System.out.println(i + "Record Updated");
	}

	public void readRecord_Metro() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("Select * from metro ");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getFloat(3) + "\t");
			System.out.print(rs.getFloat(4) + "\t");
			System.out.print(rs.getFloat(5));
			System.out.println();
		}
	}

	public void deleteRecord_Metro() throws SQLException {
		Scanner del = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter dress_code where we want delete" + RESET);
		int code_no = del.nextInt();
		String s = "Delete from Metro where code_no = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, code_no);

		int r = pstmt.executeUpdate();
		System.out.println(r + "Deleted Successfully");

	}

	public void orderData_Metro() throws SQLException {
		float price = 0, sum = 0;
		char de;
		String name_footwear = null;
		String s1;
		float pay;
		int code_no = 0;
		Scanner ord = new Scanner(System.in);
		do {
			System.out.println(CYAN_UNDERLINED + "Enter your Coined_no" + RESET);
			int coined_no = ord.nextInt();

			do {
				System.out.println(CYAN_UNDERLINED + "Enter the Code_no" + RESET);
				code_no = ord.nextInt();
				String s = "select * from metro  where code_no = ?";
				pstmt = con.prepareStatement(s);
				pstmt.setInt(1, code_no);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println(
							"..................................................................................................................................");
					System.out.println(
							"Code_no" + "\t" + "Name_of_dress" + "\t" + "Price" + "\t" + "Discount" + "t" + "Total");
					System.out.print(rs.getInt(1) + "\t");
					System.out.print(rs.getString(2) + "\t");
					System.out.print(rs.getFloat(3) + "\t");
					System.out.print(rs.getFloat(4) + "\t");
					System.out.print(rs.getFloat(5));
					System.out.println();

					price = rs.getFloat(5);
					sum += price;

					name_footwear = rs.getString(2);
				}
				System.out.println("do you want continue to order(y/n)");
				de = ord.next().charAt(0);
			} while (de == 'y' || de == 'Y');
			System.out.println(CYAN_BACKGROUND_BRIGHT + "Total amount : " + sum + RESET);

			System.out.println(PURPLE_BOLD_BRIGHT + "*********Payment*********" + RESET);

			System.out.println("Enter payment amount");
			pay = ord.nextFloat();
			float balance = 0;

			if (pay > sum) {
				balance = sum - pay;
				System.out.println("Thank you shopping\n balance" + balance);
			}
			do {
				int bal = 0;

				if (pay < sum) {
					balance = sum - pay;
					System.out.println("oh your payment is lesser than total \n you have pay : " + balance);
					System.out.println("Enter your Balance amount : ");
					bal = ord.nextInt();
					if (bal == balance) {
						System.out.println("Thank You Shopping \n Over The Metro");
					}

					pay = pay + bal;
				}
			} while (pay != sum);

			if (pay == sum) {
				System.out.println("Thank you shopping\n HAVE A NICE DAY");
			}

			pstmt = con.prepareStatement("insert into custtable_Metro values(?,?,?,?,?,?)");
			pstmt.setInt(1, coined_no);
			pstmt.setInt(2, code_no);
			pstmt.setString(3, name_footwear);
			pstmt.setFloat(4, price);
			pstmt.setFloat(5, sum);
			pstmt.setFloat(6, pay);
			pstmt.executeUpdate();

			s1 = "select * from custtable_Metro where coined_no = ?";
			pstmt = con.prepareStatement(s1);
			pstmt.setInt(1, coined_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						"..............................................................................................................................");
				System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Price" + "\t" + "Total" + "\t" + "Payment");
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getInt(2) + "\t");
				System.out.print(rs.getFloat(4) + "\t");
				System.out.print(rs.getFloat(5) + "\t");
				System.out.print(rs.getFloat(6));

				System.out.println();
			}
			System.out.println(CYAN_BACKGROUND_BRIGHT + "Total Paid Amount : " + sum + RESET);

			System.out.println("do you want continue to METRO(y/n)");
			de = ord.next().charAt(0);
		} while (de == 'y' || de == 'Y');

	}

	public void selectData_Metro() throws SQLException {
		Scanner sel = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Footwear_code" + RESET);
		int code_no = sel.nextInt();
		String s = "select * from Metro  where code_no = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, code_no);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getFloat(3) + "\t");
			System.out.print(rs.getFloat(4) + "\t");
			System.out.print(rs.getFloat(5));
			System.out.println();
		}

	}

	public void custtable_Metro() throws SQLException {

		String s1;
		char re;
		st = con.createStatement();
		rs = st.executeQuery("Select * from custtable_Metro ");

		System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Name_of_dress" + "\t" + "Price" + "\t" + "Total"
				+ "\t" + "Payment");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getInt(2) + "\t");
			System.out.print(rs.getString(3) + "\t");
			System.out.print(rs.getFloat(4) + "\t");
			System.out.print(rs.getFloat(5) + "\t");
			System.out.print(rs.getFloat(6));
			System.out.println();
		}
		System.out.println(
				"....................................................................................................................................................................");

		do {

			Scanner use = new Scanner(System.in);
			System.out.println(CYAN_UNDERLINED + "Enter a Coined_no" + RESET);
			int coined_no = use.nextInt();
			s1 = "select * from custtable_Metro where coined_no = ?";
			pstmt = con.prepareStatement(s1);
			pstmt.setInt(1, coined_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						"...............................................................................................................................................");
				System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Price" + "\t" + "Total" + "\t" + "Payment");
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getInt(2) + "\t");
				System.out.print(rs.getFloat(4) + "\t");
				System.out.print(rs.getFloat(5) + "\t");
				System.out.print(rs.getFloat(6));
				System.out.println();
			}

			System.out.println("do you want continue to order(y/n)");
			re = use.next().charAt(0);
		} while (re == 'y' || re == 'Y');
	}

	public void insertData_Bata() throws SQLException {

		Scanner in = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Footwear_Code" + RESET);
		int code_no = in.nextInt();
		System.out.println(CYAN_UNDERLINED + "Enter the Footwear_name" + RESET);
		String footwear = in.next();
		System.out.println(CYAN_UNDERLINED + "Enter the price" + RESET);
		float price = in.nextFloat();
		System.out.println(CYAN_UNDERLINED + "Enter the discount" + RESET);
		float discount = in.nextFloat();

		float sum = (discount / 100);
		sum = +price * sum;
		float total = price - sum;

		pstmt = con.prepareStatement("insert into bata values(?,?,?,?,?)");
		pstmt.setInt(1, code_no);
		pstmt.setString(2, footwear);
		pstmt.setFloat(3, price);
		pstmt.setFloat(4, discount);
		pstmt.setFloat(5, total);

		int n = pstmt.executeUpdate();
		System.out.println(n + " Record is Inserted");
	}

	public void updateData_Bata() throws SQLException {
		Scanner up = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter code_no where we want update" + RESET);
		int code_no = up.nextInt();
		System.out.println(CYAN_UNDERLINED + "Enter the Name_of_Footwear" + RESET);
		String Name_of_footwear = up.next();
		System.out.println(CYAN_UNDERLINED + "Enter the Price" + RESET);
		float price = up.nextFloat();
		System.out.println(CYAN_UNDERLINED + "Enter the discount amount" + RESET);
		float discount = up.nextFloat();
		float sum = (discount / 100);
		sum = +price * sum;
		float Total = price - sum;

		String s = "update bata  Set Name_of_footwear = ? , price = ?,discount =?,Total=? where code_no = ?";
		pstmt = con.prepareStatement(s);

		pstmt.setString(1, Name_of_footwear);
		pstmt.setFloat(2, price);
		pstmt.setFloat(3, discount);
		pstmt.setFloat(4, Total);
		pstmt.setFloat(5, code_no);

		int i = pstmt.executeUpdate();
		System.out.println(i + "Record Updated");
	}

	public void readRecord_Bata() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("Select * from bata ");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getFloat(3) + "\t");
			System.out.print(rs.getFloat(4) + "\t");
			System.out.print(rs.getFloat(5));
			System.out.println();
		}
	}

	public void deleteRecord_Bata() throws SQLException {
		Scanner del = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter code_no where we want delete" + RESET);
		int code_no = del.nextInt();
		String s = "Delete from Bata where code_no = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, code_no);

		int r = pstmt.executeUpdate();
		System.out.println(r + "Deleted Successfully");

	}

	public void orderData_Bata() throws SQLException {
		float price = 0, sum = 0;
		char de;
		String name_footwear = null;
		String s1;
		float pay;
		int code_no = 0;
		Scanner ord = new Scanner(System.in);
		do {
			System.out.println(CYAN_UNDERLINED + "Enter your Coined_no" + RESET);
			int coined_no = ord.nextInt();

			do {
				System.out.println(CYAN_UNDERLINED + "Enter the Code_no" + RESET);
				code_no = ord.nextInt();
				String s = "select * from bata  where code_no = ?";
				pstmt = con.prepareStatement(s);
				pstmt.setInt(1, code_no);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println(
							"..................................................................................................................................");
					System.out.println(
							"Code_no" + "\t" + "Name_of_dress" + "\t" + "Price" + "\t" + "Discount" + "t" + "Total");
					System.out.print(rs.getInt(1) + "\t");
					System.out.print(rs.getString(2) + "\t");
					System.out.print(rs.getFloat(3) + "\t");
					System.out.print(rs.getFloat(4) + "\t");
					System.out.print(rs.getFloat(5));
					System.out.println();

					price = rs.getFloat(5);
					sum += price;

					name_footwear = rs.getString(2);
				}
				System.out.println("do you want continue to order(y/n)");
				de = ord.next().charAt(0);
			} while (de == 'y' || de == 'Y');

			System.out.println(CYAN_BACKGROUND_BRIGHT + "Total amount : " + sum + RESET);

			System.out.println(PURPLE_BOLD_BRIGHT + "*********Payment*********" + RESET);

			System.out.println("Enter payment amount");
			pay = ord.nextFloat();
			float balance = 0;

			if (pay > sum) {
				balance = sum - pay;
				System.out.println("Thank you shopping\n balance" + balance);
			}
			do {
				int bal = 0;
				if (pay < sum) {
					balance = sum - pay;
					System.out.println("oh your payment is lesser than total \n you have pay : " + balance);
					System.out.println("Enter your Balance amount : ");
					bal = ord.nextInt();
					if (bal == balance) {
						System.out.println("Thank You Shopping \n Over The Bata");
					}

					pay = pay + bal;
				}
			} while (pay != sum);

			if (pay == sum) {
				System.out.println("Thank you shopping\n HAVE A NICE DAY");
			}

			pstmt = con.prepareStatement("insert into custtable_BATA values(?,?,?,?,?,?)");
			pstmt.setInt(1, coined_no);
			pstmt.setInt(2, code_no);
			pstmt.setString(3, name_footwear);
			pstmt.setFloat(4, price);
			pstmt.setFloat(5, sum);
			pstmt.setFloat(6, pay);
			pstmt.executeUpdate();

			s1 = "select * from custtable_BATA where coined_no = ?";
			pstmt = con.prepareStatement(s1);
			pstmt.setInt(1, coined_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						"..............................................................................................................................");
				System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Price" + "\t" + "Total" + "\t" + "Payment");
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getInt(2) + "\t");
				System.out.print(rs.getFloat(4) + "\t");
				System.out.print(rs.getFloat(5) + "\t");
				System.out.print(rs.getFloat(6));

				System.out.println();
			}
			System.out.println(CYAN_BACKGROUND_BRIGHT + "Total Paid Amount : " + sum + RESET);

			System.out.println("do you want continue to BATA(y/n)");
			de = ord.next().charAt(0);
		} while (de == 'y' || de == 'Y');

	}

	public void selectData_Bata() throws SQLException {
		Scanner sel = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Footwear_code" + RESET);
		int code_no = sel.nextInt();
		String s = "select * from bata  where code_no = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, code_no);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getFloat(3) + "\t");
			System.out.print(rs.getFloat(4) + "\t");
			System.out.print(rs.getFloat(5));
			System.out.println();
		}

	}

	public void custtable_Bata() throws SQLException {

		String s1;
		char re;
		st = con.createStatement();
		rs = st.executeQuery("Select * from custtable_BATA ");

		System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Name_of_dress" + "\t" + "Price" + "\t" + "Total"
				+ "\t" + "Payment");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getInt(2) + "\t");
			System.out.print(rs.getString(3) + "\t");
			System.out.print(rs.getFloat(4) + "\t");
			System.out.print(rs.getFloat(5) + "\t");
			System.out.print(rs.getFloat(6));
			System.out.println();
		}
		System.out.println(
				"....................................................................................................................................................................");

		do {

			Scanner use = new Scanner(System.in);
			System.out.println(CYAN_UNDERLINED + "Enter a Coined_no" + RESET);
			int coined_no = use.nextInt();
			s1 = "select * from custtable_BATA where coined_no = ?";
			pstmt = con.prepareStatement(s1);
			pstmt.setInt(1, coined_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						"...............................................................................................................................................");
				System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Price" + "\t" + "Total" + "\t" + "Payment");
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getInt(2) + "\t");
				System.out.print(rs.getFloat(4) + "\t");
				System.out.print(rs.getFloat(5) + "\t");
				System.out.print(rs.getFloat(6));
				System.out.println();
			}

			System.out.println("do you want continue to order(y/n)");
			re = use.next().charAt(0);
		} while (re == 'y' || re == 'Y');
	}

	public void insertData_Rocia() throws SQLException {

		Scanner in = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Footwear_Code" + RESET);
		int code_no = in.nextInt();
		System.out.println(CYAN_UNDERLINED + "Enter the Footwear_name" + RESET);
		String footwear = in.next();
		System.out.println(CYAN_UNDERLINED + "Enter the Price" + RESET);
		float price = in.nextFloat();
		System.out.println(CYAN_UNDERLINED + "Enter the Discount" + RESET);
		float discount = in.nextFloat();

		float sum = (discount / 100);
		sum = +price * sum;
		float total = price - sum;

		pstmt = con.prepareStatement("insert into rocia values(?,?,?,?,?)");
		pstmt.setInt(1, code_no);
		pstmt.setString(2, footwear);
		pstmt.setFloat(3, price);
		pstmt.setFloat(4, discount);
		pstmt.setFloat(5, total);

		int n = pstmt.executeUpdate();
		System.out.println(n + " Record is Inserted");
	}

	public void updateData_Rocia() throws SQLException {
		Scanner up = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter code_no where we want update" + RESET);
		int code_no = up.nextInt();
		System.out.println(CYAN_UNDERLINED + "Enter the Name_of_Footwear" + RESET);
		String Name_of_footwear = up.next();
		System.out.println(CYAN_UNDERLINED + "Enter the Price" + RESET);
		float price = up.nextFloat();
		System.out.println(CYAN_UNDERLINED + "Enter the Discount Amount" + RESET);
		float discount = up.nextFloat();
		float sum = (discount / 100);
		sum = +price * sum;
		float Total = price - sum;

		String s = "update rocia  Set Name_of_footwear = ? , price = ?,discount =?,Total=? where code_no = ?";
		pstmt = con.prepareStatement(s);

		pstmt.setString(1, Name_of_footwear);
		pstmt.setFloat(2, price);
		pstmt.setFloat(3, discount);
		pstmt.setFloat(4, Total);
		pstmt.setFloat(5, code_no);

		int i = pstmt.executeUpdate();
		System.out.println(i + "Record Updated");
	}

	public void readRecord_Rocia() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("Select * from rocia ");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getFloat(3) + "\t");
			System.out.print(rs.getFloat(4) + "\t");
			System.out.print(rs.getFloat(5));
			System.out.println();
		}
	}

	public void deleteRecord_Rocia() throws SQLException {
		Scanner del = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter code_no where we want delete" + RESET);
		int code_no = del.nextInt();
		String s = "Delete from rocia where code_no = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, code_no);

		int r = pstmt.executeUpdate();
		System.out.println(r + "Deleted Successfully");

	}

	public void orderData_Rocia() throws SQLException {
		float price = 0, sum = 0;
		char de;
		String name_footwear = null;
		String s1;
		float pay;
		int code_no = 0;
		Scanner ord = new Scanner(System.in);
		do {
			System.out.println(CYAN_UNDERLINED + "Enter your Coined_no" + RESET);
			int coined_no = ord.nextInt();

			do {
				System.out.println(CYAN_UNDERLINED + "Enter the Code_no" + RESET);
				code_no = ord.nextInt();
				String s = "select * from rocia  where code_no = ?";
				pstmt = con.prepareStatement(s);
				pstmt.setInt(1, code_no);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println(
							"..................................................................................................................................");
					System.out.println(
							"Code_no" + "\t" + "Name_of_dress" + "\t" + "Price" + "\t" + "Discount" + "t" + "Total");
					System.out.print(rs.getInt(1) + "\t");
					System.out.print(rs.getString(2) + "\t");
					System.out.print(rs.getFloat(3) + "\t");
					System.out.print(rs.getFloat(4) + "\t");
					System.out.print(rs.getFloat(5));
					System.out.println();

					price = rs.getFloat(5);
					sum += price;

					name_footwear = rs.getString(2);
				}
				System.out.println("do you want continue to order(y/n)");
				de = ord.next().charAt(0);
			} while (de == 'y' || de == 'Y');

			System.out.println(CYAN_BACKGROUND_BRIGHT + "Total amount : " + sum + RESET);

			System.out.println(PURPLE_BOLD_BRIGHT + "*********Payment*********" + RESET);

			System.out.println("Enter payment amount");
			pay = ord.nextFloat();
			float balance = 0;

			if (pay > sum) {
				balance = sum - pay;
				System.out.println("Thank you shopping\n balance" + balance);
			}

			do {
				int bal = 0;
				if (pay < sum) {
					balance = sum - pay;
					System.out.println("oh your payment is lesser than total \n you have pay : " + balance);
					System.out.println("Enter your Balance amount : ");
					bal = ord.nextInt();
					if (bal == balance) {
						System.out.println("Thank You Shopping \n Over The Rocia");
					}

					pay = pay + bal;
				}
			} while (pay != sum);

			if (pay == sum) {
				System.out.println("Thank you shopping\n HAVE A NICE DAY");
			}

			pstmt = con.prepareStatement("insert into custtable_rocia values(?,?,?,?,?,?)");
			pstmt.setInt(1, coined_no);
			pstmt.setInt(2, code_no);
			pstmt.setString(3, name_footwear);
			pstmt.setFloat(4, price);
			pstmt.setFloat(5, sum);
			pstmt.setFloat(6, pay);
			pstmt.executeUpdate();

			s1 = "select * from custtable_rocia where coined_no = ?";
			pstmt = con.prepareStatement(s1);
			pstmt.setInt(1, coined_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						"..............................................................................................................................");
				System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Price" + "\t" + "Total" + "\t" + "Payment");
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getInt(2) + "\t");
				System.out.print(rs.getFloat(4) + "\t");
				System.out.print(rs.getFloat(5) + "\t");
				System.out.print(rs.getFloat(6));

				System.out.println();
			}
			System.out.println(CYAN_BACKGROUND_BRIGHT + "Total Paid  Amount : " + sum + RESET);

			System.out.println("do you want continue to ROCIA(y/n)");
			de = ord.next().charAt(0);
		} while (de == 'y' || de == 'Y');

	}

	public void selectData_Rocia() throws SQLException {
		Scanner sel = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter the Footwear_code" + RESET);
		int code_no = sel.nextInt();
		String s = "select * from rocia  where code_no = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, code_no);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.print(rs.getFloat(3) + "\t");
			System.out.print(rs.getFloat(4) + "\t");
			System.out.print(rs.getFloat(5));
			System.out.println();
		}

	}

	public void custtable_Rocia() throws SQLException {

		String s1;
		char re;
		st = con.createStatement();
		rs = st.executeQuery("Select * from custtable_rocia ");

		System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Name_of_dress" + "\t" + "Price" + "\t" + "Total"
				+ "\t" + "Payment");
		while (rs.next()) {
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getInt(2) + "\t");
			System.out.print(rs.getString(3) + "\t");
			System.out.print(rs.getFloat(4) + "\t");
			System.out.print(rs.getFloat(5) + "\t");
			System.out.print(rs.getFloat(6));
			System.out.println();
		}
		System.out.println(
				"....................................................................................................................................................................");

		do {

			Scanner use = new Scanner(System.in);
			System.out.println(CYAN_UNDERLINED + "Enter a Coined_no" + RESET);
			int coined_no = use.nextInt();
			s1 = "select * from custtable_rocia where coined_no = ?";
			pstmt = con.prepareStatement(s1);
			pstmt.setInt(1, coined_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						"...............................................................................................................................................");
				System.out.println("Coined_no" + "\t" + "Code_no" + "\t" + "Price" + "\t" + "Total" + "\t" + "Payment");
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getInt(2) + "\t");
				System.out.print(rs.getFloat(4) + "\t");
				System.out.print(rs.getFloat(5) + "\t");
				System.out.print(rs.getFloat(6));
				System.out.println();
			}

			System.out.println("do you want continue to order(y/n)");
			re = use.next().charAt(0);
		} while (re == 'y' || re == 'Y');
	}

	public void insert_offer() throws SQLException {

		Scanner in = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter offer details" + RESET);
		String offer = in.next();

		pstmt = con.prepareStatement("insert into offer(offer_details) values(?)");
		pstmt.setString(1, offer);

		int n = pstmt.executeUpdate();
		System.out.println(n + " offer is Inserted");
	}

	public void delete_offer() throws SQLException {
		Scanner del = new Scanner(System.in);
		System.out.println(CYAN_UNDERLINED + "Enter id where we want delete" + RESET);
		int id = del.nextInt();
		String s = "Delete from offer where id = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, id);

		int r = pstmt.executeUpdate();
		System.out.println(r + "Deleted Successfully");

	}

	public void readRecord_offer() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("Select * from offer ");
		System.out.println(BLACK_BOLD
				+ "----------------------------------------------------------------------------------" + RESET);
		while (rs.next()) {
			System.out.println("ID" + "\t" + "offer");
			System.out.print(rs.getInt(1) + "\t");
			System.out.print(rs.getString(2) + "\t");
			System.out.println();
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {

		Mall_Management_System mm = new Mall_Management_System();
		String username = "suba";
		String password = "12345";

		String RESET = "\033[0m"; // Text Reset
		String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
		String CYAN_BOLD_BRIGHT = "\033[1;96m"; // CYAN
		String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
		String CYAN_BACKGROUND_BRIGHT = "\033[0;106m"; // CYAN
		String PURPLE = "\033[0;35m"; // PURPLE
		String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
		String BLACK_BOLD = "\033[1;30m"; // BLACK
		// TODO Auto-generated method stub
		int Find_shopes;
		char mall;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println(PURPLE_BOLD_BRIGHT + PURPLE_UNDERLINED + "          ***************WELCOME MALL"
					+ GREEN_BOLD_BRIGHT + " MANAGEMENT SYSTEM*******************          " + RESET);
			System.out.println(
					" (I love chocolate, and I love to shop - just give me a good boutique. I like mall scenarios, too, because there's more right there at hand. I think Nashville could use some better shopping! )");
			System.out.println(" ");
			System.out.println(CYAN_BACKGROUND_BRIGHT + "CHOOSING CHOICE SHOPPING....................     " + RESET);
			System.out.println();
			System.out.println(PURPLE_BOLD_BRIGHT
					+ "CHOOSE 1- Food_shop \nCHOOSE 2- Dresses_shop \nCHOOSE 3- Slippers_shop \nCHOOSE 4- Route \nCHOOSE 5- Offering"
					+ RESET);
			Find_shopes = sc.nextInt();

			char food = 0;
			do {
				// foodshop

				switch (Find_shopes) {
				case 1:
					int choice;
					Scanner sc1 = new Scanner(System.in);

					System.out.println(
							"*********************************************************************************************");
					System.out.println(GREEN_BOLD_BRIGHT
							+ "CHOOSE 1  Non_veg_food \nCHOOSE 2  Veg_food \nCHOOSE 3  Ice_Baker\nCHOOSE 4  Sweets_baker"
							+ RESET);
					choice = sc.nextInt();

					switch (choice) {
					case 1:

						System.out.println(" ");
						System.out.println(
								BLACK_BOLD + "CHOOSE 1  Manage Admin \nCHOOSE 2  Ordering / Visiting Users " + RESET);
						int choice3 = sc.nextInt();

						switch (choice3) {
						case 1:
							Scanner ad = new Scanner(System.in);
							System.out.println(" Enter ADMIN Name ");
							String name = ad.next();
							System.out.println("Enter ADMIN Password");
							String pass = ad.next();
							if (name.equals(username) && pass.equals(password)) {
								// System.out.println("Valid ");

								System.out.println("************************************************* ");
								System.out.println(GREEN_BOLD_BRIGHT
										+ "CHOOSE 1   Insert a Record \nCHOOSE 2  update a Record \nCHOOSE 3  Delete a Record\nCHOOSE 4  Read Categories \nCHOOSE 5  Get Some Record  \nCHOOSE 6 Read_CustomerRecord"
										+ RESET);
								int choice4 = sc.nextInt();

								switch (choice4) {
								case 1:
									mm.insertData_Nonveg();
									mm.readRecord_Nonveg();
									break;
								case 2:
									mm.updateData_Nonveg();
									mm.readRecord_Nonveg();
									break;

								case 3:
									mm.deleteRecord_Nonveg();
									mm.readRecord_Nonveg();
									break;

								case 4:
									mm.readRecord_Nonveg();
									break;

								case 5:
									mm.selectData_Nonveg();
									break;
								case 6:
									mm.user_orderData();
									break;

								default:
									System.out.println("Invalid Choice ");
								}

							} else {
								System.out.println("Invalid");

							}
							break;

						case 2:

							System.out.println("********************************************");
							System.out.println(PURPLE_BOLD_BRIGHT + "CHOOSE 1.  FOR Order\nCHOOSE  2.GET Menu" + RESET);
							int choice5 = sc.nextInt();

							switch (choice5) {
							case 1:
								mm.orderData_Nonveg();
								break;
							case 2:
								mm.readRecord_Nonveg();
								break;

							default:
								System.out.println("Invalid Choice");
							}

							break;

						}
						break;

					case 2:

						System.out.println(" ");
						System.out.println(BLACK_BOLD + "CHOOSE 1  Manage Admin \nCHOOSE 2  Ordering/Visting " + RESET);
						int choice4 = sc.nextInt();

						switch (choice4) {
						case 1:
							Scanner ad = new Scanner(System.in);
							System.out.println(" Enter ADMIN Name ");
							String name = ad.next();
							System.out.println("Enter ADMIN Password");
							String pass = ad.next();
							if (name.equals(username) && pass.equals(password)) {
								System.out.println("************************************************* ");
								System.out.println(GREEN_BOLD_BRIGHT
										+ "CHOOSE 1   Insert a Record \nCHOOSE 2  update a Record \nCHOOSE 3  Delete a Record\nCHOOSE 4  Read Categories \nCHOOSE 5  Get Some Record  \nCHOOSE 6 Read_CustomerRecord"
										+ RESET);
								int choice5 = sc.nextInt();

								switch (choice5) {
								case 1:
									mm.insertData_veg();
									mm.readRecord_veg();
									break;
								case 2:
									mm.updateData_veg();
									mm.readRecord_veg();
									break;

								case 3:
									mm.deleteRecord_veg();
									mm.readRecord_veg();
									break;

								case 4:
									mm.readRecord_veg();
									break;

								case 5:
									mm.selectData_veg();
									break;
								case 6:
									mm.user_orderData();
									break;

								default:
									System.out.println("Invalid Choice");
								}
							} else {
								System.out.println("Incorrect");

							}
							break;

						case 2:
							System.out.println("********************************************");
							System.out.println(PURPLE_BOLD_BRIGHT + "CHOOSE 1  FOR Order\nCHOOSE  2  GET Menu" + RESET);
							int choice6 = sc.nextInt();

							switch (choice6) {
							case 1:
								mm.orderData_veg();
								break;
							case 2:
								mm.readRecord_veg();
								break;

							default:
								System.out.println("Invalid Choice");
							}
							break;
						}
						break;
					case 3:

						System.out.println("");
						System.out
								.println(BLACK_BOLD + "CHOOSE 1  Manage Admin \nCHOOSE 2  Ordering/Visting  " + RESET);
						int choice5 = sc.nextInt();
						switch (choice5) {
						case 1:
							Scanner ad = new Scanner(System.in);
							System.out.println(" Enter ADMIN Name ");
							String name = ad.next();
							System.out.println("Enter ADMIN Password");
							String pass = ad.next();
							if (name.equals(username) && pass.equals(password)) {
								System.out.println(
										"*****************************************************************************");
								System.out.println(GREEN_BOLD_BRIGHT
										+ "CHOOSE 1  Insert A Record \nCHOOSE 2  Update A Record \nCHOOSE 3 Delete A Record \n4. Read Categories \n5.Get Some Record\n6. Read_CustomerRecord"
										+ RESET);
								int choice6 = sc.nextInt();

								switch (choice6) {
								case 1:
									mm.insertData_iceBaker();
									mm.readRecord_icebaker();
									break;
								case 2:
									mm.updateData_iceBaker();
									mm.readRecord_icebaker();
									break;

								case 3:
									mm.deleteRecord_iceBaker();
									mm.readRecord_icebaker();
									break;

								case 4:
									mm.readRecord_icebaker();
									break;

								case 5:
									mm.selectData_iceBaker();
									break;
								case 6:
									mm.user_orderData();
									break;

								default:
									System.out.println("Invalid Choice");
								}
							} else {
								System.out.println("Incorrect");

							}
							break;

						case 2:
							System.out.println("***************************************");
							System.out.println(PURPLE_BOLD_BRIGHT + "CHOOSE 1  FOR Order\nCHOOSE  2  GET Menu" + RESET);
							int choice7 = sc.nextInt();

							switch (choice7) {
							case 1:
								mm.orderData_iceBaker();
								break;
							case 2:
								mm.readRecord_icebaker();
								break;

							default:
								System.out.println("Invalid Choice");
							}
							break;
						}
						break;

					case 4:
						System.out.println(" ");
						System.out.println(
								BLACK_BOLD + "CHOOSE 1  Manage Admin \nCHOOSE 2  Ordering/Visting  \n" + RESET);
						int choice6 = sc.nextInt();
						switch (choice6) {
						case 1:
							Scanner ad = new Scanner(System.in);
							System.out.println(" Enter ADMIN Name ");
							String name = ad.next();
							System.out.println("Enter ADMIN Password");
							String pass = ad.next();
							if (name.equals(username) && pass.equals(password)) {
								System.out.println(
										"*********************************************************************************");
								System.out.println(GREEN_BOLD_BRIGHT
										+ "CHOOSE 1  Insert A Record \nCHOOSE 2  Update A Record \nCHOOSE 3  Delete A Record \nCHOOSE 4  Read Categories \nCHOOSE 5 GET Some Record \nCHOOSE 6  Read_CustomerRecord"
										+ RESET);
								int choice7 = sc.nextInt();

								switch (choice7) {
								case 1:
									mm.insertData_sweetsBaker();
									mm.readRecord_sweetsBaker();
									break;
								case 2:
									mm.updateData_sweetsBaker();
									mm.readRecord_sweetsBaker();
									break;

								case 3:
									mm.deleteRecord_sweetsBaker();
									mm.readRecord_sweetsBaker();
									break;

								case 4:
									mm.readRecord_sweetsBaker();
									break;

								case 5:
									mm.selectData_sweetsBaker();
									break;
								case 6:
									mm.user_orderData();
									break;

								default:
									System.out.println("Invalid Choice");
								}
							} else {
								System.out.println("INVALID");

							}
							break;

						case 2:
							System.out.println("******************************************");
							System.out.println(PURPLE_BOLD_BRIGHT + "CHOOSE 1  FOR Order \nCHOOSE 2 GET  Menu" + RESET);
							int choice8 = sc.nextInt();

							switch (choice8) {
							case 1:
								mm.orderData_sweetsBaker();
								break;
							case 2:
								mm.readRecord_sweetsBaker();
								break;

							default:
								System.out.println("Invalid Choice");
							}
							break;

						}

						break;

					}

					break;
				}
				System.out.println();
				System.out.println("WAKE UP  IT' S FOOD O ' CLOCK!! \n do u want anything in food shop(y/n)");
				food = sc.next().charAt(0);
			} while (food == 'y' || food == 'Y');
//dressshop	       
			switch (Find_shopes) {
			case 2:
				int choice;
				Scanner sc1 = new Scanner(System.in);

				System.out.println("******************************************************************");
				System.out
						.println(GREEN_BOLD_BRIGHT + "CHOOSE 1. MAX \nCHOOSE 2. TRENDS \nCHOOSE 3. LifeStyle" + RESET);
				choice = sc.nextInt();

				switch (choice) {
				case 1:

					System.out.println(" ");
					System.out.println(BLACK_BOLD + "CHOOSE 1  Manage Admin \nCHOOSE 2  Ordering  \n" + RESET);
					int choice3 = sc.nextInt();

					switch (choice3) {
					case 1:
						Scanner ad = new Scanner(System.in);
						System.out.println(" Enter ADMIN Name ");
						String name = ad.next();
						System.out.println("Enter ADMIN Password");
						String pass = ad.next();
						if (name.equals(username) && pass.equals(password)) {
							// System.out.println("successfully inserted");

							System.out.println(
									"*********************************************************************************************");
							System.out.println(GREEN_BOLD_BRIGHT
									+ "CHOOSE 1  Insert A Record \nCHOOSE 2  Update A Record \nCHOOSE 3  Delete A Record \nCHOOSE 4  Read Categories \nCHOOSE 5  Get Some Record \nCHOOSE 6  Read_CustomerRecord"
									+ RESET);
							int choice4 = sc.nextInt();
							switch (choice4) {
							case 1:
								mm.insertData_max();
								mm.readRecord_max();
								break;
							case 2:
								mm.updateData_max();
								mm.readRecord_max();
								break;

							case 3:
								mm.deleteRecord_max();
								mm.readRecord_max();
								break;

							case 4:
								mm.readRecord_max();
								break;

							case 5:
								mm.selectData_max();
								break;
							case 6:
								mm.custtable_max();
								break;

							default:
								System.out.println("Invalid Choice");
							}
						} else {
							System.out.println("INVALID");

						}
						break;

					case 2:

						System.out.println("********************************************");
						System.out.println(PURPLE_BOLD_BRIGHT + "CHOOSE 1  FOR Order \nCHOOSE 2  GET Menu" + RESET);
						int choice5 = sc.nextInt();

						switch (choice5) {
						case 1:
							mm.orderData_max();
							break;
						case 2:
							mm.readRecord_max();
							break;

						default:
							System.out.println("Invalid Choice");
						}

						break;

					}
					break;

				case 2:

					System.out.println(" ");
					System.out.println(BLACK_BOLD + "CHOOSE 1  Manage Admin \nCHOOSE  2   ordering \n" + RESET);
					int choice4 = sc.nextInt();

					switch (choice4) {
					case 1:
						Scanner ad = new Scanner(System.in);
						System.out.println(" Enter ADMIN Name ");
						String name = ad.next();
						System.out.println("Enter ADMIN Password");
						String pass = ad.next();
						if (name.equals(username) && pass.equals(password)) {
							System.out.println(
									"***********************************************************************************************");
							System.out.println(GREEN_BOLD_BRIGHT
									+ "CHOOSE 1  Insert A Record \nCHOOSE 2  Update A Record \nCHOOSE 3  Delete A Record \nCHOOSE 4  Read Categories  \nCHOOSE 5  Get Some Record \nCHOOSE 6  Read_CustomerRecord"
									+ RESET);
							int choice5 = sc.nextInt();

							switch (choice5) {
							case 1:
								mm.insertData_Trends();
								mm.readRecord_Trends();
								break;
							case 2:
								mm.updateData_Trends();
								mm.readRecord_Trends();
								break;

							case 3:
								mm.deleteRecord_Trends();
								mm.readRecord_Trends();
								break;

							case 4:
								mm.readRecord_Trends();
								break;

							case 5:
								mm.selectData_Trends();
								break;
							case 6:
								mm.custtable_Trends();
								break;

							default:
								System.out.println("Invalid Choice");
							}
						} else {
							System.out.println("INVALID");

						}
						break;

					case 2:
						System.out.println("**************************************************");
						System.out.println(PURPLE_BOLD_BRIGHT + "CHOOSE 1  FOR Order \nCHOOSE  2  GET Menu" + RESET);
						int choice6 = sc.nextInt();

						switch (choice6) {
						case 1:
							mm.orderData_Trends();
							break;
						case 2:
							mm.readRecord_Trends();
							break;

						default:
							System.out.println("Invalid Choice");
						}
						break;
					}
					break;

				case 3:

					System.out.println(" ");
					System.out.println(BLACK_BOLD + "CHOOSE 1  Manage Admin \nCHOOSE 2  Ordering \n" + RESET);
					int choice5 = sc.nextInt();
					switch (choice5) {
					case 1:
						Scanner ad = new Scanner(System.in);
						System.out.println(" Enter ADMIN Name ");
						String name = ad.next();
						System.out.println("Enter ADMIN Password");
						String pass = ad.next();
						if (name.equals(username) && pass.equals(password)) {
							System.out.println(
									"**************************************************************************************");
							System.out.println(GREEN_BOLD_BRIGHT
									+ "CHOOSE 1  Insert A Record \nCHOOSE 2  Update A Record \nCHOOSE 3  Delete A Record \nCHOOSE 4  Read Categories \nCHOOSE 5  Get Some Read\nCHOOSE 6  Read_CustomerRead"
									+ RESET);
							int choice6 = sc.nextInt();

							switch (choice6) {
							case 1:
								mm.insertData_Lifestyle();
								mm.readRecord_Lifestyle();
								break;
							case 2:
								mm.updateData_Lifestyle();
								mm.readRecord_Lifestyle();
								break;

							case 3:
								mm.deleteRecord_LifeStyle();
								mm.readRecord_Lifestyle();
								break;

							case 4:
								mm.readRecord_Lifestyle();
								break;

							case 5:
								mm.selectData_Lifestyle();
								break;
							case 6:
								mm.custtable_LifeStyle();
								break;

							default:
								System.out.println("Invalid Choice");
							}
						} else {
							System.out.println("INVALID");

						}
						break;

					case 2:
						System.out.println("********************************************");
						System.out.println(PURPLE_BOLD_BRIGHT + "CHOOSE 1  FOR Order \nCHOOSE  2  GET Menu" + RESET);
						int choice7 = sc.nextInt();

						switch (choice7) {
						case 1:
							mm.orderData_Lifestyle();
							break;
						case 2:
							mm.readRecord_Lifestyle();
							break;

						default:
							System.out.println("Invalid Choice");
						}
						break;
					}
					break;

				}

			}

//next switch case for footwear
			switch (Find_shopes) {
			case 3:
				int choice;
				Scanner sc1 = new Scanner(System.in);

				System.out.println("********************************************************");
				System.out.println(GREEN_BOLD_BRIGHT + "CHOOSE 1  METRO \nCHOOSE 2  BATA \nCHOOSE 3  ROCIA" + RESET);
				choice = sc.nextInt();

				switch (choice) {
				case 1:

					System.out.println(" ");
					System.out.println(BLACK_BOLD + "CHOOSE 1  Manage Admin \nCHOOSE 2  Ordering  \n" + RESET);
					int choice3 = sc.nextInt();

					switch (choice3) {
					case 1:
						Scanner ad = new Scanner(System.in);
						System.out.println(" Enter ADMIN Name ");
						String name = ad.next();
						System.out.println("Enter ADMIN Password");
						String pass = ad.next();
						if (name.equals(username) && pass.equals(password)) {
							// System.out.println("successfully inserted");

							System.out.println(
									"****************************************************************************************");
							System.out.println(GREEN_BOLD_BRIGHT
									+ "CHOOSE 1  Insert A Record \nCHOOSE 2  Update A Record \nCHOOSE 3  Delete A Record \nCHOOSE 4  Read Categories \nCHOOSE 5  search \nCHOOSE 6  Read_CustomerRecord"
									+ RESET);
							int choice4 = sc.nextInt();
							switch (choice4) {
							case 1:
								mm.insertData_Metro();
								mm.readRecord_Metro();
								break;
							case 2:
								mm.updateData_Metro();
								mm.readRecord_Metro();
								break;

							case 3:
								mm.deleteRecord_Metro();
								mm.readRecord_Metro();
								break;

							case 4:
								mm.readRecord_Metro();
								break;

							case 5:
								mm.selectData_Metro();
								break;
							case 6:
								mm.custtable_Metro();
								break;

							default:
								System.out.println("Invalid Choice");
							}
						} else {
							System.out.println("INVALID");

						}
						break;

					case 2:

						System.out.println("*********************************************");
						System.out.println(PURPLE_BOLD_BRIGHT + "CHOOSE 1  FOR Order \nCHOOSE 2  GET Menu" + RESET);
						int choice5 = sc.nextInt();

						switch (choice5) {
						case 1:
							mm.orderData_Metro();
							break;
						case 2:
							mm.readRecord_Metro();
							break;

						default:
							System.out.println("Invalid Choice");
						}

						break;

					}
					break;

				//
				case 2:

					System.out.println(" ");
					System.out.println(BLACK_BOLD + "CHOOSE 1  Manage Admin \nCHOOSE 2  Ordering  \n" + RESET);
					int choice4 = sc.nextInt();

					switch (choice4) {
					case 1:
						Scanner ad = new Scanner(System.in);
						System.out.println(" Enter ADMIN Name ");
						String name = ad.next();
						System.out.println("Enter ADMIN Password");
						String pass = ad.next();
						if (name.equals(username) && pass.equals(password)) {
							System.out.println(
									"*****************************************************************************************");
							System.out.println(GREEN_BOLD_BRIGHT
									+ "CHOOSE 1  Insert A Record  \nCHOOSE 2  Update A Record \nCHOOSE 3  Delete A Record \nCHOOSE 4  Read Categories \nCHOOSE 5  Get Some Record \nCHOOSE 6  Read_CustomerRead"
									+ RESET);
							int choice5 = sc.nextInt();

							switch (choice5) {
							case 1:
								mm.insertData_Bata();
								mm.readRecord_Bata();
								break;
							case 2:
								mm.updateData_Bata();
								mm.readRecord_Bata();
								break;

							case 3:
								mm.deleteRecord_Bata();
								mm.readRecord_Bata();
								break;

							case 4:
								mm.readRecord_Bata();
								break;

							case 5:
								mm.selectData_Bata();
								break;
							case 6:
								mm.custtable_Bata();
								break;

							default:
								System.out.println("Invalid Choice");
							}
						} else {
							System.out.println("INVALID");

						}
						break;

					case 2:
						System.out.println("***************************************************");
						System.out.println(PURPLE_BOLD_BRIGHT + "CHOOSE 1 FOR Order \nCHOOSE 2  GET Menu" + RESET);
						int choice6 = sc.nextInt();

						switch (choice6) {
						case 1:
							mm.orderData_Bata();
							break;
						case 2:
							mm.readRecord_Bata();
							break;

						default:
							System.out.println("Invalid Choice");
						}
						break;
					}
					break;

				case 3:

					System.out.println(" ");
					System.out.println(BLACK_BOLD + "CHOOSE 1  Manage Admin \nCHOOSE 2  Ordering/visit \n" + RESET);
					int choice5 = sc.nextInt();
					switch (choice5) {
					case 1:
						Scanner ad = new Scanner(System.in);
						System.out.println(" Enter ADMIN Name ");
						String name = ad.next();
						System.out.println("Enter ADMIN Password");
						String pass = ad.next();
						if (name.equals(username) && pass.equals(password)) {
							System.out.println(
									"*************************************************************************");
							System.out.println(GREEN_BOLD_BRIGHT
									+ "CHOOSE 1  Insert A Record \nCHOOSE 2  Update A Record \nCHOOSE 3  Delete A Record \nCHOOSE 4  Read Categories \nCHOOSE 5  Read Some Record \nCHOOSE 6  Read_CustomerRead"
									+ RESET);
							int choice6 = sc.nextInt();

							switch (choice6) {
							case 1:
								mm.insertData_Rocia();
								mm.readRecord_Rocia();
								break;
							case 2:
								mm.updateData_Rocia();
								mm.readRecord_Rocia();
								break;

							case 3:
								mm.deleteRecord_Rocia();
								mm.readRecord_Rocia();
								break;

							case 4:
								mm.readRecord_Rocia();
								break;

							case 5:
								mm.selectData_Rocia();
								break;
							case 6:
								mm.custtable_Rocia();
								break;

							default:
								System.out.println("Invalid Choice");
							}
						} else {
							System.out.println("INVALID");

						}
						break;

					case 2:
						System.out.println("***********************************************");
						System.out.println(PURPLE_BOLD_BRIGHT + "CHOOSE 1  FOR Order \nCHOOSE  2  GET Menu" + RESET);
						int choice7 = sc.nextInt();

						switch (choice7) {
						case 1:
							mm.orderData_Rocia();
							break;
						case 2:
							mm.readRecord_Rocia();
							break;

						default:
							System.out.println("Invalid Choice");
						}
						break;
					}
					break;

				}
			}

//route
			switch (Find_shopes) {
			case 4:
				int choice;
				Scanner sc1 = new Scanner(System.in);

				System.out.println(
						"****************************************************************************************************");
				System.out.println(GREEN_BOLD_BRIGHT
						+ "  CHOOSE 1 - Move To FirstFloor \n  CHOOSE 2 - Move To SecondFloor \n  CHOOSE 3 - Move To ThirdFloor "
						+ RESET);
				System.out.println(
						"****************************************************************************************************");
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					System.out.println(BLACK_BOLD + "1 FLOOR\n     MAX -- TRENDS -- LIFESTYLE -- 3D GAME " + RESET);
					break;
				case 2:
					System.out.println(BLACK_BOLD
							+ "2 FLOOR\n    NON_VEG FOOD SHOP -- VEG_FOOD SHOP -- ICE_BAKER -- SWEETS_BAKER -- relaxing park "
							+ RESET);
					break;
				case 3:
					System.out.println(
							BLACK_BOLD + "3 FLOOR\n     METRO -- BATA -- ROCIA -- GAMING STATION -- THERTER " + RESET);
					break;

				default:
					System.out.println(PURPLE_BOLD_BRIGHT + "********TIME TO SHOP" + GREEN_BOLD_BRIGHT
							+ " DON'T MISS IT********  " + RESET);

				}

			}

//offer
			switch (Find_shopes) {
			case 5:
				System.out.println(
						BLACK_BOLD + "     MAX -- BUY 1 GET 1 OFFER FROM JANUARY 15 2022 TO JANUARY 25 2022  " + RESET);

				System.out.println(BLACK_BOLD + "     TRENDS -- KURTHI_SET FOR 12% OFFER" + RESET);

				System.out.println(BLACK_BOLD + "     TRENDS -- SHIRT HAVE 20% OFFER FOR EVERY SHIRT" + RESET);

				System.out
						.println(BLACK_BOLD + "     TANDOORI CHICKEN -- RS:300 ONLY FOR FULLY DRILLED PIECES" + RESET);

				System.out.println(BLACK_BOLD + "     ROCIA -- 12% OF EVERY CROCS SLIPPERS" + RESET);

				System.out.println(
						BLACK_BOLD + "     METRO -- 20% FOR BOOTSHOES FROM JANUARY 15 TO JANUARY 26 2022" + RESET);
				System.out.println(" ");
				System.out.println(PURPLE_BOLD_BRIGHT + "********TIME TO SHOP" + GREEN_BOLD_BRIGHT
						+ " DON'T MISS IT**********************" + RESET);
				System.out.println(PURPLE_BOLD_BRIGHT + "********I think it's for you LOOK GO" + GREEN_BOLD_BRIGHT
						+ " FEEL GOOD********" + RESET);
				System.out.println(" ");

				System.out.println(
						GREEN_BOLD_BRIGHT + "  CHOOSE 1 - SEE MORE OFFER \n  CHOOSE 2 - MANAGE ADMIN \n  " + RESET);
				int choice8 = sc.nextInt();

				switch (choice8) {
				case 1:
					mm.readRecord_offer();
					break;
				case 2:
					Scanner ad = new Scanner(System.in);
					System.out.println(" Enter ADMIN Name ");
					String name = ad.next();
					System.out.println("Enter ADMIN Password");
					String pass = ad.next();
					if (name.equals(username) && pass.equals(password)) {
						System.out.println(
								"***********************************************************************************************");
						System.out.println(GREEN_BOLD_BRIGHT
								+ "CHOOSE 1  Insert A Record \nCHOOSE  2 Deleted A Offer \nCHOOSE 3 Read Offer"
								+ RESET);
						int choice5 = sc.nextInt();

						switch (choice5) {
						case 1:
							mm.insert_offer();
							mm.readRecord_offer();
							break;
						case 2:
							mm.delete_offer();
							mm.readRecord_offer();
							break;
						case 3:
							mm.readRecord_offer();

						}

					}
				}
			}
			System.out.println(" ");
			System.out.println("do u want to continue with  shopping(y/n)");
			mall = sc.next().charAt(0);
		} while (mall == 'y' || mall == 'Y');

		System.out.println(PURPLE_BOLD_BRIGHT + "           LOOK GOOD FEEL GOOD     " + RESET);
		System.out.println(GREEN_BOLD_BRIGHT + "  KEEP VISTING ME WAITING FOR YOU" + RESET);
		System.out.println(
				PURPLE_BOLD_BRIGHT + "***********THANK YOU*" + GREEN_BOLD_BRIGHT + "*BYE BYE*************" + RESET);

	}

}
