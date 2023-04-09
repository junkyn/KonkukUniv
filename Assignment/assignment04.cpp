#include <iostream>
using namespace std;

// 복잡한 예외처리는 하지 않았습니다. 

class Account { //연락처 클래스
private:
	string name=""; // 추가할 연락처의 이름
	int number=0; // 추가할 연락처의 번호
public:
	Account() {
		this->name = " ";
		this->number = 0;
	}
	Account(string name, int number) { // 이름과 번호로 연락처 객체 생성
		this->name = name;
		this->number = number;
		cout << "주소록이 갱신되었습니다." << endl;
	}
	void display() { // 연락처 출력 함수
		cout << name << " : " << number<<endl;
	}
};
class Accountbook {	// 주소록 클래스
private:
	string name = ""; // 사용자의 이름
	Account a[10]; // 연락처들을 담을 배열
	int idx = 0; // 연락처의 개수를 셀 변수
public:
	Accountbook() {

	}
	Accountbook(string name) {  // 사용자의 이름을 입력받아 생성
		this->name = name;
	}
	void display() {  // 주소록 출력 함수
		cout << this->name << "님의 주소록" << endl;
		for (int i = 0; i < idx; i++) { // 연락처 배열에 있는 연락처들을 각각 출력한다.
			cout << i+1<<") "; a[i].display();
		}
	}
	void add(string name, int number) { // 연락처 추가 함수
		if (idx == 10) {
			cout << "배열 최대 크기 도달" << endl;
			return;
		}
		a[idx++] = Account(name, number); // 연락처 배열에 사용자가 입력한 이름, 전화번호로 연락처 생성 후 배열에 추가
		cout << "현재 연락처 개수는 " << idx << "개 입니다. (최대 10개)" << endl;
	}
};


int main() {
	string name;
	cout << "이름을 입력하세요" << endl;
	cin >> name;
	Accountbook ac = Accountbook(name);
	int n;

	while (true) { // 간단한 CLI 구축
		cout << endl;
		cout << "명령어 목록" << endl;
		cout << "1 : 주소록 전체 출력" << endl;
		cout << "2 이름 전화번호(공백없이 숫자만입력) : 연락처 추가" << endl;
		cout << "3 : 프로그램 종료" << endl;
		cin >> n;
		cout << endl;
		switch (n) {
		case 1: ac.display(); break;
		case 2: cin >> name >> n; ac.add(name, n); break;
		case 3: return 0;
		}
	}
}