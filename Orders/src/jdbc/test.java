package jdbc;

public class test {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
tools t=new tools();
t.initConnection();

//չʾ���ݿ���������
t.showAllOrders();
t.showAllGoods();

//����
System.out.println("--------------------����----------------------");
t.add(2, 7);
t.add(3, 2);
t.showAllOrders();

//����
System.out.println("--------------------����----------------------");
t.update(1, 1, 6);
t.showAllOrders();

//ɾ��
System.out.println("--------------------ɾ��----------------------");
t.delete(4);
t.showAllOrders();

//��ѯ
System.out.println("--------------------��ѯ----------------------");
t.searchOrders(2, 3);//���ݶ���id��Χ���Ҷ���
t.searchOrders(2);//������Ʒid���Ҷ���
t.searchGoods(2, 3);//���ݶ���id��Χ���Ҹö�����Ʒ��ϸ��Ϣ

//��ҳ��ѯ��ÿҳ������Ϣ
System.out.println("--------------------��ҳ----------------------");
t.paging(1);
t.paging(2);

t.closeConnection();
	}

}
//�о���Ҫд��������Ʒɶ��
//��ѧ��һ��㣬����ʽ������������ᣬ��������
