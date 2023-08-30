#include <iostream>
#include <list>
#include <string>

using namespace std;

int main() {
	list<char> l;
	string s;
	getline(cin, s, '\n');

	for (char c : s) {
		l.push_back(c);
	}
	l.reverse();
	for (char c : l) {
		cout << c;
	}
	
}