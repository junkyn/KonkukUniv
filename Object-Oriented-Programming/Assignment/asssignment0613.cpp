#include <iostream>
#include <vector>
using namespace std;

template <class T>
vector<T>& operator+=(vector<T>& v, T a) {
	v.push_back(a);
	return v;
}
template <class T>
T& operator-=(vector<T>& v, T& s) {
	s = v.back();
	v.pop_back();
	return s;
}
template <class T>
ostream& operator<<(ostream& out, vector<T> v) {
	if (v.empty()) out << "empty";
	else {
		for (int i = 0; i < v.size()-1; i++) {
			out << v[i] << ",";
		}
		out << v[v.size() - 1];
	}
	out << endl;
	return out;
}

int main() {
	vector<int> vcont;
	int s1, s2, s3;

	vcont += 1;
	(vcont += 2) += 3;
	cout << vcont;

	vcont -= s1;
	cout << vcont;
	vcont -= s2;
	cout << vcont;
	vcont -= s3;
	cout << s1 << "," << s2 <<","<< s3<<endl;
	cout << vcont;
}