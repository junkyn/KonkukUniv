#include <iostream>
#include <queue>
using namespace std;

class Adder {
protected:
	int add(int a, int b) {
		return a + b;
	}
};
class Subtractor {
protected:
	int minus(int a, int b) {
		return a - b;
	}
};
class Calculator : public Adder, public Subtractor {
public:
	int calc(char op, int a, int b);
};
int Calculator::calc(char op, int a, int b) {
	int res = 0;
	switch (op) {
	case '+':
		res = add(a, b);
		break;
	case '-':
		res = minus(a, b);
		break;
	}
	return res;

}
int main() {
	Calculator cal;
	queue<int> n;
	queue<char> op;
	int a;
	char c = 0;
	while (c != '=') {
		if (cin >> a) n.push(a);
		else { cout << "입력 오류" << endl; return 0; }
		if (cin >> c) op.push(c);
		else { cout << "입력 오류" << endl; return 0; }
	}
	int answer=0;
	int ca = op.size();
	for (int i = 0; i < ca-1; i++) {
		char c = op.front();
		int a=0;
		if (answer != 0) {
			a = answer;
		}
		else {
			a = n.front();
			n.pop();
		}
		int b = n.front(); n.pop();
		op.pop();
		answer = cal.calc(c, a, b);
	}
	cout << answer << endl;
}