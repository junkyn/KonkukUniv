#include <iostream>
#include <string>
using namespace std;

class Animal {
protected:
	string name;
public:
	Animal(string n) : name(n){}
	virtual void sound() = 0;
};
class Dog : public Animal {
public:
	Dog(string n) :Animal(n) {}
	void sound() {
		cout << name<<"�� �۸�"<<endl;
	}
};
class Cat : public Animal {
public:
	Cat(string n) :Animal(n) {}
	void sound() {
		cout << name << "�� �߿˾߿�" << endl;
	}
};

int main() {
	Dog dog("�����");
	Cat cat("����");
	dog.sound();
	cat.sound();
}