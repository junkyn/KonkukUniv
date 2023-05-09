#include <iostream>
using namespace std;

class Shape {
private:
	int x=0;
	int y = 0;
public:
	Shape() {
	}
	Shape(int x, int y) {
		this->x = x;
		this->y = y;
	}
	virtual float GetArea() {
		return 0;
	}
	void setX(int x) {
		this->x = x;
	}
	void setY(int y) {
		this->y = y;
	}
	int getX() {
		return x;
	}
	int getY() {
		return y;
	}
};
class Oval : public Shape {
public:
	Oval(int x, int y) {
		setX(x);
		setY(y);
	}
	float GetArea() {
		return getX() * getY() * 3.14f;
	}
};
class Rect : public Shape {
public:
	Rect(int x, int y) {
		setX(x);
		setY(y);
	}
	float GetArea() {
		return getX() * getY();
	}
};
class Tri : public Shape {
public:
	Tri(int x, int y) {
		setX(x);
		setY(y);
	}
	float GetArea() {
		return getX() * getY() * 1 / 2;
	}
};
int main() {
	Oval oval(10, 20);
	Rect rect(30, 40);
	Tri tri(20, 30);

	cout << "ÆÄÀü³ĞÀÌ´Â " << oval.GetArea() << endl;
	cout << "ºÎ¸®¶Ç³ĞÀÌ´Â " << rect.GetArea() << endl;
	cout << "»ï°¢±è¹ä³ĞÀÌ´Â " << tri.GetArea() << endl;

}