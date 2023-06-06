#include <iostream>
#include <vector>
using namespace std;

int main() {
	vector<int> v;
	int c;
	while(1) {
		cout << "정수를 입력하세요!(0을 입력하면 종료): ";
		cin >> c;
		if (c == 0)break;
		v.push_back(c);
		cout << "입력된 수: ";
		int sum = 0;
		for (int i : v) {
			cout << i<<" ";
			sum += i;
		}cout << endl;
		cout << "평균 = " << (double) sum/ v.size() << endl;;
	}
}
