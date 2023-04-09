#include <iostream>
using namespace std;

// ������ ����ó���� ���� �ʾҽ��ϴ�. 

class Account { //����ó Ŭ����
private:
	string name=""; // �߰��� ����ó�� �̸�
	int number=0; // �߰��� ����ó�� ��ȣ
public:
	Account() {
		this->name = " ";
		this->number = 0;
	}
	Account(string name, int number) { // �̸��� ��ȣ�� ����ó ��ü ����
		this->name = name;
		this->number = number;
		cout << "�ּҷ��� ���ŵǾ����ϴ�." << endl;
	}
	void display() { // ����ó ��� �Լ�
		cout << name << " : " << number<<endl;
	}
};
class Accountbook {	// �ּҷ� Ŭ����
private:
	string name = ""; // ������� �̸�
	Account a[10]; // ����ó���� ���� �迭
	int idx = 0; // ����ó�� ������ �� ����
public:
	Accountbook() {

	}
	Accountbook(string name) {  // ������� �̸��� �Է¹޾� ����
		this->name = name;
	}
	void display() {  // �ּҷ� ��� �Լ�
		cout << this->name << "���� �ּҷ�" << endl;
		for (int i = 0; i < idx; i++) { // ����ó �迭�� �ִ� ����ó���� ���� ����Ѵ�.
			cout << i+1<<") "; a[i].display();
		}
	}
	void add(string name, int number) { // ����ó �߰� �Լ�
		if (idx == 10) {
			cout << "�迭 �ִ� ũ�� ����" << endl;
			return;
		}
		a[idx++] = Account(name, number); // ����ó �迭�� ����ڰ� �Է��� �̸�, ��ȭ��ȣ�� ����ó ���� �� �迭�� �߰�
		cout << "���� ����ó ������ " << idx << "�� �Դϴ�. (�ִ� 10��)" << endl;
	}
};


int main() {
	string name;
	cout << "�̸��� �Է��ϼ���" << endl;
	cin >> name;
	Accountbook ac = Accountbook(name);
	int n;

	while (true) { // ������ CLI ����
		cout << endl;
		cout << "��ɾ� ���" << endl;
		cout << "1 : �ּҷ� ��ü ���" << endl;
		cout << "2 �̸� ��ȭ��ȣ(������� ���ڸ��Է�) : ����ó �߰�" << endl;
		cout << "3 : ���α׷� ����" << endl;
		cin >> n;
		cout << endl;
		switch (n) {
		case 1: ac.display(); break;
		case 2: cin >> name >> n; ac.add(name, n); break;
		case 3: return 0;
		}
	}
}