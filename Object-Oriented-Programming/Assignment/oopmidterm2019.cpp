#include <iostream>
#include <fstream>
#include <string>
#include <vector>
using namespace std;

class Item {
private:
	string code = "noinfo";
	string name = "noinfo";
	int num = 0;
	int sum = 0;
	int price = 0;
	string order = "";
public:
	Item() {

	}
	Item(string code, string name, int n, int p) {
		this->code = code;
		this->name = name;
		num = n;
		price = p;

	}
	void print() {
		cout << code << "\t" << name << "\t" << num << "\t" << price << endl;
	}
	void print(bool t) {
		if (t) {
			cout << code << "\t" << name << "\t" << num << "\t" << price << "\t" << sum<<"\t"<<order << endl;
		}
	}
	string getName() {
		return name;
	}
	string getCode() {
		return code;
	}
	void sell(string store) {
		num--;
		sum += price;
		order += store + " ";
	}

};

class JkchoiMart {
private:
	ifstream readFile;
	string password = "0000";
	Item* ilist;
	int size=0;
public:
	JkchoiMart(string s) {
		readFile.open(s);
		if (readFile.is_open()) {
			readFile >> size;
			cout << size;
			ilist = new Item[size];
			string str[4];
			for (int i = 0; i < size; i++) {

				for (int k = 0; k < 4; k++) {
					readFile >> str[k];
					
					
				}
				ilist[i] = Item(str[0], str[1], stoi(str[2]), stoi(str[3]));
			}
		}
		else
			cout << "파일 열기 실패";
	}
	JkchoiMart(string s, string pass) {
		readFile.open(s);
		password = pass;
		if (readFile.is_open()) {
			readFile >> size;
			ilist = new Item[size];
			
			for (int i = 0; i < size; i++) {
				string str[4];
				for (int k = 0; k < 4; k++) {
					readFile >> str[k];
					
				}
				ilist[i] = Item(str[0], str[1], stoi(str[2]), stoi(str[3]));
			}


		}
		else
			cout << "파일 열기 실패";
	}
	JkchoiMart(const JkchoiMart& mart) {
		this->password = mart.password;
		this->size = mart.size;
		this->ilist = new Item[size];
		for (int i = 0; i < size; i++) {
			this->ilist[i] = mart.ilist[i];
		}
	}

	~JkchoiMart(){
		delete[] ilist;
		if (readFile.is_open())
			readFile.close();
	}
	void showItems() {
		cout << "***** JkchoiMart 제품 현황 *****\n\n" <<
			"*******************************\n" <<
			"코드\t제품명\t재고\t가격\n" <<
			"*******************************\n";
		for (int i = 0; i < size; i++) {
			ilist[i].print();
		}
	}
	void showItems(string pass) {
		if (pass.compare(password) == 0) {
			cout << "***** JkchoiMart 제품 현황 *****\n\n" <<
				"******************************************************\n" <<
				"코드\t제품명\t재고\t가격\t누적금액\t주문자\n" <<
				"******************************************************\n";
			for (int i = 0; i < size; i++) {
				ilist[i].print(true);
			}
		}
		else {
			cout << "***** JkchoiMart 제품 현황 *****\n\n" <<
				"*******************************\n" <<
				"코드\t제품명\t재고\t가격\n" <<
				"*******************************\n";
			for (int i = 0; i < size; i++) {
				ilist[i].print();
			}
		}
	}
	string findItems(string s) {
		for (int i = 0; i < size; i++) {
			if (ilist[i].getName().compare(s) == 0) {
				return ilist[i].getCode();
			}
		}
		return "-1";
	}
	bool sellItem(string code, string store) {
		for (int i = 0; i < size; i++) {
			if (ilist[i].getCode().compare(code) == 0) {
				ilist[i].sell(store);
				return true;
			}
		}
		return false;
	}

};

JkchoiMart makeMart(string filename, string pass) {
	JkchoiMart jk(filename, pass);
	return jk;
}

int main() {
	cout << "202211389 최준규" << endl;
	Item item1;
	Item item2("A0001", "새우깡", 5, 100);
	item1.print();
	item1.print(true);
	item2.print();
	item2.print(true);
	JkchoiMart jkchoi1("product.txt");
	JkchoiMart jkchoi2("product.txt", "greenjoa");
	jkchoi1.showItems();

	string itemCode = jkchoi1.findItems("새우깡2");
	if (itemCode.compare("-1") != 0) {
		cout << "찾는 제품의 코드는 " << itemCode << "입니다." << endl;
	}
	else {
		cout << "판매하지 않는 제품입니다." << endl;
	}
	itemCode = jkchoi1.findItems("새우깡");
	if (itemCode.compare("-1") != 0) {
		cout << "찾는 제품의 코드는 " << itemCode << "입니다." << endl;
	}
	else {
		cout << "판매하지 않는 제품입니다." << endl;
	}
	if (jkchoi1.sellItem(itemCode, "건대점")) {
		cout << "납품이 완료되었습니다." << endl << endl;
	}
	else {
		cout << "납품이 완료되지 않았습니다." << endl << endl;
	}
	jkchoi1.showItems();

	jkchoi1.showItems("0000");

	JkchoiMart greenjoa(jkchoi1);
	greenjoa.showItems();

	JkchoiMart jkchoi = makeMart("product.txt", "1234");
	jkchoi.showItems();
}