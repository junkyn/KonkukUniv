#include <iostream>
#include <algorithm>
#include <string>
#include <queue>
#include <fstream>
using namespace std;
class Music {
private:
	string name;
	int index;
public:
	Music(string s, int n){
		this->name = s;
		this->index = n;
	}
	friend bool operator < (const Music& m1, const Music& m2) {
		return m1.name.size() < m2.name.size();
	}
	friend bool operator > (const Music& m1, const Music& m2) {
		return m1.name.size() > m2.name.size();
	}
	friend ostream& operator << (ostream& out, Music& m) {
		out << "°î ÀÌ¸§ : " << m.name << endl;
		out << "°î ¹øÈ£ : " << m.index << endl;
		return out;
	}
	string getName() {
		return name;
	}
	int getIndex() {
		return index;
	}
};


priority_queue<Music> m;
int main() {
	ifstream is("test.txt");
	if (is.is_open()) {
		while (!is.eof()) {
			string s;
			int n;
			getline(is, s,'\t');
			is >> n;
			is.ignore();
			m.push(Music(s, n));
		}
	}
	Music m1("Butterfly",1);
	m.push(m1);
	ofstream os("test.txt", ios::app);
	if (os.is_open()) {
		os << endl;
		os << m1.getName() << "\t" << m1.getIndex();
	}
	is.close();
	os.close();
	int size = m.size();
	for (int i = 0; i < size; i++) {
		Music tp = m.top();
		cout << tp;
		m.pop();
	}


}