#include <iostream>
#include <vector>
using namespace std;

int main() {
	vector<int> v;
	int c;
	while(1) {
		cout << "������ �Է��ϼ���!(0�� �Է��ϸ� ����): ";
		cin >> c;
		if (c == 0)break;
		v.push_back(c);
		cout << "�Էµ� ��: ";
		int sum = 0;
		for (int i : v) {
			cout << i<<" ";
			sum += i;
		}cout << endl;
		cout << "��� = " << (double) sum/ v.size() << endl;;
	}
}
