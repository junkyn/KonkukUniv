#include <iostream>
#include <string>
using namespace std;

class Buy {
	static int income; // Ŭ���� ���� ���� ����
private:

public:

	Buy(string s) { // ������ 
		if (s.compare("���")==0) { // �Ű������� �����
			addincome(500); // ���Կ� 500 �߰�
		}
		else if (s.compare("����")==0) { // �Ű������� ������
			addincome(700); // ���Կ� 700 �߰�
		}
		else if (s.compare("��")==0) { // �Ű������� ���
			addincome(600); // ���Կ� 600 �߰�
		}
		else if (s.compare("������")==0) { // �Ű������� ��������
			addincome(650); // ���Կ� 650 �߰�
		}
		else { // �Ű������� ���,����,��,�������� �ƴ϶��
			cout << "�׷� ��ǰ�� �����!" << endl; // ���ٰ� ���
		}
	}

	static void addincome(int price) { // ���Կ� �Ű�������ŭ ������
		income += price;
	}
	static void showIncome() {
		cout <<"�� ���� : " << income << "��" << endl;
	}
};
int Buy::income = 0;
int main() {
	string input;
	while (1) {
		cout << "���ϰ��Կ��� ���(500��), ����(700��), ��(600��), ������(650��) �־��" << endl;
		cout << "��� ���� ������ �Է����ּ���(������ q�� �Է������) : ";
		cin >> input;
		if (input.compare("q")==0)
			return 0;
		else {
			Buy b(input);
		}
		Buy::showIncome();
	}
}