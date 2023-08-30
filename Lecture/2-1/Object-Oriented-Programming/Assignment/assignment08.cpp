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
		cout << name<<"啊 港港"<<endl;
	}
};
class Cat : public Animal {
public:
	Cat(string n) :Animal(n) {}
	void sound() {
		cout << name << "啊 具克具克" << endl;
	}
};

int main() {
	Dog dog("大大捞");
	Cat cat("成捞");
	dog.sound();
	cat.sound();
}