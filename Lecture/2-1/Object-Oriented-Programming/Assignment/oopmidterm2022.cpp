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
			<< "메 뉴 이 름 : " << menu << "\n"
			<< "판 매 가 격 : " << price << "\n"
			<< "총 판매금액 : " << income << " 원\n"
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
		cout << "식당 이름 : " << name << endl;
		cout << "식당 지점 : " << place << endl;
		cout << "등록가능메뉴 : " << limit <<"개"<< endl;
		cout << "현재판매메뉴 : " << sale <<"개"<< endl;
		cout << "총 판매금액 : " << income << endl;
		cout << "***** 메뉴별 판매현황 (등록 메뉴 순서) *****" << endl;
		if (sale == 0) cout << "등록된 메뉴가 없습니다." << endl;
		for (int i = 0; i < sale; i++) {
			list[i].show();
		}
	}
	void addNewMenu(JkchoiMenu* menu) {
		for (int i = 0; i < sale; i++) {
			if (list[i].getName().compare(menu->getName()) == 0) {
				cout << "기존에 등록된 메뉴입니다." << endl;
				return;
			}
		}
		list[sale] = *menu;
		this->income += list[sale++].getPrice();
		cout << "메뉴 추가가 완료되었습니다." << endl;

		return;
	}
	void order(string menu) {
		for (int i = 0; i < sale; i++) {
			if (list[i].getName().compare(menu) == 0) {
				list[i].sell();
				cout << "주문이 완료되었습니다." << endl;
				return;
			}
		}
		cout << "메뉴를 다시 한번 확인해 주세요." << endl;
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
		cout << "***** 메뉴별 판매현황 (판매금액 정렬) *****" << endl;
		if (sale == 0) cout << "등록된 메뉴가 없습니다." << endl;
		for (int i = 0; i < sale; i++) {
			list[olist[i]].show();
		}
	}
};

int main() {
	cout << "202211389 최준규\n";
	JkchoiMenu menu;
	JkchoiMenu menu1("토마토파스타", 100);

	menu1.show();

	JkchoiRestaurant konkuk("건국레스토랑", "건국대지점", 5);
	konkuk.show();

	konkuk.addNewMenu(new JkchoiMenu("토마토파스타", 100));
	konkuk.addNewMenu(new JkchoiMenu("명란파스타", 200));
	konkuk.addNewMenu(new JkchoiMenu("봉골레파스타", 200));
	konkuk.addNewMenu(new JkchoiMenu("봉골레파스타", 300));

	konkuk.order("토마토파스타");
	konkuk.order("토마토파스타");
	konkuk.order("봉골레파스타");
	konkuk.order("토마토파스타");
	konkuk.order("명란");

	konkuk.show();
	
	konkuk.showOrderBySales();

	JkchoiRestaurant konkuk2(konkuk);
	konkuk2.addNewMenu(new JkchoiMenu("건국피자", 200));
	konkuk2.show();
}