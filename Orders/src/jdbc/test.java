package jdbc;

public class test {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
tools t=new tools();
t.initConnection();

//展示数据库已有内容
t.showAllOrders();
t.showAllGoods();

//增加
System.out.println("--------------------增加----------------------");
t.add(2, 7);
t.add(3, 2);
t.showAllOrders();

//更新
System.out.println("--------------------更新----------------------");
t.update(1, 1, 6);
t.showAllOrders();

//删除
System.out.println("--------------------删除----------------------");
t.delete(4);
t.showAllOrders();

//查询
System.out.println("--------------------查询----------------------");
t.searchOrders(2, 3);//根据订单id范围查找订单
t.searchOrders(2);//根据商品id查找订单
t.searchGoods(2, 3);//根据订单id范围查找该订单商品详细信息

//分页查询，每页两条信息
System.out.println("--------------------分页----------------------");
t.paging(1);
t.paging(2);

t.closeConnection();
	}

}
//感觉还要写个增加商品啥的
//刚学懂一点点，三大范式，事务管理还不会，，，，悲
