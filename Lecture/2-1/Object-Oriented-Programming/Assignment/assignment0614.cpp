#include <iostream>
#include <cmath>
#define PI 3.14
using namespace std;

class Shape {
protected:
	double area;
public:
	virtual void calcArea() = 0;
};
class TwoDShape : public Shape {
private:
public:	
	virtual void calcArea() = 0;
};
class Triangle : public TwoDShape {
private:
	double side1;
	double side2;
	double side3;
public:
	Triangle(double a, double b, double c) {
		this->side1 = a;
		this->side2 = b;
		this->side3 = c;
	}
	virtual void calcArea() {
		double s = (side1 + side2 + side3) / 2;
		area = sqrt(s * (s - side1) * (s - side2) * (s - side3));
		cout << "The area of the Triangle is : " << area << endl;

	}
};
class Circle : public TwoDShape {
private: double radius;
public:
	Circle(double r) {
		this->radius = r;
	}
	virtual void calcArea() {
		area = PI * radius * radius;
		cout << "The area of the Circle is : " << area<<endl;
	}
};
class Rectangle : public TwoDShape {
private: 
	double width;
	double length;
public:
	Rectangle(double w, double l) {
		this->width = w;
		this->length = l;
	}
	virtual void calcArea() {
		area = width * length;
		cout << "The area of the Rectangle is : " << area << endl;

	}
};
int main() {
	int s;
	cout << "Select the shape you would like to enter: " << endl;
	cout << "1 - Triangle" << endl;
	cout << "2 - Rectangle" << endl;
	cout << "3 - Circle" << endl;
	cout << "4 - Exit" << endl;
	cout << "Enter selection: ";
	cin >> s;
	Shape* shape;
	switch (s) {
	case 1:
		double a, b, c;
		cout << "Enter the Side1 : ";
		cin >> a;
		cout << "Enter the Side2 : ";
		cin >> b;
		cout << "Enter the Side3 : ";
		cin >> c;
		shape = new Triangle(a, b, c);
		break;

	case 2:
		double l, w;
		cout << "Enter the length : ";
		cin >> l;
		cout << "Enter the width : ";
		cin >> w;
		shape = new Rectangle(w, l);
		break;
	case 3:
		double r;
		cout << "Enter the radius : ";
		cin >> r;
		shape = new Circle(r);
		break;
	case 4:
		return 0;
	default:
		cout << "Error" << endl;
		return 0;
	}
	shape->calcArea();


}