#include <iostream>

using namespace std;

class JkchoiMenu {
private:
	string menu="";
	int price = 0;
	int income=0;
public:
	JkchoiMenu() {
	}
	JkchoiMenu(string menu, int price) {
		this->menu = menu;
		this->price = price;
	}
	
	void show() {
		cout << "----------------------------\n"
			<< "�� �� �� �� : " << menu << "\n"
			<< "�� �� �� �� : " << price << "\n"
			<< "�� �Ǹűݾ� : " << income << " ��\n"
			<< "----------------------------\n";
	}
	string getName() {
		return menu;
	}
	void sell() {
		income += price;
	}
	int getPrice() {
		return price;
	}
	int getIncome() {
		return income;
	}

};

class JkchoiRestaurant {
private:
	string name="";
	string place="";
	int limit=0;
	int sale=0;
	int income=0;
	JkchoiMenu* list = NULL;
public:
	JkchoiRestaurant() {
	}
	JkchoiRestaurant(string name, string place, int limit) {
		this->name = name;
		this->place = place;
		this->limit = limit;
		list = new JkchoiMenu[limit];
	}
	JkchoiRestaurant(const JkchoiRestaurant& rest) {
		this->name = rest.name;
		this->place = rest.place;
		this->limit = rest.limit;
		this->sale = rest.sale;
		this->income = rest.income;
		this->list = new JkchoiMenu[limit];
		for (int i = 0; i < sale; i++) {
			list[i] = rest.list[i];
		}
	}
	void show() {
		cout << "�Ĵ� �̸� : " << name << endl;
		cout << "�Ĵ� ���� : " << place << endl;
		cout << "��ϰ��ɸ޴� : " << limit <<"��"<< endl;
		cout << "�����ǸŸ޴� : " << sale <<"��"<< endl;
		cout << "�� �Ǹűݾ� : " << income << endl;
		cout << "***** �޴��� �Ǹ���Ȳ (��� �޴� ����) *****" << endl;
		if (sale == 0) cout << "��ϵ� �޴��� �����ϴ�." << endl;
		for (int i = 0; i < sale; i++) {
			list[i].show();
		}
	}
	void addNewMenu(JkchoiMenu* menu) {
		for (int i = 0; i < sale; i++) {
			if (list[i].getName().compare(menu->getName()) == 0) {
				cout << "������ ��ϵ� �޴��Դϴ�." << endl;
				return;
			}
		}
		list[sale] = *menu;
		this->income += list[sale++].getPrice();
		cout << "�޴� �߰��� �Ϸ�Ǿ����ϴ�." << endl;

		return;
	}
	void order(string menu) {
		for (int i = 0; i < sale; i++) {
			if (list[i].getName().compare(menu) == 0) {
				list[i].sell();
				cout << "�ֹ��� �Ϸ�Ǿ����ϴ�." << endl;
				return;
			}
		}
		cout << "�޴��� �ٽ� �ѹ� Ȯ���� �ּ���." << endl;
		return;
	}
	void showOrderBySales() {
		int temp;
		int* olist = new int[sale];
		for (int i = 0; i < sale; i++) {
			olist[i] = i;
		}
		for (int i = 0; i < sale-1; i++) {
			for (int j = i + 1; j < sale; j++) {
				if (list[olist[i]].getIncome() < list[olist[j]].getIncome()) {
					temp = i;
					olist[i] = j;
					olist[j] = temp;
				}
			}
		}
		cout << "***** �޴��� �Ǹ���Ȳ (�Ǹűݾ� ����) *****" << endl;
		if (sale == 0) cout << "��ϵ� �޴��� �����ϴ�." << endl;
		for (int i = 0; i < sale; i++) {
			list[olist[i]].show();
		}
	}
};

int main() {
	cout << "202211389 ���ر�\n";
	JkchoiMenu menu;
	JkchoiMenu menu1("�丶���Ľ�Ÿ", 100);

	menu1.show();

	JkchoiRestaurant konkuk("�Ǳ��������", "�Ǳ�������", 5);
	konkuk.show();

	konkuk.addNewMenu(new JkchoiMenu("�丶���Ľ�Ÿ", 100));
	konkuk.addNewMenu(new JkchoiMenu("����Ľ�Ÿ", 200));
	konkuk.addNewMenu(new JkchoiMenu("�����Ľ�Ÿ", 200));
	konkuk.addNewMenu(new JkchoiMenu("�����Ľ�Ÿ", 300));

	konkuk.order("�丶���Ľ�Ÿ");
	konkuk.order("�丶���Ľ�Ÿ");
	konkuk.order("�����Ľ�Ÿ");
	konkuk.order("�丶���Ľ�Ÿ");
	konkuk.order("���");

	konkuk.show();
	
	konkuk.showOrderBySales();

	JkchoiRestaurant konkuk2(konkuk);
	konkuk2.addNewMenu(new JkchoiMenu("�Ǳ�����", 200));
	konkuk2.show();
}