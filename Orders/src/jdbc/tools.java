package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class tools {
	Connection conn;
	
	public void initConnection() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		//System.out.println("1succeed");
	} catch (Exception e) {
		e.printStackTrace();
	}
	try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data?serverTimezone=UTC&useSSL=false","root","chenzhen2003");
		//System.out.println("2succeed");
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	public void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
				conn=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void showAllOrders() {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from ordersdata");
			while (rs.next()) { 
				System.out.print("orders id: " + rs.getInt("ordersid")+"  "); 
				System.out.print("goods id: " + rs.getInt("goodsid")+"  ");
				System.out.print("amount: " + rs.getInt("amount")+"  ");
				System.out.print("time: " + rs.getString("time")+"  ");
				System.out.println("pay: " + rs.getDouble("pay"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void searchOrders(int beginning,int end) {
		try {
			String sql = "select * from ordersdata where ordersid >=" + beginning +" and ordersid <= "+end;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) { 
				System.out.print("orders id: " + rs.getInt("ordersid")+"  "); 
				System.out.print("goods id: " + rs.getInt("goodsid")+"  ");
				System.out.print("amount: " + rs.getInt("amount")+"  ");
				System.out.print("time: " + rs.getString("time")+"  ");
				System.out.println("pay: " + rs.getDouble("pay"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void searchOrders(int goodsid) {
		try {
			String sql = "select * from ordersdata where goodsid= "+goodsid;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) { 
				System.out.print("orders id: " + rs.getInt("ordersid")+"  "); 
				System.out.print("goods id: " + rs.getInt("goodsid")+"  ");
				System.out.print("amount: " + rs.getInt("amount")+"  ");
				System.out.print("time: " + rs.getString("time")+"  ");
				System.out.println("pay: " + rs.getDouble("pay"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void add( int goodsid, int amount) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from goodsdata where id= "+goodsid);
			double pay=0;
			while (rs.next()) { 
		    pay=amount*rs.getDouble("price");}
			String sql = "insert into ordersdata (goodsid, amount,pay) values(?,?,?) ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, goodsid); 
			ps.setInt(2, amount); 
			ps.setDouble(3, pay);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int ordersid) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("delete from ordersdata where ordersid = " + ordersid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(int ordersid,int goodsid, int amount) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from goodsdata where id= "+goodsid);
			double pay=0;
			while (rs.next()) { 
		    pay=amount*rs.getDouble("price");}
			String sql = "update ordersdata set goodsid = ? , amount= ? ,pay="+ pay +" where ordersid = ? ";
		    PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, goodsid); 
			ps.setInt(2, amount); 
			ps.setInt(3, ordersid); 
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public void searchGoods(int beginning,int end) {
		try {
			String sql = "SELECT o.ordersid, o.goodsid, g.name,g.price FROM ordersdata o INNER JOIN goodsdata g ON o.goodsid = g.id WHERE ordersid >="+beginning+ " and ordersid <= "+end;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) { 
				System.out.print("orders id: " + rs.getInt("ordersid")+"  "); 
				System.out.print("goods id: " + rs.getInt("goodsid")+"  ");
				System.out.print("name: " + rs.getString("name")+"  ");
				System.out.println("price: " + rs.getDouble("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showAllGoods() {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from goodsdata");
			while (rs.next()) { 
				System.out.print("goods id: " + rs.getInt("id")+"  ");
				System.out.print("name: " + rs.getString("name")+"  ");
				System.out.println("price: " + rs.getDouble("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void paging(int page) {
		try {
			int offset=2 * (page - 1);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ordersid, goodsid, amount, time, pay\r\n"
					+ "FROM ordersdata\r\n"
					+ "LIMIT 2 OFFSET "+ offset+";");
			while (rs.next()) { 
				System.out.print("orders id: " + rs.getInt("ordersid")+"  "); 
				System.out.print("goods id: " + rs.getInt("goodsid")+"  ");
				System.out.print("amount: " + rs.getInt("amount")+"  ");
				System.out.print("time: " + rs.getString("time")+"  ");
				System.out.println("pay: " + rs.getDouble("pay"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}


