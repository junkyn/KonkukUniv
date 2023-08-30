#include <iostream>
#define PI 3.14
using namespace std;

class Point {
private: int x, y;
public:
	Point(int x, int y) {
		this->x = x;
		this->y = y;
	}
	void Move(int x, int y) {
		this->x += x;
		this->y += y;
	}
	void Draw() {
		cout << "���� ��ǥ: (" << x << ", " << y << ")" << endl;
	}
};
class Circle : public Point {
private: int x, y; double radius;
public: 
	Circle(int x, int y, int r) : Point(x,y){
		this->x = x;
		this->y = y;
		this->radius = r;
	}
	void Move(int x, int y) {
		this->x += x;
		this->y += y;
	}
	void Draw() {
		cout << "���� ��ǥ: (" << x << ", " << y << ")" << endl;
		cout << "���� ������: " << radius << endl;
	}
	double GetArea() {
		return radius * radius * PI;
	}
};

int main() {

	Point p(1, 1);
	p.Draw();
	p.Move(2, 2);
	p.Draw();
	cout << "\n";

	Circle c(1, 1, 3);
	c.Draw();
	c.Move(3, 3);
	c.Draw();
	cout << "���� ���̴�: " << c.GetArea() << endl;

	return 0;
}